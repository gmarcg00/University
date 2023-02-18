#include <stdio.h>
#include <math.h>
#define TAM1 10
int main()
{
int i;

	int inicializa(int a[]){
		for (i=0;i<TAM1;i++){
			printf("Elemento %i\n",(i+1));
			scanf("%d",&a[i]);
		}
		return 0;
	}
	int maximo(int a[]){
		int b = a[0];
		for (i=0;i<TAM1;i++){
			if (a[i]>b){
				b=a[i];
			}
		}
		return b;
	}
int elementos [TAM1];
		inicializa(elementos);
		printf("El resultado es %d\n",maximo(elementos));
return 0;
	}
	

