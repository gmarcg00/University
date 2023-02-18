#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>

void manejadoraSomelier (int signal);
void manejadoraJefeSala(int signal);
int calculaAleatorios(int min, int max);
void manejadoraMozo (int signal);

int main(int argc, char* argv[]){
		int numPinches,i,estado,numeroDevuelto,pincheDevuelve,platosHechos,jefeDevuelve;
		numPinches=atoi(argv[1]);
		pid_t pid,hijoMuerto,pincheMuerto,jefeMuerto;
		struct sigaction ss={0};
		platosHechos=0;
		int *hijos=(int*)malloc(sizeof(int)*(numPinches+2));		
		printf("CHEF:Bienvenido al restaurante ABaC\n");
		sleep(1);
		printf("CHEF:Yo soy el chef del restaurante\n");
		for(i=0;i<numPinches+2;i++){
			sleep(1);
			pid=fork();
			srand(getpid());
			if(pid==0){
				if(i==0){
					printf("SOMELIER:Yo soy el somelier\n");
					ss.sa_handler=manejadoraSomelier;
					if(-1==sigaction(SIGUSR1,&ss,NULL)){
						perror("Error\n");
						return -1;
					}
					if(-1==sigaction(SIGUSR2,&ss,NULL)){
						perror("Error\n");
						return -1;
					}
					pause();//Para que no se muera
				}else if(i==1){
					sleep(1);
					printf("JEFE DE SALA:Yo soy el jefe de sala\n");
					//Jefe de sala
					ss.sa_handler=manejadoraJefeSala;
					if(-1==sigaction(SIGUSR1,&ss,NULL)){
						perror("Error\n");
						return -1;
					}
					pause();//Para que no se muera
				}else{
					//Pinches
					sleep(1);
					printf("PINCHE %d: Yo soy el pinche %d\n",i-1,i-1);
					kill(getpid(),SIGSTOP);//Dormimos a los pinches para que esta parte del codigo no se ejecute nada mas entrar
					int aleatorioPinche=calculaAleatorios(2,5);
					sleep(aleatorioPinche);
					int platoHecho=calculaAleatorios(0,1);
					if(platoHecho==0){
						//El plato se ha cocinado bien
						exit(0);
					}else{
						//El plato no se ha cocinado bien
						exit(1);
					}
				}	
				break;
			}else if(pid!=0 && pid!=-1){
					hijos[i]=pid;
			}else{
				perror("Error\n");
				return -1;
			}
		}
		//hijos[0]=Somelier
		//hijos[1]=Jefe de sala
		//hijos[2]=Pinche 1
		if(pid!=0){
			sleep(3);
			int aleatorio=calculaAleatorios(0,1);
			if(aleatorio==0){
				printf("CHEF:Somelier faltan ingredientes, mira a ver que puedes hacer\n");
				kill(hijos[0],SIGUSR1);
			}else{
				printf("CHEF:Somelier falta vino, mira a ver que puedes hacer\n");
				kill(hijos[0],SIGUSR2);
			}
		}
		hijoMuerto=wait(&estado);
		numeroDevuelto=WEXITSTATUS(estado);//Guardo el valor que devuelve el somelier al morir
			if(numeroDevuelto==1){
				for(i=0;i<numPinches+2;i++){
					kill(hijos[i],SIGKILL);
					printf("El cocinero %d ha muerto\n",i-1);
					sleep(1);
				}
				printf("El programa ha terminado\n");
				return 0;
			}else if(numeroDevuelto==2){
				printf("CHEF: Recibido, avisaré a los pinches\n");
				for(i=2;i<numPinches+2;i++){
					kill(hijos[i],SIGCONT);
					sleep(1);
					printf("CHEF:El pinche %d esta cocinando\n",i-1);
					pincheMuerto=wait(&estado);
					pincheDevuelve=WEXITSTATUS(estado);
					if(pincheDevuelve==0){
						sleep(1);
						printf("CHEF:El plato se ha cocinado bien\n");
						platosHechos++;
					}else{
						sleep(1);
						printf("CHEF:El plato no se ha cocinado bien\n");
					}	
				}
				
			}else if(numeroDevuelto==3){
				printf("CHEF:Recibido, avisaré a los pinches\n");
				for(i=2;i<numPinches+2;i++){
					kill(hijos[i],SIGCONT);
					sleep(1);
					printf("CHEF:El pinche %d esta cocinando\n",i-1);
					pincheMuerto=wait(&estado);
					pincheDevuelve=WEXITSTATUS(estado);
					if(pincheDevuelve==0){
						sleep(1);
						printf("CHEF:El plato se ha cocinado bien\n");
						platosHechos++;
					}else{
						sleep(1);
						printf("CHEF:El plato no se ha cocinado bien\n");
					}
				}	
			}
			
			if(platosHechos==0){
				sleep(1);
				printf("CHEF:El numero de platos hechos es %d\n",platosHechos);
				sleep(1);
				printf("CHEF:El restaurante ha cerrado\n");
				kill(hijos[1],SIGKILL);
				return 0;
			}else{
				printf("CHEF:El numero de platos hechos es %d\n",platosHechos);
				sleep(1);
				printf("CHEF:Avisaré al jefe de sala para que monte las mesas\n");
				kill(hijos[1],SIGUSR1);
				jefeMuerto=wait(&estado);
				jefeDevuelve=WEXITSTATUS(estado);
				if(jefeDevuelve==1){
					printf("CHEF:PUEDE ABRIRSE EL RESTAURANTE\n");
					return 0;
				}
			}
				
	return 0;
}

