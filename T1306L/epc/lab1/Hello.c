#include <stdio.h>
#include <stdlib.h>

int a;
int b;
int sum;

void showInfor(){
	printf("\nI am a function \n");
}

int add2Number(int a, int b) {
	return a + b;
}

void inputData(){
	printf("\nWhat is your name?: \n");
	char name[100];
	int age ;
	scanf("%[^\n]s", name) ;
	printf("Welcome %s to programing with C\n", name);
	fflush(stdout);
	printf("How old are you?: \n");
	scanf("%d", &age) ;

	if(age < 16 ) {
		printf("You are supper! you can learn C very early\n");
	} else if(age < 35) {
		printf("Do you work for any company?\n");
	} else if(age > 35){
		printf("Are you programer?\n");
	}

}

int main(){
	a = 1;
	b=2 ;
	sum = a+b ;

	printf("\nHello C ! \n");
	printf("Total: %d \n", sum);

	showInfor();

	printf("Add 2 numbers : %d and %d we have %d\n", a, b, add2Number(a,b));

	inputData();
}