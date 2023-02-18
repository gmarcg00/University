.data
cad1: .asciiz "Introduce un numero"
.text
.globl main
main:
	li $v0 4
	li $a0 cade1
	syscall
	
	li $v0 