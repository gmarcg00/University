#include "vehiculo.h"

Vehiculo::Vehiculo()
{

}

//Constructor sobrecargado
Vehiculo:: Vehiculo(QString nombre,QString ruedas,bool motor,QString tipoCombustible,QString color,bool vagones,int potencia,bool combustible,bool alas, bool reactores,bool tren,bool locomotora,bool kitReparaciones){
    this->nombre=nombre;
    this->ruedas=ruedas;
    this->motor=motor;
    this->tipoCombustible=tipoCombustible;
    this->color=color;
    this->vagones=vagones;
    this->potencia=potencia;
    this->combustible=combustible;
    this->alas=alas;
    this->reactores=reactores;
    this->tren=tren;
    this->locomotora=locomotora;
    this->kitReparaciones=kitReparaciones;

}
