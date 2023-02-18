#include <stdio.h>
#include <stdlib.h>
void Fibonacci(int *vector,int tam);
void imprimeFibonacci(int *vector,int tam);
int elementosVector(int *vector,int tam);
int main(){
	int tam,i;
	printf("Indique el numero de elementos del vector\n");
	scanf("%d",&tam);
	int *vector;
	vector=(int*)malloc(sizeof(int)*tam);
	Fibonacci(vector,tam);
	imprimeFibonacci(vector,tam);
	printf("El numero de elementos pares es: %d \n",elementosVector(vector,tam));
	free(vector);
	free(par);
	return 0;
}

void Fibonacci(int *vector,int tam){
		int i;
		vector[0]=1;
		vector[1]=1;
		for(i=2;i<tam;i++){
			vector[i]=vector[i-1]+vector[i-2];
		}
		
}
void imprimeFibonacci(int *vector,int tam){
	int i;
	for(i=0;i<tam;i++){
		printf("%d\n",vector[i]);
	}	
}
int elementosVector(int *vector,int tam){
	int i,elementos=0;
	for(i=0;i<tam;i++){
		if((vector[i]%2)==0){
			elementos++;
		}
	}
	return elementos;
}
		
	