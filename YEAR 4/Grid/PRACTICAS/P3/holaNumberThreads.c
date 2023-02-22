#include <stdio.h>
#include <omp.h>

int main(){
	int nthreads, tid, master=0;
	printf("Trabajamos con 4 threads\n");
	//.......Set number of threads to 4
	omp_set_num_threads(4);
	//.......Obtain the number of threads
	nthreads=omp_get_num_threads();
	printf("Número de threads en ejecución = %d\n", nthreads);
	#pragma omp parallel private(tid) //Región paralela. Cada 
	{	//thread contiene sus propias copias de variables
		//........Get the id of the thread
		tid=omp_get_thread_num();
		printf("Hola desde el thread = %d\n",tid);
		if (omp_get_thread_num()==master){ //If I am the master
			//.........Obtain the number of threads
			nthreads=omp_get_num_threads();
			printf("Número de threads = %d\n", nthreads);
		}
	}
	//Todos los threads se unen al maestro y finalizan
	printf("Trabajamos ahora con 3 threads\n");
	//.......Set number of threads to 3
	omp_set_num_threads(3);
	//.......Obtain the number of threads
	nthreads=omp_get_num_threads();
	printf("Número de threads en ejecución = %d\n",nthreads);
	#pragma omp parallel private(tid)
	{
		//........Get the id of the thread
		tid=omp_get_thread_num();
		printf("Hola desde el thread = %d\n",tid);
		if (omp_get_thread_num()==master){ //If I am the master
			//......Obtain the number of threads
			nthreads=omp_get_num_threads();
			printf("Número de threads = %d\n", nthreads);
		}
	}
}