#include "aluf.h"
#include <iostream>
#include <math.h>
#include <QMainWindow>
#include <string>

using namespace std;

struct campos{
    unsigned f:23;
    unsigned e:8;
    unsigned s:1;
};

union valores{
    float real;
    unsigned int entero;
    struct campos fields;
};

ALUF::ALUF()
{

}

ALUF::~ALUF()
{

}


unsigned int* ALUF::multiplica(unsigned int mantisaA, unsigned int mantisaB, unsigned int signoA, unsigned int signoB, unsigned int exponenteA, unsigned int exponenteB)
{

    clear();
    unsigned int auxiliar=8388608;
    unsigned int* solucion = new unsigned int[3];

    mA = mantisaA;
    mB = mantisaB;
    sA = signoA;
    sB = signoB;
    eA = exponenteA;
    eB = exponenteB;
    cout << eA << endl;
    cout << eB << endl;


    //PASO 1
    //Comprueba el signo de los dos numeros
    unsigned int sP;
    if(sA != sB)
      sP = 1;
    else
        sP = 0;
    //PASO 2
    //Suma los dos exponentes de los dos numeros
      int eP;
      if(eA <127 && eB<127){
          eP=eA+eB;
      }else{
          eA = eA -127;
          eB = eB -127;
          eP=eA+eB+127;
          eA = eA +127;
          eB = eB +127;
      }

        cout << "Exp despues suma: " << eP <<endl;
    //PASO 3
      //Realizamos multiplicacion binaria sin signo
      unsigned int* PjuntoA = multiplicaSinSigno(mA, mB, sA, sB, eA, eB);
      unsigned int A = PjuntoA[1];
      P = PjuntoA[0];

      //Si P es 8000000
      if(!(P & 0x800000)) {
          //Y A tambien,se desplaza P uno a la izquierda y se le suma uno
          if(A & 0x800000)
            P = (P << 1) + 1;
          //Si no simplemente se desplaza uno a la izquierda
          else
             P = P << 1;
          //Siempre se desplaza A la izquierda y se multiplica por 0xffffff(11111111...)
          A = A << 1;
          A = A & 0xffffff;
      }
      else  {
          eP = eP + 1;
      }
      //Calculamos el bit de redondeo
      for(unsigned int i = 0; i < n; i++) {
          if ( i == n-1) {
               if(A & 0x800000){
                   r = 1;
               }
          //Calculamos el bit sticy(OR del resto de bits de a)
          }else {
              if(AND(A,i) == 1)
                  st = 1;
          }
      }

      //Redondeo del calculo del producto
      if((r==1 && st == 1) || (r == 1 && st == 0 && (P & 1))) {
          P = P + 1;
      }


      //Calculamos el desbordamiento del exponente
      //overflow

      if(eP > 254){
          solucion[1]=256;
      }

      //underflow
      unsigned int eMinimo = 00000000;
      int t;

      if(eP < eMinimo){

          t = eP - eMinimo;

          if(t >= n){//n es el nº de bits de la mantisa
              cout << "t>=n" <<endl;
              solucion[1]=0;
          }else{
              cout << "t<n" <<endl;

              int ultP;

              for(int i=0;i<t;i++){

                  ultP = passBinario(0,P,24);

                  mA=mA>>1;

                  P=P>>1;

                  if(ultP == 1){

                      mA = mA + auxiliar;
                  }
              }

              eP = eMinimo;

          }
      }

      unsigned int fP = P & 0x7fffff;


      solucion[0] = sP;
      solucion[1] = eP;
      solucion[2] = fP;

      return solucion;
}
unsigned int* ALUF:: suma(unsigned int mantisaA, unsigned int mantisaB, unsigned int signoA, unsigned int signoB, unsigned int exponenteA, unsigned int exponenteB, bool multiplicando)
{

    mA = mantisaA;
    mB = mantisaB;
    sA = signoA;
    sB = signoB;
    eA = exponenteA;
    eB = exponenteB;
    bool denormal = false;
    cout << eA << endl;
    //COMPROBACION OPERANDOS DENORMALES
    if(eA == 0) {
        eA = 1;
        mA = 0;
        denormal = true;
    }
    if(eB == 0) {
        eB = 1;
        mB = 0;
        denormal = true;
    }

    unsigned int compruebaBits;
    //PASO 1 (P=0,g=0,r=0,st=0,intercambiados=false,complementadoP=false)
        clear();
    //PASO 2 COMPARACION DE EXPONENTES
        if(eA < eB){
            //INTERCAMBIAMOS LOS OPERANDOS
            unsigned int sAux, eAux, mAux ,fAux;
            sAux = sA;
            eAux = eA;
            fAux = fA;
            mAux = mA;

            sA = sB;
            eA = eB;
            fA = fB;
            mA = mB;

            sB = sAux;
            eB = eAux;
            fB = fAux;
            mB = mAux;

            Operandos_intercambiados = true;
        }
    //PASO 3 ASIGNAMOS EL EXPONENTE DE LA SUMA Y CALCULAMOS LA DIFERENCIA ENTRE LOS EXPONENTES
        eS = eA;
        d = eA - eB;
    //PASO 4 COMPROBACION DE SIGNOS

       if(eA <127 || eB < 127){
       }else{
           if(sA != sB) {
               mB = complemento_2(mB);
           }
       }
    //PASO 5 P PASA A SER LA MANTISA DE B
        P = mB;

    //PASO 6 ASIGNAMOS LOS BITS g,r y st
        for (unsigned int i = 0; i <= d; i ++) {
            compruebaBits = pow(2,i);
            if ( i == d - 2)
                //BIT DE GUARDA
            {
                if(!(compruebaBits & P)){
                    //NO EXISTE
                    g = 0;
                }
                else{
                    //EXISTE
                    g = 1;
                }

            }
            else if(i == d - 3)
                //BIT DE REDONDEO
            {
                if(!(compruebaBits & P)){
                    //NO EXISTE
                    r = 0;
                }
                else{
                    //EXISTE
                    r = 1;
                }

            }

            else // STICKY
            {
                if((compruebaBits & P)){
                    //EXISTE
                    st = 1;
                }
            }
        }

    //PASO 7 COMPARACION DE SIGNOS
        if(sA != sB){
            //DESPLAZAMOS P A LA DERECHA D BITS
            P = P >> d;
            //INTRODUCIMOS 1oS POR LA IZQUIERDA
           for(unsigned int i = 0; i < d; i++)
             P = P + pow(2,n-(i+1));
        } else {
            //DESPLAZAMOS P A LA DERECHA D BITS
            P = P >> d;
        }
    //PASO 8 CALCULO DEL ACARREO
        unsigned int c1 = calculaAcarreo(P, mA,false, 0);
        P = mA + P;

    //PASO 9 P A COMPLEMENTO A2
        if(sA != sB && (P & 0x800000) && c1 == 0 && eA>=127 && eB>=127){
            P = (~P)+1;
            Completementando_P = true;

        }
    //PASO 10
            if(sA == sB && c1 == 1) {
                if(!multiplicando){
                //OPERACION OR
                if((g == 1) || (r == 1) || (st == 1))
                    st = 1;
                else
                    st = 0;
                //R=PRIMER BIT DE P EMPEZANDO POR LA DERECHA
                if((P & 1))
                    r = 1;
                else
                    r = 0;
                //DESPLAZAMOS 1 BIT A LA DERECHA (C1,P)
                P = P >> 1;
                if(!(P & 0b100000000000000000000000))
                    P = P + 0b100000000000000000000000;
                //AJUSTE DEL EXPONENTE DE LA SUMA
                eS= eS+1;
                }
            } else {
                /*CALCULAMOS LOS BITS QUE ES NECESARIO DESPLAZAR P
                  PARA QUE LA MANTISA ESTE NORMALIZADA */
                unsigned int k = 0;
                bool normalizada = false;
                unsigned int igualAcero = P & 0xffffff;
                if(igualAcero == 0) {
                    normalizada = true;
                }
                //DESPLAZAMOS 1 BIT A LA IZQUIERDA HASTA QUE SALGA DEL BUCLE
                while(!normalizada) {
                    if((P & 0x800000))
                        normalizada = true;
                    else {
                        k++;
                        P = P << 1;
                    }

                }
                //UNA VEZ NORMALIZADA
                if(k==0) {
                    //OPERACION OR
                    if((r == 1) || (st == 1))
                        st = 1;
                    else
                        st = 0;
                    r = g;
                 } else if(k>1) {
                    r = 0;
                    st = 0;
                 }
                //AJUSTAMOS EL EXPONENTE DE LA SUMA
                eS = eS-k;
            }

    //PASO 11 REDONDEO DE P
            if((r==1 && st==1) || (r==1 && st ==0 && P & 0 )){
                //CALCULAMOS EL ACARREO
                unsigned int c2 = calculaAcarreo(P,1,false,0);
                P = P+1;
                if( c2 == 1){
                    //DESPLAZAMOS C2,P 1 BIT A LA DERECHA
                    P = P >> 1;
                    P = P + 0b100000000000000000000000;
                    //AJUSTAMOS EL EXPONENTE
                    eS = eS+1;
                }

            }
            unsigned int mS = P;

    //PASO 12 CALCULAMOS EL SIGNO DE LA SUMA
            unsigned int sS;
            if(Operandos_intercambiados==false && Completementando_P==true){
                sS = sB;
            } else {
                sS = sA;
            }

   unsigned int fS = mS & 0x7fffff;
   mS = mS & 0xffffff;
   unsigned int* resultados = new unsigned int[4];
    if((mS == 0 || eS == 0) && !denormal)
        sS = eS = fS = mS = 0;
    resultados[0] = sS;
    resultados[1] = eS;
    resultados[2] = mS;
    resultados[3] = fS;

    return resultados;
}

