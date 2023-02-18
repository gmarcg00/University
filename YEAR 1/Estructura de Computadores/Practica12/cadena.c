#include <stdio.h>
#include <string.h>
#include <stdlib.h>
void sinespacios(char *cadena, char *cadenaout){
	int j=0;
	for(int i=0;i< strlen(cadena);i++){
		if(cadena[i] !=' ' && cadena[i]!='\n'){
			cadenaout[j]=(char)cadena[i];
			j++;
		}
	}
}
int main(int argc,char const *argv[]){
	int longitud;
	printf("Indique la longitud de la cadena\n");
	scanf("%d",&longitud);
	char* cadena;
	cadena=(char*)malloc(sizeof(char)*longitud);
	char* cadenaout;
	cadenaout=(char*)malloc(sizeof(char)*longitud);
	printf("Introduce una cadena\n");
	fgets(cadena,100,stdin);
	fgets(cadena,longitud,stdin);
	sinespacios(cadena,cadenaout);
	printf("%s\n",cadenaout);
	free(cadena);
	free(cadenaout);
	return 0;
}
