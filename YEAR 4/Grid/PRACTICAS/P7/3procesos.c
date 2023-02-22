#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <mpi.h>
#include <unistd.h>
#define n 40


int main(int argc, char* argv[]){
	int size,rank;
	int diagonal[n/2];
	
	
	MPI_Comm new_comm;
	MPI_Datatype ptype;
	MPI_Status info;
	
	MPI_Init(&argc, &argv);
	MPI_Comm_size(MPI_COMM_WORLD,&size);
	MPI_Comm_rank(MPI_COMM_WORLD,&rank);
	
	if(rank==0){
		int *vector=(int*)malloc(sizeof(int)*n);
		
		for(int i=0;i<n;i++){
			*(vector+i)=i;
		}
		
		MPI_Type_vector(n/2,1,2,MPI_INT,&ptype);
		MPI_Type_commit(&ptype);
		MPI_Send(&vector[0],1,ptype,1,0,MPI_COMM_WORLD);
		MPI_Send(&vector[1],2,ptype,2,0,MPI_COMM_WORLD);
		
	}else{
		MPI_Recv(diagonal,n/2,MPI_INT,0,0,MPI_COMM_WORLD,&info);
		int suma=0;
		
		for(int i=0;i<n;i++){
			suma+=diagonal[i];
		}
		double resultado=suma/n;
		
		printf("%f \n",resultado);
		
		
	}
	
	
	
	
	
	
	
	
}