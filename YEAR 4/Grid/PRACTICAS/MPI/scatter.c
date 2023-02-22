#include <stdio.h>
#include <mpi.h>

#define N 10

int main(int argc, char* argv[]){

	int size, rank, i, from, to, ndat, part, tag, VA[N];
	MPI_Status info;
	
	MPI_Init(&argc, &argv);
	MPI_Comm_size(MPI_COMM_WORLD,&size);
	MPI_Comm_rank(MPI_COMM_WORLD,&rank);
	
	//Inicializo vector
	for (i=0; i<N; i++) {
		VA[i] = 0;
	}
	
	//Si soy maestro
	if (rank == 0){
		for (i=1; i<N; i++) {
			VA[i] = i;
		}
	}
	
	printf("Proceso %d: VA antes de recibir datos: \n",rank);
	for (i=0; i<N; i++) {
		printf("%d  ",VA[i]);
	}
	printf("\n\n");
	
	//Calculamos cuántos datos corresponden a cada proceso
	ndat = N/size;
	//Dividimos el vector en partes, y las distribuimos entre los procesos
	MPI_Scatter(&VA[0],ndat,MPI_INT,&VA[0],N,MPI_INT,0,MPI_COMM_WORLD);
	
	//Sincronizamos todos los procesos
	MPI_Barrier(MPI_COMM_WORLD);
	//cada proceso imprime su parte del vector
	printf("Proceso %d: VA después de recibir los datos: \n", rank);
	for (i=0; i<ndat; i++) {
		printf("%d  ",VA[i]);
	}
	printf("\n\n");
	
	
	MPI_Finalize();
	return 0;
}