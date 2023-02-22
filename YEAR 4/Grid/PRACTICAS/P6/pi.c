#include <omp.h>
#include <stdio.h>
#include <math.h>
double funcion(double a);

int main(int argc, char *argv[]){
	long n, i;
	double PI25DT = 3.141592653589793238462643; 
	double h, sum,x;
	n=1000000000; //Número de intervalos
	h= 1.0 / (double) n;
	sum=0.0; //Suma de intervalos
	
	#pragma omp parallel for reduction(+:sum)
	
	
	for(i=1;i<=n;i++){
		x=h*((double)i - 0.5);
		sum+=funcion(x);
	}
	sum=h*sum;
	printf("\n Pi es aproximadamente %.16f.El error cometido es %.16f\n", sum, fabs(sum-PI25DT));
	return 0;
}

double funcion(double a){
	return (4.0/(1.0 + a*a));
}