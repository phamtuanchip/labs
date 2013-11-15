#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<conio.h>
typedef struct{
        int giave_hangnhat;
        int giave_hangnhi;
        int giave_hangba;
}giave;

typedef struct{
        char tenKH[20];
        char diachi[20];
        char TV_CLB;
        char buoichieu;
        char buoitoi;
        int sl_hangnhat_chieu;
        int sl_hangnhi_chieu;
        int sl_hangba_chieu;
        int sl_hangnhat_toi;
        int sl_hangnhi_toi;
        int sl_hangba_toi;
        int TT_chuagiam;
        int TT_duocgiam;
        int TT_phaitra;
        
}khachhang;
giave price;
khachhang *kh;
int n = 0;
int dem=0;
int tongtien=0;
void nhapgiave(){
        printf("\n----------------NHAP THONG TIN GIA VE--------------------");
        printf("\n nhap gia ve hang nhat: "); 
		fflush(stdin);
		scanf("%d",&price.giave_hangnhat);
        printf("\n nhap gia ve hang nhi: "); 
		fflush(stdin);
		scanf("%d",&price.giave_hangnhi);
        printf("\n nhap gia ve hang ba: "); 
		fflush(stdin);
		scanf("%d",&price.giave_hangba);
        
}
int nhapthongtinKH(){
        int count;
        count=0;
        char h;
        printf("\n--------------------NHAP THONG TIN KHACH HANG-------------------");
        kh = (khachhang*)malloc(sizeof(khachhang));
        do{
                count ++;
                if(count >= 1){
                        kh = (khachhang*)realloc(kh,count*sizeof(khachhang));
                }
                printf("\nnhap ho ten khach hang: "); 
				fflush(stdin);
				gets((*(kh+ count -1)).tenKH);
                printf("\nnhap dia chi: "); 
				fflush(stdin); gets((*(kh+ count -1)).diachi);
                printf("\nBan co phai thanh vien cau lac bo khong(c-k)): "); 
				fflush(stdin); scanf("%c",&((*(kh+ count -1)).TV_CLB));
                printf("\nBan co mua ve chieu khong(c-k)): "); 
				fflush(stdin); scanf("%c",&((*(kh+ count -1)).buoichieu));
                char tl1 = (*(kh+ count -1)).buoichieu;
                if (tl1 == 'c'){
                        printf("\nBan muon mua bao nhieu ve chieu hang nhat? "); 
						fflush(stdin); 
						scanf("%d",&((*(kh+ count -1)).sl_hangnhat_chieu));
                        printf("\nBan muon mua bao nhieu ve chieu hang nhi? "); 
						fflush(stdin); 
						scanf("%d",&((*(kh+ count -1)).sl_hangnhi_chieu));
                        printf("\nBan muon mua bao nhieu ve chieu hang ba? "); 
						fflush(stdin); 
						scanf("%d",&((*(kh+ count -1)).sl_hangba_chieu));
                }
                printf("\nBan co mua ve toi khong(c-k)): "); 
				fflush(stdin); 
				scanf("%c",&((*(kh+ count -1)).buoichieu));
                char tl2 = (*(kh+ count -1)).buoichieu;
                if (tl2 == 'c'){
                        printf("\nBan muon mua bao nhieu ve toi hang nhat? "); 
						fflush(stdin); 
						scanf("%d",&((*(kh+ count -1)).sl_hangnhat_toi));
                        printf("\nBan muon mua bao nhieu ve toi hang nhi? "); 
						fflush(stdin); 
						scanf("%d",&((*(kh+ count -1)).sl_hangnhi_toi));
                        printf("\nBan muon mua bao nhieu ve toi hang ba? "); 
						fflush(stdin); 
						scanf("%d",&((*(kh+ count -1)).sl_hangba_toi));
                };
                
                printf(" Ban co muon tiep tuc khong?(c-k) : ");
                fflush(stdin);
                scanf("%c",&h);
        }while( h != 'k');
        dem=count;
        return count;
}
void inhoadon(){
        printf("\n-------------------------IN HOA DON-------------------------------");
        int i = 0;
        tongtien=0;
        printf("%d",n);
        for ( i = 0; i < n; i ++){
                printf("\nMa khach hang: %d",i+1);
                printf("\nten khach hang: %s",(*(kh + i)).tenKH);
                printf("\nDia chi: %s",(*(kh+ i)).diachi);
                printf("\nBan co phai thanh vien cau lac bo khong(c-k)):%c ",(*(kh+ i)).TV_CLB);
                printf("\nBan co mua ve chieu khong(c-k)): %c",(*(kh+ i)).buoichieu);
                char tl1 = (*(kh+ i)).buoichieu;
                if (tl1 == 'c'){
                        printf("\n So luong ve hang nhat: %d",(*(kh+ i)).sl_hangnhat_chieu);
                        printf("\n So luong ve hang nhi: %d",(*(kh+ i)).sl_hangnhi_chieu);
                        printf("\n So luong ve hang ba: %d",(*(kh+ i)).sl_hangba_chieu);
                }
                printf("\nBan co mua ve toi khong(c-k)): %c",(*(kh+ i)).buoichieu);
                if (tl1 == 'c'){
                        printf("\n So luong ve hang nhat: %d",(*(kh+ i)).sl_hangnhat_toi);
                        printf("\n So luong ve hang nhi: %d",(*(kh+ i)).sl_hangnhi_toi);
                        printf("\n So luong ve hang ba: %d",(*(kh+ i)).sl_hangba_toi);
                }
                 (*(kh+ i)).TT_chuagiam = ((*(kh+ i)).sl_hangnhat_toi)*(price.giave_hangnhat) + ((*(kh+ i)).sl_hangnhi_toi)*(price.giave_hangnhi)+((*(kh+ i)).sl_hangba_toi)*(price.giave_hangba)+ 0.8*(((*(kh+ i)).sl_hangnhat_chieu)*(price.giave_hangnhat) + ((*(kh+ i)).sl_hangnhi_chieu)*(price.giave_hangnhi)+((*(kh+ i)).sl_hangba_chieu)*(price.giave_hangba));
                 char tl3 = (*(kh+ i)).TV_CLB;
                 if (tl3 == 'c'){
                                 (*(kh+ i)).TT_duocgiam = 0.1*((*(kh+ i)).TT_chuagiam);
                                 
                 }else{
                                 (*(kh+ i)).TT_duocgiam = 0;
                 }
                (*(kh+ i)).TT_phaitra = (*(kh+ i)).TT_chuagiam - (*(kh+ i)).TT_duocgiam;
                printf("\n Tong tien chua giam: %d",(*(kh+ i)).TT_chuagiam);
                printf("\n Tong tien duoc giam: %d",(*(kh+ i)).TT_duocgiam);
                printf("\n Tong tien phai tra: %d",(*(kh+ i)).TT_phaitra);
                tongtien=tongtien+(*(kh+ i)).TT_phaitra;
        }
        
}
void inthongke(){
        printf("\n So khach trong ngay la : %d",n);
        printf("\n Tong doanh thu trong ngay : %d",tongtien);
}
void thoat(){
        system("exit");
}
int main(){
      
        int A;
        do{
                printf("\n---------------------THUC DON------------------------\n");
                printf("\n1. Nhap gia ve buoi toi");
                printf("\n2. Nhap thong tin khach hang va yeu cau cua khach hang");
                printf("\n3. In hoa don cho khach hang");
                printf("\n4. In so lieu thong ke trong ngay");
                printf("\n5. Thoat");
                printf("\nMoi ban chon chuc nang(1,2,3,4,5): ");
                fflush(stdin);
                scanf("%d",&A);
                switch(A){
                        case 1: nhapgiave(); 
						break;
                        case 2: n = nhapthongtinKH(); 
						break;
                        case 3: inhoadon(); 
						break;
                        case 4: inthongke(); 
						break;
                        case 5: thoat();
                }
                
        }while(A != 5);
        system("pause");
        return 0;
}
