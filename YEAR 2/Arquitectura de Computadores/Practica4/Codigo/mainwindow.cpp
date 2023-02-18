#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "aluf.h"

using namespace std;

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    this->setStyleSheet("QMainWindow{background-image: url(fon.jpg)}");
}

MainWindow::~MainWindow()
{
    delete ui;
}


void MainWindow::on_suma_clicked()
{
    QString numm1=ui->Num1->text();
    num1 = numm1.toFloat();

    QString numm2=ui->Num2->text();
    num2 = numm2.toFloat();

    QString NaN = "NaN";
    valores o1,o2;
    o1.real = num1;
    o2.real = num2;

    char hexadecimalchar[11];
    sprintf(hexadecimalchar, "0x%x",o1.entero);
    QString Hex1 = hexadecimalchar;
    ui->Num1_2->setText(Hex1);

    sprintf(hexadecimalchar, "0x%x",o2.entero);
    QString Hex2 = hexadecimalchar;
    ui->Num2_2->setText(Hex2);

    var1.real=ui->Num1->text().toFloat();
    var2.real=ui->Num2->text().toFloat();
    ui->Num1_3->setText(printIEEE(var1));
    ui->Num2_3->setText(printIEEE(var2));

    unsigned int expA,fracA,sigA,expB,fracB,sigB;

    expA = o1.fields.e;
    fracA = o1.fields.f;
    sigA = o1.fields.s;

    expB = o2.fields.e;
    fracB = o2.fields.f;
    sigB = o2.fields.s;
    unsigned int mA = fracA+ 0b100000000000000000000000;
    unsigned int mB = fracB + 0b100000000000000000000000;

    if(expA == expB && fracA == fracB && sigA!=sigB){
        ui->ResultadoDec->setText("0");
        ui->ResultadoHex->setText("0x00000000");
        ui->ResultadoIEEE754->setText("0 00000000 00000000000000000000000");
    }else{


        ALUF alu;
        unsigned int* solucion;
        solucion = alu.suma(mA, mB, sigA, sigB, expA,  expB, false);

        union valores solu;
        solu.fields.s = solucion[0];
        solu.fields.e = solucion[1];
        solu.fields.f = solucion[3];
        if(solucion[1] > 254){
            ui->ResultadoDec->setText("Inf");
            ui->ResultadoHex->setText("0x7f800000");
            ui->ResultadoIEEE754->setText("0 11111111 00000000000000000000000");
        }else{
            float soluFloat = solu.real;
            QString solucionDecimal;
            QString solucionIEEE754;
            solucionIEEE754=QString::number(solucion[0])+" "+printBinary(solucion[1],8)+ " "+ printBinary(solucion[2],23);
            ui->ResultadoIEEE754->setText(solucionIEEE754);
            ui->ResultadoDec->setText(solucionDecimal.setNum(soluFloat));
            sprintf(hexadecimalchar, "0x%x",solu.entero);
            QString SolucionHex = hexadecimalchar;
            ui->ResultadoHex->setText(SolucionHex);
        }
    }
}


void MainWindow::on_division_clicked()
{

    QString numm1=ui->Num1->text();
       num1 = numm1.toFloat();

       QString numm2=ui->Num2->text();
       num2 = numm2.toFloat();

       union valores o1;
       union valores o2;
       o1.real = num1;
       o2.real = num2;


       char hexadecimalchar[11];
           sprintf(hexadecimalchar, "0x%x",o1.entero);
           QString Hex1 = hexadecimalchar;
           ui->Num1_2->setText(Hex1);

       char hexadecimalchar2[11];
           sprintf(hexadecimalchar2, "0x%x",o2.entero);
           QString Hex2 = hexadecimalchar2;
           ui->Num2_2->setText(Hex2);

           var1.real=ui->Num1->text().toFloat();
           var2.real=ui->Num2->text().toFloat();
           ui->Num1_3->setText(printIEEE(var1));
           ui->Num2_3->setText(printIEEE(var2));

            if(num1 == 0) {
                        ui->ResultadoDec->setText("0");
                        ui->ResultadoHex->setText("0x00000000");

            }

            if(num2 == 0) {
                        ui->ResultadoDec->setText("0");
                        ui->ResultadoHex->setText("0x00000000");

            }

            unsigned int mA= var1.fields.f;
            unsigned int mB= var2.fields.f;
            unsigned int expA = var1.fields.e;
            unsigned int expB = var2.fields.e;
            unsigned int sigA = var1.fields.s;
            unsigned int sigB = var2.fields.s;
            cout << var1.fields.e << endl;

           if(ui->Num1->text()=="0" || ui->Num2->text()=="0"){
               ui->ResultadoDec->setText("NaN");
               ui->ResultadoHex->setText("0x7fffffff");
               ui->ResultadoIEEE754->setText("0 11111111 11111111111111111111111");
           }else if(var1.fields.e == 255){
               ui->ResultadoDec->setText("Inf");
               ui->ResultadoHex->setText("0x7f800000");
               ui->ResultadoIEEE754->setText("0 11111111 00000000000000000000000");
           }else if(var2.fields.e == 255){
               ui->ResultadoDec->setText("NaN");
               ui->ResultadoHex->setText("0x7fffffff");
               ui->ResultadoIEEE754->setText("0 11111111 11111111111111111111111");
           }else{
               ALUF alu;
               unsigned int* solucion;
               solucion = alu.divide(mA, mB, sigA, sigB, expA, expB);
               union valores solu;
               solu.fields.s =solucion[0];
               solu.fields.e =solucion[1];
               solu.fields.f =solucion[2];
               if(solucion[1] > 254){
                   ui->ResultadoDec->setText("Inf");
                   ui->ResultadoHex->setText("0x7f800000");
                   ui->ResultadoIEEE754->setText("0 11111111 00000000000000000000000");
               }else{
                   float soluFloat = solu.real;
                   QString solucionDecimal;
                   QString solucionIEEE754;
                   ui->ResultadoDec->setText(solucionDecimal.setNum(soluFloat));
                   solucionIEEE754=QString::number(solucion[0])+" "+printBinary(solucion[1],8)+ " "+ printBinary(solucion[2],23);
                   ui->ResultadoIEEE754->setText(solucionIEEE754);
                   sprintf(hexadecimalchar, "0x%x",solu.entero);
                   QString SolucionHex = hexadecimalchar;
                   ui->ResultadoHex->setText(SolucionHex);
               }
           }

}

