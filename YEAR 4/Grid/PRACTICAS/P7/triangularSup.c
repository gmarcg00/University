#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <mpi.h>
#include <unistd.h>

void rellenaMatriz(int *matriz,int n);
void rellenaBlockLens(int blockLens[],int n);
void rellenaDisplacements(int displacements[],int n);
int calculaMitadSuperior(int n);

int main(int argc, char* argv[]){
	
	int rank,size,n=5,blocklens[n],displacements[n],resultado[calculaMitadSuperior(n)];
	int* matriz=(int*)malloc(sizeof(int)*n*n);
	MPI_Comm new_comm;
	MPI_Datatype ptype;
	MPI_Status info;
	
	MPI_Init(&argc, &argv);
	MPI_Comm_size(MPI_COMM_WORLD,&size);
	MPI_Comm_rank(MPI_COMM_WORLD,&rank);
	
	
	srand(getpid());
	rellenaMatriz(matriz,n);
	rellenaBlockLens(blocklens,n);
	rellenaDisplacements(displacements,n);
	
	
	MPI_Type_indexed(n,blocklens,displacements, MPI_INT,&ptype);
	MPI_Type_commit(&ptype);
	
	if(rank==1){
		MPI_Send(&matriz, 1, ptype, rank+1, 0, MPI_COMM_WORLD); 
	}else if(rank==2){
		MPI_Recv(resultado,calculaMitadSuperior(n), MPI_INT, 0, 0, MPI_COMM_WORLD, &info);
			for(int i=0;i<n;i++){
				printf("%d \n",resultado[i]);
			}
	}
	
	
	
	MPI_Finalize();
	return 0;



}


void rellenaMatriz(int *matriz,int n){
	for(int i=0;i<n*n;i++){
		*(matriz+i)=rand()%(-n*n);
	}
	
}

void rellenaBlockLens(int blockLens[],int n){
	for(int i=0;i<n;i++){
		blockLens[i]=n-i;
	}
	
	
}

void rellenaDisplacements(int displacements[],int n){
	for(int i=0;i<n;i++){
		displacements[i]=n*i+i;
	}
}

int calculaMitadSuperior(int n){
	int num;
	for(int i=0;i<n;i++){
		num+=n-i;
	}
	
	return num;
	
}