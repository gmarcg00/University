/********************************************************************************
** Form generated from reading UI file 'byn.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_BYN_H
#define UI_BYN_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTextBrowser>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Byn
{
public:
    QWidget *centralwidget;
    QTextBrowser *textBrowser;
    QWidget *gridLayoutWidget_2;
    QGridLayout *gridLayout_3;
    QLabel *label_5;
    QLabel *label_4;
    QLabel *label_2;
    QLabel *label_3;
    QLabel *label;
    QLineEdit *lineEdit_2;
    QLineEdit *lineEdit_3;
    QLineEdit *lineEdit_4;
    QLineEdit *lineEdit_5;
    QLineEdit *lineEdit;
    QPushButton *ejecutar;
    QLabel *label_6;
    QLineEdit *lineEdit_6;
    QPushButton *resetear;
    QPushButton *origen;
    QPushButton *destino;
    QMenuBar *menubar;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *Byn)
    {
        if (Byn->objectName().isEmpty())
            Byn->setObjectName(QString::fromUtf8("Byn"));
        Byn->resize(520, 477);
        centralwidget = new QWidget(Byn);
        centralwidget->setObjectName(QString::fromUtf8("centralwidget"));
        textBrowser = new QTextBrowser(centralwidget);
        textBrowser->setObjectName(QString::fromUtf8("textBrowser"));
        textBrowser->setGeometry(QRect(0, 0, 521, 31));
        textBrowser->setStyleSheet(QString::fromUtf8("background-color: rgb(255, 170, 0)"));
        gridLayoutWidget_2 = new QWidget(centralwidget);
        gridLayoutWidget_2->setObjectName(QString::fromUtf8("gridLayoutWidget_2"));
        gridLayoutWidget_2->setGeometry(QRect(10, 30, 351, 411));
        gridLayout_3 = new QGridLayout(gridLayoutWidget_2);
        gridLayout_3->setSpacing(8);
        gridLayout_3->setObjectName(QString::fromUtf8("gridLayout_3"));
        gridLayout_3->setContentsMargins(0, 0, 0, 0);
        label_5 = new QLabel(gridLayoutWidget_2);
        label_5->setObjectName(QString::fromUtf8("label_5"));
        QFont font;
        font.setFamily(QString::fromUtf8("Arial"));
        font.setBold(true);
        font.setWeight(75);
        label_5->setFont(font);

        gridLayout_3->addWidget(label_5, 3, 0, 1, 1);

        label_4 = new QLabel(gridLayoutWidget_2);
        label_4->setObjectName(QString::fromUtf8("label_4"));
        label_4->setFont(font);

        gridLayout_3->addWidget(label_4, 5, 0, 1, 1);

        label_2 = new QLabel(gridLayoutWidget_2);
        label_2->setObjectName(QString::fromUtf8("label_2"));
        label_2->setFont(font);

        gridLayout_3->addWidget(label_2, 1, 0, 1, 1);

        label_3 = new QLabel(gridLayoutWidget_2);
        label_3->setObjectName(QString::fromUtf8("label_3"));
        label_3->setFont(font);

        gridLayout_3->addWidget(label_3, 4, 0, 1, 1);

        label = new QLabel(gridLayoutWidget_2);
        label->setObjectName(QString::fromUtf8("label"));
        label->setFont(font);

        gridLayout_3->addWidget(label, 0, 0, 1, 1);

        lineEdit_2 = new QLineEdit(gridLayoutWidget_2);
        lineEdit_2->setObjectName(QString::fromUtf8("lineEdit_2"));
        lineEdit_2->setReadOnly(true);

        gridLayout_3->addWidget(lineEdit_2, 1, 1, 1, 1);

        lineEdit_3 = new QLineEdit(gridLayoutWidget_2);
        lineEdit_3->setObjectName(QString::fromUtf8("lineEdit_3"));
        lineEdit_3->setReadOnly(true);

        gridLayout_3->addWidget(lineEdit_3, 3, 1, 1, 1);

        lineEdit_4 = new QLineEdit(gridLayoutWidget_2);
        lineEdit_4->setObjectName(QString::fromUtf8("lineEdit_4"));
        lineEdit_4->setReadOnly(true);

        gridLayout_3->addWidget(lineEdit_4, 4, 1, 1, 1);

        lineEdit_5 = new QLineEdit(gridLayoutWidget_2);
        lineEdit_5->setObjectName(QString::fromUtf8("lineEdit_5"));
        lineEdit_5->setEnabled(true);
        lineEdit_5->setReadOnly(true);

        gridLayout_3->addWidget(lineEdit_5, 5, 1, 1, 1);

        lineEdit = new QLineEdit(gridLayoutWidget_2);
        lineEdit->setObjectName(QString::fromUtf8("lineEdit"));
        lineEdit->setReadOnly(true);

        gridLayout_3->addWidget(lineEdit, 0, 1, 1, 1);

        ejecutar = new QPushButton(gridLayoutWidget_2);
        ejecutar->setObjectName(QString::fromUtf8("ejecutar"));
        ejecutar->setStyleSheet(QString::fromUtf8("background-color: rgb(255,0,0)"));

        gridLayout_3->addWidget(ejecutar, 6, 1, 1, 1);

        label_6 = new QLabel(gridLayoutWidget_2);
        label_6->setObjectName(QString::fromUtf8("label_6"));

        gridLayout_3->addWidget(label_6, 7, 0, 1, 1);

        lineEdit_6 = new QLineEdit(gridLayoutWidget_2);
        lineEdit_6->setObjectName(QString::fromUtf8("lineEdit_6"));
        lineEdit_6->setReadOnly(true);

        gridLayout_3->addWidget(lineEdit_6, 7, 1, 1, 1);

        resetear = new QPushButton(gridLayoutWidget_2);
        resetear->setObjectName(QString::fromUtf8("resetear"));
        resetear->setStyleSheet(QString::fromUtf8("background-color: rgb(255,0,0)"));

        gridLayout_3->addWidget(resetear, 8, 0, 1, 2);

        origen = new QPushButton(centralwidget);
        origen->setObjectName(QString::fromUtf8("origen"));
        origen->setGeometry(QRect(370, 40, 134, 81));
        origen->setStyleSheet(QString::fromUtf8("background-color: rgb(170, 0, 0)"));
        destino = new QPushButton(centralwidget);
        destino->setObjectName(QString::fromUtf8("destino"));
        destino->setGeometry(QRect(370, 140, 134, 81));
        destino->setStyleSheet(QString::fromUtf8("background-color:rgb(170, 0, 0)"));
        Byn->setCentralWidget(centralwidget);
        menubar = new QMenuBar(Byn);
        menubar->setObjectName(QString::fromUtf8("menubar"));
        menubar->setGeometry(QRect(0, 0, 520, 26));
        Byn->setMenuBar(menubar);
        statusbar = new QStatusBar(Byn);
        statusbar->setObjectName(QString::fromUtf8("statusbar"));
        Byn->setStatusBar(statusbar);

        retranslateUi(Byn);

        QMetaObject::connectSlotsByName(Byn);
    } // setupUi

    void retranslateUi(QMainWindow *Byn)
    {
        Byn->setWindowTitle(QCoreApplication::translate("Byn", "MainWindow", nullptr));
        textBrowser->setHtml(QCoreApplication::translate("Byn", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
"<html><head><meta name=\"qrichtext\" content=\"1\" /><style type=\"text/css\">\n"
"p, li { white-space: pre-wrap; }\n"
"</style></head><body style=\" font-family:'MS Shell Dlg 2'; font-size:7.8pt; font-weight:400; font-style:normal;\">\n"
"<p align=\"center\" style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">ALGORITMO 2:IM\303\201GENES A TONOS ROSADOS</p></body></html>", nullptr));
        label_5->setText(QCoreApplication::translate("Byn", "Tiempo 3", nullptr));
        label_4->setText(QCoreApplication::translate("Byn", "Tiempo 5", nullptr));
        label_2->setText(QCoreApplication::translate("Byn", "Tiempo 2", nullptr));
        label_3->setText(QCoreApplication::translate("Byn", "Tiempo 4", nullptr));
        label->setText(QCoreApplication::translate("Byn", "Tiempo 1", nullptr));
        ejecutar->setText(QCoreApplication::translate("Byn", "Ejecutar", nullptr));
        label_6->setText(QCoreApplication::translate("Byn", "<html><head/><body><p align=\"center\"><span style=\" font-weight:600;\">Media</span></p></body></html>", nullptr));
        resetear->setText(QCoreApplication::translate("Byn", "Resetear", nullptr));
#if QT_CONFIG(whatsthis)
        origen->setWhatsThis(QCoreApplication::translate("Byn", "<html><head/><body><p><br/></p></body></html>", nullptr));
#endif // QT_CONFIG(whatsthis)
        origen->setText(QCoreApplication::translate("Byn", "Seleccionar Imagen", nullptr));
#if QT_CONFIG(whatsthis)
        destino->setWhatsThis(QCoreApplication::translate("Byn", "<html><head/><body><p><br/></p></body></html>", nullptr));
#endif // QT_CONFIG(whatsthis)
        destino->setText(QCoreApplication::translate("Byn", "Seleccionar Destino", nullptr));
    } // retranslateUi

};

namespace Ui {
    class Byn: public Ui_Byn {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_BYN_H
