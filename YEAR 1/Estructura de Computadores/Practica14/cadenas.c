#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main(int argc, char const *argv[3]){
	char* cadena;
	cadena=(char*)malloc(sizeof(char)*100);
	char* catCadena;
	catCadena=(char*)malloc(sizeof(char)*116);
	printf("Introduce una cadena:\n");
	fgets(cadena,100,stdin);
	strtok(cadena,"\n");
	printf("La longitud de la cadena\"%s\" es %ld\n",cadena,strlen(cadena));
	catCadena=strcat(cadena,"\nLa cadena ha sido anyadida");
	printf("\n%s\n", catCadena);
	if(strstr(cadena,argv[1]) != NULL){
		printf("\n La cadena esta contenida en la introducida por el teclado\n");
	}else{
		printf("\n La cadena no esta contenida en la introducida por el teclado\n");
	}
	int posicion=atoi(argv[2]);
	printf("La letra en la posicion %d de la cadena \"%s\" es %c\n",posicion,cadena,cadena[posicion-1]);
	free(cadena);
	free(catCadena);
	return 0;
}