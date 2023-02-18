#include <stdio.h>
int main ()
{
	int Base,Exp,Pot,i;
	printf("Escribe un número (base):");
	scanf("%d",&Base);
	printf("Escribe un número (exponente):");
	scanf("%d",&Exp);
	if (( Base >= 1) && (Exp >= 1)){
		Pot = 1;
		for (i=1;Exp >= 1;Exp = Exp - 1){
			Pot = Pot * Base;
		}
		printf("La potencia es:%d",Pot);
	}
	else{
		printf("La base y el exponente han de ser mayores o iguales a 1");
	}
return 0;
}

