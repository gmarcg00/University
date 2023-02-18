#include <stdio.h>
int main()
{
int num,i,Total;
i = 1;
Total = 1;
printf("Escriba un número\n");
scanf("%d",&num);
printf("El numero es %d\n",num);
for(i=num;i>1;i--){
		Total = i * Total;
        }
	printf("El resultado es %d\n",Total);
return 0;
}
