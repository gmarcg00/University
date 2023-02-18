#include "mainwindow.h"
#include "quicksort.h"
#include "byn.h"
#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow mw;
     //Quicksort mw;
  // Byn mw;
    mw.show();

    return a.exec();
}
