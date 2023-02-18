#!/bin/bash
	entrada=1
	echo Selecciona una opcion
	while test $entrada -ne 4
	do
		echo Copia el codigo del programa 
		echo Compila el codigo del programa 
		echo Ejecuta el programa 
		echo Salir del Script 
		read entrada

		case $entrada in

			1) echo Copia el codigo del problema
			   cat programa.c;;

			2) echo  Compila el programa
			   gcc programa.c -o programa;;

			3) echo  Ejecuta el programa
			   read -p "Introduzca el numero de pinches" pinche
			   if test -f programa
			   then
					if test -x programa
					then
						./programa $pinche
					else
						echo No tiene permisos de ejecucion
					fi
				else
					echo Compile y ejecute el codigo
					
			   fi;;

			4) echo  Salir del programa
			   exit 0;;

			*) echo Opcion incorrecta;;
		esac
	done
