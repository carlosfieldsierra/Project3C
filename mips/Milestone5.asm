main:
	# CopyQuadruple
	li $t0, 100
	# ParameterQuadruple
	move $a0, $t1
	# ParameterQuadruple
	move $a1, $t0
	# CallQuadruple
	# Prelouge
	addi $sp, $sp, -100
	sw $ra, 96($sp)
	addiu $fp, $sp, 96
	sw $s0, 92($sp)
	sw $s1, 88($sp)
	sw $s2, 84($sp)
	sw $s3, 80($sp)
	sw $s4, 76($sp)
	sw $s5, 72($sp)
	sw $s6, 68($sp)
	sw $s7, 64($sp)
	sw $a0, 60($sp)
	sw $a1, 56($sp)
	sw $a2, 52($sp)
	sw $a3, 48($sp)
	sw $t0, 44($sp)
	sw $t1, 40($sp)
	sw $t2, 36($sp)
	sw $t3, 32($sp)
	sw $t4, 28($sp)
	sw $t5, 24($sp)
	sw $t6, 20($sp)
	sw $t7, 16($sp)
	sw $t8, 12($sp)
	sw $t9, 8($sp)
	jal cond
	# Epilouge
	lw $ra, 96($sp)
	lw $s7, 64($sp)
	lw $s6, 68($sp)
	lw $s5, 72($sp)
	lw $s4, 76($sp)
	lw $s3, 80($sp)
	lw $s2, 84($sp)
	lw $s1, 88($sp)
	lw $s0, 92($sp)
	lw $a3, 48($sp)
	lw $a2, 52($sp)
	lw $a1, 56($sp)
	lw $a0, 60($sp)
	lw $t9, 8($sp)
	lw $t8, 12($sp)
	lw $t7, 16($sp)
	lw $t6, 20($sp)
	lw $t5, 24($sp)
	lw $t4, 28($sp)
	lw $t3, 32($sp)
	lw $t2, 36($sp)
	lw $t1, 40($sp)
	lw $t0, 44($sp)
	addi $sp, $sp, 100
	move $t2, $v0
	# CopyQuadruple
	li $t3, 0
	# ParameterQuadruple
	move $a0, $t4
	# ParameterQuadruple
	move $a1, $t3
	# CallQuadruple
	# Prelouge
	addi $sp, $sp, -100
	sw $ra, 96($sp)
	addiu $fp, $sp, 96
	sw $s0, 92($sp)
	sw $s1, 88($sp)
	sw $s2, 84($sp)
	sw $s3, 80($sp)
	sw $s4, 76($sp)
	sw $s5, 72($sp)
	sw $s6, 68($sp)
	sw $s7, 64($sp)
	sw $a0, 60($sp)
	sw $a1, 56($sp)
	sw $a2, 52($sp)
	sw $a3, 48($sp)
	sw $t0, 44($sp)
	sw $t1, 40($sp)
	sw $t2, 36($sp)
	sw $t3, 32($sp)
	sw $t4, 28($sp)
	sw $t5, 24($sp)
	sw $t6, 20($sp)
	sw $t7, 16($sp)
	sw $t8, 12($sp)
	sw $t9, 8($sp)
	jal cond
	# Epilouge
	lw $ra, 96($sp)
	lw $s7, 64($sp)
	lw $s6, 68($sp)
	lw $s5, 72($sp)
	lw $s4, 76($sp)
	lw $s3, 80($sp)
	lw $s2, 84($sp)
	lw $s1, 88($sp)
	lw $s0, 92($sp)
	lw $a3, 48($sp)
	lw $a2, 52($sp)
	lw $a1, 56($sp)
	lw $a0, 60($sp)
	lw $t9, 8($sp)
	lw $t8, 12($sp)
	lw $t7, 16($sp)
	lw $t6, 20($sp)
	lw $t5, 24($sp)
	lw $t4, 28($sp)
	lw $t3, 32($sp)
	lw $t2, 36($sp)
	lw $t1, 40($sp)
	lw $t0, 44($sp)
	addi $sp, $sp, 100
	move $t5, $v0
	# AssignmentQuadruple
	move $t6, $t2
	add $t6, $t6, $t5
	# CopyQuadruple
	li $t7, 5
	# CopyQuadruple
	li $t8, 0
	# ParameterQuadruple
	move $a0, $t9
	# ParameterQuadruple
	move $a1, $t7
	# ParameterQuadruple
	move $a2, $t8
	# CallQuadruple
	# Prelouge
	addi $sp, $sp, -100
	sw $ra, 96($sp)
	addiu $fp, $sp, 96
	sw $s0, 92($sp)
	sw $s1, 88($sp)
	sw $s2, 84($sp)
	sw $s3, 80($sp)
	sw $s4, 76($sp)
	sw $s5, 72($sp)
	sw $s6, 68($sp)
	sw $s7, 64($sp)
	sw $a0, 60($sp)
	sw $a1, 56($sp)
	sw $a2, 52($sp)
	sw $a3, 48($sp)
	sw $t0, 44($sp)
	sw $t1, 40($sp)
	sw $t2, 36($sp)
	sw $t3, 32($sp)
	sw $t4, 28($sp)
	sw $t5, 24($sp)
	sw $t6, 20($sp)
	sw $t7, 16($sp)
	sw $t8, 12($sp)
	sw $t9, 8($sp)
	jal makeVal
	# Epilouge
	lw $ra, 96($sp)
	lw $s7, 64($sp)
	lw $s6, 68($sp)
	lw $s5, 72($sp)
	lw $s4, 76($sp)
	lw $s3, 80($sp)
	lw $s2, 84($sp)
	lw $s1, 88($sp)
	lw $s0, 92($sp)
	lw $a3, 48($sp)
	lw $a2, 52($sp)
	lw $a1, 56($sp)
	lw $a0, 60($sp)
	lw $t9, 8($sp)
	lw $t8, 12($sp)
	lw $t7, 16($sp)
	lw $t6, 20($sp)
	lw $t5, 24($sp)
	lw $t4, 28($sp)
	lw $t3, 32($sp)
	lw $t2, 36($sp)
	lw $t1, 40($sp)
	lw $t0, 44($sp)
	addi $sp, $sp, 100
	move $s0, $v0
	# AssignmentQuadruple
	move $s1, $t6
	add $s1, $s1, $s0
	# ParameterQuadruple
	move $a0, $s1
	# CallQuadruple
	addi $sp, $sp, -100
	sw $ra, 96($sp)
	addiu $fp, $sp, 96
	sw $s0, 92($sp)
	sw $s1, 88($sp)
	sw $s2, 84($sp)
	sw $s3, 80($sp)
	sw $s4, 76($sp)
	sw $s5, 72($sp)
	sw $s6, 68($sp)
	sw $s7, 64($sp)
	sw $a0, 60($sp)
	sw $a1, 56($sp)
	sw $a2, 52($sp)
	sw $a3, 48($sp)
	sw $t0, 44($sp)
	sw $t1, 40($sp)
	sw $t2, 36($sp)
	sw $t3, 32($sp)
	sw $t4, 28($sp)
	sw $t5, 24($sp)
	sw $t6, 20($sp)
	sw $t7, 16($sp)
	sw $t8, 12($sp)
	sw $t9, 8($sp)
	jal _system_out_println
	lw $ra, 96($sp)
	lw $s7, 64($sp)
	lw $s6, 68($sp)
	lw $s5, 72($sp)
	lw $s4, 76($sp)
	lw $s3, 80($sp)
	lw $s2, 84($sp)
	lw $s1, 88($sp)
	lw $s0, 92($sp)
	lw $a3, 48($sp)
	lw $a2, 52($sp)
	lw $a1, 56($sp)
	lw $a0, 60($sp)
	lw $t9, 8($sp)
	lw $t8, 12($sp)
	lw $t7, 16($sp)
	lw $t6, 20($sp)
	lw $t5, 24($sp)
	lw $t4, 28($sp)
	lw $t3, 32($sp)
	lw $t2, 36($sp)
	lw $t1, 40($sp)
	lw $t0, 44($sp)
	addi $sp, $sp, 100
	# CallQuadruple
	jal _system_exit
