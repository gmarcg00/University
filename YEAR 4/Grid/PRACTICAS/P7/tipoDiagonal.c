#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <mpi.h>
#include <unistd.h>
#define n 5


void rellenaMatriz(int matriz[n][n]);

int main(int argc, char* argv[]){
	int size,rank;
	int matriz [n][n];
	int diagonal[n];
	
	MPI_Comm new_comm;
	MPI_Datatype ptype;
	MPI_Status info;
	
	MPI_Init(&argc, &argv);
	MPI_Comm_size(MPI_COMM_WORLD,&size);
	MPI_Comm_rank(MPI_COMM_WORLD,&rank);
	
	if(rank==0){
		rellenaMatriz(matriz);
		MPI_Type_vector(n,1,n+1,MPI_INT,&ptype);
		MPI_Type_commit(&ptype);
		MPI_Send(&(matriz[0][0]),1,ptype,1,0,MPI_COMM_WORLD);
		
	}else{
	
		MPI_Recv(diagonal,n,MPI_INT,0,0,MPI_COMM_WORLD,&info);
		for(int i=0;i<n;i++){
			printf("%d, ",diagonal[i]);
		}
	}
	
	
	

}

void rellenaMatriz(int matriz[n][n]){
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			if(i==j){
				matriz[i][j]=i*i;
			}else{
				matriz[i][j]=rand()%(-n*n);
			}
		}
	}
	
}