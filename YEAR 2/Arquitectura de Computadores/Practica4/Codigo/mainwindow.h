#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

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

    valores var1;
    valores var2;

private slots:

    void on_suma_clicked();
    void on_multiplicacion_clicked();
    void on_division_clicked();
    QString printBinary(int n,int i);
    QString printIEEE(valores var);

private:
    Ui::MainWindow *ui;
    float num1;
    float num2;
};

#endif // MAINWINDOW_H
