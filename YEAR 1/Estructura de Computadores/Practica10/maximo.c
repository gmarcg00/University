#include <stdlib.h>
#include <stdio.h>
int calcularmaximo(int *vector, int n);//Declaraciòn del vector//
int calcularmaximo(int *vector, int n){
	int max=0;
	for(int i=0;i<n;i++){
		if(*(vector+i)>max){
			max=*(vector+i);
		}
	}
return max;	
}
int main(){
	int *numeros,n;
	printf("Introduzca una cantidad de numeros\n");
	scanf("%d",&n);
	numeros=(int *) malloc (n*sizeof(int));
	for(int i=0;i<n;i++){
		printf("Introduce un número\n");
		scanf("%d",&*(numeros+i));
	}
	printf(" El máximo es %d\n", calcularmaximo(numeros, n));
	return 0;
}
