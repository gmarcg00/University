#include <stdio.h>
#include <mpi.h>

#define N 10

int main(int argc, char* argv[]){

	int size, rank, i, from, to, ndat, tag, VA[N];
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
		to = 1; tag = 0;
		MPI_Send(&VA[0],N,MPI_INT,to,tag,MPI_COMM_WORLD);
	}
	//si soy esclavo 1...
	else if (rank == 1){
		printf("Proceso %d: VA antes de recibir datos: \n",rank);
		for (i=0; i<N; i++) {
			printf("%d  ",VA[i]);
		}
		printf("\n\n");
		from = 0; tag = 0;
		MPI_Recv(&VA[0],N,MPI_INT,from,tag,MPI_COMM_WORLD,&info);
		MPI_Get_count(&info,MPI_INT,&ndat);
		printf("Proceso 1 recibe VA de %d: tag %d, ndat %d; \nVA = ", info.MPI_SOURCE, info.MPI_TAG, ndat);
		for (i=0; i<ndat; i++) {
			printf("%d  ",VA[i]);
		}
		printf("\n\n");
	}

	MPI_Finalize();
	return 0;
}