#include<stdio.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<stdlib.h>

int main(void){
	printf("Soy el proceso %d \n",getpid());
	int retorno;
	retorno=execl("/usr/bin/cal", "cal", (char *) 0);
	printf("Soy el proceso %d \n",getpid());
}
	
