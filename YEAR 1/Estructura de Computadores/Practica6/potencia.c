#include <stdio.h>
#include <math.h> //Utilizar el codigo gcc -o volumen volumen.c -lm//
int main(){
int num1,num2,total,resultado_potencia;
int potencia(int a,int b){
	total = pow(a,b);
	return total;
}
	printf("Introduzca un número(base)\n");
	scanf("%d",&num1);
	printf("Introduzca otro número(exponente)\n");
	scanf("%d",&num2);
	if (num1>0 && num2>0){
		resultado_potencia = potencia(num1,num2);
		printf("El resultado es %d\n",total);
	}
	else{
		printf("Error, los números no son positivos\n");
	}
return 0;
}