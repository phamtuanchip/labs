#include<stdio.h>
void nhap()
{
     printf("\nBan vua chon 'Vao du lieu tao luyen'");     
}
void in1()
{
     printf("\nBan vua chon 'In ra thong tin tap luyen cua tung ngay'");     
}
void in2()
{
     printf("\nBan vua chon 'In ra thong tin tap luyen cua tat ca cac ngay'");     
}
void vantocTB()
{
     printf("\nBan vua chon 'Van toc trung binh'");     
}
void khoangcachMax()
{
     printf("\nBan vua chon 'Khoang cach chay dai nhat'");     
}
int main()
{
    int i;
    do
    {
          printf("\n\n\n---------------------------MENU----------------------------");  
          printf("\n   1. Vao du lieu tap luyen");
          printf("\n   2. In ra thong tin tap luyen cua tung ngay");
          printf("\n   3. In ra thong tin tap luyen cua tat ca cac ngay");
          printf("\n   4. Van toc trung binh");
          printf("\n   5. Khoang cach chay dai nhat");
          printf("\n   6. Thoat");  
          
          printf("\n\n Ban hay chon 1 so:  ");  
          scanf("%d",&i);
          switch(i)
          {
                   case 1:  nhap();   break;     
                   case 2:  in1();   break;
                   case 3:  in2();   break;
                   case 4:  vantocTB();   break;
                   case 5:  khoangcachMax();   break;
                   case 6:  exit(0);
          }
    } while(i != 6);   
}
