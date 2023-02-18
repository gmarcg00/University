#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define TAM 3
void inicializa(int matriz[TAM][TAM]){//Genera la matriz//
	for(int i=0;i<TAM;i++){
		for(int j=0;j<TAM;j++){
			matriz[i][j]=rand()%(10)+1;
		}
	}
}
void multiplica(int matrizA[TAM][TAM],int matrizB[TAM][TAM],int resultado[TAM][TAM]){
	//Genera 2 matrices, las multiplica y guarda el resultado en otra//
	for(int i=0;i<TAM;i++){
		for(int j=0;j<TAM;j++){
			resultado[i][j]=0;
			for(int k=0;k<TAM;k++){
				resultado[i][j]+=matrizA[i][k]*matrizB[k][j];//Multiplicamos las matrices//
			}
		}
	}
}	
int main(){
	srand(time(NULL));//Semilla//
	int matrizA[TAM][TAM];
	int matrizB[TAM][TAM];
	int matrizC[TAM][TAM];
	inicializa(matrizA);
	inicializa(matrizB);
	multiplica(matrizA,matrizB,matrizC);
	for(int i=0;i<TAM;i++){
		for(int j=0;j<TAM;j++){
			printf("%d\t",matrizC[i][j]);//Imprimimos la matriz//
		}
	printf("\n");
	}
return 0;
}
	
	
