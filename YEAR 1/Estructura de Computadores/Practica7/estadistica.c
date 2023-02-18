#include <stdio.h>
#include <math.h>
#define TAM1 15
int main()
{
//Declaración de variables//	
int i;
int numeros [TAM1];
float resultado=0,b,resultado_media=0;
double resultado_desviacion=0;


//Funciones del programa//
	float media(int a[]){ //Comienzo de la función media//
		for (i=0;i<TAM1;i++){
			resultado_media = resultado_media + a[i];
		}
	return (resultado_media/TAM1);//La función devuelve la media//
	}	
	float varianza(int a[]){ //Comienzo de la función varianza//
		for (i=0;i<TAM1;i++){
			b = (a[i] - media(a));
			resultado = resultado + pow(b,2);
		}
	return (resultado/TAM1); //La función devuelve la varianza//
	}
	
	double desviacion_tipica(int a[]){ //Comienza la función desviación tipica//
		for (i=0;i<TAM1;i++){
			resultado_desviacion = sqrt(varianza(a));
		}
	return resultado_desviacion;
	}
//Volvemos al main//			
for(i=1;i<=TAM1;i++){
	printf("Número %i\n",i); // Nos pide que introduzcamos 15 numeros, desde el 1 hasta el 15//
	scanf("%i",&numeros[i]); // Asociamos cada numero que introducimos a cada elemento del vector numeros//
}
	varianza(numeros);
	media(numeros);
	desviacion_tipica(numeros);
	printf("El resultado de la varianza es %f\nEl resultado de la media es %f\nEl resultado de la desviación tipica es %f\n",varianza(numeros),media(numeros),desviacion_tipica(numeros));
	
	return 0;
}
