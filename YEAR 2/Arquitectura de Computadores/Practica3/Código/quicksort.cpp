#include "quicksort.h"
#include "ui_quicksort.h"
#include <QFileDialog>
#include <iostream>
#include <vector>
#include <fstream>

using namespace std;

Quicksort::Quicksort(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::Quicksort)
{
    ui->setupUi(this);
}

Quicksort::~Quicksort()
{
    delete ui;
}

int Quicksort::divide(int *array, int start, int end)
{
    int left;
    int right;
    int pivot;
    int aux;
    pivot = array[start];
    left = start;
    right = end;

    while(left<right){
        while(array[right] > pivot){
            right--;
        }

        while((left<right)&&(array[left]<=pivot)){
            left++;
        }

        if(left<right){
            aux=array[left];
            array[left]=array[right];
            array[right]=aux;
        }
    }

    aux=array[right];
    array[right]=array[start];
    array[start]=aux;

    return right;
}

void Quicksort::quicksortt(int *numeros1, int start, int end)
{
    int pivot;

    if(start < end){
        pivot = divide(numeros1, start, end);
        quicksortt(numeros1, start, pivot-1);
        quicksortt(numeros1, pivot+1, end);
    }

}

void Quicksort::on_ejecutar_clicked()
{    
    ofstream numeros;
    ofstream file;
    ofstream resultados;
    srand(time(NULL));
    clock_t start;
    clock_t final;
    double totalMs;
    int num;
    int i=0;
    int j=0;
    int numeros1[2050];
    if(ui->lineEdit->text()==""){
        QString rutaCopia2 = QFileDialog::getExistingDirectory();
        QString numberos = "/numeros.txt";
        rutaCopia2.append(numberos);
        numeros.open(rutaCopia2.toStdString());
    }
    while(i<2050){
        num= 1 + rand()%9;
        numeros1[i] = num;
        numeros << numeros1[i];
        i++;
    }
    start = clock();
    quicksortt(numeros1,0,2049);
    final = clock();
    totalMs=((double)(final-start)/CLOCKS_PER_SEC)*1000.0;
    QString tiempo = QString::number(totalMs);
    tiempo = tiempo+ " ms";
    if(ui->lineEdit->text()==""){
        /*QString rutaCopia1 = QFileDialog::getExistingDirectory();
        QString tiempos = "/tiempos.txt";
        rutaCopia1.append(tiempos);
        file.open(rutaCopia1.toStdString());
        file << "Hola";*/
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
        file << "Primer tiempo: " << totalTiempo[0] << "\n"
             << "Segundo tiempo :" << totalTiempo[1] << "\n"
             << "Tercer tiempo: " << totalTiempo[2] << "\n"
             << "Cuarto tiempo :" << totalTiempo[3] << "\n";
    }else if(ui->lineEdit_5->text()==""){
        ui->lineEdit_5->setText(tiempo);
        totalTiempo.push_back(totalMs);
        QString rutaCopia1 = QFileDialog::getExistingDirectory();
        QString tiempos = "/tiempos.txt";
        rutaCopia1.append(tiempos);
        file.open(rutaCopia1.toStdString());
        double total;
        file << "Primer tiempo: " << totalTiempo[0] << "\n"
             << "Segundo tiempo :" << totalTiempo[1] << "\n"
             << "Tercer tiempo: " << totalTiempo[2] << "\n"
             << "Cuarto tiempo :" << totalTiempo[3] << "\n"
             << "Quinto tiempo: " << totalTiempo[4] << "\n";
        total = totalTiempo[0]+totalTiempo[1]+totalTiempo[2]+totalTiempo[3]+totalTiempo[4];
        double media=total/5.00;
        file<< "Media: "<< media << "\n" << endl;
        QString numberos = "/resultados.txt";
        QString rutaCopia3 = QFileDialog::getExistingDirectory();
        rutaCopia3.append(numberos);
        resultados.open(rutaCopia3.toStdString());
        while(j<2050){
            resultados << numeros1[j];
            j++;
        }
        resultados.close();
        file.close();
        numeros.close();
        QString mediaa = QString::number(media);
        mediaa = mediaa+ " ms"; 
        ui->lineEdit_6->setText(mediaa);

    }
}

void Quicksort::on_resetear_clicked()
{
    ui->lineEdit->setText("");
    ui->lineEdit_2->setText("");
    ui->lineEdit_3->setText("");
    ui->lineEdit_4->setText("");
    ui->lineEdit_5->setText("");
    ui->lineEdit_6->setText("");
}
