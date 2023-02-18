#include <stdio.h>
int main()
{
int horas,minutos,segundos,dias,segundostotales;
printf("Introduzca una cantidad de segundos\n");
scanf("%d", &segundostotales);
if (segundostotales >= 0) {
	dias = segundostotales/86400;
	horas = (segundostotales%86400)/3600;
	minutos = (segundostotales%86400)%3600/60;
	segundos = (segundostotales%86400)%3600%60;
	printf("Los segundos introducidos son %d dias, %d horas, %d minutos y %d segundos\n",dias,horas,minutos,segundos);
			}
else{
	printf("Introduzca una cantidad mayor o igual que 0");
}
return 0;
}
