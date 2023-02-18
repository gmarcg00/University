#include<linux/module.h>/*Needed by all modules*/
#include<linux/kernel.h>/*Needed for KERN_INFO*/
#include<linux/init.h>	/*Needed for the macros*/
#include<linux/fs.h>	/*libfs stuff*/

#include<linux/buffer_head.h>/*buffer_head*/
#include<linux/slab.h>/*kmem_cache*/
#include "assoofs.h"

int assoofs_fill_super(struct super_block *sb, void *data, int silent);
struct assoofs_inode_info *assoofs_get_inode_info(struct super_block *sb, uint64_t inode_no);
static struct inode *assoofs_get_inode(struct super_block *sb, int ino);
int assoofs_sb_get_a_freeblock(struct super_block *sb, uint64_t *block);
void assoofs_save_sb_info(struct super_block *vsb);
void assoofs_add_inode_info(struct super_block *sb, struct assoofs_inode_info *inode);
int assoofs_save_inode_info(struct super_block *sb, struct assoofs_inode_info *inode_info);
struct assoofs_inode_info *assoofs_search_inode_info(struct super_block *sb, struct assoofs_inode_info *start, struct assoofs_inode_info *search);


/*
* Operaciones sobre ficheros
*/
ssize_t assoofs_read(struct file * filp, char __user * buf, size_t len, loff_t * ppos);
ssize_t assoofs_write(struct file * filp, const char __user * buf, size_t len, loff_t * ppos);
const struct file_operations assoofs_file_operations = {
	.read = assoofs_read,
	.write = assoofs_write,
};

//Recibe un puntero con el archivo que queremos leer, el buffer de usuario(el que te muestra el ordenador por la pantalla, en este caso sera la propia terminal),la longitud de datos a leer y el ppos(el cursor)
ssize_t assoofs_read(struct file * filp, char __user * buf, size_t len, loff_t * ppos) {
	struct assoofs_inode_info *inode_info = filp->f_path.dentry->d_inode->i_private;
	struct buffer_head *bh;
	char *buffer;
	int nbytes;
	printk(KERN_INFO "Read request\n");
	
	//Comprobamos que el cursor no haya llegada al final del archivo
	if (*ppos >= inode_info->file_size) return 0;//fin del archivo
	

	bh = sb_bread(filp->f_path.dentry->d_inode->i_sb, inode_info->data_block_number);
	buffer = (char *)bh->b_data;//Cargamos los datos del inodo
	nbytes = min((size_t) inode_info->file_size, len); // Hay que comparar len con el tamano del fichero por si llegamos al final del fichero
	copy_to_user(buf, buffer, nbytes);
	*ppos += nbytes;//Actualizamos el cursor
	return nbytes;
}


ssize_t assoofs_write(struct file * filp, const char __user * buf, size_t len, loff_t * ppos) {
	struct assoofs_inode_info *inode_info = filp->f_path.dentry->d_inode->i_private;
	struct buffer_head *bh;
	struct super_block *sb = filp->f_path.dentry->d_inode->i_sb;
	char *buffer;
	printk(KERN_INFO "Write request\n");
	
	bh = sb_bread(filp->f_path.dentry->d_inode->i_sb, inode_info->data_block_number);
	buffer = (char *)bh->b_data;
	buffer += *ppos;//Avanza hasta el final del buffer
	copy_from_user(buffer, buf, len);
	*ppos += len;
	mark_buffer_dirty(bh);
	sync_dirty_buffer(bh);
	brelse(bh);
	inode_info->file_size = *ppos;
	assoofs_save_inode_info(sb, inode_info);
	return len;
}


/*
* Operaciones sobre directorios
*/
static int assoofs_iterate(struct file *filp, struct dir_context *ctx);
const struct file_operations assoofs_dir_operations = {
	.owner = THIS_MODULE,
	.iterate = assoofs_iterate,
};


