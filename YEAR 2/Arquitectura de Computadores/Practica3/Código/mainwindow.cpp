#include "mainwindow.h"
#include <QApplication>
#include <stdlib.h>
#include "ui_mainwindow.h"
#include "quicksort.h"
#include "byn.h"

using namespace std;

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_quicksort_clicked()
{
    Quicksort*qs=new Quicksort();
    qs->show();
}

void MainWindow::on_byn_clicked()
{
    Byn*byn=new Byn();
    byn->show();
}
