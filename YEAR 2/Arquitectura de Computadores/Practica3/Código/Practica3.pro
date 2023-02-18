
QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

INCLUDEPATH += $$PWD

TARGET = Practica3
TEMPLATE = app


SOURCES += main.cpp \
    byn.cpp \
    mainwindow.cpp \
    quicksort.cpp

FORMS += \
    byn.ui \
    mainwindow.ui \
    quicksort.ui

HEADERS += \
    byn.h \
    mainwindow.h \
    quicksort.h
