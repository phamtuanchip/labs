#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int n ;
void nhap()
  {
      do
      {
          printf(" Nhap n=");
          scanf("%d",&n);
      }
      while (n>=12);
  }
unsigned long giaithua()
  {
      int i;
      unsigned long kq;
      kq=1;
      for (i=1;i<=n;i++)
          kq=kq*i; 
       printf("\n gia tri giai thua %d",kq);    // DAY NE THAY . NEU EM BO CAI NAY DI MA DUNG HAM XUAT THI KQ DUNG , CON DUNG THI KQ NHU THAY THAY 
       return kq;
  }
/* void xuat(int n)
  { 
  printf("\n gia tri giai thua %d",giaithua(n));
  }*/
  void tong()
  {
  	int i;
	unsigned long s;s=0;
  	for(i=1;i<=n;i++)
  	s=giaithua(i)+s;
  	printf("\ntong so %d",s);
  }
int main(int argc, char *argv[]) {
	
      nhap(n);
      printf("%d n: " , n);
      giaithua(n);
      tong(n);
     // xuat(n);
      getchar();
	return 0;
}
