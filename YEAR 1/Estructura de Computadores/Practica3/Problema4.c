#include <stdio.h>
int main()
{
//Este programa lee un número y escribe el mes que le corresponde imprimiendo su nombre por pantalla

//Declaro las variables de mi función

int numero;

printf("Escribe un número\n");

scanf("%d",&numero);

//Le pedimos al usuario que introduzca un número

switch(numero){
	case 1:
		printf("Enero\n");
break;
	case 2:
		printf("Febrero\n");
break;
	case 3:
		printf("Marzo\n");
break;
	case 4:
		printf("Abril\n");
break;
	case 5:
		printf("Mayo\n");
break;
	case 6:
		printf("Junio\n");
break;
	case 7:
		printf("Julio\n");
break;
	case 8:
		printf("Agosto\n");
break;
	case 9:
		printf("Septiembre\n");
break;
	case 10:
		printf("Octubre\n");
break;
	case 11:
		printf("Noviembre\n");
break;
	case 12:
		printf("Diciembre\n");
break;
	default:
		printf("Mes no válido\n");
return 0;
}
}
	
