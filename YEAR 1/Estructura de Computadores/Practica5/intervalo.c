#include <stdio.h>
int main()
{
int num1,num2,resultado,i;
resultado = 0;
	do{printf("Introduzca un número\n");
	scanf("%d",&num1);
	printf("Introduzca otro número\n");
	scanf("%d",&num2);}while(num1<num2);
		for (i=num1;i>=num2;i--){
			resultado = resultado + i;
		}
		printf("El resultado es %d \n", resultado);
return 0;
}
