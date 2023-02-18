#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define tamano 50
void inicializa (int vector[tamano],int tam);
void imprimir (int vector[tamano]);

int main(){
	srand(time(NULL));
	int vector[tamano];
	int tam=tamano;
	inicializa(vector,tam);
	imprimir(vector);
	return 0;	
}
void inicializa(int vector[tamano],int tam){
	int i=0,aleatorio,prueba=1;

	for(i=0;i<tamano;i++){
		do{
			prueba=1;
			aleatorio=rand()%(50)+1;
			int j;
			for(j=0;j<tamano;j++){
				if(vector[j]==aleatorio){
					prueba=0;
				}
			}
		}while(prueba!=1);
		vector[i]=aleatorio;		
	}
}
void imprimir(int vector[tamano]){
	int j;
	for(j=0;j<tamano;j++){
		printf("%d   ",vector[j]);
	}
}
	