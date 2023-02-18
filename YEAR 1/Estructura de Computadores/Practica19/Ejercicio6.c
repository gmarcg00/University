#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define tam 100
void quita_espacios(char *cad, char *cadsin);
int main(){
	char *cad;
	cad=(char*)malloc(sizeof(char)*tam);
	char *cadsin;
	cadsin=(char*)malloc(sizeof(char)*tam);
	printf("Introduzca una cadena de como maximo 100 caracteres\n");
	gets(cad);
	quita_espacios(cad,cadsin);
	printf("%s",cadsin);
	free(cad);
	free(cadsin);
	return 0;
}
void quita_espacios(char *cad, char *cadsin){
	int i,j=0;
	int longitud=strlen(cad);
	for(i=0;i<longitud;i++){
		if(cad[i]!=' '){
			cadsin[j]=cad[i];
			j++;
		}
	}
}
