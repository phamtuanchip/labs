#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */
void question1()
{
	char date[30];
	char name[30];
	int masv, malop;
}
void question2()
{
	float capital, sum[20],Interes_rate,interes;
	int years,i;

	printf("\n nhap so tien von ban dau : ");
	scanf("%f",& capital);
	printf("\n nhap so tien lai :");
	scanf("%f",&Interes_rate);
	printf("\n nhap so nam can tinh:");
	scanf("%d",&years);
	if(capital>0&&Interes_rate>0)
	{
		printf("Year        Interest        Sum    \n");
	     printf("-------+-------------+------------\n");
		for(i=1;i<=years;i++)
	{ 
	   interes = capital * Interes_rate / 100;
	   capital+= interes;
	   printf("%d \t %0.2f  \t  %0.2f ",i,interes,capital);
	  	printf("\n ");
	}
	}
}
void question3()
{
   int day[30],i,j,n;
   printf("nhap so phan tu cua day");
   scanf("%d",&n);
   for(i=0;i<n; i++)
   {
   	printf("\n day[%d]=",i);
   	scanf("%d",&day[i]);
   	if(day[i]==1) exit ;
   	else continue;
   }
   for	(i=0,j=n-1;i<j;i++,j--)
   {
   	if(day[i]!=day[j]) 
	{
		printf("  day so khong doi xung"); break;
   	}
   	else  printf("\n");
	
   }
   printf("\n day so doi xung nhau");
}
int main(int argc, char *argv[]) {
	int ch;
	

printf("***************************************************\n");

printf("\n* Date of Examination: (dd/mm/yyyy)\n");

printf("* Student Name:	 (H? và tên thí sinh)\n");

printf("* Student Number:	 (mã s? sinh viên)\n ");

printf("* Batch:	 (mã l?p)\n* Please select the number for appropriate tasks\n*	1. Quit program\n*	2. Question 2\n*	3. Question 3\n***************************************************\n");
	printf("**************** '   MENU    ' **********************\n");
	printf("\n1.THOAT CHUONG TRINH.\n2.THUC HIEN QUESTION2.\n3.THUC HIEN QUESTION3\n");
	scanf("%d",&ch);
	switch(ch)
	{
		case 1 : exit (0); break;
		case 2 :	question2(); break;
		case 3 : 	question3();break;
	}

	getch();
	return 0;
}
