//#ifndef ALUMNO_H
//#define ALUMNO_H

#ifndef ALUMNO_H
#define ALUMNO_H
#include "persona.h"
#include <vector>

class Alumno:public Persona
{
public:

    //Constructores de la clase
     Alumno(string dni);
     Alumno(string dni,string nombre);
     Alumno(string dni,string nombre,string apellidos);

     //Funcion para añadir nota al array
     void addNota(float nota);

     //Funcion para obtener una nota del array de notas
     float getNota(unsigned int indice);

     //Funcion para devolver la media
     float getMedia();

     //
	 unsigned int getNumberNotes();

	 vector<float> getNotas();

	 void imprimirNotas();

     //Imprime la media
     void imprimirMedia();

     //Funcion que imprime los datos, las notas y la media
	 void imprimir() override;
private:
    vector<float> notas;
    static const unsigned int max_notas=3;

};

//#endif // ALUMNO_H
#endif // ALUMNO_H