unsigned int* ALUF:: divide (unsigned int mantisaA, unsigned int mantisaB, unsigned int signoA, unsigned int signoB, unsigned int exponenteA, unsigned int exponenteB)
{

    unsigned int aux=8388608;
    unsigned int sumA = 0;
    unsigned int sumB = 0;
    mA = mantisaA;
    mB = mantisaB;
    sA = signoA;
    sB = signoB;
    eA = exponenteA;
    eB = exponenteB;


    sumA = mA + aux;
    sumB = mB + aux;

    //PASO 1 ESCALAMOS A Y B

    float aEsca=0;
    float bEsca=0;
    int pos=24;
    //Cogemos los 23 bits menos significativos de la mantisa que equivalen a la parte decimal
    //y asi obtenemos el numero decimal escalado
    for(int i=23;i>=0;i--){
        aEsca = aEsca + ((passBinario(i,sumA,24)) * (pow(2,i-pos+1)));
        bEsca = bEsca + ((passBinario(i,sumB,24)) * (pow(2,i-pos+1)));
    }

    //PASO 2 APROXIMAMOS BPRIMA
    //Aproximamos el inverso de un numero real segun la tabla
    float Bpri = 0.0;
    if((bEsca>=1)&&(bEsca<1.25)){
        Bpri = 1;
    }
    else if((bEsca>=1.25)&&(bEsca<2)){
        Bpri = 0.8;
    }

    //PASO 3 ASIGNAR X0 e Y0
    float x0;
    float y0;

    x0 = aEsca * Bpri;
    y0 = bEsca * Bpri;


    //PASO 4 ITERAMOS
    float r;
    float x1=0;
    bool primero=true;
    do{
        if (primero){
            primero=false;
        }
        else{
        x0=x1;
        }
        r = 2-y0;
        y0 = y0 * r;
        x1 = x0 * r;
    }
    //El proceso se detiene cuando la diferencia de x1 - x0 sea menos que 10e-4
   while(abs(x1-x0)>0.0001);


   unsigned int sDiv = 0 ;

   //PASO 5 CALCULAMOS EL SIGNO
   if((sA==0)&&(sB==0)){
        sDiv = 0;
   }
   else if((((sA==0)&&(sB==1))||(((sA==1)&&(sB==0))))){
        sDiv = 1;
    }
   else if((sA==1)&&(sB==1)){
        sDiv = 0;
    }
    //PASO 6 CALCULAMOS EL EXPONENTE
    unsigned int expDiv;
    unsigned int mantDiv = 0;

    valores fin;

    fin.real = x1;
    unsigned int expoDiv;

    mantDiv = fin.fields.f;
    //expoDiv es la multiplicación de A * 1/B
    expoDiv = fin.fields.e;

   expDiv = eA - eB + expoDiv;

    //PASO 7 ALMACENAMOS EL RESULTADO
    QString resultado;
    resultado=QString::number(sDiv)+" "+aBinario(expDiv, 8)+" "+aBinario(mantDiv, 23);
    cout << expDiv << endl;
    unsigned int* solucion = new unsigned int[3];

    solucion[0]=sDiv;
    solucion[1]=expDiv;
    solucion[2]=mantDiv;

    return solucion;

}

