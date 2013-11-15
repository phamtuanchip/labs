#include<stdio.h>
#include<string.h>
#include<stdlib.h>
typedef struct{
        int giahangnhat;
        int giahangnhi;
        int giahangba;
}giave;

typedef struct{
        char ten[20];
        char diachi[20];
        char is_clb;
        char is_after;
        char is_even;
        int sl_hangnhat_chieu;
        int sl_hangnhi_chieu;
        int sl_hangba_chieu;
        int sl_hangnhat_toi;
        int sl_hangnhi_toi;
        int sl_hangba_toi;
        int tongtienchuagiam;
        int tongtienduocgiam;
        int tongtienphaitra;
        
}khachhang;
giave price;
khachhang *pkh;
int N = 0;
void nhapgiave(){
        printf("\n----------------1.NHAP GIA VE BUOI TOI--------------------");
        printf("\n Nhap gia ve hang nhat(VND): "); 
        scanf("%d",&price.giahangnhat);
        printf("\n Nhap gia ve hang nhi(VND): ");
        scanf("%d",&price.giahangnhi);
        printf("\n Nhap gia ve hang ba(VND): ");
        scanf("%d",&price.giahangba);
        
}
int nhapthongtinKH(){
        int count;
        char continuea;
        printf("\n------------------2.NHAP THONG TIN KHACH HANG-----------------");
        pkh = (khachhang*)malloc(sizeof(khachhang));
        do{
                count ++;
                if(count > 1){
                        pkh = (khachhang*)realloc(pkh,count*sizeof(khachhang));
                }
                printf("\n nhap ho ten khach hang: "); 
                scanf("%s",(*(pkh+ count -1)).ten);
                printf("\n  nhap dia chi khach hang: "); 
                scanf("%s",(*(pkh+ count -1)).diachi);
                printf("\n la thanh vien cau lac bo (c-k)): "); 
                scanf("%c",&((*(pkh+ count -1)).is_clb));
                printf("\n mua ve buoi chieu (c-k)): "); 
                scanf("%c",&((*(pkh+ count -1)).is_after));
                char tl1 = (*(pkh+ count -1)).is_after;
                if (tl1 == 'c'){
                        printf("\n so luong ve chieu hang nhat: ");
                        scanf("%d",&((*(pkh+ count -1)).sl_hangnhat_chieu));
                        printf("\n so luong ve chieu hang nhi: "); 
                        scanf("%d",&((*(pkh+ count -1)).sl_hangnhi_chieu));
                        printf("\n so luong ve chieu hang ba: ");  
                        scanf("%d",&((*(pkh+ count -1)).sl_hangba_chieu));
                }
                printf("\n mua ve toi (c-k)): ");
                scanf("%c",&((*(pkh+ count -1)).is_even));
                char tl2 = (*(pkh+ count -1)).is_even;
                if (tl2 == 'c'){
                        printf("\n so luong ve toi hang nhat: "); 
                        scanf("%d",&((*(pkh+ count -1)).sl_hangnhat_toi));
                        printf("\n so luong ve toi hang nhi: "); 
                         scanf("%d",&((*(pkh+ count -1)).sl_hangnhi_toi));
                        printf("\n so luong ve toi hang ba: "); 
                        scanf("%d",&((*(pkh+ count -1)).sl_hangba_toi));
                };
                
                printf(" Ban co muon tiep tuc khong?");
                scanf("%c",&continuea);
        }while( continuea != 'n');
        return count;
}
void inhoadon(){
        printf("\n-------------------------IN HOA DON-------------------------------");
        int i = 0;
        printf("%d",N);
        for ( i = 0; i < N; i ++){
                printf("\nMa khach hang: %d",i+1);
                printf("\nTen khach hang: %s",(*(pkh + i)).ten);
                printf("\nDia chi khach hang: %s",(*(pkh+ i)).diachi);
                printf("\nBan co phai thanh vien cau lac bo khong?(c-k)):%c ",(*(pkh+ i)).is_clb);
                
                printf("\nBan co mua ve chieu khong(c-k)): %c",(*(pkh+ i)).is_after);
                char tl1 = (*(pkh+ i)).is_after;
                if (tl1 == 'c'){
                        printf("\n So luong ve chieu hang nhat: %d",(*(pkh+ i)).sl_hangnhat_chieu);
                        printf("\n So luong ve chieu hang nhi: %d",(*(pkh+ i)).sl_hangnhi_chieu);
                        printf("\n So luong ve chieu hang ba: %d",(*(pkh+ i)).sl_hangba_chieu);
                }
                printf("\nBan co mua ve toi khong(c-k)): %c",(*(pkh+ i)).is_even);
                if (tl1 == 'c'){
                        printf("\n So luong ve toi hang nhat: %d",(*(pkh+ i)).sl_hangnhat_toi);
                        printf("\n So luong ve toi hang nhi: %d",(*(pkh+ i)).sl_hangnhi_toi);
                        printf("\n So luong ve toi hang ba: %d",(*(pkh+ i)).sl_hangba_toi);
                }
                 (*(pkh+ i)).tongtienchuagiam = ((*(pkh+ i)).sl_hangnhat_toi)*(price.giahangnhat) + ((*(pkh+ i)).sl_hangnhi_toi)*(price.giahangnhi)+((*(pkh+ i)).sl_hangba_toi)*(price.giahangba)+ 0.8*(((*(pkh+ i)).sl_hangnhat_chieu)*(price.giahangnhat) + ((*(pkh+ i)).sl_hangnhi_chieu)*(price.giahangnhi)+((*(pkh+ i)).sl_hangba_chieu)*(price.giahangba));
                 char tl3 = (*(pkh+ i)).is_clb;
                 if (tl3 == 'c'){
                                 (*(pkh+ i)).tongtienduocgiam = 0.1*((*(pkh+ i)).tongtienchuagiam);
                                 
                 }else{
                                 (*(pkh+ i)).tongtienduocgiam = 0;
                 }
                (*(pkh+ i)).tongtienphaitra =         (*(pkh+ i)).tongtienchuagiam - (*(pkh+ i)).tongtienduocgiam;
                printf("\n Tong tien chua giam(vnd): %d",(*(pkh+ i)).tongtienchuagiam);
                printf("\n Tong tien duoc giam(vnd): %d",(*(pkh+ i)).tongtienduocgiam);
                printf("\n Tong tien phai tra(vnd): %d",(*(pkh+ i)).tongtienphaitra);
                
        }
        
}
void thongke(){
        
}
// thoat
void thoat(){
        system("exit");
}
int main(){
        
        int choice;
        do{
                printf("\n---------------------THUC DON------------------------\n");
                printf("\n 1. Nhap gia ve buoi toi");
                printf("\n 2. Nhap thong tin khach hang va yeu cau cua khach hang");
                printf("\n 3. In hoa don cho khach hang");
                printf("\n 4. In so lieu thong ke trong ngay");
                printf("\n 5. Thoat");
                printf("\n Moi ban chon chuc nang(1,2,3,4,5): ");
                fflush(stdin);
                scanf("%d",&choice);
                switch(choice){
                        case 1: nhapgiave(); 
                        break;
                        case 2: nhapthongtinKH(); 
                        break;
                        case 3: inhoadon(); 
                        break;
                        case 4: inthongke();
                        break;
                        case 5: thoat();
                }
                
        }while(choice != 5);
        system("pause");
        return 0;
}
