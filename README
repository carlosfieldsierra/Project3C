# Project 3c: Code Generation

A complier for MiniJava
## Run Instructions
To use the complier put the MiniJava file inside the `milestones` folder
and run this below
```bash
make file=Milestone1
```
**Note** the file is `Milestone1.java` put you should only put `Milestone1`.
This will both generate the mips code and then run it.
Also `Milestone1`-`Milestone5` are already in the milestone folder. So you can run them all already

**Lastly** the mips file will be in the `mips` folder with the same name so `milestones/Milestone1.java` mips will at `mips/Milestone1.asm`
## Issues
- I only got to Milestone 5 so the stuff beyond that won't work
- The label of a method is it's name so this complier does not support methods of the same name in different classes  

## Example Test
Below are each milestone and it's corresponding mips code
## Milestone1
**MiniJava**
```java
class Milestone1 {
    public static void main(String[] args){
        System.out.println(69);
    }
}
```
```mips
main:
	# CopyQuadruple
	li $t0, 69
	# ParameterQuadruple
	move $a0, $t0
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
```
## Milestone2
**MiniJava**
```java
class Milestone2 {
    public static void main(String[] args){
        System.out.println((1+(2*3)+1));
    }
}
```
**Mips**
```mips
main:
	# CopyQuadruple
	li $t0, 1
	# CopyQuadruple
	li $t1, 2
	# CopyQuadruple
	li $t2, 3
	# AssignmentQuadruple
	move $t3, $t1
	mul $t3, $t3, $t2
	# AssignmentQuadruple
	move $t4, $t0
	add $t4, $t4, $t3
	# CopyQuadruple
	li $t5, 1
	# AssignmentQuadruple
	move $t6, $t4
	add $t6, $t6, $t5
	# ParameterQuadruple
	move $a0, $t6
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
```
## Milestone3
**MiniJava**
```java
class Milestone3 {
    public static void main(String[] args){
        System.out.println(
            new G().getOne() // 1
            +new G().getTwo() // 2
            +new G().getZero()  // 0
            +new G().addNums(1, 1, 1) // 3
            ); // = 6
    }
}

class G{
    public int addNums(int a,int b,int c){
        return a+b+c;
    }
    public int getZero(){
        return 0;
    }
    public int getOne(){
        return 1;
    }
    public int getTwo(){
        return 2;
    }
}
```
**Mips**
```mips
main:
	# ParameterQuadruple
	move $a0, $t0
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
	jal getOne
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
	move $t1, $v0
	# ParameterQuadruple
	move $a0, $t2
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
	jal getTwo
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
	move $t3, $v0
	# AssignmentQuadruple
	move $t4, $t1
	add $t4, $t4, $t3
	# ParameterQuadruple
	move $a0, $t5
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
	jal getZero
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
	move $t6, $v0
	# AssignmentQuadruple
	move $t7, $t4
	add $t7, $t7, $t6
	# CopyQuadruple
	li $t8, 1
	# CopyQuadruple
	li $t9, 1
	# CopyQuadruple
	li $s0, 1
	# ParameterQuadruple
	move $a0, $s1
	# ParameterQuadruple
	move $a1, $t8
	# ParameterQuadruple
	move $a2, $t9
	# ParameterQuadruple
	move $a3, $s0
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
	jal addNums
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
	move $s2, $v0
	# AssignmentQuadruple
	move $s3, $t7
	add $s3, $s3, $s2
	# ParameterQuadruple
	move $a0, $s3
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
getOne:
	# CopyQuadruple
	li $t0, 1
	move $v0, $t0
	jr $ra
getZero:
	# CopyQuadruple
	li $t0, 0
	move $v0, $t0
	jr $ra
getTwo:
	# CopyQuadruple
	li $t0, 2
	move $v0, $t0
	jr $ra
addNums:
	# AssignmentQuadruple
	move $t0, $a1
	add $t0, $t0, $a2
	# AssignmentQuadruple
	move $t1, $t0
	add $t1, $t1, $a3
	move $v0, $t1
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
```
## Milestone4
**MiniJava**
```java

class Milestone4 {
    public static void main(String[] args){
        System.out.println(
            new Test().printThenAdd(0,1,2)
            + 
            new Stuff().addAll(10,10,10)
        );
    }
}

class Test{

    public int printThenAdd(int a,int b,int c){
        int sum;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        sum =a;
        sum = sum+b;
        sum = sum+c;
        return sum;
    }
}

class Stuff{

    public int addAll(int i,int j,int k){
        int x;
        int y;
        int z;
        x=i;
        y=j;
        z=k;
        x=x+y+z;
        return x;
    }
}
```
**Mips**
```mips
main:
	# CopyQuadruple
	li $t0, 0
	# CopyQuadruple
	li $t1, 1
	# CopyQuadruple
	li $t2, 2
	# ParameterQuadruple
	move $a0, $t3
	# ParameterQuadruple
	move $a1, $t0
	# ParameterQuadruple
	move $a2, $t1
	# ParameterQuadruple
	move $a3, $t2
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
	jal printThenAdd
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
	move $t4, $v0
	# CopyQuadruple
	li $t5, 10
	# CopyQuadruple
	li $t6, 10
	# CopyQuadruple
	li $t7, 10
	# ParameterQuadruple
	move $a0, $t8
	# ParameterQuadruple
	move $a1, $t5
	# ParameterQuadruple
	move $a2, $t6
	# ParameterQuadruple
	move $a3, $t7
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
	jal addAll
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
	move $t9, $v0
	# AssignmentQuadruple
	move $s0, $t4
	add $s0, $s0, $t9
	# ParameterQuadruple
	move $a0, $s0
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
addAll:
	# CopyQuadruple
	move $s1, $a1
	# CopyQuadruple
	move $s2, $a2
	# CopyQuadruple
	move $s3, $a3
	# AssignmentQuadruple
	move $t3, $s1
	add $t3, $t3, $s2
	# AssignmentQuadruple
	move $t0, $t3
	add $t0, $t0, $s3
	# CopyQuadruple
	move $s1, $t0
	move $v0, $s1
	jr $ra
printThenAdd:
	# ParameterQuadruple
	move $a0, $a1
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
	# ParameterQuadruple
	move $a0, $a3
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
	move $s4, $a1
	# AssignmentQuadruple
	move $t3, $s4
	add $t3, $t3, $a2
	# CopyQuadruple
	move $s4, $t3
	# AssignmentQuadruple
	move $t0, $s4
	add $t0, $t0, $a3
	# CopyQuadruple
	move $s4, $t0
	move $v0, $s4
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
```
## Milestone5
**MiniJava**
```java
class Milestone5 {
    public static void main(String[] args) {
       System.out.println(
           new Cond().cond(100) // 25
           +
           new Cond().cond(0) // 5
           +
           new Recursive().makeVal(5, 0) // 5
       ); // = 35
    }
}

class Cond{

    public int cond(int a){
        int var;
        if (10<a){
            if (a<20){
               var = 15; 
            } else {
                var = 25;
            }
        }else {
            var=5;
        }
        return var;

    }
}
class Recursive{

    public int makeVal(int val ,int zero){
        int temp;
        if (val<zero){
            temp= zero-1;
        } else {
            System.out.println(zero);
            temp = this.makeVal(val, zero+1);
        }
        return temp;
    }
}
```
**Mips**
```
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
```