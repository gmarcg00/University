#include "byn.h"
#include "ui_byn.h"
#include <iostream>
#include <QFileDialog>
#include <QImageReader>
#include <QImage>
#include <QLabel>
#include <QTGui>

using namespace std;

Byn::Byn(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::Byn)
{
    ui->setupUi(this);

    ui->destino->setEnabled(false);
    ui->ejecutar->setEnabled(false);
    ui->resetear->setEnabled(false);
}

Byn::~Byn()
{
    delete ui;
}

void Byn::on_ejecutar_clicked()
{
    clock_t start;
    clock_t final;
    double totalMs;
    start = clock();
    QImage imageaux = image->convertToFormat(QImage::Format_RGBX64);
    for(int i=0;i<imageaux.width();i++){
        for(int j=0;j<imageaux.height();j++){
            QColor color(imageaux.pixel(i,j));
            color.setGreen(88);
            imageaux.setPixelColor(i,j,color);
        }
    }
    QString imageName = "ImagenRosada.jpg";
    imageaux.save(rutaCopia+"\\"+imageName);
    final = clock();
    QLabel*myLabel=new QLabel();
    myLabel->setPixmap(QPixmap::fromImage(imageaux));
    myLabel->show();
    totalMs=((double)(final-start)/CLOCKS_PER_SEC)*1000.0;
    QString tiempo = QString::number(totalMs);
    tiempo = tiempo+ " ms";
    if(ui->lineEdit->text()==""){
        ui->lineEdit->setText(tiempo);
        totalTiempo.clear();
        totalTiempo.push_back(totalMs);
    }else if(ui->lineEdit_2->text()==""){
        ui->lineEdit_2->setText(tiempo);
        totalTiempo.push_back(totalMs);
    }else if(ui->lineEdit_3->text()==""){
        ui->lineEdit_3->setText(tiempo);
        totalTiempo.push_back(totalMs);
    }else if(ui->lineEdit_4->text()==""){
        ui->lineEdit_4->setText(tiempo);
        totalTiempo.push_back(totalMs);
    }else if(ui->lineEdit_5->text()==""){
        ui->lineEdit_5->setText(tiempo);
        totalTiempo.push_back(totalMs);
        double total;
        total = totalTiempo[0]+totalTiempo[1]+totalTiempo[2]+totalTiempo[3]+totalTiempo[4];
        double media=total/5.00;
        QString mediaa = QString::number(media);
        mediaa = mediaa+ " ms";
        ui->lineEdit_6->setText(mediaa);
    }
}

void Byn::on_resetear_clicked()
{
    ui->lineEdit->setText("");
    ui->lineEdit_2->setText("");
    ui->lineEdit_3->setText("");
    ui->lineEdit_4->setText("");
    ui->lineEdit_5->setText("");
    ui->lineEdit_6->setText("");
}

void Byn::on_origen_clicked(){
    QString fileName = QFileDialog::getOpenFileName(this,
        tr("Open Image"), "",tr("Image Files (*.png *.jpg *.bmp)"));
    image = new QImage();
    image->load(fileName);
    //image->save(fileName);
    ui->origen->setStyleSheet("background-color: rgb(0,0,170)");
    ui->destino->setEnabled(true);
}

void Byn::on_destino_clicked(){
    rutaCopia = QFileDialog::getExistingDirectory();
    cout << rutaCopia.toStdString()<<endl;
    ui->destino->setStyleSheet("background-color:rgb(0,0,170)");
    ui->ejecutar->setEnabled(true);
    ui->resetear->setEnabled(true);
}
