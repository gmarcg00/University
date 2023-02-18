#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define tam 5
void inicializa (int matriz[tam][tam]){
	for(int i=0;i<tam;i++){
		for(int j=0;j<tam;j++){
			matriz[i][j]=rand()%(13)+3;
		}
		printf("\n");
	}
}
int main(){
	srand(time(NULL));
	int matriz[tam][tam];
	inicializa(matriz);
	for(int i=0;i<tam;i++){
		for(int j=0;j<tam;j++){
			printf("%d, ",matriz[i][j]);
		}
	printf("\n");
	}
return 0;
}

	

