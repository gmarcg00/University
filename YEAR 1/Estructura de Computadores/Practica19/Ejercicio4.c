#include <stdio.h>
#include <stdlib.h>

int main(){
	int aux=3;
	if(aux%2)
		printf("Creo que apruebo\n");
	printf("Bueno siempre me queda otra convocatoria\n");
	FILE *Archivo;
	Archivo=fopen("SolucionEj4.txt","w");
	char *Solucion;
	Solucion=(char*)malloc(sizeof(char)*100);
	Solucion="El programa imprime : Creo que apruebo \n Bueno siempre me queda otra convocatoria\n";
	fputs(Solucion,Archivo);
	return 0;
}