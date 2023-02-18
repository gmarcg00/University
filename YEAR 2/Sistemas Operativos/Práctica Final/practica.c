#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <ctype.h>
/* ----------------- Declaraciones globales ----------------- */
#define atendedoresTotales 2
#define coorTotales 1

//ESTRUCTURAS

//Lista de 15 solicitudes
struct listaSolicitudes{
	int idSolicitud;
	int estasSiendoAtendido;//variable de espera a ser atendido
	int tipoSolicitud;
	int finalizado;
	int espera;//variable de espera a entrar en actividad
	pthread_t hiloSolicitud;
};
struct listaSolicitudes *usuarios;
int usuariosActividad[4];


//Lista de 3 atendedores
struct listaAtendedores{
	int idAtendedor;
	int tipoAtendedor;
	int ocupado;
	int descansando;
	int solicitudesAtendidas;
	int estadisticas;
	pthread_t hiloAtendedor;
};
struct listaAtendedores *trabajador;

//Estructura del coordinador
struct listaCoordinador{
	int idCoordinador;
	int numeroParticipantesEchados;
	int vecesLanzaActividad;
	pthread_t hiloCoordinador;
};
struct listaCoordinador coordinador;

//Estructura de Actividades Sociales
struct listaActividades{
	int idParticipante;
	pthread_t hiloActividad;
};
struct listaActividades *actividades;


//SEMAFOROS

//Semaforo para la cola de solicitudes
pthread_mutex_t semaforoColaSolicitudes;

//Semaforo para el fichero log
pthread_mutex_t semaforoFicheroLog;

//Semaforo para la cola social
pthread_mutex_t semaforoColaActividadesSocial;

//Semaforo para
pthread_mutex_t atendedorDescansa;

//Semaforo para que si hay mas de un atendedor libre , una solicitud sea solo cogida por uno de ellos
pthread_mutex_t recogeSolicitante;

//Semaforo para la variable de condicion+
pthread_mutex_t mutexCondicion;

//Semaforo para la funcion sacarPosicion
pthread_mutex_t eliminaSolicitante;


//VARIABLES DE CONDICION
pthread_cond_t condicionesCoordinador;//nos avisará de que podemos comenzar la actividad social


//FUNCIONES
void nuevaSolicitud(int signal);
void *accionesSolicitud(void *idSolicitud);
void *accionesAtendedor(void *idAtendedor);
void *accionesCordSocial(void *idCoordinador);
void tipoAtencion(int identificadorAtendedor,int solicitud);
void salirPrograma();
void writeLogMessage(char *id,char *msg);
int sacarPosicion(int identificadorSolicitud);
int calculaAleatorios(int min, int max);
void estadisticas();
int cuentaGenteActividad();
void *actividadActividad(void *idActividad);


//VARIABLES
int contadorSolicitudes;//Numero de solicitudes que pueden acceder al sistema, se introduce por consola
int contadorParticipantes;//Numero de solicitudes que llegan al sistema
int contadorAtendedoresPro;
int programaEnEjecucion;//Variable para comprobar si el programa esta en ejecucion
int participantesActividad;
int genteEsperando;//


//FICHERO DE LOG
FILE *fichero;
const char *Nombre_Fichero="registroTsunamiDemocratico.log";


