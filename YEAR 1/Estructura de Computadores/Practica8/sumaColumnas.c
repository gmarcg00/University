#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define filas 3
#define columnas 6
void inicializa(int matriz[filas][columnas]){
	for(int i=0;i<filas;i++){
		for(int j=0;j<columnas;j++){
			matriz[i][j]=rand()%(19)+1;/*Entre 1 y 20
			para que no se repitan numeros*/
		}
	}
}
int sumaColumnas(int matriz[filas][columnas], int vector[columnas]){
	for(int j=0;j<columnas;j++){
		int suma=0;//Inicializamos suma en 0//
		for(int i=0;i<filas;i++){
			suma=suma+matriz[i][j];
		}
		vector[j]=suma;//Guardamos la suma en el vector j//
	}
}
int main(){
	srand(time(NULL));//Semilla//
	int matriz[filas][columnas],vector[columnas];//Creamos matriz y vector//
	inicializa(matriz);//Guardamos la matriz creada por la función inicializa en la matriz//
	sumaColumnas(matriz,vector);
	for(int i=0;i<filas;i++){
		for(int j=0;j<columnas;j++){
			printf("%d, ", matriz[i][j]);
		}
		printf("\n");
	}
	for(int i=0;i<columnas;i++){
			printf("La suma de la columna %d es %d\n",i+1,vector[i]);
	}
return 0;
}

	
	

	
