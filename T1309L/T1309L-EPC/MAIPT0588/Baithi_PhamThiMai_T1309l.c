#include<stdio.h>
#include<string.h>
#include<stdlib.h>
typedef struct{
        int giavehangnhat;
        int giavehangnhi;
        int giavehangba;
}giave;
typedef struct{
	char ten[30];
	char diachi[30];
	char clb;
	char after;
	char even;
	int sl_hangnhat_c;
	int sl_hangnhi_c;
	int sl_hangba_c;
	int sl_hangnhat_t;
	int sl_hangnhi_t;
	int sl_hangba_t;
	int tongsotienduocgiam;
	int tongsotienchuagiam;
	int tongsotienphaitra;
	
}khachhang;
giave price;
khachhang *pkh;
int N = 0;
int dem=0;
void nhapgiave(){
        printf("\n----------------HAY NHAP THONG TIN GIA VE--------------------");
        printf("\n Nhap gia ve hang nhat: "); fflush(stdin);scanf("%d",&price.giavehangnhat);
        printf("\n Nhap gia ve hang nhi: "); fflush(stdin);scanf("%d",&price.giavehangnhi);
        printf("\n Nhap gia ve hang ba: "); fflush(stdin);scanf("%d",&price.giavehangba);
        
}
int nhapthongtinKH(){
        int count;
        char continuea;
        printf("\n--------------------HAY NHAP THONG TIN KHACH HANG-------------------");
        pkh = (khachhang*)malloc(sizeof(khachhang));
        do{
                count ++;
                if(count >= 1){
                        pkh = (khachhang*)realloc(pkh,count*sizeof(khachhang));
                }
                printf("\nNhap ho ten: "); fflush(stdin);
				 scanf("%s",(*(pkh+ count -1)).ten);
                printf("\nNhap dia chi: "); fflush(stdin); 
				scanf("%s",(*(pkh+ count -1)).diachi);
                printf("\nBan co phai thanh vien cau lac bo khong(c-k)): "); fflush(stdin); 
				scanf("%c",&((*(pkh+ count -1)).clb));
                printf("\nBan co mua ve chieu khong(c-k)): "); fflush(stdin);
				 scanf("%c",&((*(pkh+ count -1)).after));
                char tl1 = (*(pkh+ count -1)).after;
                if (tl1 == 'c'){
                        printf("\nBan muon mua bao nhieu ve chieu hang nhat? "); fflush(stdin);
						 scanf("%d",&((*(pkh+ count -1)).sl_hangnhat_c));
                        printf("\nBan muon mua bao nhieu ve chieu hang nhi? "); fflush(stdin); 
						scanf("%d",&((*(pkh+ count -1)).sl_hangnhi_c));
                        printf("\nBan muon mua bao nhieu ve chieu hang ba? "); fflush(stdin);
						 scanf("%d",&((*(pkh+ count -1)).sl_hangba_c));
                }
                printf("\nBan co mua ve toi khong(c-k)): "); fflush(stdin); 
				scanf("%c",&((*(pkh+ count -1)).even));
                char tl2 = (*(pkh+ count -1)).even;
                if (tl2 == 'c'){
                        printf("\nBan muon mua bao nhieu ve toi hang nhat? "); fflush(stdin); 
						scanf("%d",&((*(pkh+ count -1)).sl_hangnhat_t));
                        printf("\nBan muon mua bao nhieu ve toi hang nhi? "); fflush(stdin); 
						scanf("%d",&((*(pkh+ count -1)).sl_hangnhi_t));
                        printf("\nBan muon mua bao nhieu ve toi hang ba? "); fflush(stdin);
						 scanf("%d",&((*(pkh+ count -1)).sl_hangba_t));
                };
                
                printf(" Ban co muon tiep tuc khong?");
                fflush(stdin);
                scanf("%c",&continuea);
        }while( continuea != 'n');
        dem=count;
        return count;
        
}
void inhoadon(){
        printf("\n-------------------------IN HOA DON-------------------------------\n");
        int i = 0;
        printf("%d",N);
        for ( i = 0; i < N; i ++){
                printf("\nMa khach hang: %d",i+1);
                printf("\nTen khach hang: %s",(*(pkh + i)).ten);
                printf("\nDia chi: %s",(*(pkh+ i)).diachi);
                printf("\nBan co phai thanh vien cau lac bo khong(c-k)):%c ",(*(pkh+ i)).clb);
                
                printf("\nBan co mua ve chieu khong(c-k)): %c",(*(pkh+ i)).after);
                char tl1 = (*(pkh+ i)).after;
                if (tl1 == 'c'){
                        printf("\n So luong ve hang nhat: %d",(*(pkh+ i)).sl_hangnhat_c);
                        printf("\n So luong ve hang nhi: %d",(*(pkh+ i)).sl_hangnhi_c);
                        printf("\n So luong ve hang ba: %d",(*(pkh+ i)).sl_hangba_c);
                }
                printf("\nBan co mua ve toi khong(c-k)): %c",(*(pkh+ i)).even);
                if (tl1 == 'c'){
                        printf("\n So luong ve hang nhat: %d",(*(pkh+ i)).sl_hangnhat_t);
                        printf("\n So luong ve hang nhi: %d",(*(pkh+ i)).sl_hangnhi_t);
                        printf("\n So luong ve hang ba: %d",(*(pkh+ i)).sl_hangba_t);
                }
                 (*(pkh+ i)).tongsotienchuagiam = ((*(pkh+ i)).sl_hangnhat_t)*(price.giavehangnhat) + ((*(pkh+ i)).sl_hangnhi_t)*(price.giavehangnhi)+((*(pkh+ i)).sl_hangba_t)*(price.giavehangba)+ 0.8*(((*(pkh+ i)).sl_hangnhat_c)*(price.giavehangnhat) + ((*(pkh+ i)).sl_hangnhi_c)*(price.giavehangnhi)+((*(pkh+ i)).sl_hangba_c)*(price.giavehangba));
                 char tl3 = (*(pkh+ i)).clb;
                 if (tl3 == 'c'){
                                 (*(pkh+ i)).tongsotienduocgiam = 0.1*((*(pkh+ i)).tongsotienchuagiam);
                                 
                 }else{
                                 (*(pkh+ i)).tongsotienduocgiam = 0;
                 }
                (*(pkh+ i)).tongsotienphaitra =         (*(pkh+ i)).tongsotienchuagiam - (*(pkh+ i)).tongsotienduocgiam;
                printf("\n Tong tien chua giam (vnd): %d",(*(pkh+ i)).tongsotienchuagiam);
                printf("\n Tong tien duoc giam(vnd): %d",(*(pkh+ i)).tongsotienduocgiam);
                printf("\n Tong tien phai tra(vnd): %d",(*(pkh+ i)).tongsotienphaitra);
                
        }
        
}
        
