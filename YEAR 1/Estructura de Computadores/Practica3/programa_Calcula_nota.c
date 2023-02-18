#include <stdio.h>
int main ()
{
	int Num;
	printf("Escribe la nota numérica:");
	scanf("%d",&Num);
	if((Num == 5) || (Num == 6)){
	printf("Aprobado");
	}
	else{
		if ((Num == 7) || (Num == 8)){
		printf("Notable");
	    }
		else{
			if ((Num == 9) || (Num == 10)){
			printf("Sobresaliente");
	    }
			else{
				printf("Suspenso");
		}
	}
}
return 0;
}

