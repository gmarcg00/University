#ifndef VEHICULO_H
#define VEHICULO_H

#include <QMainWindow>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Vehiculo
{
public:
    Vehiculo();
    Vehiculo(QString nombre,QString ruedas,bool motor,QString tipoCombustible,QString color,bool vagones,int potencia,bool combustible,bool alas, bool reactores,bool tren,bool locomotora,bool kitReparaciones);
     ~Vehiculo();
    //Objetos
    QString nombre,ruedas,tipoCombustible,color,numVagones,matricula;
    int potencia;
    bool combustible,alas,reactores,tren,locomotora,kitReparaciones,ruedaRepuesto,vagones,motor;
};

#endif // VEHICULO_H
