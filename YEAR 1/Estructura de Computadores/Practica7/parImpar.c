#include <stdio.h>
#include <math.h>
#define TAM 15
//Declaración de funciones//
void parImpar(int a[],int b[]);
void inicio(int a[]);
//Declaracion de variables//
int i,Pares=0,Impares=0,num=0;
void inicio(int a[]){
	for(i=1;i<TAM;i++){
		printf("Elemento %i\n",i);
		scanf("%d",&a[i]);
	}

}
void parImpar(int a[],int b[]){
	for(i=0;i<TAM;i++){
		if(a[i]%2 == 0){
			Pares=++Pares;
		}
		else{
			Impares=++Impares;
		}
	}
	b[0]=Pares;
	b[1]=Impares;
}
int main(){
	int numeros[TAM],resultado[2];
	inicio(numeros);//Pasamos el array numeros a la función inicio//
	parImpar(numeros,resultado);
	printf("El array contiene %d numeros pares y %d	numeros impares\n",resultado[0],resultado[1]);
return 0;
}
	
	
	