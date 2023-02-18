#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void inicializa(int *matriz, int n, int m){//Genera una matriz aleatoria//
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			*(matriz+i*m+j)=random()%(21)-10;
		}
	}
}
void matriz(int *matriz, int n, int m){//Imprime la matriz generada aleatoriamente//
	printf("La matriz generada es \n");
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			printf("%d\t", *(matriz+i*m+j));
		}
		printf("\n");
	}
}
void calculaMinimo(int *matriz,int *minimo, int n, int m){//Calcula el minimo de cada fila//
	for(int j=0;j<n;j++){
		*(minimo + j)=*(matriz+j*m);
		for(int i=0;i<m;i++){
			if(*(minimo +j)>*(matriz+j*m+i)){
				*(minimo+j)=*(matriz+j*m+i);
			}
		}
	}
}
void minimos(int *matriz, int n){//Imprime el minimo de cada fila//
	printf("El valor mínimo de cada fila es \n");
	for(int j=0;j<n;j++){
		printf("%d\t", *(matriz+j));
	}
	printf("\n");
}
int main(){
	srand(time(NULL));
	int *_matriz,*minimo,n,m;
	printf("Indique el número de filas\n");
	scanf("%d",&n);
	printf("Indique el número de columnas\n");
	scanf("%d",&m);
	_matriz=(int *)malloc(sizeof(int)*n*m);
	minimo=(int *)malloc(sizeof(int)*n);
	inicializa(_matriz,n,m);
	calculaMinimo(_matriz,minimo,n,m);
	matriz(_matriz,n,m);
	minimos(minimo,n);
return 0;
}
	
	
	
	
		