void ALUF:: clear()
{
    P = 0;
    g = 0;
    r = 0;
    st = 0;
    n = 24;
    Operandos_intercambiados = false;
    Completementando_P = false;
}

unsigned int ALUF:: complemento_2(unsigned int m)
{
    unsigned int m_c2 = (~m & 0xffffff) +1;
    return m_c2;
}
unsigned int* ALUF:: multiplicaSinSigno(unsigned int mA, unsigned int mB, unsigned int sA, unsigned int sB, unsigned int eA, unsigned int eB)
{
    //PASO 1
    //Declara e incializa los valores
    unsigned int mP = 0;
    unsigned int sP = 0;
    unsigned int eP = 0;
    unsigned int mBaux = mB;
    unsigned int* pValores = new unsigned int[4];
    unsigned int* PjuntoA = new unsigned int[2];
    unsigned int c = 0;

    //PASO 2
    //Recorremos toda la mantisa
    for(unsigned int i=0; i < n; i++) {
        c = 0;
        if(mA & 1) {
            //Calculamos el acarreo de las mantisas
            c = calculaAcarreo(mBaux, mP, false, 0);
            //Sumamos ambos numeros
            pValores=suma(mBaux, mP, 0, 0, eB, eP, true);
            //Guardamos en sp(signo),ep(exponente) y mp(mantisa) los valores obtenidos en
            //la suma
            sP = pValores[0];
            eP = pValores[1];
            mP = pValores[2];
            mP = mP & 0xffffff;

        } else
            mP = mP + 0;


        if(c==0) {
            //Mueve la mantisa 1 a la derecha eliminando su bit menos significativo
            mP = mP >> 1;
        }
       else {
            //Mueve uno a la derecha la mantisa y suma 1
            mP = (mP >> 1) + 0x800000;
        }
        if(mP & 1) {
            //Si la mantisa del nuevo numero es igual a 1 en bits,se desplaza 1 a la derecha y
            //se suma 1
            mA = (mA >> 1) + 0x800000;
        }else {
            //Mueve la mantisa 1 a la derecha eliminando su bit menos significativo
            mA = mA >> 1;
        }
    }
    mP = mP & 0xffffff;
    mA = mA & 0xffffff;
    //Guardamos en la primera posicion del array el valor de la mantisa del producto y en la 2 el valor de la mantisa de A
    PjuntoA[0] = mP;
    PjuntoA[1] = mA;

    return PjuntoA;

}

