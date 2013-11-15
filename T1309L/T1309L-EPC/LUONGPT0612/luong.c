#include<stdio.h>
#include<conio.h>
struct ve{
       int hangnhat;
       int hangnhi;
       int hangba;
      
       };
struct khach {
       char ten[20];
       char diachi[25];
       char thanhvien;
       char chieu;
       char toi;
       int sl_ve1;
       int sl_ve2;
       int sl_ve3;
       };
       struct ve giave;
       struct khach thongtin[300];
void nhapgiave(){
     printf("Gia ve hang nhat(VND):     "); scanf("%d", &giave.hangnhat);
     printf("Gia ve hang nhi(VND):     "); scanf("%d", &giave.hangnhi);
     printf("Gia ve hang ba(VND):     "); scanf("%d", &giave.hangba);
     };
void thongtinkhach(){
     int i=0;
     do{
         i++;
     printf(" ma khach hang %d", i);
     printf("\n %-30s", "ten khach hang   ");           
     scanf("%s", &thongtin[i].ten);
     fflush(stdin);
     printf("\n %-30s", "Dia chi khach hang  ");                   
     scanf("%s", &thongtin[i].diachi);
     fflush(stdin);
     printf("\n %-30s", "thanh vien cau lac bo (c/k):");            
     scanf("%c", &thongtin[i].thanhvien);
     fflush(stdin);
     printf("\n %-30s", "buoi chieu  ");
     scanf("%c", &thongtin[i].chieu);
     fflush(stdin);
     printf("\n %-30s", "so luong ve hang nhat:    ");
     scanf("%d", &thongtin[i].sl_ve1);
     fflush(stdin);
     printf("\n %-30s", "so luong ve hang nhi:  ");
     scanf("%d", &thongtin[i].sl_ve2);
     fflush(stdin);
     printf("\n %-30s", "so luong ve hang ba:   ");
     scanf("%d", &thongtin[i].sl_ve3);
     fflush(stdin);
 
     } while(i>300);
     };
void thoat(){
     printf("moi ban go mot phim bat ki de thoat khoi he thong");
     system("exit");
     }
main()
{
      int n;
     
          printf("%15s %10 %15s \n", "", "THUC DON", "");
          printf("%5s %-40s \n", "", "1. Nhap vao gia ve buoi toi");
          printf("%5s %-60s \n", "", "2. Nhap vao thong tin khach hang va yeu cau cua khach hang");
          printf("%5s %-30s \n", "", "3. In hoa don cho khach hang");
          printf("%5s %-30s \n", "", "4. In so lieu thong ke trong ngay");
          printf("%5s %-10s \n \n", "", "5. Thoat");
          printf("moi ban nhap vao 1 phim chuc nang (1, 2, 3, 4 , 5):");
          scanf("%d", &n);
     switch(n){
              case 1: nhapgiave(); break;
              case 2: thongtinkhach(); break;
              case 5 : thoat(); break;
              default: printf("ban da nhap sai phim chuc nang, moi nhap lai: ");
              }
             
      system("pause");
}
