#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main(){
	int aux=3;
	if(aux%2)
		printf("Creo que apruebo\n");
	else
		printf("Bueno siempre me queda otra convocatoria\n");
	FILE *Archivo;
	Archivo=fopen("SolucionEj3.txt","w");
	char *solucion=(char*)malloc(sizeof(char)*50);
	solucion="El programa imprime: Creo que apruebo ";
	fputs(solucion,Archivo);
	fclose(Archivo);
	return 0;
}