#include <math.h>
#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */


 
int main(int argc, char *argv[]) {
	char str [80];
  	int a,b,c;

  printf ("nhap a: ");
  scanf ("%d",&a);  
  printf ("\n nhap b: ");
  scanf ("%d",&b);
  printf ("\n nhap c: ");
  scanf ("%d",&c);
  if(a+b > c || b +c > a || a+c > b) {
  	printf ("\n a b c khong the la canh cua 1 tam giac");
  } else {
  	printf ("\n a b c la canh cua 1 tam giac");
  }
  
	return 0;
}
