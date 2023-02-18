#include "persona.h"

Persona::Persona(string DNI)
{
    this->dni=DNI;
}

Persona::Persona(string DNI,string Nombre)
{
    this->dni=DNI;
    this->nombre=Nombre;
}

Persona::Persona(string DNI,string Nombre,string Apellidos){
    this->dni=DNI;
    this->nombre=Nombre;
    this->apellidos=Apellidos;
}


string Persona::getDNI()
{
    return dni;
}


void Persona::imprimir()
{
	cout << "DNI: " << getDNI() << endl;
	cout << "Nombre: " << getNombre() << endl;
	cout << "Apellidos: " << getApellidos() << endl;
}

string Persona::getNombre()
{
    return nombre;
}

string Persona::getApellidos()
{
    return apellidos;
}



