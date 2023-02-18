#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>

//DECLARACION DE FUNCIONES

void funcionMedico(int signal);
void funcionFarmaceutico(int signal);
void funcionPacientes(int signal);
int calculaAleatorios(int min, int max);


int main(int argc, char* argv[] ){
	
	//COMPROBACION DE NUMERO DE  ARGUMENTOS
	
	if(argc!=2){
		printf("Numero de argumentos incorrecto\n");
		return 0;
	}
	
	//DECLARACION DE VARIABLES
	
	int numPacientes,i,estado,hijoMuerto,hijoDevuelve,vacunados,pacienteMuerto,pacienteDevuelve;
	numPacientes=atoi(argv[1]);
	pid_t pid, pidPacientes;
	vacunados=0;
	struct sigaction ss={0};
	int *hijosEpidemiologo=(int*)malloc(sizeof(int)*(2));
	int *pacientes=(int*)malloc(sizeof(int)*(numPacientes));
	int cont=0;
	
	printf("EPIDEMIOLOGO:Bienvenido al centro de pruebas experimentales de COVID \n");
	sleep(1);
	printf("EPIDEMIOLOGO:Yo soy el epidemiologo del centro de pruebas y mi identificador es %d \n",getpid());
	
	//CREACION DE PROCESOS
	
	for(i=0;i<2;i++){
		sleep(1);
		pid=fork();
		if(pid==0){
			if(i==0){
				//CODIGO DEL MEDICO
				sleep(1);
				printf("MEDICO:Hola, soy el medico encargado del centro de pruebas y mi identificador es %d \n",getpid());
				pid_t pidPacientes;
				
				//ARMAMOS LA SEÑAL SIGUSR2
				ss.sa_handler=funcionMedico;
				if(-1==sigaction(SIGUSR2,&ss,NULL)){
					perror("Error\n");
					return -1;	
				}
				
				//DORMIMOS EL PROCESO A LA ESPERA DE QUE RECIBA UNA SEÑAL Y ESTE DESPIERTE
				pause();
				
				//CREACION DE LOS PACIENTES
				for(int j=0;j<numPacientes;j++){
					sleep(1);
					pidPacientes=fork();
					if(pidPacientes==0){
						//CODIGO DE LOS PACIENTES
						sleep(1);
						printf("PACIENTE %d:Hola a todos, soy el paciente %d y mi identificador es %d\n",j+1,j+1,getpid());
						//ARMAMOS LA SEÑAL SIGUSR1
						ss.sa_handler=funcionPacientes;
						if(-1==sigaction(SIGUSR1,&ss,NULL)){
							perror("Error\n");
							return -1;	
						}
						//DORMIMOS EL PROCESO A LA ESPERA DE QUE RECIBA UNA SEÑAL Y ESTE DESPIERTE
						pause();
						sleep(1);
						printf("PACIENTE %d: Estoy listo para vacunarme \n",j+1);
						sleep(1);
						exit(calculaAleatorios(1,10));
					}else if(pidPacientes!=0 && pidPacientes!=-1){
						//GUARDO EL PID DE CADA PACIENTE
						pacientes[j]=pidPacientes;
					}else{
						perror("Error en la llamada a fork()\n");
					}
				}
				//MANDAMOS LAS SEÑALES A LOS PACIENTES
				while(cont<numPacientes){
					kill(*(pacientes+cont),SIGUSR1);
					pacienteMuerto=wait(&estado);
					pacienteDevuelve=WEXITSTATUS(estado);
					if(pacienteDevuelve%2==0){
						vacunados++;
						printf("MEDICO:El paciente %d ha generado anticuerpos contra la enfermedad\n",cont+1);
						cont++;
					}else{
						printf("MEDICO:El paciente %d no ha generado anticuerpos contra la enfermedad\n",cont+1);
						cont++;
					}
				}
				//MUERE DEVOLCIENDO EL NUMERO DE VACUNADOS
				exit(vacunados);
			}else if(i==1){
				//CODIGO DEL FARMACEUTICO
				sleep(1);
				printf("FARMACEUTICO:Hola, soy el farmaceutico encargado del centro de pruebas y mi identificador es %d \n",getpid());
				//ARMAMOS LA SEÑAL SIGUSR1
				ss.sa_handler=funcionFarmaceutico;
				if(-1==sigaction(SIGUSR1,&ss,NULL)){
					perror("Error\n");
					return -1;	
				}
				//DORMIMOS EL PROCESO A LA ESPERA DE QUE RECIBA UNA SEÑAL Y ESTE DESPIERTE
				pause();
			}
		}else if(pid!=0 && pid!=-1){
			//GUARDO EL PID DEL MEDICO Y DEL FARMACEUTICO
			hijosEpidemiologo[i]=pid;
			
		}else{
			//ERROR EN LA LLAMADA A FORK
			perror("Error en la llamada a fork()\n");
		}
	}
	if(pid!=0){
		sleep(2);
		printf("EPIDEMIOLOGO:Farmaceutico, ¿tenemos dosis para todos?\n");
		kill(hijosEpidemiologo[1],SIGUSR1);
		//EL EPIDEMIOLOGO ESPERA A QUE MUERA EL FARMACEUTICO
		hijoMuerto=wait(&estado);
		//ALMACENA EL VALOR QUE DEVUELVE EL FARMACEUTICO AL MORIR
		hijoDevuelve=WEXITSTATUS(estado);
		if(hijoDevuelve==0){
			sleep(2);
			printf("EPIDEMILOGO:Lamento comunicarles que el fin del mundo ha llegado\n");
			sleep(2);
			
			//MATAMOS AL MEDICO
			kill(hijosEpidemiologo[0],SIGKILL);
			printf("EPIDEMIOLOGO:El medico ha decidido no continuar con este sufrimiento \n");
			sleep(2);
			printf("EPIDEMILOGO:¡¡¡SALVESE QUIEN PUEDA!!!\n");
			
			free(pacientes);
			free(hijosEpidemiologo);
			
			return 0;
		}else{
			sleep(2);
			printf("EPIDEMILOGO:¡Genial, comencemos a vacunar a los pacientes!\n");
			kill(hijosEpidemiologo[0],SIGUSR2);
			sleep(2);
			//EL EPIDEMIOLOGO ESPERA A QUE MUERA EL MEDICO
			hijoMuerto=wait(&estado);
			//ALMACENA EL VALOR QUE DEVUELVE EL MEDICO AL MORIR
			hijoDevuelve=WEXITSTATUS(estado);
			sleep(1);
			printf("EPIDEMIOLOGO:El numero de pacientes vacunados con exito es %d \n",hijoDevuelve);
			
			free(pacientes);
			free(hijosEpidemiologo);
			
			return 0;
		}
	}
}

void funcionFarmaceutico(int signal){
	if(signal==SIGUSR1){
		if(calculaAleatorios(0,1)==0){
			sleep(1);
			printf("FARMACEUTICO:Vaya, no hay dosis suficientes para todos\n");
			exit(0);
		}else if(calculaAleatorios(0,1)==1){
			sleep(1);
			printf("FARMACEUTICO:Estamos de suerte, hay dosis para todos \n");
			exit(1);
		}
	}
	
}

void funcionMedico(int signal){
	if(signal==SIGUSR2){
		sleep(1);
		printf("MEDICO:Es hora de empezar a vacunar\n");
	}
}

void funcionPacientes(int signal){
	if(signal==SIGUSR1)
		sleep(1);
}

int calculaAleatorios(int min, int max) {
	srand(getpid());
	return rand() % (max-min+1) + min;
}