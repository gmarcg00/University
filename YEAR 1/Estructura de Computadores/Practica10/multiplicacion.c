#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void inicializa(int *matriz, int n, int m){//Genera una matriz aleatoria//
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			*(matriz+i*m+j)=random()%(10)+1;
		}
	}
}
void inicializa2(int *matriz,int n, int m){
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			*(matriz+i*m+j)=0;
		}
	}
}
void matriz(int *matriz, int n, int m){//Imprime la matriz generada anteriormente//
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			printf("%d\t",*(matriz+i*n+j));
		}
		printf("\n");
	}
}
void multiplicacion( int *matrizA, int *matrizB, int *matrizC, int n, int m){
	int tamano=n;
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			for(int k=0;k<tamano;k++){
				*(matrizC+i*tamano+j)=*(matrizC+i*tamano+j) +*(matrizA+i*n+k)**(matrizB+k*m+j);
			}
		}
	}
}
int main(){
	srand(time(NULL));
	int *_matrizA,*_matrizB,*_matrizC,n,m;
	printf("Indique el tamaño de la matriz\n");
	scanf("%d",&n);
	m=n;
	_matrizA=(int *)malloc(sizeof(int)*n*m);
	_matrizB=(int *)malloc(sizeof(int)*n*m);
	_matrizC=(int *)malloc(sizeof(int)*n*m);
	inicializa(_matrizA,n,m);
	inicializa(_matrizB,n,m);
	inicializa2(_matrizC,n,m);
	multiplicacion(_matrizA,_matrizB,_matrizC,n,m);
	printf("La matriz A es \n");
	matriz(_matrizA,n,m);
	printf("La matriz B es \n");
	matriz(_matrizB,n,m);
	printf("La matriz C es \n");
	matriz(_matrizC,n,m);
return 0;
}
	

	