cond:
	# CopyQuadruple
	li $t1, 10
	# AssignmentQuadruple
	move $t0, $t1
	slt $t0, $t0, $a1
	# IfQuadruple
	beq $t0, $zero, L12896681694
	# CopyQuadruple
	li $t2, 20
	# AssignmentQuadruple
	move $t4, $a1
	slt $t4, $t4, $t2
	# IfQuadruple
	beq $t4, $zero, L91510067370
	# CopyQuadruple
	li $t3, 15
	# CopyQuadruple
	move $s2, $t3
	j L111577213552
	L91510067370: 
	# CopyQuadruple
	li $t5, 25
	# CopyQuadruple
	move $s2, $t5
	L111577213552: 
	j L141289479439
	L12896681694: 
	# CopyQuadruple
	li $t6, 5
	# CopyQuadruple
	move $s2, $t6
	L141289479439: 
	move $v0, $s2
	jr $ra
makeVal:
	# AssignmentQuadruple
	move $t1, $a1
	slt $t1, $t1, $a2
	# IfQuadruple
	beq $t1, $zero, L61537358694
	# CopyQuadruple
	li $t0, 1
	# AssignmentQuadruple
	move $t2, $a2
	sub $t2, $t2, $t0
	# CopyQuadruple
	move $s3, $t2
	j L152109957412
	L61537358694: 
	# ParameterQuadruple
	move $a0, $a2
	# CallQuadruple
	addi $sp, $sp, -100
	sw $ra, 96($sp)
	addiu $fp, $sp, 96
	sw $s0, 92($sp)
	sw $s1, 88($sp)
	sw $s2, 84($sp)
	sw $s3, 80($sp)
	sw $s4, 76($sp)
	sw $s5, 72($sp)
	sw $s6, 68($sp)
	sw $s7, 64($sp)
	sw $a0, 60($sp)
	sw $a1, 56($sp)
	sw $a2, 52($sp)
	sw $a3, 48($sp)
	sw $t0, 44($sp)
	sw $t1, 40($sp)
	sw $t2, 36($sp)
	sw $t3, 32($sp)
	sw $t4, 28($sp)
	sw $t5, 24($sp)
	sw $t6, 20($sp)
	sw $t7, 16($sp)
	sw $t8, 12($sp)
	sw $t9, 8($sp)
	jal _system_out_println
	lw $ra, 96($sp)
	lw $s7, 64($sp)
	lw $s6, 68($sp)
	lw $s5, 72($sp)
	lw $s4, 76($sp)
	lw $s3, 80($sp)
	lw $s2, 84($sp)
	lw $s1, 88($sp)
	lw $s0, 92($sp)
	lw $a3, 48($sp)
	lw $a2, 52($sp)
	lw $a1, 56($sp)
	lw $a0, 60($sp)
	lw $t9, 8($sp)
	lw $t8, 12($sp)
	lw $t7, 16($sp)
	lw $t6, 20($sp)
	lw $t5, 24($sp)
	lw $t4, 28($sp)
	lw $t3, 32($sp)
	lw $t2, 36($sp)
	lw $t1, 40($sp)
	lw $t0, 44($sp)
	addi $sp, $sp, 100
	# CopyQuadruple
	li $t4, 1
	# AssignmentQuadruple
	move $t3, $a2
	add $t3, $t3, $t4
	# ParameterQuadruple
	move $a0, $s4
	# ParameterQuadruple
	move $a1, $a1
	# ParameterQuadruple
	move $a2, $t3
	# CallQuadruple
	# Prelouge
	addi $sp, $sp, -100
	sw $ra, 96($sp)
	addiu $fp, $sp, 96
	sw $s0, 92($sp)
	sw $s1, 88($sp)
	sw $s2, 84($sp)
	sw $s3, 80($sp)
	sw $s4, 76($sp)
	sw $s5, 72($sp)
	sw $s6, 68($sp)
	sw $s7, 64($sp)
	sw $a0, 60($sp)
	sw $a1, 56($sp)
	sw $a2, 52($sp)
	sw $a3, 48($sp)
	sw $t0, 44($sp)
	sw $t1, 40($sp)
	sw $t2, 36($sp)
	sw $t3, 32($sp)
	sw $t4, 28($sp)
	sw $t5, 24($sp)
	sw $t6, 20($sp)
	sw $t7, 16($sp)
	sw $t8, 12($sp)
	sw $t9, 8($sp)
	jal makeVal
	# Epilouge
	lw $ra, 96($sp)
	lw $s7, 64($sp)
	lw $s6, 68($sp)
	lw $s5, 72($sp)
	lw $s4, 76($sp)
	lw $s3, 80($sp)
	lw $s2, 84($sp)
	lw $s1, 88($sp)
	lw $s0, 92($sp)
	lw $a3, 48($sp)
	lw $a2, 52($sp)
	lw $a1, 56($sp)
	lw $a0, 60($sp)
	lw $t9, 8($sp)
	lw $t8, 12($sp)
	lw $t7, 16($sp)
	lw $t6, 20($sp)
	lw $t5, 24($sp)
	lw $t4, 28($sp)
	lw $t3, 32($sp)
	lw $t2, 36($sp)
	lw $t1, 40($sp)
	lw $t0, 44($sp)
	addi $sp, $sp, 100
	move $t5, $v0
	# CopyQuadruple
	move $s3, $t5
	L152109957412: 
	move $v0, $s3
	jr $ra

