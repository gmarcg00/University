#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define TAM 500


void inicializa(int matriz[TAM][TAM]){
	for(int i=0;i<TAM;i++){
		for(int j=0;j<TAM;j++){
			matriz[i][j]=rand()%(10)+1;
		}
	}
}

void multiplica(int matrizA[TAM][TAM],int matrizB[TAM][TAM],int resultado[TAM][TAM]){
	for(int i=0;i<TAM;i++){
		for(int j=0;j<TAM;j++){
			int aux=0;
			for(int k=0;k<TAM;k++){
				aux+=matrizA[i][k]*matrizB[k][j];
			}
			resultado[i][j]=aux;
		}
	}
	
}

void imprimeMatriz(int matriz[TAM][TAM]){
	for(int i=0;i<TAM;i++){
		for(int j=0;j<TAM;j++){
			printf("%d\t",matriz[i][j]);
		}
	printf("\n");
	}
	
	
}

int main(){
	srand(time(NULL));
	int matrizA[TAM][TAM];
	int matrizB[TAM][TAM];
	int matrizC[TAM][TAM];
	inicializa(matrizA);
	inicializa(matrizB);
	clock_t time1=clock();
	printf("Tiempo 1: %f",(double)time1);
	printf("\n");
	multiplica(matrizA,matrizB,matrizC);
	clock_t time2=clock();
	printf("Tiempo 2: %f",(double)time2);
	printf("\n");
	double duration=(double)(time2-time1);
	//imprimeMatriz(matrizA);
	printf("\n");
	//imprimeMatriz(matrizB);
	printf("\n");
	//imprimeMatriz(matrizC);
	
	printf("\n");
	printf("Tiempo final: %f",duration);
	
	
	
}