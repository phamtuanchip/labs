#include <stdio.h>

int a;
int b;
int sum;

void showInfor(){
	printf("\nI am a function \n");
}

int add2Number(int a, int b) {
	return a + b;
}

int main(){
	a = 1;
	b=2 ;
	sum = a+b ;

	printf("\nHello C ! \n");
	printf("Total: %d \n", sum);

	showInfor();

	printf("Add 2 numbers : %d and %d we have %d\n", a, b, add2Number(a,b));

}