static int assoofs_iterate(struct file *filp, struct dir_context *ctx) {
	struct inode *inode;
	struct super_block *sb;
	struct assoofs_inode_info *inode_info;
	struct buffer_head *bh;
	struct assoofs_dir_record_entry *record;
	int i;
	printk(KERN_INFO "Iterate request\n");
	
	inode = filp->f_path.dentry->d_inode;//Accede a la estructura del diresctorio 
	sb = inode->i_sb;//Apunta al inodo del directorio
	inode_info = inode->i_private;//inode info apunta a la informacion privada del inodo i private
	
	if (ctx->pos) return 0;
	if ((!S_ISDIR(inode_info->mode))) return -1;
	bh = sb_bread(sb, inode_info->data_block_number);//Cargamos el contenido del bloque en el buffer head
	record = (struct assoofs_dir_record_entry *)bh->b_data;//Guardamos en record toda la informacion del directorio
	for (i = 0; i < inode_info->dir_children_count; i++) {//Recorremos el directorio desde 0 hasta el numero de hijos
		dir_emit(ctx, record->filename, ASSOOFS_FILENAME_MAXLEN, record->inode_no, DT_UNKNOWN);
		ctx->pos += sizeof(struct assoofs_dir_record_entry);
		record++;
	}
	brelse(bh);
	return 0;
}


/*
* Operaciones sobre inodos
*/
static int assoofs_create(struct inode *dir, struct dentry *dentry, umode_t mode, bool excl);
struct dentry *assoofs_lookup(struct inode *parent_inode, struct dentry *child_dentry, unsigned int flags);
static int assoofs_mkdir(struct inode *dir, struct dentry *dentry, umode_t mode);
static struct inode_operations assoofs_inode_ops = {
	.create = assoofs_create,
	.lookup = assoofs_lookup,
	.mkdir = assoofs_mkdir,
};

//Esta funcion busca la entrada (struct dentry) con el nombre correcto (child dentry->d name.name) en el directorio padre (parent inode). Se utiliza para recorrer y mantener el arbol de inodos.
struct dentry *assoofs_lookup(struct inode *parent_inode, struct dentry *child_dentry, unsigned int flags) {
	struct assoofs_inode_info *parent_info = parent_inode->i_private;
	struct super_block *sb = parent_inode->i_sb;
	struct buffer_head *bh;
	struct assoofs_dir_record_entry *record;
	int i;
	
	printk(KERN_INFO "Lookup request\n");
	// 1.- Acceder al bloque de disco con el contenido del directorio apuntado por parent inode.
	bh = sb_bread(sb, parent_info->data_block_number);
	
	// 2. Recorrer el contenido del directorio buscando la entrada cuyo nombre se corresponda con el que buscamos. Si se localiza la entrada, entonces tenemos construir el inodo correspondiente.

	record = (struct assoofs_dir_record_entry *)bh->b_data;
	for (i=0; i < parent_info->dir_children_count; i++) {
		if (!strcmp(record->filename, child_dentry->d_name.name)) {
			struct inode *inode = assoofs_get_inode(sb, record->inode_no); // Función auxiliar que obtine la información de
																		   //un inodo a partir de su número de inodo.
			inode_init_owner(inode, parent_inode, ((struct assoofs_inode_info *)inode->i_private)->mode);
			d_add(child_dentry, inode);
			return NULL;//devolvemos NULL aunque se haya encontrado la entrada
		}
		record++;
	}	
	
	// 3.- Fin de la función

	return NULL;
}

