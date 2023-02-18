#include <stdio.h>
#include <stdlib.h>
int main(){
	struct Coche{
		float precio;
		char modelo[100];
		int anyo_matriculacion;
	};
	struct Coche *c;
	int i,num_coches=5;
	c=(struct Coche *)malloc(num_coches*sizeof(struct Coche));
	for(i=0;i<num_coches;i++){
		printf("Introduzca el modelo del coche %d\n",i+1);
		while(getchar()!='\n');
		fgets((c+i)->modelo,100,stdin);
		printf("Introduzca el precio del coche %d\n",i+1);
		scanf("%f",&(c+i)->precio);
		printf("Introduzca el año de matriculacion del coche %d\n",i+1);
		scanf("%d",&(c+i)->anyo_matriculacion);
	}
return 0;
}
