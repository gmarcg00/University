#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main( int argc, char *argv[]){
	
	int fildes[2];
	pipe(fildes);
	
	if(fork()){
		char buffer [11];
		read(fildes[0],buffer,sizeof(buffer));
			
	}else{
		write(fildes[1],"hola mundo\n",sizeof("hola mundo\n"));
	}
	
	//Si alguien lee el extremo de lectura , se queda ahi hasta que haya algo que leer
	//Por eso no hace falta poner un wair en el padre para que espere a que el hijo escriba
	//Al leer el extremo de lectura, el padre se queda esperando a que el hijo escriba
}
