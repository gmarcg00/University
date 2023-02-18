#ifndef ALUF_H
#define ALUF_H
#include <iostream>
#include <QMainWindow>
#include <string>

using namespace std;

class ALUF
{
public:
    ALUF();
    ~ALUF();
    unsigned int* multiplica(unsigned int mantisaA, unsigned int mantisaB, unsigned int signoA, unsigned int signoB, unsigned int exponenteA, unsigned int exponenteB);
    unsigned int* suma(unsigned int mantisaA, unsigned int mantisaB, unsigned int signoA, unsigned int signoB, unsigned int exponenteA, unsigned int exponenteB, bool multiplicando);
    unsigned int* divide(unsigned int mantisaA, unsigned int mantisaB, unsigned int signoA, unsigned int signoB, unsigned int exponenteA, unsigned int exponenteB);
    void clear();
    unsigned int calculaAcarreo(unsigned int n1, unsigned int n2, bool acarreo, unsigned int bit);
    unsigned int complemento_2(unsigned int m);
    unsigned int* multiplicaSinSigno(unsigned int mA, unsigned int mB, unsigned int sA, unsigned int sB, unsigned int eA, unsigned int eB);
    unsigned int AND(unsigned int number, unsigned int position);
    QString aBinario(unsigned int numero, int length);
    int passBinario(int pos, unsigned int var, int length);

private:
    //Suma
    unsigned int g , r, st, n, P;
    bool Operandos_intercambiados, Completementando_P;
    unsigned int eS, d;

    //Multiplicacio o division( s = signo, e = exponente, f = fraccionaria, m =mantisa
    unsigned int sA, eA, fA, mA, sB,eB,fB,mB;
};
#endif // ALUF_H