//Esta funcion nos permitira crear nuevos inodos para archivos
static int assoofs_create(struct inode *dir, struct dentry *dentry, umode_t mode, bool excl) {
	struct inode *inode;
	struct super_block *sb;
	struct assoofs_inode_info *inode_info;
	struct assoofs_inode_info *parent_inode_info;
	struct assoofs_dir_record_entry *dir_contents;
	struct buffer_head *bh;
	uint64_t count;
	
	printk(KERN_INFO "New file request\n");
	
	sb = dir->i_sb; // obtengo un puntero al superbloque desde dir
	count = ((struct assoofs_super_block_info *)sb->s_fs_info)->inodes_count; // obtengo el número de inodos de la información persistente del superbloque
	
	inode = new_inode(sb);//Creamos el nuevo inodo
	inode->i_ino = count + 1; // Asigno número al nuevo inodo a partir de count
	
	//Comprobamos que el valor de count no es superior al numero maximo de objetos soportados en assoofs
	if(inode->i_ino >= ASSOOFS_MAX_FILESYSTEM_OBJECTS_SUPPORTED){
		printk(KERN_ERR "Error, no hay suficiente espacio para el nuevo archivo\n");
		return -12;//error de no hay suficiente espacio ENOMEM
	}
	
	inode->i_sb = sb;//grabamos el superbloque en el inodo
	inode_info = kmalloc(sizeof(struct assoofs_inode_info), GFP_KERNEL);
	inode_info->inode_no = inode->i_ino;
	inode_info->mode = mode; // El segundo mode me llega como argumento
	inode_info->file_size = 0;
	inode->i_private = inode_info;//Guardo la informacion persistente del propio bloque
	
	inode->i_op = &assoofs_inode_ops;
	inode->i_fop = &assoofs_file_operations;
	inode->i_atime = inode->i_mtime = inode->i_ctime = current_time(inode);//fechas de acceso, de modificación y creación de este inodo
	
	assoofs_sb_get_a_freeblock(sb, &inode_info->data_block_number);//Necesitamos un bloque libre para introducir el inodo
	assoofs_add_inode_info(sb, inode_info);//Guardamos la informacion persistente del nuevo inodo en disco
	
	// 2.- Modificar el contenido del directorio padre, añadiendo una nueva entrada para el nuevo archivo o directorio. El nombre lo sacaremos del segundo parámetro
	
	parent_inode_info = dir->i_private;
	bh = sb_bread(sb, parent_inode_info->data_block_number);//Cargamos la informacion del padre en el buffer

	dir_contents = (struct assoofs_dir_record_entry *)bh->b_data;//Rellenamos el contenido del directorio con la informacion del padre
	dir_contents += parent_inode_info->dir_children_count;//Me desplazo hasta el ultimo de los hijos, ya que el nuevo tendra que ser el ultimo
	dir_contents->inode_no = inode_info->inode_no; // inode_info es la información persistente del inodo creado en el paso 2.

	strcpy(dir_contents->filename, dentry->d_name.name);//Ccopiamos el nombre que nos ha venido por la estructura de directorios(dentry) en el contenido del directorio
	mark_buffer_dirty(bh);
	sync_dirty_buffer(bh);//Compara lo que hay en disco con lo que hay en memoria y si ha cambiado algo lo guarda tambien en el disco
	brelse(bh);
	
	// 3.- Actualizar persistente del inodo padre
	parent_inode_info->dir_children_count++;//Incrementamos el numero de hijos
	assoofs_save_inode_info(sb, parent_inode_info);//Actualizamos la informacion del inodo padre
	inode_init_owner(inode, dir, mode);//Asignamos propietario al inodo
	d_add(dentry, inode);//Lo anadimos a la estructura de inodos
	return 0;
}