# main is testing the functions I've provided. You will include this code at the end
# of your output file so that you may call these system services.

#main:
#	li $a0, 100
#	jal _new_array
#	move $s0, $v0
#	move $a0, $v0
#	jal _system_out_println
#	lw $a0, 0($s0)
#	jal _system_out_println
#	jal _system_exit

_system_exit:
	li $v0, 10 #exit
	syscall
	
# Integer to print is in $a0. 
# Kills $v0 and $a0
_system_out_println:
	# print integer
	li  $v0, 1 
	syscall
	# print a newline
	li $a0, 10
	li $v0, 11
	syscall
	jr $ra
	
# $a0 = number of bytes to allocate
# $v0 contains address of allocated memory
_new_object:
	# sbrk
	li $v0, 9 
	syscall
	
	#initialize with zeros
	move $t0, $a0
	move $t1, $v0
_new_object_loop:
	beq $t0, $zero, _new_object_exit
	sb $zero, 0($t1)
	addi $t1, $t1, 1
	addi $t0, $t0, -1
	j _new_object_loop
_new_object_exit:
	jr $ra
	
# $a0 = number of bytes to allocate 
# $v0 contains address of allocated memory (with offset 0 being the size)	
_new_array:
	# add space for the size (1 integer)
	addi $a0, $a0, 4
	# sbrk
	li $v0, 9
	syscall
#initialize to zeros
	move $t0, $a0
	move $t1, $v0
_new_array_loop:
	beq $t0, $zero, _new_array_exit
	sb $zero, 0($t1)
	addi $t1, $t1, 1
	addi $t0, $t0, -1
	j _new_array_loop
_new_array_exit:
	#store the size (number of ints) in offset 0
	addi $t0, $a0, -4
	sra $t0, $t0, 2
	sw $t0, 0($v0)
	jr $ra