void manejadoraJefeSala(int signal){
	if(signal==SIGUSR1){
		sleep(3);
		printf("JEFE DE SALA:He finalizado con el montaje de las mesas\n");
		exit(1);
	}
}

void manejadoraSomelier (int signal){
	pid_t pid,hijoMuerto;
	int numeroDevuelto,estado;
	pid=fork();
	struct sigaction ss={0};
	if(pid==0){
		ss.sa_handler=manejadoraMozo;
		if(-1==sigaction(SIGPIPE,&ss,NULL)){
			perror("Error\n");
			exit(-1);
		}
		pause();//Para que no se muera
	}else if(pid!=0 && pid!=-1){
		if(signal==SIGUSR1){
			sleep(1);
			printf("SOMELIER:Oido chef, avisaré al mozo para que busque los ingredientes\n");
			sleep(1);
			kill(pid,SIGPIPE);
			hijoMuerto=wait(&estado);
			numeroDevuelto=WEXITSTATUS(estado);
			if(numeroDevuelto==1){
				sleep(1);
				printf("SOMELIER:Chef, el mozo ha encontrado los ingredientes que faltaban\n");
				exit(3);
			}else{
				sleep(1);
				printf("SOMELIER:Chef, el mozo no ha encontrado los ingredientes que faltaban\n");
				exit(2);
			}
		}else if(signal==SIGUSR2){
			sleep(1);
			printf("SOMELIER:Oido chef, avisaré al mozo para que busque el vino\n");
			sleep(1);
			kill(pid,SIGPIPE);
			hijoMuerto=wait(&estado);
			numeroDevuelto=WEXITSTATUS(estado);
			if(numeroDevuelto==1){
				sleep(1);
				printf("SOMELIER:Chef, el mozo ha encontrado el vino\n");
				exit(3);
			}else{
				sleep(1);
				printf("SOMELIER:Chef, el mozo no ha encontrado el vino\n");
				exit(1);
			}
		}
	}else{
		perror("Error\n");
		exit(-1);
	}
}

void manejadoraMozo (int signal){
	sleep(1);
	printf("MOZO:Oido Somelier\n");
	int aleatorio=calculaAleatorios(0,1);
	if(aleatorio==1){
		sleep(1);
	}else{
		sleep(1);
	}
	exit(aleatorio);	
}

int calculaAleatorios(int min, int max) {
	return rand() % (max-min+1) + min;
}