#include <stdio.h>

int main ()
{
	int Num, Total, i;
	printf("Escribe cuantos números quieres usar");
	scanf("%d", &Num);
	Total = 0;
	i = 1;
	if (Num >=1) {
	while(i <= Num){
	Total = Total + i;
	i = i +1;
	}
	printf("La suma es %d \n", Total);
	}
	else{
	printf("El número ha de ser mayor o igual a 1");
	}
return 0;
}
