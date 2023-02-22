#include <stdio.h>
#include <mpi.h>


int main(int argc, char* argv[]){
	
	int size, rank,rankLeft ,i, from, to, ndat, part, tag, VA[N],next,previous;
	MPI_Status info;
	
	MPI_Init(&argc, &argv);
	MPI_Comm_size(MPI_COMM_WORLD,&size);
	MPI_Comm_rank(MPI_COMM_WORLD,&rank);
	
	next=(rank+1)%size;
	previous=(rank-1+size)%size;
	
	if(rank==0){
		MPI_Send(&rank,ndat,MPI_INT,next,0,MPI_COMM_WORLD);
		MPI_Recv(&rankLeft,N,MPI_INT,previous,0,MPI_COMM_WORLD,&info);
	}else{
		MPI_Recv(&rankLeft,N,MPI_INT,previous,0,MPI_COMM_WORLD,&info);
		MPI_Send(&rank,ndat,MPI_INT,next,0,MPI_COMM_WORLD);
	}
	
	
	printf("Soy el proceso %d ,a mi izquierda tengo al proceo %d y a mi derecha tengo al proceso %d \n",rank,previous,next);
	

	MPI_Finalize();
	return 0;
}