#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define tam 5
void inicializa(int matriz[tam][tam]){
	for(int i=0;i<tam;i++){
		for(int j=0;j<tam;j++){
			matriz[i][j]=random()%(47)+4;//Crea numeros entre 4 y 50//
		}
	}
}
int maximo(int matriz[tam][tam]){
	int maxValor=0;
	for(int i=0;i<tam;i++){
		for(int j=0;j<tam;j++){
			if(matriz[i][j]>maxValor){
				maxValor=matriz[i][j];
			}
		}
	}
return maxValor;
}
int main(){
	srand(time(NULL));
	int matriz[tam][tam];
	inicializa(matriz);
	int resultado=maximo(matriz);
	for(int i=0;i<tam;i++){
		for(int j=0;j<tam;j++){
			printf("%d, ",matriz[i][j]);
		}
		printf("\n");
	}
	
	printf("El valor máximo es %d\n",resultado);
return 0;
}
	
	