int main(int argc, char* argv[]) {
	
	if(argc!=3){
		printf("Numero de argumentos incorrecto\n");
		return -1;
	}else{
		contadorSolicitudes=atoi(argv[1]);
		contadorAtendedoresPro=atoi(argv[2]);
		
	}
	int pidProceso=getpid();
		
	
	printf("\n------------------------------------------ TSUNAMI DEMOCRATICO -----------------------------------------\n");
	printf("Introduzca 'kill -10 %d' en el terminal si desea ingresar mediante invitacion\n",pidProceso);
	printf("Introduzca 'kill -12 %d' en el terminal si desea ingresar mediante un codigo QR\n",pidProceso);
	printf("Introduzca 'kill -13 %d` en el terminal si desea ver las estadisticas\n",pidProceso);
	printf("Introduzca 'kill -2 %d' en el terminal si desea finalizar el programa.\n",pidProceso);
	 
	
	
	if(signal(SIGPIPE,estadisticas)==SIG_ERR){
		exit(-1);
	}
	if(signal(SIGINT,salirPrograma)==SIG_ERR){
		exit(-1);
	}
	if(signal(SIGUSR1,nuevaSolicitud)==SIG_ERR){
		exit(-1);
	}
	if(signal(SIGUSR2,nuevaSolicitud)==SIG_ERR){
		exit(-1);
	}
	
	srand(time(NULL));
	
	// ----------------- Inicializamos los recursos ----------------- 
	
	//Inicializamos los semaforos
	pthread_mutex_init(&semaforoColaSolicitudes,NULL);
	pthread_mutex_init(&semaforoColaActividadesSocial,NULL);
	pthread_mutex_init(&semaforoFicheroLog,NULL);
	pthread_mutex_init(&atendedorDescansa,NULL);
	pthread_mutex_init(&recogeSolicitante,NULL);
	pthread_mutex_init(&mutexCondicion,NULL);	
	pthread_mutex_init(&eliminaSolicitante,NULL);
	
	//Inicializamos la variable de condicion
	pthread_cond_init(&condicionesCoordinador,NULL);
	
	
	//Inicializamos el contador de participantes, programaEnEjecucion,participantesActividad
	contadorParticipantes=0;
	programaEnEjecucion=1;
	participantesActividad=0;
	
	// Inicializamos la lista de solicitudes
	int i;
	usuarios=(struct listaSolicitudes*)malloc(sizeof(struct listaSolicitudes)*contadorSolicitudes);
	for(i=0;i<contadorSolicitudes;i++){
		//Inicializamos los identificadores de las solicitudes
		usuarios[i].idSolicitud=0;
		//Inicializamos si la solicitud esta siendo atendida, un 1 si lo esta y un 0 si no lo esta
		usuarios[i].estasSiendoAtendido=0;
		//Inicializamos el tipo de solicitud, 1 si es in invitacion y 2 si es QR
		usuarios[i].tipoSolicitud=0;
		
		usuarios[i].espera=0;
		//Inicializamos si ha finalizado la atencion a la solicitud, un 1 si lo esta y un 0 si no lo esta
		usuarios[i].finalizado=0;
	}
	//Inicializamos la lista de atendedores
	
	trabajador=(struct listaAtendedores*)malloc(sizeof(struct listaAtendedores)*(contadorAtendedoresPro+atendedoresTotales));
	for(i=0;i<(atendedoresTotales+contadorAtendedoresPro);i++){
		//Inicializamos los identificadores de los atendedores
		trabajador[i].idAtendedor=i+1;
		//Inicializamos el tipo de atendedor
		if(i<2){
			trabajador[i].tipoAtendedor=i;
		}else{
			trabajador[i].tipoAtendedor=2;
		}
		//Inicializamos si esta descansando
		trabajador[i].descansando=0;
		//Inicializamos si esta ocupado o no, 1 si lo esta y 0 si no lo esta
		trabajador[i].ocupado=0;
		//Inicializamos el numero de solicitudes atendidas
		trabajador[i].solicitudesAtendidas=0;
		
		//Inicializamos para las estadísticas
		trabajador[i].estadisticas=0;
	}	
	
	//Inicializamos la lista de actividades
	actividades = (struct listaActividades*)malloc(sizeof(struct listaActividades)*4);
	for(i=0; i<4; i++){
		actividades[i].idParticipante=0;
		usuariosActividad[i]=0;
	}
	
	//Inicializamos al coordinador
	coordinador.idCoordinador=1;
	
	
	//Inicializamos el fichero log
	
	fichero=fopen("registroTsunamiDemocratico.log","w");
	fprintf(fichero, "**: Hola");
	fclose(fichero);
	
	//Creamos 3 hilos atendedores
	
	for(i=0;i<(atendedoresTotales+contadorAtendedoresPro);i++){
		pthread_create(&trabajador[i].hiloAtendedor,NULL,accionesAtendedor,(void *)&trabajador[i].idAtendedor);
	}
	
	//Creamos el hilo coordinador
	
	for(i=0;i<coorTotales;i++){
		pthread_create(&coordinador.hiloCoordinador,NULL,accionesCordSocial,(void *)&coordinador.idCoordinador);
	}
	
	//Espera por las señales de forma infinita
	while(programaEnEjecucion==1){
		
		sleep(1);
	}
	
	//Espera a que acaben todos los hilos
	int gente=0;
	do{
		sleep(1);
		gente = 0;
		//recorro el puntero de usuarios para ver si hay alguien ahí
		for(i=0; i<contadorSolicitudes; i++){
			if(usuarios[i].idSolicitud!=0){
				gente++;
			}
		}
		
		//Si hay alguien en la cola de usuarios ya no hace falta que mire más
		if(gente==0){
			
			//miro si hay gente en el puntero de atendedores
			for(i=0; i<contadorAtendedoresPro+2; i++){
				if(trabajador[i].idAtendedor!=0){
					gente++;
				}
			}
			
			//si ya no hay atendedores miro los que están en la cola de actividad
			if(gente==0){
				for(i=0; i<4; i++){
					if(usuariosActividad[i]!=0){
						gente++;
					}
				}
				//si hay gente en este punto hay que sacarlos así que envío la señal que saca al coordinador del wait
				if(gente>0){
					pthread_mutex_lock(&mutexCondicion);
					pthread_cond_signal(&condicionesCoordinador);
					pthread_mutex_unlock(&mutexCondicion);
				}
			}
		}
	}while(gente>0);
		
		
	
	
}
 
