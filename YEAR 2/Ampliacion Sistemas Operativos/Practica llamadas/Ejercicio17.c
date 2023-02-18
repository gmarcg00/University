#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>



int main(int argc, char *argv[]){
	
	char buffer[2];
	
	int fildes=open(argv[1],O_RDONLY);
	int fildes2=open(argv[2],O_CREAT | O_WRONLY|O_TRUNC);
	do{
		read(fildes,buffer,sizeof(buffer));
		write(fildes2,buffer,sizeof(buffer));
	}while(read(fildes,buffer,sizeof(buffer))==2);
	
	close(fildes);
	close(fildes2);
	
	
	return 0;
}
