#include "profesor.h"
#include "alumno.h"

Profesor::Profesor(string DNI):Persona(DNI){

}

Profesor::Profesor(string DNI,string Nombre):Persona(DNI,Nombre){

}

Profesor::Profesor(string DNI,string Nombre,string Apellidos):Persona(DNI,Nombre,Apellidos){

}

void Profesor::addAlumno(Alumno& student){
    for(Alumno s:alumnos){
        if(s.getDNI()==student.getDNI()){//Si los dnis son iguales, el alumno ya estaba en la lista del profe
            return;//Para la ejecucion del metodo
        }
    }
	alumnos.push_back(student);
}

void Profesor::addNota(unsigned int indice, float nota1){
    if(nota1>=0.0 && nota1<=10.0){
        alumnos[indice].addNota(nota1);
    }
}

void Profesor::addNota(unsigned int indice, float nota1,float nota2){
    addNota(indice,nota1);
    addNota(indice, nota2);
}

void Profesor::addNota(unsigned int indice, float nota1,float nota2,float nota3){
    addNota(indice,nota1,nota2);
    addNota(indice, nota3);
}

float Profesor::getMedia(Alumno& s){
	return s.getMedia();
}

void Profesor::imprimirAlumnos(){
    for(unsigned i=0;i<alumnos.size();i++){//for(Alumno alumno:alumnos) alumno=alumno[i]
        alumnos[i].imprimir();
    }
}
Alumno* Profesor::buscaMejor(){
    if(alumnos.size()>0){
        float mejor=0.0f;
        unsigned int indiceMejor=0;
        for(unsigned int i=0;i < alumnos.size();i++){
            if( alumnos[i].getNumberNotes()==3 && alumnos[i].getMedia()>mejor){
                mejor=alumnos[i].getMedia();
                indiceMejor=i;
            }
        }
		return &alumnos[indiceMejor];
    }
	return nullptr;
}

void Profesor::imprimir()
{
	Persona::imprimir();
	cout << "Lista de alumnos:" << endl;
	imprimirAlumnos();
}
