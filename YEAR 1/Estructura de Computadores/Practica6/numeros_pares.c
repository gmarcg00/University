#include <stdio.h>
int main(){
int num1,num2,i,resultado_pares,total;
int numeros_pares(int a,int b){
	for (i=num2;i>=num1;i--){
			total = i%2;
		if (total == 0){
			printf("El/Los números pares son el %d\n",i);
		}
	} 
	return total;
}
	printf("Introduzca un número\n");
	scanf("%d",&num1);
	printf("Introduzca otro número\n");
	scanf("%d",&num2);
	if (num1 < num2){
	resultado_pares = numeros_pares(num1,num2);
	}
	else{
			printf("Error\n");
			return 0;
		}
return 0;
}