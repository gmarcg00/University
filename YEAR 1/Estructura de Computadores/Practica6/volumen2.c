#include <stdio.h>
#include <math.h> //Utilizar el codigo gcc -o volumen volumen.c -lm//
#define pi 3.1416
int main(){ 
int Volumen;
double radio,altura,lado1,lado2;
double resultadocono;
double resultadoorto;
double volumencono(double a,double b){
	double total =(pow(a,2) * pi * b)/3;
	return total; //Cuando llamo a la función volumencono  me devuelve el calcullo del total//
				      }
double volumenorto(double a,double b, double altura){
	double total =(a * b * altura);
	return total;
				     }	
	do{
		printf("¿Qué volumen quieres calcular?\n");
		printf("Escriba '1' para el volumen de un cono\n");
		printf("Escriba '2' para el volumen de un ortodedro\n");
		printf("Escriba '3' para salir del programa\n");
		scanf("%d",&Volumen);
		if (Volumen!=1 && Volumen!=2 && Volumen!=3){
			printf("Error\n");
		}
	}while(Volumen!=1 && Volumen!=2 && Volumen!=3);
	switch(Volumen){
		case 1:
			printf("Volumen de un Cono\n");
			printf("Indique el radio de la base\n");
			scanf("%lf",&radio);
			printf("Indique la altura del cono\n");
			scanf("%lf",&altura);
			resultadocono = volumencono(radio,altura);
			printf("El volumen del cono es %lf\n",resultadocono);
			break;
		case 2:
			printf("Volumen de un ortoedro\n");
			printf("Indique el valor del lado1(base)\n");
			scanf("%lf",&lado1);
			printf("Indique el valor del lado2(altura)\n");
			scanf("%lf",&lado2);
			resultadoorto = volumencono(lado1,lado2);
			printf("El volumen del ortoedro es %lf\n",resultadoorto);
			break;
		case 3:
			return 0;
			break;
			}
return 0;
}

