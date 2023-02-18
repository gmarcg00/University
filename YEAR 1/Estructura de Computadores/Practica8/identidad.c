#include <stdio.h>
#define tamano_filas 9
#define tamano_columnas 9
//Declaración de funciones//
void inicializa(int matriz[tamano_filas][tamano_columnas]);
//Declaración de variables//
int j,i;
int main(){	
	int resultado_matriz[tamano_filas][tamano_columnas];//Crea la matriz//
	inicializa(resultado_matriz); //Pasamos el array resultado_matriz a la funcion inicializa//
	for(i=0;i<tamano_filas;i++){
		for(j=0;j<tamano_columnas;j++){
			printf("%d", resultado_matriz[i][j]);
		}
		printf("\n");
	}
	
return 0;
}
void inicializa(int matriz[tamano_filas][tamano_columnas]){
	for(i=0;i<tamano_filas;i++){
		for(j=0;j<tamano_columnas;j++){
			if(i==j){
				matriz[i][j]=1;
			}
			else{
				matriz[i][j]=0;
			}
		}
	}
}
