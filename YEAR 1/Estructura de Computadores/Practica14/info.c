#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define caracter 100
#define lineas 5
void leerMatriz(char matriz[caracter][lineas],FILE *file){
	
	
}
int main(int argc, char const *argv[3]){
	FILE *file;
	char matriz[lineas][caracter];
	fopen(argv[1],"r");
	if (file==NULL){
		printf("Error al abrir el archivo\n");
		exit-1;
	}else{
		leerMatriz(matriz,file);
	}
	return 0;
}
