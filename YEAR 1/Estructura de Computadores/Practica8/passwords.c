#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define TAM1 26
#define TAM2 8
//Declaración de funciones//
void inicio(char a[]);//Alamacena en un array de 26 espacios las 26 letras//
void numeros(int a[]);//Genera numeros aleatorios//
//Declaracion de variables//
int i,letra_ascii=97,num_aleatorio;
void inicio(char a[]){
	for(i=0;i<TAM1;i++){
		a[i]=(char)letra_ascii;//Paso de int a char la variable letra_ascii//
		letra_ascii++;// Le sumo 1 a la variable letra_ascii//
	}
}
void numeros(int a[]){
	srand(time(NULL));//Genera una semilla que no sea constante//
	for(i=0;i<TAM2;i++){
		a[i]=num_aleatorio=rand()%(25+1);//Guarda un numero aleatorio en cada elemento del vector//
		
	}
}
int main(){
	int posicion_nueve=TAM2+1;
	char letras[TAM1],pasword[posicion_nueve];//Creamos los arrays letras y pasword//
	int aleatorio[TAM2];//Creamos el array aleatorio de tamaño TAM2//
	inicio(letras);//Pasamos el array letras a la función inicio//
	numeros(aleatorio);//Pasamos el array aleatorio a la funcion numeros//
	for(i=0;i<TAM2;i++){
		pasword[i]=letras[aleatorio[i]];/*La posición i del array pasword es igual
		a la posicion del array letra ocupada por la posicion i del array aleatorio*/
	}
	printf("Su contraseña es %s",pasword);
return 0;
}
		
		
	
	


