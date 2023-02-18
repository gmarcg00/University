//#ifndef PERSONA_H
//#define PERSONA_H

#include<iostream>

using namespace std;

class Persona
{
public:

    //Constructores de la clase
    Persona(string DNI);

    Persona(string DNI, string Nombre);

    Persona(string DNI, string Nombre, string Apellidos);

    //Getters

    string getNombre();

    string getApellidos();

    string getDNI();

    //Imprime los datos de la persona
	virtual void imprimir();


private:
    string nombre;
    string apellidos;
    string dni;

};

//#endif // PERSONA_H
