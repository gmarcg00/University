#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <ctype.h>

// ------------- Declaraciones Globales ------------- //

#define enfermerasIniciales 2

//ESTRUCTURAS
struct listaPacientes{
	int idPaciente;
	int tipoPaciente;
	int siendoAtendido; //1(siendo atendido) 0(no siendo atendido)
	int finalizado; //1(acabaron de atenderlo) 0(no acabaron de atenderlo)
	int vacuna; 
	int reaccion; //1(ha dado reaccion) 0(no ha dado reaccion)
	int serologia;
	pthread_t hiloPaciente;
};
struct listaPacientes *pacientes;
int pacientesEstudio;

struct listaEnfermeras{
	int idEnfermera;
	int tipoEnfermera;
	int ocupado;
	int pacientesAtendidos;
	int descansando;
	pthread_t hiloEnfermera;
};
struct listaEnfermeras *enfermeras;

struct listaMedicos{
	int idMedico;
	pthread_t hiloMedico;
};
struct listaMedicos medico;

struct listaEstadistico{
	int idEstadistico;
	pthread_t hiloEstadistico;
};
struct listaEstadistico estadistico;


	

//FUNCIONES
void nuevoPaciente(int signal);
void *accionesPaciente(void *idPaciente);
void *accionesEnfermera(void *idEnfermera);
void *accionesMedico(void *idMedico);
void *accionesEstadistico(void *idEstadistico);
void writeLogMessage(char *id,char *msg);
int calculaAleatorios(int min, int max);

//SEMAFOROS

//Semaforo para la cola de solicitudes
pthread_mutex_t semaforoColaPacientes;

//Semaforo para el fichero log
pthread_mutex_t semaforoFicheroLog;

//Semaforo para la variable de condicion
pthread_mutex_t mutexCondicion;

//VARIABLES DE CONDICION
pthread_cond_t condicionesEstadistico;//nos avisará de que podemos comenzar el estudio

//Semaforo para la cola del estudio
pthread_mutex_t mutexColaEstudio;

//Semaforo para atender un paciente
pthread_mutex_t semaforoAtiende;

//Semaforo para atender a los pacientes
pthread_mutex_t atiendePacientes;

//Semaforo para que descansen las enfermeras
pthread_mutex_t enfermeraDescansa;



//VARIABLES
int contadorPacientes;//Numero de pacientes que pueden acceder al sistema, se introduce por consola
int enfermerasSenior;//Numero de enfermer@s que atienden a pacientes senior, se introduce por consola
int programaEnEjecucion;//Indica si el programa se está ejecutando o no
int contadorSolicitudes;//Numero de pacientes que intentan acceder al sistema
int genteEsperando;

//FICHERO DE LOG
FILE *fichero;
const char *Nombre_Fichero="registroVacunaCovid19.log";


