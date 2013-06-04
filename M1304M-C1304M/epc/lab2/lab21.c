#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */


long cong(int s1, int s2) {
return s1 + s2;
}

long tru(int s1, int s2){
	return s1 - s2 ;
}

float nhan(int s1, int s2){
	return s1*s2;
}

double chia(int s1, int s2){
	if(s2 == 0) {
		printf("khong the chia cho so 0");
		return 0 ;
	} else {
	 return s1/s2 ;
	}
}

int main(int argc, char *argv[]) {
	
	int a = 10;
	int b = 5;
	printf("\n tong 2 so la %d",cong(a, b));
	printf("\n hieu 2 so la %d",tru(a, b));
	printf("\n tich 2 so la %f",nhan(a, b));
	printf("\n chia 2 so la %f",chia(a, b));
	return 0;
}
