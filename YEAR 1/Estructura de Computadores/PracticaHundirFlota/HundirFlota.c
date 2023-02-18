#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define NUMBARCOS1 4
#define NUMBARCOS2 2
#define NUMBARCOS3 1

int menu();
void hundirLaFlota(int opcion, int filas,  int columnas);
void colocarBarcosManualmente(int *t, int filas, int columnas);
void colocarBarcosAutomaticamente(int *t, int filas, int columnas);
void inicializarTablero(int *t, int filas, int columnas);
void imprimirTablero(int *t, int filas, int columnas);
void imprimirTableroArchivo(int *t, int filas, int columnas, FILE *pa);
int comprobacionEspacioParaBarco(int *t, int filas, int columnas, int iniFila, int iniCol, int tamBarco, int orientacion);
int compruebaGanador (int *b, int filas, int columnas);
int compruebaDisparo(int *t, int filas, int columnas, int posFila, int posCol);
void juegoManual(int filas, int columnas);
void juegoAutomatico(int filas, int columnas);

int main(int argc, char *argv[]){
//Función principal. 
//	- comprueba que el número de argumentos del main es correcto. 
//		--Si no son correctos, mensaje de error y fin del programa
//		--Si son correctos:
//				--- lee las filas y las columnas del tablero
//				--- mientras la opción leída no sea 3, juega al hundir la flota 
	
	if(argc!=3){
		printf("El número de argumentos no es correcto\n");
		return -1;
	}else{
		int filas=atoi(argv[1]);
		int columnas=atoi(argv[2]);
		if(filas<=5 || columnas<=5){
			printf("El tablero no es lo suficientemente grande\n");
			return -1;
		}
		
		srand(time(NULL));
		
		int selection;
		do {
			selection = menu();
			hundirLaFlota(selection,filas,columnas);
		} while (selection != 3);
		
	}

	return 0;
}

int menu(){
//Función menu.
//	- INPUTS: nada
//	- OUTPUTS: la opción leída (1, 2 ó 3)
//  - Presenta el menú por pantalla y lee una opción. Si no es 1, 2 ó 3 da un mensaje de error y vuelve a leerla hasta que sea correcta.
	int opcion;
	do{
		printf("Bienvenido a hundir la flota\n");
		printf("Introduzca 1 para jugar(ordenador vs jugador)\n");
		printf("Introduzca 2 para jugar(ordenador vs ordenador)\n");
		printf("Introduzca 3 para salir del juego\n");
		scanf("%d",&opcion);
		if(opcion<= 0 || opcion >= 4){
			printf("Error\n");
		}
	}while(opcion<= 0 || opcion >= 4);
	
	return opcion;
}

void hundirLaFlota(int opcion, int filas,  int columnas){
//Función hundirLaFlota
//	- INPUTS: opción (1, 2 ó 3), filas y columnas del tablero
//  - OUTPUTS: nada
//  - Según la opción leída llama a jugar manual (opción 1), jugar automático (opción 2) o muestra un mensaje de fin (opción 3)
	switch(opcion){
		case 1:
			printf("Ha elegido el modo ordenador vs jugador\n");
			juegoManual(filas,columnas);
			break;
		case 2:
			printf("Ha elegido el modo ordenador vs ordenador\n");
			juegoAutomatico(filas,columnas);
			break;
		case 3:
			printf("Hasta pronto\n");
			exit(0);
	}
}	
void juegoManual(int filas, int columnas){
//Función juegoManual
// 	- INPUTS: número de filas y número de columnas del tablerro
//	- OUTPUTS: nada
//	- Juega de forma manual:
//		-- Crea los 4 tableros, los inicializa (llamando a la función inicializar tantas veces como tableros haya) y en 2 pone los barcos de forma manual o automática, según indique el usuario
//		-- Escribe por pantalla los tableros y las tiradas al inicio del juego
//		-- Por turnos, cada jugador elige una fila y columna de 1 al número de filas y de 1 al número de columnas. Se comprueba si hay barco indicando agua o tocado (se indica por pantalla).
//		-- Se comprueba si hay ganador. Si lo hay acaba la partida indicando quíén ha ganado por pantalla
//		-- EXTRA: comprobar que no se ha repetido ese disparo

}
void juegoAutomatico(int filas, int columnas){
//Función juegoAutomatico
// 	- INPUTS: número de filas y número de columnas del tablerro
//	- OUTPUTS: nada
//	- Juega de forma automática:
//		-- Abre el archivo para escribir en él todas las salidas
//		-- Crea los 4 tableros, los inicializa (llamando a la función inicializar tantas veces como tableros haya) y en 2 pone los barcos automáticamente
//		-- Escribe en el archivo los tableros y las tiradas al inicio del juego
//		-- Por turnos, cada jugador genera una fila y columna automáticamente (se indica en el archivo). Se comprueba si hay barco indicando agua o tocado (se indica en el archivo).
//		-- Se comprueba si hay ganador. Si lo hay acaba la partida indicando quíén ha ganado en el archivo
//		-- EXTRA: comprobar que no se ha repetido ese disparo
	FILE *Archivo;
	int *barcosj1,*tableroj1,*barcosj2,*tableroj2;
	barcosj1=(int*)malloc(filas*columnas*sizeof(int));
	tableroj1=(int*)malloc(filas*columnas*sizeof(int));
	barcosj2=(int*)malloc(filas*columnas*sizeof(int));
	tableroj2=(int*)malloc(filas*columnas*sizeof(int));
	
	inicializarTablero(barcosj1,filas,columnas);
	inicializarTablero(tableroj1,filas,columnas);
	inicializarTablero(barcosj2,filas,columnas);
	inicializarTablero(tableroj2,filas,columnas);
	
	Archivo=fopen("HundirFlotapartida.txt", "w");
	
	colocarBarcosAutomaticamente(barcosj1,filas,columnas);
	colocarBarcosAutomaticamente(barcosj2,filas,columnas);
	return;
}