int main (int argc, char* argv[]){
	
	if(argc!=3){
		printf("Numero de argumentos incorrecto\n");
		return -1;
	}
	
	contadorPacientes=atoi(argv[1]);
	enfermerasSenior=atoi(argv[2]);
	
	int pidProceso=getpid();
	
	printf("------------------------------------------ VACUNA COVID19 ------------------------------------------ \n");
	printf("Introduzca 'kill -10 %d' en el terminal si desea ingresar como paciente junior\n",pidProceso);
	printf("Introduzca 'kill -12 %d' en el terminal si desea ingresar como paciente medio\n",pidProceso);
	printf("Introduzca 'kill -13 %d` en el terminal si desea ingresar como paciente senior\n",pidProceso);
	printf("Introduzca 'kill -2 %d' en el terminal si desea finalizar el programa.\n",pidProceso);
	
	
	if(signal(SIGUSR1,nuevoPaciente)==SIG_ERR){
		exit(-1);
	}
	if(signal(SIGUSR2,nuevoPaciente)==SIG_ERR){
		exit(-1);
	}
	if(signal(SIGPIPE,nuevoPaciente)==SIG_ERR){
		exit(-1);
	}
	
	srand(time(NULL));
	
	// ------------- INICIALIZAMOS LOS RECURSOS ------------- //
	
	//Inicializamos los semaforos
	pthread_mutex_init(&semaforoColaPacientes,NULL);
	pthread_mutex_init(&semaforoFicheroLog,NULL);
	pthread_mutex_init(&mutexCondicion,NULL);
	pthread_mutex_init(&mutexColaEstudio,NULL);
	pthread_mutex_init(&semaforoAtiende,NULL);
	pthread_mutex_init(&enfermeraDescansa,NULL);
	
	//Inicializamos las variables de condición
	pthread_cond_init(&condicionesEstadistico,NULL);
	
	//Inicializamos las variables globales
	programaEnEjecucion=1;
	contadorSolicitudes=0;
	genteEsperando=0;
	
	//Inicializamos la lista de pacientes 
	int i;
	pacientes=(struct listaPacientes*)malloc(sizeof(struct listaPacientes)*contadorPacientes);
	for(i=0;i<contadorPacientes;i++){
		//Inicializamos los identificadores de los pacientes
		pacientes[i].idPaciente=0;
		//Inicializamos el tipo de paciente
		pacientes[i].tipoPaciente=0; // 1 si es junior 2 si es medio y 3 si es senior
		//Inicializamos la variable siendo atendido;
		pacientes[i].siendoAtendido=0;
		//Inicializamos la variable finalizado
		pacientes[i].finalizado=0;
		//Inicializamos la variable vacuna
		pacientes[i].vacuna=0;
		//Inicializamos la variable reaccion
		pacientes[i].reaccion=0;
		//Inicializamos la variable serologia
		pacientes[i].serologia=0;
	}
	
	//Inicializamos la lista de enfermer@s
	enfermeras=(struct listaEnfermeras*)malloc(sizeof(struct listaEnfermeras)*(enfermerasSenior+enfermerasIniciales));
	for(i=0;i<(enfermerasSenior+enfermerasIniciales);i++){
		//Inicializamos los identificadores de los pacientes
		enfermeras[i].idEnfermera=i+1;
		//Inicializamos el tipo de enfermera
		if(i<2){
			enfermeras[i].tipoEnfermera=i+1;
		}else{
			enfermeras[i].tipoEnfermera=3;
		}
		//Inicializamos la variable ocupado
		enfermeras[i].ocupado=0;
		//Inicializamos la variable pacientesAtendidos
		enfermeras[i].pacientesAtendidos=0;
		
		enfermeras[i].descansando=0;
	}
	
	//Inicializamos al medico
	medico.idMedico=0;
	
	//Inicializamos al estadistico
	estadistico.idEstadistico=0;
	
	
	
	//Inicializamos el fichero log
	fichero=fopen("registroVacunaCovid19.log","w");
	fprintf(fichero, "**: Hola\n");
	fclose(fichero);
	
	//Creamos los hilos de las enfermeras
	
	for(i=0;i<(enfermerasSenior+enfermerasIniciales);i++){
		pthread_create(&enfermeras[i].hiloEnfermera,NULL,accionesEnfermera,(void*)&enfermeras[i].idEnfermera);
	}
	
	
	//Creamos al médico
	pthread_create(&medico.hiloMedico,NULL,accionesMedico,(void*)&medico.idMedico);
	
	//Creamos al estadistico
	pthread_create(&estadistico.hiloEstadistico,NULL,accionesEstadistico,(void*)&estadistico.idEstadistico);
	
	while(programaEnEjecucion==1){
		//vacunamos a por las señales de forma infinita
		sleep(1);
	}	
	free(pacientes);
	return 0;
}