//Recibe el directorio, la estructura de directorios y el modo
static int assoofs_mkdir(struct inode *dir , struct dentry *dentry, umode_t mode) {
	struct inode *inode;
	struct super_block *sb;
	struct assoofs_inode_info *inode_info = NULL;
	struct assoofs_inode_info *parent_inode_info;
	struct assoofs_dir_record_entry *dir_contents;
	struct buffer_head *bh;
	uint64_t count;
	
	printk(KERN_INFO "New directory request\n");
	
	sb = dir->i_sb; // obtengo un puntero al superbloque desde dir
	count = ((struct assoofs_super_block_info *)sb->s_fs_info)->inodes_count; // obtengo el número de inodos de la información persistente del superbloque
	
	inode = new_inode(sb);//Creamos el nuevo inodo
	inode->i_ino = count + 1; // Asigno número al nuevo inodo a partir de count

	if(inode->i_ino >= ASSOOFS_MAX_FILESYSTEM_OBJECTS_SUPPORTED){
		printk(KERN_ERR "Error, no hay suficiente espacio para el nuevo archivo\n");
		return -12;//error de no hay suficiente espacio ENOMEM
	}
	
	inode->i_sb = sb;//grabamos el superbloque en el inodo
	
	inode_info = kmalloc(sizeof(struct assoofs_inode_info), GFP_KERNEL);
	inode_info->inode_no = inode->i_ino;
	inode_info->mode = S_IFDIR | mode; // El segundo mode me llega como argumento
	inode_info->dir_children_count = 0;//número de hijos es cero porque el directorio está vacío
	inode->i_private = inode_info;
	
	inode->i_op = &assoofs_inode_ops;
	inode->i_fop = &assoofs_dir_operations;
	inode->i_atime = inode->i_mtime = inode->i_ctime = current_time(inode);//fechas de acceso, de modificación y creación de este inodo
	
	assoofs_sb_get_a_freeblock(sb, &inode_info->data_block_number);
	assoofs_add_inode_info(sb, inode_info);
	
	// 2.- Modificar el contenido del directorio padre, añadiendo una nueva entrada para el nuevo archivo o directorio. El nombre lo sacaremos del segundo parámetro
	
	parent_inode_info = dir->i_private;
	bh = sb_bread(sb, parent_inode_info->data_block_number);

	dir_contents = (struct assoofs_dir_record_entry *)bh->b_data;
	dir_contents += parent_inode_info->dir_children_count;
	dir_contents->inode_no = inode_info->inode_no; // inode_info es la información persistente del inodo creado en el paso 2.

	strcpy(dir_contents->filename, dentry->d_name.name);//Copiamos el nombre del hijo 
	mark_buffer_dirty(bh);//Marcamos el buffer como sucio
	sync_dirty_buffer(bh);
	brelse(bh);
	
	// 3.- Actualizar persistente del inodo padre
	parent_inode_info->dir_children_count++;//Incrementamos el numero de hijos
	assoofs_save_inode_info(sb, parent_inode_info);//Guardamos la informacion del padre
	inode_init_owner(inode, dir, S_IFDIR | mode);//Asignamos propietario
	d_add(dentry, inode);//Anadimos el inodo
	return 0;
}


/*
* Operaciones sobre el superbloque
*/
static const struct super_operations assoofs_sops = {
	.drop_inode = generic_delete_inode,
};


/*
* Inicialización del superbloque
*/
int assoofs_fill_super(struct super_block *sb, void *data, int silent) {
	
	// 1.- Leer la información persistente del superbloque del dispositivo de bloques
	struct buffer_head *bh;//Declaramos el buffer head para poder acceder al superbloque
	struct assoofs_super_block_info *assoofs_sb;//Estructura para guardar la informacion leida del superbloque
	struct inode *root_inode;//Declaramos el inodo raiz
	
	printk(KERN_INFO "assoofs_fill_super request\n");
	bh = sb_bread(sb, ASSOOFS_SUPERBLOCK_BLOCK_NUMBER); // sb lo recibe assoofs_fill_super como argumento
	assoofs_sb = (struct assoofs_super_block_info *)bh->b_data;//Guardamos en assoofs_sb el contenido del bloque
	
	brelse(bh);//Liberamos memoria en el buffer_head
	
	// 2.- Comprobar los parámetros del superbloque
	//Comprobamos que el tamaño del bloque que hemos leido coincide con el tamaño del superbloque
	if(assoofs_sb->block_size != ASSOOFS_DEFAULT_BLOCK_SIZE){
		printk(KERN_INFO "El size leído no corresponde con el size del superbloque\n");
		return -1;//EPERM error operación no permitida
	}
	
	//Comprobomas que el numero magico del bloque leido es igual que el del superbloque
	if(assoofs_sb->magic != ASSOOFS_MAGIC){
		printk(KERN_INFO "El magic no es igual que el magic en el superbloque\n");
		return -1;
	}
	
	// 3.- Escribir la información persistente leı́da del dispositivo de bloques en el superbloque sb, incluı́do el campo s_op con las operaciones que soporta.
	
	sb->s_magic = ASSOOFS_MAGIC;//Asignar el numero magico definido en assoofs.h al campo magic del superbloque
	sb->s_maxbytes = ASSOOFS_DEFAULT_BLOCK_SIZE;//Asignar el tamaño del bloque definido en assoofs.h al campo s_maxbytes del superbloque
	sb->s_op = &assoofs_sops;//Asigna las operaciones propias del superbloque (borrar, crear etc) al campo s_op del superbloque
	sb->s_fs_info = assoofs_sb;//Guardar la informacion leida del bloque 0 del disco en el campo fs_info del superbloque para no tener que acceder continuamente al bloque 0 del disco
	
	// 4.- Crear el inodo raı́z y asignarle operaciones sobre inodos (i_op) y sobre directorios (i_fop)
	
	root_inode = new_inode(sb);//Creamos el inodo raiz con new_inode, le pasamos el superbloque del sistema de ficheros donde queremos crear el inodo
	inode_init_owner(root_inode, NULL, S_IFDIR); // S_IFDIR para directorios, S_IFREG para ficheros.
	
	root_inode->i_ino = ASSOOFS_ROOTDIR_INODE_NUMBER;//Asignamos el numero de inodo
	root_inode->i_sb = sb;//Asignamos el superbloque del sistema de ficheros al que pertenece
	root_inode->i_op = &assoofs_inode_ops; // dirección de una variable de tipo struct inode_operations previamente declarada
	root_inode->i_fop = &assoofs_dir_operations; // dirección de una variable de tipo struct file_operations previamente declarada. En la práctica tenemos 2: assoofs_dir_operations y assoofs_file_operations. La primera la utilizaremos cuando creemos inodos para directorios (como el directorio raı́z) y la segunda cuando creemos inodos para ficheros.
	root_inode->i_atime = root_inode->i_mtime = root_inode->i_ctime = current_time(root_inode);//Asignamos fechas de creacion,modificacion y acceso
	root_inode->i_private = assoofs_get_inode_info(sb, ASSOOFS_ROOTDIR_INODE_NUMBER);//Asignamos la información persistente del inodo con assoofs_get_inode_info
	
	//Introducimos el nuevo inodo en el arbol de inodos, en este caso se trata del inodo raiz
	sb->s_root = d_make_root(root_inode);//Asignamos al campo s_root del superbloque el resultado de la funcion d_make_root
	return 0;
}


