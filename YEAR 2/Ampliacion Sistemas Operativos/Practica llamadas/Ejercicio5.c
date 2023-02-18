#include<stdio.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<stdlib.h>


int main(void){
	int pid=fork();
	if(pid==0){
		printf("[H] ppid=%5d, pid =%5d\n",getppid(),getpid());
		exit(33);
	}else{
		int estado;
		printf("[P] ppid=%5d, pid=%5d, H=%5d\n",getppid(),getpid(),pid);
		waitpid(pid,&estado,0);
		printf("El proceso %d acaba de terminar con estado %d\n",pid,WEXITSTATUS(estado));
	}
	return 0;
}
