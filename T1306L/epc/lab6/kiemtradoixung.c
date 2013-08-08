//Kiem tra mang co doi xung hay ko
#include <stdio.h>
//#include <conio.h>

int checkDoiXung(int a[],int n);

int main()
{
	int a[100],n,i;
	//clrscr();
	printf(" Nhap so phan tu: ");
	scanf("%d",&n);
	for(i=0;i<n;i++)
		{
			printf("a[%d]= ",i);
			scanf("%d",&a[i]);
		}
	printf("\n Mang da nhap la:\n");
	for(i=0;i<n;i++)
		printf("%d ",a[i]);
	if(checkDoiXung(a,n))
		printf("\n Mang doi xung\n");
	else printf("\n Mang ko doi xung \n");
	getchar();
	return 0;
}

int checkDoiXung(int a[],int n)
{
	int i=0,j=n-1;
	while(i<=j)
		{
			if(a[i]!=a[j]) return 0;
			i++;
			j--;
		}
	return 1;
}