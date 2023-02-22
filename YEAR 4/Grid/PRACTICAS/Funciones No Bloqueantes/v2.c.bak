#include <stdio.h>
#include <mpi.h>


int main(int argc, char* argv[]){
	int size, rank,rankLeft ,i, from, to, ndat, part, tag,next,previous;
	MPI_Status info;
	MPI_Request request, requestReceptor;
	
	MPI_Init(&argc, &argv);
	MPI_Comm_size(MPI_COMM_WORLD,&size);
	MPI_Comm_rank(MPI_COMM_WORLD,&rank);
	
	next=(rank+1)%size;
	previous=(rank-1+size)%size;
	

	MPI_Isend(&rank,1,MPI_INT,next,0,MPI_COMM_WORLD,&request);
	MPI_Irecv(&rankLeft,1,MPI_INT,previous,0,MPI_COMM_WORLD,&requestReceptor);

	MPI_Wait(&requestReceptor,&info);
	
	printf("Soy el proceso %d ,a mi izquierda tengo al proceo %d y a mi derecha tengo al proceso %d \n",rank,previous,next);
	
	
	MPI_Wait(&request,&info);
	MPI_Finalize();
	return 0;
	
	
	
	
}