unsigned int ALUF:: AND(unsigned int number, unsigned int position)
{
    unsigned int compruebaBits = pow(2,n - position);
    if((number & compruebaBits) == 0)
        return 0;
    else
        return 1;
}

unsigned int ALUF:: calculaAcarreo(unsigned int n1, unsigned int n2, bool acarreo, unsigned int bit)
{
    if(bit == n) {
        if(acarreo)
            return 1;
        else
            return 0;
    }else {
        unsigned int comprueba = pow (2,bit);
        if(((comprueba & n1)!= 0 && (comprueba & n2)!= 0) || ((comprueba & n1)!=0 || (comprueba & n2)!=0) && acarreo)
            acarreo = true;

        else
            acarreo = false;

        return calculaAcarreo(n1, n2, acarreo, bit+1);
        }
}

//Devuelve el numero binario en int
int ALUF::passBinario(int pos, unsigned int var, int length)
{
    if(pos>=0){
        QString conversor = aBinario(var, length);
        int num=-1;
        num=conversor.mid(conversor.length()-pos-1, 1).toInt();

        return num;
    }
    else{
        return -1;
    }
}

//Metodo que devuelve en numero binario en QString
QString ALUF::aBinario(unsigned int numero, int length)
{
    int resto, divisor = 2;
    unsigned int dividendo;
    QString binario = "";
    dividendo = numero;

    while(dividendo >= divisor){ //Mientras el dividendo sea mayor o igual que el divisor, es decir, mayor o igual que 2.
    resto = dividendo % 2;
     if(resto == 1)
      binario = "1" + binario; //Si el resto es igual a 1 concatenamos 1 a la variable string llamada binario
     else
      binario = "0" + binario; // Sino concatemanos 0
     //Es importante este orden de concatenación (primero el bit y luego el valor de la variable) esto para que nuestro número
     //en sistema binario esté ordenado correctamente.
      dividendo = dividendo/divisor; // Actualizamos el valor del dividendo dividiendolo entre 2.
    }
     if(dividendo == 1)
      binario = "1" + binario; // Por último sí el valor final del dividendo es 1 concatenamos 1 sino concatenamos 0.
     else
      binario = "0" + binario;

     if(length>=binario.length()){
        while(length!=binario.length()){
            binario="0" + binario;
        }
     }
     else{
         while(length!=binario.length()){
             binario.remove(0, 1);
         }
     }

    return binario;
}
