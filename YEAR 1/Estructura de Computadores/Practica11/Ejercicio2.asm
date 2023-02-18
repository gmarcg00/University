.data # Zona de memoria de la máquina donde se ñamacenan los datos
num1: .word 3
num2: .word 7
salto: .asciiz "\n"
cadA: .asciiz "Introduzca el numero A: "
cadB: .asciiz "Introduzca el numero B: "
cadC: .asciiz "Introduzca el numero C: "
cadD: .asciiz "Introduzca el numero D: "
cadAB: .asciiz "El resultado de sumar A y B es: "
cadCD: .asciiz "El resultado de restar C y D es: "
cadABCD: .asciiz "El resultado de sumar A B C y D es: "
cadA2: .asciiz "El resultado de A al cuadrado es: "
cad3: .asciiz "El desplazamiento de 3 bits de A es: "
cad73: .asciiz "El resto de divir 7 entre 3 es: "
.globl main # Directiva de etiqueta global
 .text # Inicio del segmento de texto (instrucciones) 
main: #etiqueta main
li $v0, 4
la $a0, cadA #Imprime la cadA
syscall

li $v0, 5 #Lee el numero A
syscall 
move $t0, $v0

li $v0, 4
la $a0, cadB #Imprime la cadB
syscall

li $v0, 5 #Lee el numero B
syscall
move $t1, $v0

li $v0, 4
la $a0, cadC #Imprime la cadC
syscall

li $v0, 5 #Lee el numero C
syscall
move $t2, $v0

li $v0, 4
la $a0, cadD #Imprime la cadD
syscall

li $v0, 5 #Lee el numero D
syscall
move $t3, $v0

li $v0, 4
la $a0, salto
syscall

add $t4, $t0, $t1 #Suma A con B

li $v0, 4
la $a0, cadAB #Imprime la cadAB
syscall

li $v0, 1
move $a0, $t4 #Imprime el resultado de la suma
syscall

li $v0, 4
la $a0, salto #Imprime la cadena salto
syscall

sub $t5, $t2, $t3 #Resta C y D y lo guarda en t5

li $v0, 4
la $a0, cadCD #Imprime la cadCD
syscall

li $v0, 1
move $a0, $t5 #Imprime el resultado de la resta
syscall

li $v0, 4
la $a0, salto #Imprime la cadena salto
syscall

add $t4, $t4, $t2 #Suma A B C y lo guarda en t4
add $t4, $t4, $t3 #Suma A B C D y lo guarda en t4

li $v0, 4
la $a0, cadABCD #Imprime la cadABCD
syscall

li $v0, 1
move $a0, $t4 #Imprime el resultado de la suma
syscall

li $v0, 4
la $a0, salto #Imprime la cadena salto
syscall

mul $t6, $t0, $t0 #Multiplica A por A y lo almacena en t6

li $v0, 4
la $a0, cadA2 #Imprime la cadA2
syscall

li $v0, 1
move $a0, $t6 #Imprime el resultado de A por A
syscall

li $v0, 4
la $a0, salto #Imprime la cadena salto
syscall

lw $t5, num1 #Almacena un 3 en t5 para hacer el salto de 3 bits
sllv $t4, $t0, $t5 #Salto a la izquierda de 3 bits de t0 almacenado en t4

li $v0, 4
la $a0, cad3 #Imprime la cad3
syscall

li $v0, 1
move $a0, $t4 #Imprime el salto de 3 bits a la izquierda de A
syscall

li $v0, 4
la $a0, salto #Imprime la cadena salto
syscall

lw $t6, num2
div $t6, $t5 #Guarda el resto de la divion en t4
mfhi $t4

li $v0, 4
la $a0, cad73 #Imprime la cad73
syscall

li $v0, 1
move $a0, $t4 #Imprime el resto de dividir 7 entre 3
syscall

li $v0, 10 #Exit