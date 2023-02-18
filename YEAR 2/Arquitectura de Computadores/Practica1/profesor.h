//#ifndef PROFESOR_H
//#define PROFESOR_H
#include "alumno.h"
#include <vector>

using namespace std;

class Profesor:public Persona
{
public:
    //Constructores
    Profesor(string DNI);
    Profesor(string DNI,string Nombre);
    Profesor(string DNI,string Nombre,string Apellidos);

    //Añade un alumno a la lista de alumnos
    void addAlumno(Alumno& student);

    //Metodos para añadir notas
    void addNota(unsigned int indice, float nota1);

    void addNota(unsigned int indice, float nota1,float nota2);

    void addNota(unsigned int indice, float nota1,float nota2,float nota3);

    //Obtiene la media de cada alumno
    float getMedia(Alumno& s);

    //Metodo que recorre el array de alumnos
    void imprimirAlumnos();

    //Metodo para encontrar el mejor alumno
	Alumno* buscaMejor();

    //Imprime los datos de cada profesor y su lista de alumnos
	void imprimir() override;

private:
    //Lista de alumnos
    vector<Alumno> alumnos;
};

//#endif // PROFESOR_H
