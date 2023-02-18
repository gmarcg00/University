#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "vehiculo.h"


namespace Ui {
class MainWindow;
}


class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

    vector <Vehiculo*> listaVehiculos;

private slots:
    void on_pushButtonMatricula_clicked();
    void on_pushButtonNewCar_clicked();
    void on_motor_clicked();
    void on_combustible_clicked();
    void on_vagones_clicked();
    void addVehiculo();


private:
    Ui::MainWindow *ui;
    QString nombre,ruedas,tipoCombustible,color,numVagones,matricula;
    int potencia;
    bool combustible,alas,reactores,tren,locomotora,kitReparaciones,ruedaRepuesto,vagones,motor;
};
#endif // MAINWINDOW_H
