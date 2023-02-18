#ifndef BYN_H
#define BYN_H

#include <QMainWindow>
#include <QImage>
#include <QFileDialog>

using namespace std;

namespace Ui {
class Byn;
}

class Byn : public QMainWindow
{
    Q_OBJECT

public:
    explicit Byn(QWidget *parent = 0);
    ~Byn();

    vector <double> totalTiempo;
private slots:

    void on_ejecutar_clicked();
    void on_resetear_clicked();
    void on_origen_clicked();
    void on_destino_clicked();

private:
    Ui::Byn *ui;
    QString rutaCopia;
    QDir rutaCopiaa;
    QImage *image;
    QPixmap pixmap;
    QColor color;
};

#endif // BYN_H
