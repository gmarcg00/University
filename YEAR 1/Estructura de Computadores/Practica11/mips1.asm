 .data # comienzo del segmento de datos
cad1: .asciiz "Introduzca un n�mero" #Forma de declarar una cadena
cad2: .asciiz "El resultado es: " #Forma de declarar una cadena
 .globl main # la etiqueta main es global
 .text # comienzo del segmento de texto (instrucciones)
main: # Comienza la funci�n main
 li $v0, 4 # c�digo de la llamada print_string
 la $a0, cad1 #imprime la cadena que est� en la direcci�n $a0
 syscall
 li $v0, 5 # c�digo de la llamada read_int
 syscall # ahora se introduce un n�mero en la consola
 move $t0,$v0 # se guarda el contenido de $v0 (el n�mero) en $t1
 mul $t1,$t0,$t0 # se eleva al cuadrado
li $v0, 4 # c�digo de la llamada print_string
 la $a0, cad2 #imprime la cadena que est� en la direcci�n $a0
 syscall
 li $v0,1 # c�digo de llamada print_int
 move $a0, $t1 # $a0 = n�mero que se quiere imprimir
 syscall # se realiza la llamada
 li $v0, 10 # c�digo de llamada exit
 syscall # llamada al sistema 