void nuevaSolicitud(int signal){
	int i=0;
	int enCola=0;
	char ident[100];
	char mens[256];
	contadorParticipantes++;//Lo incremento porque llega una nueva solicitud
	sprintf(ident,"Solicitud_%d",contadorParticipantes);
	sprintf(mens,"Intentando acceder a cola\n");
	pthread_mutex_lock(&semaforoFicheroLog);
	writeLogMessage(ident,mens);
	printf("%s: %s",ident,mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	pthread_mutex_lock(&semaforoColaSolicitudes);//El primer hilo que llegue bloquea el mutex
	
	for(i=0;i<contadorSolicitudes;i++){
		printf("%d\n",usuarios[i].idSolicitud);
		if(usuarios[i].idSolicitud==0 && enCola==0){
			enCola++;
			usuarios[i].idSolicitud=contadorParticipantes;
			usuarios[i].estasSiendoAtendido=0;
			usuarios[i].espera=0;
			usuarios[i].finalizado=0;
			switch(signal){
				case SIGUSR1:
					usuarios[i].tipoSolicitud=0;//Se trata de una solicitud por inivitacion
					break;
				case SIGUSR2:
					usuarios[i].tipoSolicitud=1;//Se trata de una solicitud por QR
					break;
			}
			pthread_create(&usuarios[i].hiloSolicitud,NULL,accionesSolicitud,(void*)&usuarios[i].idSolicitud);
		}
	}
	if(enCola==0){//No hay espacio en la cola
		sprintf(mens,"No hay espacio en la cola de solicitudes, a llorar a Jerusalen\n");
		pthread_mutex_lock(&semaforoFicheroLog);
		writeLogMessage(ident,mens);
		printf("%s: %s",ident,mens);
		pthread_mutex_unlock(&semaforoFicheroLog);
	}
	pthread_mutex_unlock(&semaforoColaSolicitudes);//Desbloqueamos el mutex	
	
}

void *accionesSolicitud(void *idSolicitud) {
	
	//Pasamos a entero el puntero idSolicitud
	int identificadorSolicitud=*(int*)idSolicitud;
	int i,pos;
	char ident[100];
	char mens[250];
	
	//Guardamos hora y tipo en el log
	
	//Buscamos la posicion
	for(i=0;i<contadorSolicitudes;i++){
		if(usuarios[i].idSolicitud==identificadorSolicitud){
			pos=i;
		}
	}
	
	while(usuarios[pos].estasSiendoAtendido==0){//No estamos siendo atendidos
		sleep(3);
		if(usuarios[pos].estasSiendoAtendido==0){//Si no estamos siendo atendidos
		//Ponemos el if igual que el while ya que mientras dormimos 4 segundos puede darse que un atendedor nos atienda y el usuario se vaya
			int expulsado = calculaAleatorios(0, 100);
			if(usuarios[pos].tipoSolicitud==2 && expulsado<30){//Se descarta por no ser fiable
				//pthread_mutex_lock(&recogeSolicitante);//Cierro el mutex para que mientras estoy echando a la solicitud no vengan varios atendedores a por la misma solicitud
				usuarios[pos].idSolicitud=0;//Los atendedores atienden las solicitudes cuyo id es diferente de 0, una vez la solicitud ha sido atendida, la pongo a 0 para que no la vuelvan a atender
				pthread_mutex_lock(&semaforoFicheroLog);
				sprintf(ident,"Solicitud_%d",identificadorSolicitud);
				sprintf(mens,"¡¿Como que no soy fiable?!,te voy a explicar yo quien es fiable\n");
				writeLogMessage(ident,mens);
				printf("%s: %s",ident,mens);
				pthread_mutex_unlock(&semaforoFicheroLog);
				//pthread_mutex_unlock(&recogeSolicitante);
				pthread_exit(&usuarios[pos].hiloSolicitud);
			}
			if(usuarios[pos].tipoSolicitud==1 && expulsado<10){
				//pthread_mutex_lock(&recogeSolicitante);//Cierro el mutex para que no vengan varios atendedores a por la misma solicitud
				usuarios[pos].idSolicitud=0;//Los atendedores atienden las solicitudes cuyo id es diferente de 0, una vez la solicitud ha sido atendida, la pongo a 0 para que no la vuelvan a atender
				pthread_mutex_lock(&semaforoFicheroLog);
				sprintf(ident,"Solicitud_%d",identificadorSolicitud);
				sprintf(mens,"Ya si eso vengo mañana...\n");
				writeLogMessage(ident,mens);
				printf("%s: %s",ident,mens);
				pthread_mutex_unlock(&semaforoFicheroLog);
				//pthread_mutex_unlock(&recogeSolicitante);
				pthread_exit(&usuarios[pos].hiloSolicitud);
			}
			expulsado=calculaAleatorios(0,100);
			if(expulsado<15){
				//pthread_mutex_lock(&recogeSolicitante);//Cierro el mutex para que no vengan varios atendedores a por la misma solicitud
				usuarios[pos].idSolicitud=0;//Los atendedores atienden las solicitudes cuyo id es diferente de 0, una vez la solicitud ha sido atendida, la pongo a 0 para que no la vuelvan a atender
				pthread_mutex_lock(&semaforoFicheroLog);
				sprintf(ident,"Solicitud_%d",identificadorSolicitud);
				sprintf(mens,"El programa me ha expulsado\n");
				writeLogMessage(ident,mens);
				printf("%s: %s",ident,mens);
				pthread_mutex_unlock(&semaforoFicheroLog);
				//pthread_mutex_unlock(&recogeSolicitante);
				pthread_exit(&usuarios[pos].hiloSolicitud);
			}
		}
	}
	//Ahora si estan siendo atendidos
	usuarios[pos].espera=1;
	pthread_mutex_lock(&semaforoFicheroLog);
	sprintf(ident,"Solicitud_%d",identificadorSolicitud);
	sprintf(mens,"Buenos días caballero, quiero un puesto de dirigente, ¿cuántos tienes?\n");
	writeLogMessage(ident,mens);
	printf("%s: %s",ident,mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	
	do{
		sleep(1);
	}while(usuarios[pos].espera==0);//Esperamos a que el atendedor termine con lo que tenga que hacer
	sleep(1);
				
	if(usuarios[pos].espera>0){//Entra en la actividad
		//Dar gracias al atendedor en el log
		if(calculaAleatorios(0,1)==0){//Decide no entrar en la actividad
			//Escribo en el log
			usuarios[pos].idSolicitud=0;//Los atendedores atienden las solicitudes cuyo id es diferente de 0, una vez la solicitud ha sido atendida, la pongo a 0 para que no la vuelvan a atender
			pthread_mutex_lock(&semaforoFicheroLog);
			sprintf(ident,"Solicitud_%d",identificadorSolicitud);
			sprintf(mens,"Al final no quiero participar\n");
			writeLogMessage(ident,mens);
			printf("%s: %s",ident,mens);
			pthread_mutex_unlock(&semaforoFicheroLog);
			pthread_exit(&usuarios[pos].hiloSolicitud);
		}else{//Decidimos que entramos
			pthread_mutex_lock(&semaforoFicheroLog);
			sprintf(ident,"Solicitud_%d",identificadorSolicitud);
			sprintf(mens,"He decidio participar\n");
			writeLogMessage(ident,mens);
			printf("%s: %s",ident,mens);
			pthread_mutex_unlock(&semaforoFicheroLog);
			usuarios[pos].finalizado=1;
			while(usuarios[pos].finalizado==1){
				pthread_mutex_lock(&semaforoColaActividadesSocial);
				for(i=0;i<4;i++){
					if(usuariosActividad[i]==0){
						usuarios[pos].finalizado=0;
						usuarios[pos].espera=0;
						usuariosActividad[i]=identificadorSolicitud;
						i=4;//Paro el bucle para que solo ocupe una posicion en la lista de participantes de la actividad
						usuarios[pos].idSolicitud=0;//Los atendedores atienden las solicitudes cuyo id es diferente de 0, una vez la solicitud ha sido atendida, la pongo a 0 para que no la vuelvan a atender
						pthread_mutex_lock(&semaforoFicheroLog);
						sprintf(ident,"Solicitud_%d",identificadorSolicitud);
						sprintf(mens,"He decidido participar y estoy en la lista de la actividad\n");
						writeLogMessage(ident,mens);
						printf("%s: %s",ident,mens);
						pthread_mutex_unlock(&semaforoFicheroLog);
						if(cuentaGenteActividad()==4){
							pthread_mutex_lock(&mutexCondicion);
							pthread_cond_signal(&condicionesCoordinador);
							pthread_mutex_unlock(&mutexCondicion);
						}
						
					}
					usuarios[pos].espera=3;
				}
				pthread_mutex_unlock(&semaforoColaActividadesSocial);
				if(usuarios[pos].idSolicitud>0)//Espera a que haya sitio en la lista de actividades
					sleep(3);
			}
			usuarios[pos].espera=0;
			pthread_exit(&usuarios[pos].hiloSolicitud);
		}
	}else{//No entra en la actividad
		usuarios[pos].idSolicitud=0;//Los atendedores atienden las solicitudes cuyo id es diferente de 0, una vez la solicitud ha sido atendida, la pongo a 0 para que no la vuelvan a atender
		pthread_mutex_lock(&semaforoFicheroLog);
		sprintf(ident,"Solicitud_%d",identificadorSolicitud);
		sprintf(mens,"Me es igual van a terminar todos como yo.\n");
		writeLogMessage(ident,mens);
		printf("%s: %s",ident,mens);
		pthread_mutex_unlock(&semaforoFicheroLog);
		usuarios[pos].espera=0;
		pthread_exit(&usuarios[pos].hiloSolicitud);	
	}
}
		
			
	
	
	
void *accionesAtendedor(void *idAtendedor){
	/*Pasamos a int el puntero idAtendedor*/
	int identificadorAtendedor=*(int*)idAtendedor;
	
	//Busco la posicion en el array de atendedores
	int pos,i,lugar,id;
	for(i=0;i<(atendedoresTotales+contadorAtendedoresPro);i++){
		if(trabajador[i].idAtendedor==identificadorAtendedor){
			pos=i;
		}
	}
	char ident[100];
	char mens[250];
	sprintf(ident,"Atendedor_%d",identificadorAtendedor);
	sprintf(mens,"Preparen las carteras, la independencia no es gratis\n");
	pthread_mutex_lock(&semaforoFicheroLog);
	writeLogMessage(ident,mens);
	printf("%s: %s",ident,mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	
	do{
		lugar=-1;
		id=32767;
		do{
			sleep(1);
			pthread_mutex_lock(&recogeSolicitante);
			for(i=0;i<contadorSolicitudes;i++){
				if(usuarios[i].idSolicitud!=0 && usuarios[i].estasSiendoAtendido==0 && usuarios[i].idSolicitud<id && usuarios[i].tipoSolicitud==trabajador[pos].tipoAtendedor){
					//Cuando entra aqui es que va a atender una solicitud seguro
					lugar=i;
					id=usuarios[i].idSolicitud;
					trabajador[pos].ocupado=1;
				}
			}
			if(trabajador[pos].ocupado==0){
				for(i=0;i<contadorSolicitudes;i++){
					if(usuarios[i].idSolicitud!=0 && usuarios[i].estasSiendoAtendido==0 && usuarios[i].idSolicitud<id){
						//Cuando entra aqui es que va a atender una solicitud seguro
						lugar=i;
						id=usuarios[i].idSolicitud;
						trabajador[pos].ocupado=1;
					}
				}
			}
			if(id!=32767){
				usuarios[lugar].estasSiendoAtendido=1;
			}
			pthread_mutex_unlock(&recogeSolicitante);
		}while(trabajador[pos].ocupado==0 && programaEnEjecucion==1);
		
		sprintf(mens,"Hola futuro agitador de masas %d\n",id);
		pthread_mutex_lock(&semaforoFicheroLog);
		writeLogMessage(ident,mens);
		printf("%s: %s",ident,mens);
		pthread_mutex_unlock(&semaforoFicheroLog);
		
		
		int tiempoEspera;
		int aleatorioTipoAtencion=calculaAleatorios(1,100);
		if(1<=aleatorioTipoAtencion && aleatorioTipoAtencion<=10){
			/*Ficha con antecedentes policiales*/
			tiempoEspera=calculaAleatorios(6,10);
			sleep(tiempoEspera);
			sprintf(mens,"Fin de la atencion de la solicitud: %d ficha con antecedentes policiales\n",id);
			pthread_mutex_lock(&semaforoFicheroLog);
			writeLogMessage(ident,mens);
			printf("%s: %s",ident,mens);
			pthread_mutex_unlock(&semaforoFicheroLog);
			usuarios[lugar].espera=-1;
		}
		if(11<=aleatorioTipoAtencion && aleatorioTipoAtencion<=30){
			/*Errores en datos personales*/
			tiempoEspera=calculaAleatorios(2,6);
			sleep(tiempoEspera);
			sprintf(mens,"Fin de la atencion de la solicitud: %d debido a errores en datos personales\n",id);
			pthread_mutex_lock(&semaforoFicheroLog);
			writeLogMessage(ident,mens);
			printf("%s: %s",ident,mens);
			pthread_mutex_unlock(&semaforoFicheroLog);
			usuarios[lugar].espera=1;
		}
		if(31<=aleatorioTipoAtencion && aleatorioTipoAtencion<=100){
			/*Atencion correcta*/
			tiempoEspera=calculaAleatorios(1,4);
			sleep(tiempoEspera);
			sprintf(mens,"Fin de la atencion %d con exito\n",id);
			pthread_mutex_lock(&semaforoFicheroLog);
			writeLogMessage(ident,mens);
			printf("%s: %s",ident,mens);
			pthread_mutex_unlock(&semaforoFicheroLog);
			usuarios[lugar].espera=2;
		}
		
		trabajador[pos].solicitudesAtendidas++;
		trabajador[pos].estadisticas++;
		if(trabajador[pos].solicitudesAtendidas>=5){
			pthread_mutex_lock(&atendedorDescansa);
			int permitirDescansar=1;
			for(i=0;i<(atendedoresTotales+contadorAtendedoresPro);i++){
				if(trabajador[i].descansando==1){
					permitirDescansar=0;
				}
			}
			if(permitirDescansar==1){
				trabajador[pos].descansando=1;
			}
			pthread_mutex_unlock(&atendedorDescansa);
			if(trabajador[pos].descansando==1){
				sprintf(mens,"Que os den, me voy al bar\n");
				pthread_mutex_lock(&semaforoFicheroLog);
				writeLogMessage(ident,mens);
				printf("%s: %s",ident,mens);
				pthread_mutex_unlock(&semaforoFicheroLog);
				sleep(10);
				sprintf(mens,"Ya he vuelto del bar\n");
				pthread_mutex_lock(&semaforoFicheroLog);
				writeLogMessage(ident,mens);
				printf("%s: %s",ident,mens);
				pthread_mutex_unlock(&semaforoFicheroLog);
				trabajador[pos].descansando==0;
				trabajador[pos].solicitudesAtendidas=0;
			
			}
		}	
		genteEsperando=0;
		for(i=0;i<contadorSolicitudes;i++){
			if(usuarios[i].idSolicitud!=0){
				genteEsperando++;
			}
		}
		trabajador[pos].ocupado=0;
	}while(programaEnEjecucion==1 || genteEsperando>0 );
	sprintf(mens,"El trabajador ha terminado su jornada laboral\n");
	pthread_mutex_lock(&semaforoFicheroLog);
	writeLogMessage(ident,mens);
	printf("%s: %s",ident,mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	trabajador[pos].idAtendedor=0;
	sleep(1);
	pthread_exit(&trabajador[pos].hiloAtendedor);
}

void *accionesCordSocial(void *idCoordinador){
	char ident[100];
	char mens[250];
	int genteEsperando;
	int i;
	
	sprintf(ident, "Coordinador");
	sprintf(mens,"Coordinador de eventos festivo-culturales presente\n");
	pthread_mutex_lock(&semaforoFicheroLog);
	writeLogMessage(ident,mens);
	printf("%s: %s", ident, mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	
	do{
		
		sprintf(mens,"Esperando a los activistas leonesistas\n");
		pthread_mutex_lock(&semaforoFicheroLog);
		writeLogMessage(ident,mens);
		printf("%s: %s", ident, mens);
		pthread_mutex_unlock(&semaforoFicheroLog);
		
		pthread_mutex_lock(&mutexCondicion);
		pthread_cond_wait(&condicionesCoordinador, &mutexCondicion);
		pthread_mutex_unlock(&mutexCondicion);
		
		sprintf(mens,"Buena suerte chicos\n");
		pthread_mutex_lock(&semaforoFicheroLog);
		writeLogMessage(ident,mens);
		printf("%s: %s", ident, mens);
		pthread_mutex_unlock(&semaforoFicheroLog);
		
		for(i=0; i<4; i++){
			actividades[i].idParticipante = usuariosActividad[i];
			pthread_create(&actividades[i].hiloActividad, NULL, actividadActividad, &actividades[i].idParticipante);
		}
		
		pthread_mutex_lock(&mutexCondicion);
		pthread_cond_wait(&condicionesCoordinador, &mutexCondicion);
		pthread_mutex_unlock(&mutexCondicion);
		
		sleep(1);
		
		sprintf(mens,"Hey!, ¿Qué tal os fue?, a ver, recuento de daños\n");
		pthread_mutex_lock(&semaforoFicheroLog);
		writeLogMessage(ident,mens);
		printf("%s: %s", ident, mens);
		pthread_mutex_unlock(&semaforoFicheroLog);
		
		pthread_mutex_lock(&semaforoColaActividadesSocial);
		for(i=0; i<4; i++){
			usuariosActividad[i]=0;
		}
		pthread_mutex_unlock(&semaforoColaActividadesSocial);
		
		genteEsperando=0;
		for(i=0; i<contadorSolicitudes; i++){
			if(usuarios[i].espera==3){
				genteEsperando++;
			}
		}
	}while(programaEnEjecucion==1 && genteEsperando>0);
}

void *actividadActividad(void *idActividad){
	int id = *(int*) idActividad;
	char ident[100];
	char mens[250];
	int i;
	
	sprintf(ident, "Usuario_ %d", id);
	sprintf(mens,"Hula, hula, hula, pues eso que comenzamos, caballeros...\n");
	pthread_mutex_lock(&semaforoFicheroLog);
	writeLogMessage(ident,mens);
	printf("%s: %s", ident, mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	
	sleep(3);
	
	sprintf(mens,"Una actividad muy agradable, va a volver quien yo te diga...\n");
	pthread_mutex_lock(&semaforoFicheroLog);
	writeLogMessage(ident,mens);
	printf("%s: %s", ident, mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	
	for(i=0; i<4; i++){
		//El primero avisa al coordinador
		if(i==0){
			pthread_mutex_lock(&mutexCondicion);
			pthread_cond_signal(&condicionesCoordinador);
			pthread_mutex_unlock(&mutexCondicion);
		}
		
		actividades[i].idParticipante=0;
		
	}
	sleep(1);
	pthread_exit(&actividades[i].hiloActividad);

}

int calculaAleatorios(int min, int max){
	srand(time(NULL));
	return rand() % (max-min+1) + min;
}

void salirPrograma(int signal) {
	char ident[100];
	char mens[250];
	sprintf(ident, "*************************************");
	sprintf(mens,"Desde este momento se cierra la barra libre*************************************\n");
	pthread_mutex_lock(&semaforoFicheroLog);
	writeLogMessage(ident,mens);
	printf("%s: %s", ident, mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	programaEnEjecucion=0;
	//exit(0);
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

int sacarPosicion(int identificadorSolicitud){
	int i=0, posicion;
	while(i<15){
		if(usuarios[i].idSolicitud==identificadorSolicitud){
			posicion=i;
		}
		i++;
	}
	return posicion;
}

void estadisticas(int signal){
	int estadisticas[4]={0,0,0,0};
	int i;
	char ident[100];
	char mens[250];
	
	for(i=0; i<contadorAtendedoresPro+2; i++){
		estadisticas[0]+= trabajador[i].estadisticas;
		if(trabajador[i].ocupado==1){
			estadisticas[2]++;
		}
	}
	for(i=0; i<contadorSolicitudes; i++){
		if(usuarios[i].idSolicitud==0){
			estadisticas[1]++;
		}
		if(usuarios[i].espera==3){
			estadisticas[3]++;
		}
	}
	
	sprintf(ident, "estadistica_1");
	sprintf(mens,"Solicitudes atendidas: %d\n", estadisticas[0]);
	pthread_mutex_lock(&semaforoFicheroLog);
	writeLogMessage(ident,mens);
	printf("%s: %s", ident, mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	sprintf(ident, "estadistica_2");
	sprintf(mens,"Huecos en la cola: %d\n", estadisticas[1]);
	pthread_mutex_lock(&semaforoFicheroLog);
	writeLogMessage(ident,mens);
	printf("%s: %s", ident, mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	sprintf(ident, "estadistica_3");
	sprintf(mens,"Solicitudes siendo atendidas: %d\n", estadisticas[2]);
	pthread_mutex_lock(&semaforoFicheroLog);
	writeLogMessage(ident,mens);
	printf("%s: %s", ident, mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	sprintf(ident, "estadistica_4");
	sprintf(mens,"Esperando a la cola de actividades: %d\n", estadisticas[3]);
	pthread_mutex_lock(&semaforoFicheroLog);
	writeLogMessage(ident,mens);
	printf("%s: %s", ident, mens);
	pthread_mutex_unlock(&semaforoFicheroLog);
	
}

int cuentaGenteActividad(){
	int i;
	int gente=0;
	for(i=0;i<4;i++){
		if(usuariosActividad[i]!=0){
			gente++;
		}
	}
	return gente;
}