/*
* Montaje de dispositivos assoofs
*/
static struct dentry *assoofs_mount(struct file_system_type *fs_type, int flags, const char *dev_name, void *data) {
	struct dentry *ret = mount_bdev(fs_type, flags, dev_name, data, assoofs_fill_super);// Control de errores a partir del valor de ret. En este caso se puede utilizar la macro IS_ERR: if (IS_ERR(ret)) ...
	printk(KERN_INFO "assoofs_mount request\n");
	if (IS_ERR(ret)){
		printk(KERN_INFO "No se ha podido montar el sistema de archivos");
	}else{
		printk(KERN_INFO "Sistema de archivos montado correctamente");
	}
	return ret;
}


/*
* assoofs file system type
*/
static struct file_system_type assoofs_type = {
	.owner = THIS_MODULE,
	.name= "assoofs",
	.mount= assoofs_mount,
	.kill_sb = kill_litter_super,
};


static int __init assoofs_init(void) {
	int ret = register_filesystem(&assoofs_type);// Control de errores a partir del valor de ret
	printk(KERN_INFO "assoofs_init request\n");
	if (ret==0){
		printk(KERN_INFO "El sistema de archivos se registro correctamente");
	}else{
		printk(KERN_INFO "El sistema de archivos no se ha podido registrar");
	}
	return ret;

}

static void __exit assoofs_exit(void) {
	int ret = unregister_filesystem(&assoofs_type);// Control de errores a partir del valor de ret
	printk(KERN_INFO "assoofs_exit request\n");
	if (ret==0){
		printk(KERN_INFO "El sistema de archivos se ha eliminado correctamente");
	}else{
		printk(KERN_INFO "El sistema de archivos no se ha podido eliminar");
	}
}

//******************************************************* Funciones no declaradas en la base de la práctica *******************************************************************//
//Esta funcion auxiliar nos permitira obtener la informacion persistente del inodo nuumero inode_no del superbloque sb
struct assoofs_inode_info *assoofs_get_inode_info(struct super_block *sb, uint64_t inode_no){
	struct assoofs_inode_info *inode_info = NULL;
	struct buffer_head *bh;
	int i;
	struct assoofs_super_block_info *afs_sb = sb->s_fs_info;
	struct assoofs_inode_info *buffer = NULL;
	