void nuevoPaciente(int signal){
	int i=0;
	int enCola=0;
	char ident[100];
	char mens[256];
	contadorSolicitudes++;
	sprintf(ident,"Solicitud del paciente_%d",contadorSolicitudes);
	sprintf(mens,"Intentando acceder a cola\n");
	pthread_mutex_lock(&semaforoFicheroLog);
	writeLogMessage(ident,mens);
	printf("%s: %s",ident,mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	pthread_mutex_lock(&semaforoColaPacientes);
	for(i=0;i<contadorPacientes;i++){
		if(pacientes[i].idPaciente==0 && enCola==0){
			enCola++;
			pacientes[i].idPaciente=contadorSolicitudes;
			pacientes[i].siendoAtendido=0;
			pacientes[i].serologia=0;
			pacientes[i].vacuna=0;
			pacientes[i].reaccion=0;
			pacientes[i].finalizado=0;
			switch(signal){
			case SIGUSR1:
				pacientes[i].tipoPaciente=1;
				break;
			case SIGUSR2:
				pacientes[i].tipoPaciente=2;
				break;
			case SIGPIPE:
				pacientes[i].tipoPaciente=3;
				break;
			}
			pthread_create(&pacientes[i].hiloPaciente,NULL,accionesPaciente,(void*)&pacientes[i].idPaciente);
		}
		
	}
	
	if(enCola==0){
		//NO HAY ESPACIO EN LA COLA
		sprintf(mens,"No hay espacio en la cola de pacientes\n");
		pthread_mutex_lock(&semaforoFicheroLog);
		writeLogMessage(ident,mens);
		printf("%s: %s",ident,mens);
		pthread_mutex_unlock(&semaforoFicheroLog);
	}
	pthread_mutex_unlock(&semaforoColaPacientes);
	
}

void *accionesPaciente(void *idPaciente){
	int identificadorPaciente=*(int*)idPaciente;
	int i,pos;
	char ident[100];
	char mens[256];
	
	for(i=0;i<contadorPacientes;i++){
		printf("%d \n",pacientes[i].idPaciente);
		if(pacientes[i].idPaciente==identificadorPaciente){
			pos=i;
		}
	}

	
	// Escribir en el LOG el numero de paciente que entra y su solicitud e imprimirlo por pantalla.
	sprintf(ident,"Paciente_%d",pacientes[pos].idPaciente);
	sprintf(mens,"Estoy en la cola de pacientes\n");
	pthread_mutex_lock(&semaforoFicheroLog);
	writeLogMessage(ident,mens);
	printf("%s: %s",ident,mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	
	sleep(3);

	while(pacientes[pos].siendoAtendido==0){
		// No esta siendo atendido			
		int accionNoAtendido;
		accionNoAtendido = calculaAleatorios(1, 100);
		if(accionNoAtendido<=20){
		// El paciente se cansa de vacunar y se va.
			pthread_mutex_lock(&semaforoFicheroLog);
			sprintf(ident,"Paciente_%d",pacientes[pos].idPaciente);
			sprintf(mens,"Me voy porque me he cansado de esperar\n");
			writeLogMessage(ident,mens);
			printf("%s: %s",ident,mens);
			pthread_mutex_unlock(&semaforoFicheroLog);
			pthread_mutex_lock(&semaforoColaPacientes);
			pacientes[pos].idPaciente=0;
			pthread_mutex_unlock(&semaforoColaPacientes);
			pthread_exit(&pacientes[pos].hiloPaciente);
		}
		if(accionNoAtendido>20 && accionNoAtendido<=30){
			// El paciente se lo piensa mejor y se va.
			sprintf(ident,"Paciente_%d",pacientes[pos].idPaciente);
			sprintf(mens,"Me voy porque me lo he pensado mejor\n");
			pthread_mutex_lock(&semaforoFicheroLog);
			writeLogMessage(ident,mens);
			printf("%s: %s",ident,mens);
			pthread_mutex_unlock(&semaforoFicheroLog);
			pthread_mutex_lock(&semaforoColaPacientes);
			pacientes[pos].idPaciente=0;
			pthread_mutex_unlock(&semaforoColaPacientes);
			pthread_exit(&pacientes[pos].hiloPaciente);
		}
		if(accionNoAtendido>30 && accionNoAtendido<=35){
			// El paciente pierde el turno por ir al baño.
			sprintf(ident,"Paciente_%d",pacientes[pos].idPaciente);
			sprintf(mens,"Me voy porque he perdido el turno por ir al baño\n");
			pthread_mutex_lock(&semaforoFicheroLog);
			writeLogMessage(ident,mens);
			printf("%s: %s",ident,mens);
			pthread_mutex_unlock(&semaforoFicheroLog);
			pthread_mutex_lock(&semaforoColaPacientes);
			pacientes[pos].idPaciente=0;
			pthread_mutex_unlock(&semaforoColaPacientes);
			pthread_exit(&pacientes[pos].hiloPaciente);
		}
		sleep(3);	
	}
	
	while(pacientes[pos].serologia == 0){
		// vacunar a que termine el paciente de ser atendido
		sleep(1);
	}
	

	if(pacientes[pos].serologia>0){
		//Voy a entrar en el estudio
		sprintf(ident,"Paciente_%d",pacientes[pos].idPaciente);
		sprintf(mens,"Voy a participar en el estudio serológico\n");
		pthread_mutex_lock(&semaforoFicheroLog);
		writeLogMessage(ident,mens);
		printf("%s: %s",ident,mens);
		pthread_mutex_unlock(&semaforoFicheroLog);
		while(pacientes[pos].serologia>0){
			pthread_mutex_lock(&mutexColaEstudio);
			pacientesEstudio=identificadorPaciente;
			pthread_mutex_lock(&mutexCondicion);
			pthread_cond_signal(&condicionesEstadistico);
			pthread_mutex_unlock(&mutexCondicion);
			pthread_mutex_unlock(&mutexColaEstudio);

			sprintf(ident,"Paciente_%d",pacientes[pos].idPaciente);
			sprintf(mens,"Me voy del estudio de serologia\n");
			pacientes[pos].serologia=-1;
			pthread_mutex_lock(&semaforoFicheroLog);
			writeLogMessage(ident,mens);
			printf("%s: %s",ident,mens);
			pthread_mutex_unlock(&semaforoFicheroLog);
		}
		
		
	}else{
		//Serología==-1
		//No entro en el estudio
		
	}
	//Me voy del consultorio
	sprintf(ident,"Paciente_%d",pacientes[pos].idPaciente);
	sprintf(mens,"Me voy del consultorio\n");
	pthread_mutex_lock(&semaforoFicheroLog);
	writeLogMessage(ident,mens);
	printf("%s: %s",ident,mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	pthread_mutex_lock(&semaforoColaPacientes);
	pacientes[pos].idPaciente=0;
	pthread_mutex_unlock(&semaforoColaPacientes);
	pthread_exit(&pacientes[pos].hiloPaciente);
}

void *accionesEnfermera(void *idEnfermera){
	int identificadorEnfermera=*(int*)idEnfermera;
	int enfermerasTotales=enfermerasIniciales+enfermerasSenior;
	int i,pos,id=-1,lugar=-1;
	//id se utiliza para poder acceder al identificador del paciente desde fuera del bucle
	//lugar se utiliza para poder acceder a la posicion del paciente en el puntero de pacientes desde fuera del bucle
	
	for(i=0;i<enfermerasTotales;i++){
		if(enfermeras[i].idEnfermera==identificadorEnfermera){
			pos=i;
		}
	}
	
	char ident[100];
	char mens[250];
	
	sprintf(ident,"Enfermera_%d",enfermeras[pos].idEnfermera);
	sprintf(mens,"Es hora de atender a los pacientes\n");
	pthread_mutex_lock(&semaforoFicheroLog);
	writeLogMessage(ident,mens);
	printf("%s: %s",ident,mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	
	do{
		do{
			sleep(1);
			pthread_mutex_lock(&atiendePacientes);
			//Busco pacientes de mi tipo
			for(i=0;i<contadorPacientes;i++){
				if(enfermeras[pos].ocupado==0 && enfermeras[pos].tipoEnfermera==pacientes[i].tipoPaciente && pacientes[i].idPaciente!=0 && pacientes[i].siendoAtendido==0){
					//El enfermer@ atiende a un paciente de su tipo
					pacientes[i].siendoAtendido=1;
					enfermeras[pos].ocupado=1;
					id=pacientes[i].idPaciente;
					lugar=i;
				}
			}
			//Ahora busco cualquier paciente
			if(enfermeras[pos].ocupado==0){
				for(i=0;i<contadorPacientes;i++){
					if(enfermeras[pos].ocupado==0 && pacientes[i].idPaciente!=0 && pacientes[i].siendoAtendido==0){
						pacientes[i].siendoAtendido=1;
						enfermeras[pos].ocupado=1;
						id=pacientes[i].idPaciente;
						lugar=i;
					}
				}
			}
			pthread_mutex_unlock(&atiendePacientes);
		}while(enfermeras[pos].ocupado==0);
	
		sprintf(mens,"Enfermera %d da la bienvenida a la enfermería al paciente_%d\n",enfermeras[pos].idEnfermera,id);
		pthread_mutex_lock(&semaforoFicheroLog);
		writeLogMessage(ident,mens);
		printf("%s: %s",ident,mens);
		pthread_mutex_unlock(&semaforoFicheroLog);
		
		int aleatorio=calculaAleatorios(1,10);
		int duermen;
		if(aleatorio<=8){
			//Pacientes con todo en regla
			duermen=calculaAleatorios(1,4);
			sleep(duermen);
			pacientes[lugar].vacuna=1;
			
			
		}
		if(aleatorio==9){
			//Pacientes mal identificados
			duermen=calculaAleatorios(2,6);
			sleep(duermen);
			pacientes[lugar].vacuna=1;
			
		}
		if(aleatorio==10){
			//Paciente con catarro o gripe
			duermen=calculaAleatorios(6,10);
			sleep(duermen);
			sprintf(mens,"Fin de la atencion al paciente: %d (paciente con catarro o gripe)\n",id);
			pthread_mutex_lock(&semaforoFicheroLog);
			writeLogMessage(ident,mens);
			printf("%s: %s",ident,mens);
			pthread_mutex_unlock(&semaforoFicheroLog);
			pacientes[lugar].serologia=-1;
		
		}
		
		if(pacientes[lugar].vacuna==1){
			//Estos pacientes se van a vacunar
			aleatorio=calculaAleatorios(1,10);
			if(aleatorio==1){
				//La vacuna ha dado reacción
				
			}else{
				//La vacuna no ha dado reacción
				aleatorio=calculaAleatorios(1,100);
				if(aleatorio<=25){
					//Participan en el estudio
					pacientes[lugar].serologia=1;
				}else{
					//No participan en el estudio
					pacientes[lugar].serologia=-1;
				}
				
			}
		}

		sprintf(mens,"Fin de la atencion al paciente: %d \n",id);
		pthread_mutex_lock(&semaforoFicheroLog);
		writeLogMessage(ident,mens);
		printf("%s: %s",ident,mens);
		pthread_mutex_unlock(&semaforoFicheroLog);
		enfermeras[pos].ocupado=0;
		
		enfermeras[pos].pacientesAtendidos++;
		if(enfermeras[pos].pacientesAtendidos>=5){
			pthread_mutex_lock(&enfermeraDescansa);
			int permitirDescansar=1;
			for(i=0;i<enfermerasTotales;i++){
				if(enfermeras[i].descansando==1){
					permitirDescansar=0;
				}
			}

			if(permitirDescansar==1){
				enfermeras[pos].descansando=1;
			}
			pthread_mutex_unlock(&enfermeraDescansa);
			
			if(enfermeras[pos].descansando==1){
				sprintf(mens,"Ya he atendido a 5 pacientes, voy a tomar café\n");
				pthread_mutex_lock(&semaforoFicheroLog);
				writeLogMessage(ident,mens);
				printf("%s: %s",ident,mens);
				pthread_mutex_unlock(&semaforoFicheroLog);
				sleep(5);
				sprintf(mens,"Ya he vuelto de tomar café\n");
				pthread_mutex_lock(&semaforoFicheroLog);
				writeLogMessage(ident,mens);
				printf("%s: %s",ident,mens);
				pthread_mutex_unlock(&semaforoFicheroLog);
				enfermeras[pos].descansando==0;
				enfermeras[pos].pacientesAtendidos=0;
			}
		}
		
		genteEsperando=0;
		for(int i=0;i<contadorPacientes;i++){
			if(pacientes[i].idPaciente!=0){
				genteEsperando++;
			}
		}
	
	}while(genteEsperando>0);
	
}

void *accionesMedico(void *idMedico){
	
}

int calculaAleatorios(int min, int max){
	srand(time(NULL));
	return rand() % (max-min+1) + min;
}

void *accionesEstadistico(void *idEstadistico){
	
}


void writeLogMessage(char *id,char *msg){
	
	//Calculamos la hora actual
	time_t now=time(0);
	struct tm *tlocal=localtime(&now);
	char stnow[19];
	strftime(stnow,19,"%d/ %m/ %y %H: %M: %S",tlocal);
	
	//Escribimos en el log
	fichero=fopen(Nombre_Fichero,"a");
	fprintf(fichero,"[ %s] %s: %s\n",stnow,id,msg);
	fclose(fichero);
	
}