void inicializarTablero(int *t, int filas, int columnas){
//Función inicializarTablero
//INPUTS:
//	- puntero a un tablero (barcos, tiradas o lo que sea)
//	- filas
//	- columnas
//OUTPUS: nada
//Inicializa a 0 la tabla
	int i;
	for(i=0;i<filas*columnas;i++){
		*(t+i)=0;
	}
}

void imprimirTableroArchivo(int *t, int filas, int columnas, FILE *pa){
//Función imprimirTableroArchivo
//INPUTS:
//	- puntero a un tablero (barcos, tiradas o lo que sea)
//	- filas
//	- columnas
// 	- puntero a archivo
//OUTPUTs: nada
//Imprime en el archivo la matriz pasada	
	
}

int compruebaGanador (int *b, int filas, int columnas){
//Función compruebaGanador
//INPUTs:
//	- puntero a un tablero de barcos
//	- filas
//	- columnas
//OUTPUTS: nada
//Busca si quedan barcos por hundir. Si no, devuelve 0. 
	
}

int compruebaDisparo(int *t, int filas, int columnas, int posFila, int posCol){
//Función compruebaDisparo
//INPUTS:
//	- tablero de barcos
//	- filas
//	- columnas
//	- tiro (fila y columna)
//OUTPUTS: 0 si agua, 1 si hay un barco de 1, 2 si hay un barco de 2, 3 si hay un barco de 3
	
}

int comprobacionEspacioParaBarco(int *t, int filas, int columnas, int iniFila, int iniCol, int tamBarco, int orientacion){
//Función comprobacionEspacioParaBarco
//INPUTS:
//	- tablero de barcos
//	- filas
//	- columnas
//	- posición (fila)
//	- posición (columna)
//	- tamaño del barco (1, 2 ó 3)
//	- orientación (0 horizontal, 1 vertical)
//OUTPUTS: 1 si cabe, 0 si no cabe
//Comprueba si cabe un barco a partir de la posición indicada en la orientación indicada. Devuelve 0 si no cabe, 1 si sí.
	int i,comprueba;
	iniFila=0;
	iniCol=0;
	switch(tamBarco){
		case 1:
		for(i=0;i<tamBarco;i++){
			if(*(t+iniFila*columnas+iniCol)=0){
				comprueba=1;
			}else{
				comprueba=0;
			}
		}
		if(comprueba=1){
			printf("La posicion %d,%d esta libre\n",iniFila,iniCol);
		}else{
			printf("La posición %d,%d no está libre\n",iniFila,iniCol);
		}
		break;
		case 2:
		if(orientacion=0){//Horizontal
			for(i=0;i<tamBarco;i++){
				if(*(t+iniFila*columnas+i)=0){
					comprueba=1;
				}else{
					comprueba=0;
				}
			}
			if(comprueba=1){
				printf("\nLas dos posiciones aleatorias estan libres\n");
			}else{
				printf("\nAl menos una posicion no esta libre");
			}
				
				
		}else{//Vertical
			for(i=0;i<tamBarco;i++){
				if(*(t+i*columnas+iniCol)=0){	
					comprueba=1;
				}else{	
					comprueba=0;
				}
			}
			if(comprueba=1){
				printf("Las dos posiciones aleatorias estan libres\n");
			}else{
				printf("Al menos una posicion no esta libre\n");
			}	
		}	
		break;
		case 3:
		if(orientacion=0){//Horizontal
			for(i=0;i<tamBarco;i++){
				if(*(t+iniFila*columnas+i)=0){
					comprueba=1;
				}else{
					comprueba=0;
				}
			}
			if(comprueba=1){
				printf("Las tres posiciones estan libres\n");
			}else{
				printf("Al menos una posicion no esta libre\n");
			}			
		}else{//Vertical
			for(i=0;i<tamBarco;i++){
				if(*(t+i*columnas+iniCol)=0){
					comprueba=1;
				}else{
					comprueba=0;
				}
			}
			if(comprueba=1){
				printf("Las tres posiciones estan libres\n");
			}else{
				printf("Al menos una posicion no esta libre\n");
			}		
		}		
	}
	return comprueba;
}


