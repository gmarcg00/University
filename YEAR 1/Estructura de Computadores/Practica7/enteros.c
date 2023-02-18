#include <stdio.h>
#include <math.h>
#define TAM 20
int positivos=0,ceros=0,negativos=0,i=0; //Declaración de variables//
void inicio(int a[]);
void enteros(int a[],int b[]);//Declaracion de funciones//

void inicio(int a[]){	/*Al definir la función, no hemos puesto ningún número entre los corchetes.
		Esto significa que estamos permitiendo que un array de cualquier tamaño pueda ser pasado a la función*/
		for (i=1;i<=TAM;i++){
		printf("Entero %i\n",i);
		scanf("%d",&a[i]);
		}
}
void enteros(int a[],int b[]){
	for (i=0;i<TAM;i++){
		if(a[i]>0){
			positivos= ++positivos;
		}
		else if(a[i]<0){
			negativos= ++negativos;
		}
		else{
			ceros=++ceros;
		}
	}
	b[0]=positivos;
	b[1]=negativos;
	b[2]=ceros;
}
int main(){
	int numeros[TAM],resultado[3];// Creamos 2 arrays, numeros de tamaño TAM y resultado de tamaño 3//
	inicio(numeros);// Pasamos el array numeros a la función inicio//
	enteros(numeros,resultado);
	printf("En el vector hay %d numeros positivos,%d numeros negativos y %d ceros\n", resultado[0], resultado[1], resultado[2]);
return 0;
}
	
	

	
	
	
	
	
	
	
	

