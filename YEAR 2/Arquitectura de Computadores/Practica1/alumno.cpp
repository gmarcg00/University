#include "alumno.h"
#include<string>

Alumno::Alumno(string DNI):Persona(DNI){
}

Alumno::Alumno(string DNI,string Nombre):Persona(DNI,Nombre){
}

Alumno::Alumno(string DNI,string Nombre,string Apellidos):Persona(DNI,Nombre,Apellidos){
}


void Alumno::addNota(float nota){
       if(notas.size() < max_notas && nota>=0.0 && nota<=10.0){
            notas.push_back(nota);
       }
}
float Alumno::getNota(unsigned int indice){
    if(indice<notas.size()){
        return notas[indice];
    }
    return 0;
}
float Alumno::getMedia(){
    if(getNumberNotes()==0){
        return 0;
    }
    float suma=0.0f;
    for(unsigned i=0;i<getNumberNotes();i++){
        suma=suma+notas[i];
    }
    return suma/(float) getNumberNotes();

}

unsigned int Alumno::getNumberNotes(){
    return notas.size();
}

vector<float> Alumno:: getNotas(){
	return notas;
}


void Alumno::imprimirNotas(){
    for(unsigned int i=0;i<getNumberNotes();i++){
        cout<<"\tNotas: "<<getNota(i)<<endl;
    }
}

void Alumno::imprimirMedia(){
    cout<< "Media: " << getMedia()<<endl;
}

void Alumno::imprimir()
{
	Persona::imprimir();
	imprimirNotas();
	imprimirMedia();
}

