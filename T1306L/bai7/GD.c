#include<stdio.h>
#include<conio.h>
struct Taikhoan
{
      int MaTK;
      char ChuTK[20];
      int Masothe;
      int Tongtien;
      int GD[20];
};
struct Taikhoan a[10];
int n,i, tienbd;
void TaoTK()
{
    printf("\nNhap n = ");
    scanf("%d", &n);
    for(i=0;i<n;i++)
    {
                    printf("\nTai khoan thu %d : ", i+1);
                    printf("\nNhap ma tai khoan (8 chu so) : ");
                    scanf("%d", &a[i].MaTK);
                    printf("\nNhap ten chu tai khoan : ");
                    fflush(stdin);
                    gets(a[i].ChuTK);
                    printf("\nNhap ma so the (9 chu so) : ");
                    scanf("%d", &a[i].Masothe);
                    printf("\nNhap tong so tien (10 chu so) : ");
                    scanf("%d", &a[i].Tongtien);
                    tienbd = a[i].Tongtien;
    }
}
void InTK()
{
    for(i=0;i<n;i++)
    {
                    printf("\nTai khoan %d : ",i+1);
                    printf("\nMa tai khoan : %d", a[i].MaTK);
                    printf("\nChu tai khoan : %s", a[i].ChuTK);
                    printf("\nMa so the : %d", a[i].Masothe);
                    printf("\nTong so tien : %d", a[i].Tongtien);
    }
}
void ThucHienGD()
{
    int LoaiGD, Soluong, maTK, j=0;
    printf("\nNhap ma tai khoan (8 chu so) : ");
    scanf("%d", &maTK);
    for(i=0;i<n;i++)
    {
        if(a[i].MaTK ==maTK)
        {
            printf("\nLoai giao dich (Them tien nhap 1/Rut tien nhap 0) : ");
            scanf("%d",&LoaiGD);
            printf("\nSo luong : ");
            scanf("%d",&Soluong);
            while(1)
            {
                    if((LoaiGD==0) && (Soluong > a[i].Tongtien))
                    {
                        printf("\nKhong du tien de rut. Vui long nhap lai !");
                        printf("\nSo luong : ");
                        scanf("%d",&Soluong);
                    }
                    else
                        break;
            }
            while(j<20)
            {
             if(a[i].GD[j] != 0)  j++;
             else
               if(LoaiGD==1)
               {
                         a[i].GD[j]=Soluong;
                         a[i].Tongtien = a[i].Tongtien + Soluong;
               }
               else
               {
                a[i].GD[j] = Soluong * (-1);
                a[i].Tongtien = a[i].Tongtien - Soluong;
               }
            }
            //++j;
        }
    }
}
void InDSGD()
{
    int ma, s, tongnhap=0,tongrut=0;
    printf("\nNhap ma tai khoan :");
    scanf("%d", &ma);
    for(i=0;i<n;i++)
    {
        if(a[i].MaTK==ma)
        {
            printf("\nChu tai khoan : %s",a[i].ChuTK);
            printf("\nTong so tien ban dau : %-10d",tienbd);
            printf("\nDanh sach giao dich : ");
            printf("\nGiao dich | Kieu giao dich | So luong");
            printf("\n-------------------------------------");
            for(s=0;s<20;s++)
            {
                if(a[i].GD[s]>0)
                {
                    printf("\n%-9d | %-14s | %10d", s+1,"Nhap", a[i].GD[s]);
                    tongnhap = tongnhap + a[i].GD[s];
                }
                if(a[i].GD[s]<0)
                {
                    printf("\n%-9d | %-14s | %10d", s+1,"Rut", a[i].GD[s]/(-1));
                    tongrut = tongrut + a[i].GD[s]*(-1);
                }
            }
            printf("\nTong so tien nhap vao : %-10d",tongnhap);
            printf("\nTong so tien rut ra : %-12d",tongrut);
            printf("\nTong so tien con lai : %-11d",a[i].Tongtien);
        }
    }
}
/*
void GDmax()
{
    int ma, maxnhap, maxrut, s;
    printf("\nNhap ma tai khoan :");
    scanf("%d", &ma);
    for(i=0;i<n;i++)
    {
      if(a[i].MaTK==ma)
        {
           for(s=0;stt<j;s++)
           {
              if(GD[s]>0)
              {
                 maxnhap = GD[s]; break;
              }
              else
              {
                 maxrut = GD[s]; break;
              }
           }
        }
    for(s=0;s<j;s++)
    {
        if((GD[s]>0) && (GD[s]> maxnhap)) maxnhap = GD[s];
        if((GD[s]<0) && (GD[s]< maxrut)) maxrut = GD[s];
    }
    printf("\nGiao dich nhap lon nhat : %d",maxnhap);
    printf("\nGiao dich rut lon nhat : %d",maxrut*(-1));
}
*/
void menu()
{
  printf("\n1. Tao tai khoan moi");
  printf("\n2. In chi tiet tai khoan");
  printf("\n3. Thuc hien giao dich");
  printf("\n4. In ra danh sach giao dich cua khach hang");
  printf("\n5. Giao dich lon nhat");
  printf("\n6. Thoat");
  printf("\n  Lua chon : ");
}
int main()
{
  char ch;
  do
  {
  menu();
  fflush(stdin);
  scanf("%c",&ch);
  switch(ch)
     {
     case '1': TaoTK();
          getch(); break;
     case '2': InTK();
          getch(); break;
     case '3': ThucHienGD();
          getch(); break;
     case '4': InDSGD();
          getch(); break;
     case '5': //GDmax();
          getch(); break;
     case '6': exit(0);
     default : printf("\nInvalib! re-enter..."); getch();
     }
  } while(ch!='6');
  getch();
}
