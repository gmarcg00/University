#include<stdio.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>


int main(void){
	int pid=fork();
	if(pid==0){
		printf("[H] ppid=%5d, pid =%5d\n",getppid(),getpid());
	}else{
		int estado;
		printf("[P] ppid=%5d, pid=%5d, H=%5d\n",getppid(),getpid(),pid);
		pid=wait(&estado);
		printf("El proceso %d acaba de terminar con estado %d\n",pid,estado);
	}
	return 0;
}
//Wait devuelve el pid del proceso que acaba de terminar y en estado devuelve el estado