void inthongke(){
	
	 printf("\n---------------------------THONG KE ----------------------------"); 
	  int j;
    printf("\n %30s|%10s|%10s","","BUOI CHIEU","BUOI TOI" ); 
    printf("\n %30s|%10s|%10s","So luong ve hang nhat","sl_hangnhat_c","sl_hangnhat_t" ); 
    printf("\n %30s|%10s|%10s","So luong ve hang nhi","sl_hangnhi_c","sl_hangnhi_t" ); 
    printf("\n %30s|%10s|%10s","So luong ve hang ba","sl_hangba_c","sl_ba_t" ); 
    
    
        
}  
void thoat(){
        system("exit");
}  
int main(){
        
        int chon;
        do{
                printf("\n---------------------THUC DON------------------------\n");
                printf("\n1. Nhap gia ve buoi toi");
                printf("\n2. Nhap thong tin khach hang va yeu cau cua khach hang");
                printf("\n3. In hoa don cho khach hang");
                printf("\n4. In so lieu thong ke trong ngay");
                printf("\n5. Thoat");
                printf("\nMoi ban chon chuc nang(1,2,3,4,5): ");
                fflush(stdin);
                scanf("%d",&chon);
                switch(chon){
                        case 1: nhapgiave(); break;
                        case 2: nhapthongtinKH(); break;
                        case 3: inhoadon(); break;
                        case 4: inthongke(); break;
                        case 5: thoat();
                }
                
        }while(chon != 5);
        system("pause");
        return 0;
}    
        

	
