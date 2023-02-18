/*Este programa suma los elementos de una matriz,
dichos elementos han sido generados de forma aleatoria*/
#include <stdio.h>
#include <stdlib.h>//Para generar numeros aleatorios//
#include <time.h>//Para obtener la hora del sistema//
#define tam 6
void inicializa(int matriz[tam][tam]){//Función inicializa, genera una matriz de numeros aleatorios//
	for(int i=0;i<tam;i++){//Generamos las filas de la matriz//
		for(int j=0;j<tam;j++){//Generamos las columnas de la matriz//
			matriz[i][j]=rand()%(19)+1;/*Genera numeros aleatorios entre 1 y 20
			y los guarda en los elementos de la matriz */
		}
	}
}
int suma(int matriz[tam][tam]){//Funcion suma, suma los elemntos de la matriz//
	int suma=0;//Declaramos e inicializamos en 0 la variable//
	for(int i=0;i<tam;i++){
		for(int j=0;j<tam;j++){
			suma += matriz[i][j];
		}
	}
return suma;
}
int main(){
	srand(time(NULL));//Generamos la semilla//
	int matriz[tam][tam];//Creamos una matriz//
	inicializa(matriz);//Pasamos la variable matriz a la funcion inicializa//
	suma(matriz);//Pasamos la variable matriz a la función suma//
	int resultado=suma(matriz);/*Declaramos resultado y le asignamos*/
	printf("El resultado de la suma de elementos es: %d\n",resultado);
return 0;
}

		
			