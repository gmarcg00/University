#ifndef QUICKSORT_H
#define QUICKSORT_H

#include <QMainWindow>
#include <QFileDialog>
#include <iostream>
#include <vector>

using namespace std;

namespace Ui {
class Quicksort;
}

class Quicksort : public QMainWindow
{
    Q_OBJECT

public:
    explicit Quicksort(QWidget *parent = 0);
    ~Quicksort();
    int divide(int *array, int start, int end);
    void quicksortt(int *array, int start, int end);

    vector <double> totalTiempo;

private slots:

    void on_ejecutar_clicked();
    void on_resetear_clicked();

private:
    Ui::Quicksort *ui;

};

#endif // QUICKSORT_H
