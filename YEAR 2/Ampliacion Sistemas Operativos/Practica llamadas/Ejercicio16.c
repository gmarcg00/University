#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <fcntl.h>

int main(int argc, char *argv[]){
	
	
	int fildes=open(argv[1],O_CREAT | O_WRONLY);
	write(fildes,"hola mundo\n",sizeof("hola mundo\n"));
	close(fildes);
	
	return 0;
}
	
