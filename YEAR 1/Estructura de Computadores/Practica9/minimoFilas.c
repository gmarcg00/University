#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define filas 8
#define columnas 5
void inicializa(int matriz[filas][columnas]){
	for(int i=0;i<filas;i++){
		for(int j=0;j<columnas;j++){
			matriz[i][j]=random()%(21)-10;
		}
	}
}
void minimoFilas(int matriz[filas][columnas], int minimoValor[filas]){
	for(int i=0;i<filas;i++){
		 minimoValor[i]=matriz[i][0];
		for(int j=0;j<columnas;j++){
			if(minimoValor[i]>matriz[i][j]){
				minimoValor[i]=matriz[i][j];
			}
		}
	}
}
int main(){
	srand(time(NULL));
	int matriz[filas][columnas],minimoValor[filas];
	inicializa(matriz);
	minimoFilas(matriz,minimoValor);
	for(int i=0;i<filas;i++){
		for(int j=0;j<columnas;j++){
			printf("%d, ", matriz[i][j]);
		}
		printf("\n");
	}
	for(int i=0;i<filas;i++){
			printf("El valor mínimo de la fila %d es %d\n",i+1,minimoValor[i]);
	}
return 0;
}
	
	
	
			
				
			
			
			 
			
	