void colocarBarcosAutomaticamente(int *t, int filas, int columnas){
//Función colocarBarcosAutomaticamente
//INPUTS:
//	- Tablero de barcos
//	- filas
//	- columnas	
//OUTPUTS: nada
//Coloca de forma automática 4 barcos de 1 posición, 2 de 2 posiciones y 1 de 3 posiciones en el tablero
	
	int fila=rand()%(filas)+0;//Genera filas desde 0 hasta (filas-1)
	int columna=rand()%(columnas)+0;//Genera columnas desde 0 hata (columnas-1)
	int orientacion=rand()%(2);//Genera numeros entre 0 y 1
	//Barcos de tamaño 1
	int i,salida=0;
	if(comprobacionEspacioParaBarco(t,filas,columnas,fila,columna,1,orientacion)==1){
			for(i=0;i<NUMBARCOS1;i++){
				int fila=rand()%(filas)+0;//Genera filas desde 0 hasta (filas-1)
				int columna=rand()%(columnas)+0;//Genera columnas desde 0 hata (columnas-1)
				*(t+fila*columnas+columna)=1;
			}
	}else{
		do{
			for(i=0;i<NUMBARCOS1;i++){
				int fila=rand()%(filas)+0;//Genera filas desde 0 hasta (filas-1)
				int columna=rand()%(columnas)+0;//Genera columnas desde 0 hata (columnas-1)
				*(t+fila*columnas+columna)=1;
			}
		}while(comprobacionEspacioParaBarco(t,filas,columnas,fila,columna,1,orientacion)==0);
	}	
	//Barcos de tamaño 2
	if(comprobacionEspacioParaBarco(t,filas,columnas,fila,columna,2,orientacion)==1){
		for(i=0;i<NUMBARCOS2;i++){
			int fila=rand()%(filas)+0;//Genera filas desde 0 hasta (filas-1)
			int columna=rand()%(columnas)+0;//Genera columnas desde 0 hata (columnas-1)
			*(t+fila*columnas+columna)=1;
		}
	}else{
		do{
			for(i=0;i<NUMBARCOS2;i++){
				int fila=rand()%(filas)+0;//Genera filas desde 0 hasta (filas-1)
				int columna=rand()%(columnas)+0;//Genera columnas desde 0 hata (columnas-1)
				*(t+fila*columnas+columna)=1;
			}
		}while(comprobacionEspacioParaBarco(t,filas,columnas,fila,columna,1,orientacion)==0);
	}
	//Barcos de tamaño 3
	if(comprobacionEspacioParaBarco(t,filas,columnas,fila,columna,1,orientacion)==1){
		for(i=0;i<NUMBARCOS3;i++){
			int fila=rand()%(filas)+0;//Genera filas desde 0 hasta (filas-1)
			int columna=rand()%(columnas)+0;//Genera columnas desde 0 hata (columnas-1)
			*(t+fila*columnas+columna)=1;
		}
	}else{
		do{
			for(i=0;i<NUMBARCOS2;i++){
				int fila=rand()%(filas)+0;//Genera filas desde 0 hasta (filas-1)
				int columna=rand()%(columnas)+0;//Genera columnas desde 0 hata (columnas-1)
				*(t+fila*columnas+columna)=1;
			}
		}while(comprobacionEspacioParaBarco(t,filas,columnas,fila,columna,1,orientacion)==0);
	}	
}


void colocarBarcosManualmente(int *t, int filas, int columnas){
//Función colocarBarcosManualmente
//INPUTS:
//	- Tablero de barcos
//	- filas
//	- columnas	
//OUTPUTS: nada
//Coloca de forma manual 4 barcos de 1 posición, 2 de 2 posiciones y 1 de 3 posiciones en el tablero
}