void MainWindow::on_multiplicacion_clicked()
{
    QString numm1=ui->Num1->text();
    num1 = numm1.toFloat();

    QString numm2=ui->Num2->text();
    num2 = numm2.toFloat();

    union valores o1;
    union valores o2;
    o1.real = num1;
    o2.real = num2;


    char hexadecimalchar[11];
        sprintf(hexadecimalchar, "0x%x",o1.entero);
        QString Hex1 = hexadecimalchar;
        ui->Num1_2->setText(Hex1);

    char hexadecimalchar2[11];
        sprintf(hexadecimalchar2, "0x%x",o2.entero);
        QString Hex2 = hexadecimalchar2;
        ui->Num2_2->setText(Hex2);

        var1.real=ui->Num1->text().toFloat();
        var2.real=ui->Num2->text().toFloat();
        ui->Num1_3->setText(printIEEE(var1));
        ui->Num2_3->setText(printIEEE(var2));

    unsigned int expA,fracA,sigA,expB,fracB,sigB;
    expA = o1.fields.e;
    fracA = o1.fields.f;
    sigA = o1.fields.s;
    expB = o2.fields.e;
    fracB = o2.fields.f;
    sigB = o2.fields.s;

    unsigned int mA = fracA+ 0b100000000000000000000000;
    unsigned int mB = fracB + 0b100000000000000000000000;
    ALUF alu;
    unsigned int* solucion;
    solucion = alu.multiplica(mA, mB, sigA, sigB, expA, expB);

    union valores solu;
    solu.fields.s = solucion[0];
    solu.fields.e = solucion[1];
    solu.fields.f = solucion[2];
    cout << expA << endl;
    if(solucion[1] > 254 ){
        ui->ResultadoDec->setText("Inf");
        ui->ResultadoHex->setText("0x7f800000");
        ui->ResultadoIEEE754->setText("0 11111111 00000000000000000000000");
    }else if(solucion[1]<=0 || ( expA < 127 && expB < 127)){
        ui->ResultadoDec->setText("NaN");
        ui->ResultadoHex->setText("0x7fffffff");
        ui->ResultadoIEEE754->setText("0 11111111 11111111111111111111111");
    }else{
    float soluFloat = solu.real;
    QString solucionDecimal;
    QString solucionIEEE754;
    solucionIEEE754=QString::number(solucion[0])+" "+printBinary(solucion[1],8)+ " "+ printBinary(solucion[2],23);
    ui->ResultadoIEEE754->setText(solucionIEEE754);
    ui->ResultadoDec->setText(solucionDecimal.setNum(soluFloat));
    sprintf(hexadecimalchar, "0x%x",solu.entero);
    QString SolucionHex = hexadecimalchar;
    ui->ResultadoHex->setText(SolucionHex);
    }

}

QString MainWindow:: printBinary(int n, int i)
{

    int k;
    QString binario;

    for (k = i - 1; k >= 0; k--) {

        if ((n >> k) & 1){
            binario=binario+"1";
        }
        else{
            binario=binario+"0";
        }
    }

    return binario;
}

QString MainWindow::printIEEE(valores var)
{

    QString binario;
    binario=QString::number(var.fields.s)+" "+printBinary(var.fields.e, 8)+" "+printBinary(var.fields.f, 23);

    return binario;
}
