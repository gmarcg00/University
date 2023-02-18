#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "QMessageBox"
#include <stdlib.h>
#include <time.h>
#include <string>
#include <vehiculo.h>
#include <iostream>


using namespace std;

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    //Medidas de la ventana
    this->setFixedSize(650,450);
     ui->horizontalPotencia->setEnabled(false);
     ui->spinPotencia->setEnabled(false);
     ui->tipoCombustible->setEnabled(false);
     ui->horizontalVagones->setEnabled(false);

}

MainWindow::~MainWindow()
{
    delete ui;
}

//Generamos matricula aleatoria al clickar en "Generar Matricula"
void MainWindow::on_pushButtonMatricula_clicked(){
    char matricula[8];
    string numeros="0123456789";
    string letras="BCFGHJKLMNPRSTVWXYZ";
    
    srand(time(NULL));

    for(int i=0;i<4;i++){
        matricula[i]=numeros[rand()%numeros.length()];
    }

    for(int i=4;i<7;i++){
        matricula[i]=letras[rand()%letras.length()];
    }

    matricula[7]='\0';
    ui->lineEditMatricula->setText(matricula);
}


void MainWindow:: on_motor_clicked(){
    if(ui->motor->isChecked()){
        ui->horizontalPotencia->setEnabled(true);
        ui->spinPotencia->setEnabled(true);
        potencia=ui->spinPotencia->value();
    }else{
         ui->horizontalPotencia->setEnabled(false);
          ui->spinPotencia->setEnabled(false);
    }
}

void MainWindow::on_combustible_clicked(){
    if(ui->combustible->isChecked()){
        ui->tipoCombustible->setEnabled(true);
       tipoCombustible=ui->tipoCombustible->currentText();
    }else{
        ui->tipoCombustible->setEnabled(false);
    }
}

void MainWindow::on_vagones_clicked(){
    if(ui->vagones->isChecked()){
        ui->horizontalVagones->setEnabled(true);
        vagones=ui->spinVagones->value();
    }else{
        ui->horizontalVagones->setEnabled(false);
    }
}

void MainWindow:: addVehiculo(){
    Vehiculo *v1 =new Vehiculo(nombre,ruedas,motor,tipoCombustible,color,vagones,potencia,combustible,alas,reactores,tren,locomotora,kitReparaciones);
    listaVehiculos.push_back(v1);

    QString numero=QString::number(listaVehiculos.size());
    ui->lineEditVehiculos->setText(numero);
}
void MainWindow::on_pushButtonNewCar_clicked(){
    nombre=ui->lineEditNombre->text();
    ruedas=ui->ruedas->currentText();
    matricula=ui->lineEditMatricula->text();
    motor=ui->motor->isChecked();
    combustible=ui->combustible->isChecked();
    color=ui->color->currentText();
    ruedaRepuesto=ui->ruedaRepuesto->isChecked();
    kitReparaciones=ui->reparacionPinchazos->isChecked();
    alas=ui->alas->isChecked();
    reactores=ui->reactores->isChecked();
    tren=ui->tren->isChecked();
    locomotora=ui->locomotora->isChecked();
    vagones=ui->vagones->isChecked();

    QMessageBox mensaje;

    if(ruedas=="2" && motor==false && combustible==false && alas==false && tren==false && locomotora==false && vagones==false && kitReparaciones==true){
        mensaje.setText("El vehiculo es una bicicleta");
        mensaje.exec();
        addVehiculo();


    }

    else if(ruedas=="3" && motor==false && combustible==false && alas==false && reactores==false && tren==false && locomotora==false && vagones==false && kitReparaciones==true){
        mensaje.setText("El vehiculo es un triciclo");
        mensaje.exec();
        addVehiculo();
    }

    else if(ruedas=="2" && motor==true && alas==false && reactores==false && tren==false &&locomotora==false && vagones==false && kitReparaciones==true){
        if(ui->tipoCombustible->currentText()!="Queroseno"){
            mensaje.setText("El vehiculo es una motocicleta");
            mensaje.exec();
            addVehiculo();
        }else{
            mensaje.setText("La configuracion es incorrecta");
            mensaje.exec();
        }

    }
    else if(ruedas=="4" && motor==true && alas==false && reactores==false && tren==false && locomotora==false && vagones ==false && ruedaRepuesto==true){
         if(ui->horizontalPotencia->value()<250 && ui->tipoCombustible->currentText()!="Queroseno"){
             mensaje.setText("El vehiculo es un coche");
             mensaje.exec();
             addVehiculo();
         }else{
             mensaje.setText("La configuracion es incorrecta");
             mensaje.exec();
         }

    }
    else if(ruedas=="4" && motor==true && combustible==true && alas==false && reactores==false && tren==false && locomotora==false && vagones==false && kitReparaciones==true){
        if(ui->horizontalPotencia->value()>=250 && ui->horizontalPotencia->value()<=450 && ui->tipoCombustible->currentText()=="Gasolina" || ui->tipoCombustible->currentText()=="Eléctrico"){
            mensaje.setText("El vehiculo es un coche deportivo");
            mensaje.exec();
            addVehiculo();
        }else{
            mensaje.setText("La configuracion es incorrecta");
            mensaje.exec();
        }
   }else if(ruedas=="6" && motor==true && combustible==true && color=="Blanco" && alas==true && reactores==true && tren==true && locomotora==false && vagones==false && ruedaRepuesto==true){
        if(ui->horizontalPotencia->value()==450 && ui->tipoCombustible->currentText()=="Queroseno"){
            mensaje.setText("El vehiculo es un avion");
            mensaje.exec();
            addVehiculo();
        }else{
            mensaje.setText("La configuracion es incorrecta");
            mensaje.exec();
        }
    }
    else if(ruedas=="40" && motor==true && combustible==true && color=="Negro" && alas==false && reactores==false && tren==false && locomotora==true && vagones==true && ruedaRepuesto==true){
        if((ui->horizontalPotencia->value()==450) && (ui->tipoCombustible->currentText()=="Eléctrico") || (ui->tipoCombustible->currentText()=="Diesel" ))
        mensaje.setText("El vehiculo es un tren");
        mensaje.exec();
        addVehiculo();
    }else{
        mensaje.setText("La configuracion es incorrecta");
        mensaje.exec();
    }


}
