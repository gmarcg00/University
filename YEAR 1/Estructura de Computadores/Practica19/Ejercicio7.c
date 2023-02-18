#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define tam 1000
int cubos(int *vector);
int main(){
	int i;
	int *vector;
	vector=(int*)malloc(sizeof(int)*tam);
	cubos(vector);
	
	for(i=0;i<tam;i++){
		printf("%d\n",vector[i]);
	}
	printf("%d\n",cubos(vector));
	
	free(vector);
	return 0;
}
int cubos(int *vector){
	int i=0;
	int pares=0;
		do{
			if((i%2)!=0){
				vector[i]=i*i*i;
				i++;
			}
		}while(i<tam);
	for(i=0;i<tam;i++){
		if((vector[i]%2)==0){
			pares++;
		}	
	}
	return pares;
}