	// 1.- Acceder a disco para leer el bloque que contiene el almacén de inodos:
	bh = sb_bread(sb, ASSOOFS_INODESTORE_BLOCK_NUMBER);//Leemos el superbloque del almacen de inodos y almacenamos la informacion en bh->_data
	inode_info = (struct assoofs_inode_info *)bh->b_data;//Guardamos en inode_info la informacion del bloque que contiene el almacen de inodos
	
	
	// 2. Recorrer el almacén de inodos en busca del inodo inode_no:
	for (i= 0; i < afs_sb->inodes_count; i++) {
		//Comprobamos que los inodos coinciden
		if(inode_info->inode_no == inode_no) {
			buffer = kmalloc(sizeof(struct assoofs_inode_info), GFP_KERNEL);//Reservamos memoria para rellenar el buffer
			memcpy(buffer, inode_info, sizeof(*buffer));//Copiamos la informacion procedente de inode_info en el buffer
			break;
		}
		inode_info++;//Incrementamos inode_info para pasar al siguiente inodo
	}
	
	// 3.- Liberar recursos y devolver a información del inodo inode no si estaba en el almacén:
	
	brelse(bh);//Liberamos memoria en el buffer
	return buffer;//Devolvemos la informacion del inodo
}

//Esta funcion auxiliar nos permitira obtener un puntero al inodo numero ino del superbloque sb
static struct inode *assoofs_get_inode(struct super_block *sb, int ino){
	struct assoofs_inode_info *inode_info = NULL;
	struct inode *inode;
	
	// 1.- Obtener la información persistente del inodo ino. Ver la función auxiliar assoofs get inode info descrita anterior-mente.
	inode_info = assoofs_get_inode_info(sb, ino);//Guardamos en inode_info la informacion persistente del inodo ino
	
	// 2.- Crear una nueva variable de tipo struct inode e inicializarla con la función new inode.
	//Asignar valores a los campos i_ino, i_sb, i_op, i_fop, i_atime, i_mtime, i_ctime e i_private del nuevo inodo.
	inode = new_inode(sb);//Creamos un nuevo inodo
	inode->i_ino = ino;
	inode->i_sb = sb;
	inode->i_op = &assoofs_inode_ops;
	
	//Comprobamos si se trata de un directorio o de un fichero
	if (S_ISDIR(inode_info->mode)){
		inode->i_fop = &assoofs_dir_operations;
	}else if (S_ISREG(inode_info->mode)){
		inode->i_fop = &assoofs_file_operations;
	}else{
		printk(KERN_ERR "Unknown inode type. Neither a directory nor a file.");
	}
	inode->i_atime = inode->i_mtime = inode->i_ctime = current_time(inode);//fechas de acceso, de modificación y creación de este inodo
	inode->i_private = assoofs_get_inode_info(sb, ino); // Información persistente del inodo
	
	// 3.- Por último, devolvemos el inodo inode recién creado.
	return inode;
}

// Busca un bloque libre para guardar nuestro archivo
int assoofs_sb_get_a_freeblock(struct super_block *sb, uint64_t *block){
	struct assoofs_super_block_info *assoofs_sb = sb->s_fs_info;//Obtenemos la informacion persistente del superbloque
	int i;
	
	//Lo siguiente es buscar un bloque que solo tenga el numero de bloque que ocupa un bit
	for (i = 2; i < ASSOOFS_MAX_FILESYSTEM_OBJECTS_SUPPORTED; i++){
		if (assoofs_sb->free_blocks & (1 << i)){
			break; // cuando aparece el primer bit 1 en free_block dejamos de recorrer el mapa de bits, i tiene la posición del primer bloque libre
		}
	}
	
	//Si la i es mayor o igual que el numero de bloques soportados entonces no lo guardamos
	if(i>=ASSOOFS_MAX_FILESYSTEM_OBJECTS_SUPPORTED){
		printk(KERN_INFO "No hay espacio de almacenaje\n");
		return -12;//Sin espacio de almacenaje
	}
	
	*block = i; // Escribimos el valor de i en la dirección de memoria indicada como segundo argumento en la función
	
	assoofs_sb->free_blocks &= ~(1 << i);//Actualizamos el numero de bloques libres(buscar el ultimo y ponerlo como ocupado)
	assoofs_save_sb_info(sb);//Guardamos el superbloque
	return 0;
}

