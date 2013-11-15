#include<stdio.h>
#include<string.h>
#include<stdlib.h>


typedef struct{
        int gia_hang_nhat;
        int gia_hang_nhi;
        int gia_hang_ba;
}gia_ve;

typedef struct{
        char tenkh[30];
        char diachi[30];
        char tv_clb;
        char buoi_chieu;
        char buoi_toi;
        int sl_hangnhat_chieu;
        int sl_hangnhi_chieu;
        int sl_hangba_chieu;
        int sl_hangnhat_toi;
        int sl_hangnhi_toi;
        int sl_hangba_toi;
        
        
} khachhang;
gia_ve price;
khachhang *kh;
int N ;
int dem;
void Nhapgiave(){
        printf("\n________________________NHAP THONG TIN GIA VE____________________");
        printf("\n Nhap gia ve hang nhat: "); 
		fflush(stdin);
		scanf("%d",&price.gia_hang_nhat);
		
        printf("\n Nhap gia ve hang nhi: "); 
		fflush(stdin);
		scanf("%d",&price.gia_hang_nhi);
		
        printf("\n Nhap gia ve hang ba: "); 
		fflush(stdin);
		scanf("%d",&price.gia_hang_ba);
        
}
int NhapTTKH(){
       
        char m;
        printf("\n_______________________NHAP THONG TIN KHACH HANG________________________");
        kh = (khachhang*)malloc(sizeof(khachhang));
        do{
                dem ++;
                if(dem > 1){
                        kh = (khachhang*)realloc(kh,dem*sizeof(khachhang));
                }
                printf("\nNhap ho ten khach hang: ");
				fflush(stdin);
				scanf("%s",(*(kh+ dem -1)).tenkh);
				
				
                printf("\nNhap dia chi khach hang: "); 
				fflush(stdin); 
				scanf("%s",(*(kh+ dem -1)).diachi);
				
				
                printf("\nBan co phai thanh vien cau lac bo khong(c/k)): "); 
				fflush(stdin); 
				scanf("%c",&((*(kh+ dem -1)).tv_clb));
				
				
                printf("\nBan co mua ve chieu khong(c-k)): "); 
				fflush(stdin); 
				scanf("%c",&((*(kh+ dem -1)).buoi_chieu));
				
                char tl1 = (*(kh+ dem -1)).buoi_chieu;
                if (tl1 == 'c'){
                        printf("\nBan muon mua bao nhieu ve chieu hang nhat? "); 
						fflush(stdin); 
						scanf("%d",&((*(kh+ dem -1)).sl_hangnhat_chieu));
						
						
                        printf("\nBan muon mua bao nhieu ve chieu hang nhi? "); 
						fflush(stdin); 
						scanf("%d",&((*(kh+ dem -1)).sl_hangnhi_chieu));
						
						
                        printf("\nBan muon mua bao nhieu ve chieu hang ba? "); 
						fflush(stdin); 
						scanf("%d",&((*(kh+ dem -1)).sl_hangba_chieu));
                }
                printf("\nBan co mua ve toi khong(c-k)): "); 
				fflush(stdin); 
				scanf("%c",&((*(kh+ dem -1)).buoi_toi));
                char tl2 = (*(kh+ dem -1)).buoi_toi;
                if (tl2 == 'c'){
                        printf("\nBan muon mua bao nhieu ve toi hang nhat? "); 
						fflush(stdin); 
						scanf("%d",&((*(kh+ dem -1)).sl_hangnhat_toi));
						
                        printf("\nBan muon mua bao nhieu ve toi hang nhi? "); 
						fflush(stdin); 
						scanf("%d",&((*(kh+ dem -1)).sl_hangnhi_toi));
						
                        printf("\nBan muon mua bao nhieu ve toi hang ba? "); 
						fflush(stdin); 
						scanf("%d",&((*(kh+ dem -1)).sl_hangba_toi));
                };
                
                printf(" Ban co muon tiep tuc khong?");
                fflush(stdin);
                scanf("%c",&m);
        }while( m != 'k');
        return dem;
}
void Inhoadon(){
        printf("\n______________________IN HOA DON_____________________");
        int i = 0;
        for ( i = 0; i < dem; i ++){
                printf("\nMa khach hang: %d",i+1);
                printf("\nTen khach hang: %s",(*(kh + i)).tenkh);
                printf("\nDia chi: %s",(*(kh+ i)).diachi);
                printf("\nBan co phai thanh vien cau lac bo khong(c/k)):%c ",(*(kh+ i)).tv_clb);
                
                printf("\nBan co mua ve chieu khong(c/k)): %c",(*(kh+ i)).buoi_chieu);
                char tl1 = (*(kh+ i)).buoi_chieu;
                if (tl1 == 'c'){
                        printf("\n So luong ve hang nhat: %d",(*(kh+ i)).sl_hangnhat_chieu);
                        printf("\n So luong ve hang nhi: %d",(*(kh+ i)).sl_hangnhi_chieu);
                        printf("\n So luong ve hang ba: %d",(*(kh+ i)).sl_hangba_chieu);
                }
                
                printf("\nBan co mua ve toi khong(c/k)): %c",(*(kh+ i)).buoi_toi);
                if (tl1 == 'c'){
                        printf("\n So luong ve hang nhat: %d",(*(kh+ i)).sl_hangnhat_toi);
                        printf("\n So luong ve hang nhi: %d",(*(kh+ i)).sl_hangnhi_toi);
                        printf("\n So luong ve hang ba: %d",(*(kh+ i)).sl_hangba_toi);
                }
        }
        
}

void Thongke(){
	printf(" So luong ve Hang nhat:\n " );
	printf ("So luong ve Hang nhi:\n");
	printf ("So luong ve Hang ba:");

}

int main(){
        int chon;
        do{
                printf("\n_________________________THUC DON________________________\n");
                printf("\n1. Nhap gia ve buoi toi");
                printf("\n2. Nhap thong tin khach hang va yeu cau cua khach hang");
                printf("\n3. In hoa don cho khach hang");
                printf("\n4. In so lieu thong ke trong ngay");
                printf("\n5. Thoat");
                printf("\nMoi ban chon chuc nang(1,2,3,4,5): ");
                fflush(stdin);
                scanf("%d",&chon);
                switch(chon){
                        case 1: Nhapgiave(); break;
                        case 2: NhapTTKH(); break;
                        case 3: Inhoadon(); break;
                        case 4: Thongke(); break;
                        case 5: exit(0); break;
                }
                
        }while(chon != 5);
        system("pause");
        return 0;
}
