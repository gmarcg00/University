##include<stdio.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<stdlib.h>

int main(void){
	int pid;
	int i;
	int array[5];
	for(i=0;i<5;i++){
		pid=fork();
		if(pid==0){
			switch(i){
				case 0:
					execl("/usr/bin/xeyes","xeyes",(char *)0;
					break;
				case 1:
					execl("/usr/bin/xlogo","xlogo",(char *)0;
					break;
				case 2:
					execl("/usr/bin/xload","xload",(char *)0;
					break;
				case 3:
					execl("/usr/bin/xcalc","xcalc",(char *)0;
					break;
				case 4:
					execl("/usr/bin/xclock","xclock",(char *)0;
					break;
			}		
		}else{
			hijos[i]=pid
		
	}
	
	
}
