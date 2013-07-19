#include<stdio.h>
#include<conio.h>
#include<math.h>
using namespace std;
 void nhap(int &n)
  {
      do
      {
          printf(" Nhap n=");
          scanf("%d",&n);
      }
      while (n>=12);
  }
unsigned long giaithua(int n)
  {
      int i;
      unsigned long kq;
      kq=1;
      for (i=1;i<=n;i++)
          kq=kq*i; 
      // printf("\n gia tri giai thua %d",kq);    // day ne thay
       return kq;
  }
 void xuat(int n)
  { 
  printf("\n gia tri giai thua %d",giaithua(n));
  }
  void tong(int n)
  {
  	int i;
	unsigned long s;s=0;
  	for(i=1;i<=n;i++)
  	s=giaithua(i)+s;
  	printf("\ntong so %d",s);
  }
main()
  {
      int n;
      nhap(n);
      giaithua(n);
      tong(n);
      xuat(n);
      getch();
  }
