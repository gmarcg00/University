#include <iostream>
#include "profesor.h"
using namespace  std;

int main()
{
	Profesor profesor1 = Profesor("71715520W", "Guillermo", "Marcos Garcia");
	Profesor profesor2 = Profesor("12345674T", "Gerard", "Pique Bernabeu");

	Alumno alumno1 = Alumno("33333333R", "Gareth", "Bale");
	Alumno alumno2 = Alumno("33r", "Jordi", "Alba");

	vector<Persona> personas;
	personas.push_back(profesor1);
	personas.push_back(profesor2);
	personas.push_back(alumno1);
	personas.push_back(alumno2);
	
	profesor1.addAlumno(alumno1);
	profesor1.addAlumno(alumno2);
	float nota1 = 5.5f, nota2 = 6.0f, nota3 = 8.4f, nota4 = 9.4f;


	profesor1.addNota(0, nota1, nota2, nota1);
	profesor1.addNota(1, nota4, nota4);

	cout << "Alumnos:" << endl;
	profesor1.imprimirAlumnos();
	cout << endl;

	cout << "Imprimiendo mejor alumno" << endl;
	Alumno a = *profesor1.buscaMejor();
	a.imprimir();
	cout << endl;

    do {
            string identificador;

            cout << endl;
            cout << "Introduzca un dni (s -> STOP)" << endl;
            std::cin >> identificador;

            if (identificador == "s")
            {
                return 0;
            }

            bool isPrinted = false;
            for (unsigned int i = 0; i < personas.size(); i++)
            {
                if (personas[i].getDNI() == identificador)
                {
                    Persona p = personas[i];
                    p.imprimir();
                    isPrinted = true;
                }
            }
            if (!isPrinted)
            {
                cout << "DNI no identificado" << endl;
                cout << "Introduzca el dni de forma correcta" << endl;
            }
     } while (true);
 }
