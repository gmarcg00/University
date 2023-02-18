#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void diagonalPrincipal(int *matriz,int filas);
void diagonalSecundaria(int *matriz,int filas,int columnas);
void Inicializa(int *matriz,int filas,int columnas);
void inicializaResto(int *matriz,int filas,int columnas);
void imprimirMatriz(int *matriz,int filas,int columnas);

int main(int argc, char *argv[2]){
	int filas=atoi(argv[1]);
	int columnas=atoi(argv[2]);
	if(filas!=columnas){
		printf("Error, la matriz debe ser cuadrada\n");
		return -1;
	}
	int *matriz;
	matriz=(int*)malloc(sizeof(int)*(filas*columnas));
	srand(time(NULL));
	Inicializa(matriz,filas,columnas);
	diagonalPrincipal(matriz,filas);
	diagonalSecundaria(matriz,filas,columnas);
	inicializaResto(matriz,filas,columnas);
	imprimirMatriz(matriz,filas,columnas);
	free(matriz);
	
	return 0;
}

void diagonalPrincipal(int *matriz,int filas){
	int i;
	for(i=0;i<filas;i++){
		*(matriz+i*filas+i)=0;
	}
}
void diagonalSecundaria(int *matriz,int filas,int columnas){
	int i,j;
	for(i=filas-1;i>=0;i--){
		for(j=0;j<columnas;j++){
			if(i+j==columnas-1){
				*(matriz+i*columnas+j)=1;
			}
		}
	}
}
void Inicializa(int *matriz,int filas,int columnas){
	int i,j;
	for(i=0;i<filas;i++){
		for(j=0;j<columnas;j++){
			*(matriz+i*columnas+j)=-1;
		}
	}
}
void inicializaResto(int *matriz,int filas,int columnas){
	int i,j;
	for(i=0;i<filas;i++){
		for(j=0;j<columnas;j++){
			if(*(matriz+i*columnas+j)==-1){
				*(matriz+i*columnas+j)=rand()%(6)+5;
			}
		}
	}
}
void imprimirMatriz(int *matriz,int filas,int columnas){
	int i,j;
	for(i=0;i<filas;i++){
		for(j=0;j<columnas;j++){
			if(j==(columnas-1)){
				printf("%d\n",*(matriz+i*columnas+j));
			}else{
				printf("%d\t",*(matriz+i*columnas+j));
			}
		}
	}
}
	

	


	
	
			
			
	
