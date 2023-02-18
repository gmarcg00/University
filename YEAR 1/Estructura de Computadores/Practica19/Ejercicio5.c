#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void rellenaMatriz(int *matriz,int n);
void imprimeMatriz(int *matriz,int n);
int main(int argc, char *argv[1]){
	int n=atoi(argv[1]);
	if(argc!=2){
		printf("Error, numero de argumentos incorreto\n");
		return -1;
	}
	int *matriz;
	matriz=(int*)malloc(sizeof(int)*n*n);
	srand(time(NULL));
	rellenaMatriz(matriz,n);
	imprimeMatriz(matriz,n);
	
	
	return 0;
}

void rellenaMatriz(int *matriz,int n){
	int i,j;
	for(i=0;i<n;i++){
		for(j=0;j<n;j++){
			if(i==j){
				*(matriz+i*n+j)=0;
			}else{
				*(matriz+i*n+j)=rand()%(5)+5;
			}
		}
	}
}
void imprimeMatriz(int *matriz,int n){
	int i,j;
	for(i=0;i<n;i++){
		for(j=0;j<n;j++){
			printf("%d",*(matriz+i*n+j));
		}
		printf("\n");
	}
}
			
			
			