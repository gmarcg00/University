#include <stdio.h>
#include <stdlib.h>
#include <string.h>
void invierte(char *cadenaInicio, char *cadenaFinal){
	int j=strlen(cadenaInicio);
	for(int i=0;i<strlen(cadenaInicio);i++){
		*(cadenaFinal+(j-1))=*(cadenaInicio+1);
		j--;
	}
}
int main(int argc,char const *argv[]){
	char* cadenaInicio;
	cadenaInicio=(char*)malloc(sizeof(char)*100);
	char* cadenaFinal;
	cadenaFinal=(char*)malloc(sizeof(char)*100);
	printf("Introduce una cadena\n");
	fgets(cadenaInicio,100,stdin);
	printf("La cadena dada: \n%s\n",cadenaInicio);
	invierte(cadenaInicio,cadenaFinal);
	printf("La cadena inversa:\n");
	printf("%s\n",cadenaFinal);
	free(cadenaInicio);
	free(cadenaFinal);
	return 0;
}