// Guarda el superbloque
void assoofs_save_sb_info(struct super_block *vsb){
	struct buffer_head *bh;
	struct assoofs_super_block *sb = vsb->s_fs_info; // Información persistente del superbloque en memoria
	bh = sb_bread(vsb, ASSOOFS_SUPERBLOCK_BLOCK_NUMBER);//Leemos la informacion del superbloque
	bh->b_data = (char *)sb; // Sobreescribo los datos de disco con la información en memoria
	mark_buffer_dirty(bh);//Marcamos el buffer como sucio
	sync_dirty_buffer(bh);//Sincroniza, el campo que esta en el disco lo compara con el de el disco, y si hay algun cambio entonces sincroniza y copia los cambios en el disco
	brelse(bh);
}

//Anade informacion de un nuevo inodo
void assoofs_add_inode_info(struct super_block *sb, struct assoofs_inode_info *inode){
	struct buffer_head *bh;
	struct assoofs_inode_info *inode_info;
	struct assoofs_super_block_info *assoofs_sb = sb->s_fs_info;
	bh = sb_bread(sb, ASSOOFS_INODESTORE_BLOCK_NUMBER);
	inode_info = (struct assoofs_inode_info *)bh->b_data;//Sobre el campo inode info volcamos el almacen de inodos
	inode_info += assoofs_sb->inodes_count;//Vamos hasta el final del almacen de inodos
	memcpy(inode_info, inode, sizeof(struct assoofs_inode_info));//Copiamos la informacion del inodo en inode_info
	mark_buffer_dirty(bh);//Marcamos el buffeer como sucio 
	sync_dirty_buffer(bh);//Sincronizamos para que grabe en el disco 
	brelse(bh);
	assoofs_sb->inodes_count++;//Incrementamos el contador de inodos ya que hemos creado uno
	assoofs_save_sb_info(sb);//Guardamos la informacion del superbloque
}

//Recibe por parametro el superbloque y la informacion del inodo que queremos guardar
int assoofs_save_inode_info(struct super_block *sb, struct assoofs_inode_info *inode_info){
	struct buffer_head *bh;
	struct assoofs_inode_info *inode_pos;
	bh = sb_bread(sb, ASSOOFS_INODESTORE_BLOCK_NUMBER);//Obtenemos el almacen de inodos
	inode_pos = assoofs_search_inode_info(sb, (struct assoofs_inode_info *)bh->b_data, inode_info);//Buscamos los datos de inode info en el alamcen, para ello usamos una funcion auxiliar
	
	//Comprobamos si lo hemos encotrado
	if(inode_pos!=NULL){
		memcpy(inode_pos, inode_info, sizeof(*inode_pos));//Guardamos la informacion del inodo
		mark_buffer_dirty(bh);//Marcamos el buffer como sucio
		sync_dirty_buffer(bh);
		return 0;
	}
	return -14;//direccion no encontrada
}

//Recibe el superbloque, el puntero del almacen de inodos desde la posicion 1(START) y el inodo que queremos buscar
struct assoofs_inode_info *assoofs_search_inode_info(struct super_block *sb, struct assoofs_inode_info *start, struct assoofs_inode_info *search){
	uint64_t count = 0;
	//Vamos aumentando el contador hasta llegar al inodo que queremos buscar
	while (start->inode_no != search->inode_no && count < ((struct assoofs_super_block_info *)sb->s_fs_info)->inodes_count) {
		count++;
		start++;
	}
	
	//Si el numero del inodo del start y del search son iguales entonces hemos encontrado el inodo y lo devolvemos
	if (start->inode_no == search->inode_no)
		return start;
	else
		return NULL;
}


module_init(assoofs_init);
module_exit(assoofs_exit);
