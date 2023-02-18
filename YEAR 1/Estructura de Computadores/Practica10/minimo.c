#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int calculaminimo(int *matriz, int n, int m){//Calcula el minimo de la matriz//
	int min=*matriz;
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			if(*(matriz+i*m+j)< min){
				 min=*(matriz+i*m+j);
			}
		}
	}
	return min;
}
void inicializamatriz(int *matriz, int n, int m){//Genera una matriz//
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			*(matriz+i*m+j)=random()%(10)+1;
		}
	}
}
void matriz(int *matriz, int n,int m){//Imprime la matriz generada//
	printf("\n La matriz generada es \n");
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++){
			printf("%d\t", *(matriz+i*n+j));
		}
		printf("\n");
	}
}
int main(){
	srand(time(NULL));
	int *_matriz,n,m;
	printf("Indique el número de filas\n");
	scanf("%d", &n);
	printf("Indique el número de columnas\n");
	scanf("%d", &m);
	_matriz=(int *)malloc(sizeof(int)*n*m);
	inicializamatriz(_matriz,n,m);
	matriz(_matriz,n,m);
	printf("El mínimo es %d\n", calculaminimo(_matriz,n,m));
return 0;
}


	
		
		

