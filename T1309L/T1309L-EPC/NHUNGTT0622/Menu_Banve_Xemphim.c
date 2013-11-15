#include<stdio.h>
#include<string.h>
#include<stdlib.h>
// Khai bao kieu cau truc gia ve
typedef struct{
	int giahangnhat;
	int giahangnhi;
	int giahangba;
}giave;

// Khai bao kieu cau truc khach hang
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
int N;
// Ham thuc hien nhap gia ve bao gom 2 loai buoi chieu va buoi toi. Gia ve buoi chieu chi bang 10% gia ve buoi toi
void nhapgiave(){
	printf("\n----------------NHAP THONG TIN GIA VE--------------------");
	printf("\n Nhap gia ve hang nhat: "); fflush(stdin);scanf("%d",&price.giahangnhat);
	printf("\n Nhap gia ve hang nhi: "); fflush(stdin);scanf("%d",&price.giahangnhi);
	printf("\n Nhap gia ve hang ba: "); fflush(stdin);scanf("%d",&price.giahangba);
	
}
void checkvehang1(int d){	
	while(d>80){
		printf("\nBan da nhap vuot qua so ve hang 1 trong kho!\n Moi ban nhap lai:  ");
		fflush(stdin);
		scanf("%d",&d);	
	}	
}
void checkvehang2(int d){	
	while(d>100){
		printf("\nBan da nhap vuot qua so ve hang 2 trong kho!\n Moi ban nhap lai:  ");
		fflush(stdin);
		scanf("%d",&d);	
	}	
}
void checkvehang3(int d){	
	while(d>120){
		printf("\nBan da nhap vuot qua so ve hang 3 trong kho!\n Moi ban nhap lai:  ");
		fflush(stdin);
		scanf("%d",&d);	
	}	
}
// Ham nhap thong tin KH bao gom nhap cac thong tin ve so luong ve va loai ve
int nhapthongtinKH(){
	int count = 0;
	int temp1 = 0 ;
	int temp2 = 0 ;
	int temp3 = 0 ;
	int temp4 = 0 ;
	int temp5 = 0 ;
	int temp6 = 0 ;
	
	char continuea;
	printf("\n--------------------NHAP THONG TIN KHACH HANG-------------------");
	pkh = (khachhang*)malloc(sizeof(khachhang));
	do{
		count ++;
		if(count > 1){
			pkh = (khachhang*)realloc(pkh,count*sizeof(khachhang));
		}
		printf("\nMa khach hang: %d",count);
		printf("\nNhap ho ten: "); fflush(stdin); scanf("%[^\n]s",(*(pkh+ count -1)).ten);
		printf("\nNhap dia chi: "); fflush(stdin); scanf("%[^\n]s",(*(pkh+ count -1)).diachi);
		printf("\nLa thanh vien cau lac bo(c/k): "); fflush(stdin); scanf("%c",&((*(pkh+ count -1)).is_clb));
		printf("\nMua ve buoi chieu(c/k): "); fflush(stdin); scanf("%c",&((*(pkh+ count -1)).is_after));
		char tl1 = (*(pkh+ count -1)).is_after;
		if(tl1 == 'c'){
			printf("\nSo luong ve hang nhat: "); fflush(stdin); scanf("%d",&temp1); checkvehang1(temp1); (*(pkh+ count -1)).sl_hangnhat_chieu = temp1;
			printf("\nSo luong ve hang nhi: "); fflush(stdin); scanf("%d",&temp2); checkvehang2(temp2); (*(pkh+ count -1)).sl_hangnhi_chieu = temp2;
			printf("\nSo luong ve hang ba: "); fflush(stdin); scanf("%d",&temp3); checkvehang3(temp3); (*(pkh+ count -1)).sl_hangba_chieu = temp3;
		}else{
			(*(pkh+ count -1)).sl_hangnhat_chieu = 0;
			(*(pkh+ count -1)).sl_hangnhi_chieu = 0;
			(*(pkh+ count -1)).sl_hangba_chieu = 0;
		}
			
		printf("\nMua ve buoi toi(c/k): "); fflush(stdin); scanf("%c",&((*(pkh+ count -1)).is_even));
		char tl2 = (*(pkh+ count -1)).is_even;
		if (tl2 == 'c'){
			printf("\nSo luong ve hang nhat: "); fflush(stdin); scanf("%d",&temp4); checkvehang1(temp4); (*(pkh+ count -1)).sl_hangnhat_toi = temp4;
			printf("\nSo luong ve hang nhi: "); fflush(stdin); scanf("%d",&temp5); checkvehang2(temp5); (*(pkh+ count -1)).sl_hangnhi_toi = temp5;
			printf("\nSo luong ve hang ba: "); fflush(stdin); scanf("%d",&temp6); checkvehang3(temp6); (*(pkh+ count -1)).sl_hangba_toi = temp6;
		}else{
			(*(pkh+ count -1)).sl_hangnhat_toi = 0;
			(*(pkh+ count -1)).sl_hangnhi_toi = 0;
			(*(pkh+ count -1)).sl_hangba_toi = 0;
		}
			
		(*(pkh+ count -1)).tongtienchuagiam  = 0;
		(*(pkh+ count -1)).tongtienduocgiam = 0;
		(*(pkh+ count -1 )).tongtienphaitra = 0;
			
			
		printf(" Ban co muon tiep tuc khong?(c-k)");
		fflush(stdin);
		scanf("%c",&continuea);
	}while( continuea != 'k');
	N = count;
	return count;
}
void inhoadon(){
	if (pkh == NULL){
		printf("\nChua khoi tao danh sach khach hang\n");
	}else{
		printf("\n-------------------------IN HOA DON-------------------------------");
		int i = 0;
		for ( i = 0; i < N; i ++){
		printf("\n\n\nMa khach hang: %d",i+1);
		printf("\nTen khach hang: %s",(*(pkh + i)).ten);
		printf("\nDia chi: %s",(*(pkh+ i)).diachi);
		printf("\nLa thanh vien cau lac bo(c/k):%c ",(*(pkh+ i)).is_clb);
		
		printf("\nBan co mua ve chieu khong(c-k)): %c",(*(pkh+ i)).is_after);
		char tl1 = (*(pkh+ i)).is_after;
		if (tl1 == 'c'){
			printf("\nSo luong ve hang nhat: %d",(*(pkh+ i)).sl_hangnhat_chieu);
			printf("\nSo luong ve hang nhi: %d",(*(pkh+ i)).sl_hangnhi_chieu);
			printf("\nSo luong ve hang ba: %d",(*(pkh+ i)).sl_hangba_chieu);
		}
		printf("\nBan co mua ve toi khong(c-k)): %c",(*(pkh+ i)).is_even);
		if (tl1 == 'c'){
			printf("\nSo luong ve hang nhat: %d",(*(pkh+ i)).sl_hangnhat_toi);
			printf("\nSo luong ve hang nhi: %d",(*(pkh+ i)).sl_hangnhi_toi);
			printf("\nSo luong ve hang ba: %d",(*(pkh+ i)).sl_hangba_toi);
		}
	 	(*(pkh+ i)).tongtienchuagiam = ((*(pkh+ i)).sl_hangnhat_toi)*(price.giahangnhat) + ((*(pkh+ i)).sl_hangnhi_toi)*(price.giahangnhi)+((*(pkh+ i)).sl_hangba_toi)*(price.giahangba)+ 0.8*(((*(pkh+ i)).sl_hangnhat_chieu)*(price.giahangnhat) + ((*(pkh+ i)).sl_hangnhi_chieu)*(price.giahangnhi)+((*(pkh+ i)).sl_hangba_chieu)*(price.giahangba));
	 	char tl3 = (*(pkh+ i)).is_clb;
	 	if (tl3 == 'c'){
	 			(*(pkh+ i)).tongtienduocgiam = 0.1*((*(pkh+ i)).tongtienchuagiam);
	 			
	 	}else{
	 			(*(pkh+ i)).tongtienduocgiam = 0;
	 	}
		(*(pkh+ i)).tongtienphaitra = 	(*(pkh+ i)).tongtienchuagiam - (*(pkh+ i)).tongtienduocgiam;
		printf("\n\n\nTong tien chua giam(VND): %d",(*(pkh+ i)).tongtienchuagiam);
		printf("\nTong tien duoc giam(VND): %d",(*(pkh+ i)).tongtienduocgiam);
		printf("\nTong tien phai tra(VND): %d",(*(pkh+ i)).tongtienphaitra);
	}
	}
	
	
}

void inthongke(){
	int tongvechieuhangnhat = 0;
	int tongvechieuhanghai = 0;
	int tongvechieuhangba = 0;
	int tongvetoihangnhat = 0;
	int tongvetoihanghai = 0;
	int tongvetoihangba = 0;
	int soluongbantongcongchieu = 0;
	int soluongbantongcongtoi = 0;
	float tylebanhetsoghechieu = 0.0;
	float tylebanhetsoghetoi = 0;
	int tongcongtienchuagiam = 0;
	int tongcongtienduocgiam = 0;
	int tongcongtienthuvao = 0;
	int i = 0;
	if (pkh == NULL){
		printf("\n Chua khoi tao danh sach khach hang\n");
	}else{
		for (i = 0 ; i < N; i ++){
			tongvechieuhangnhat = tongvechieuhangnhat + (*(pkh+ i)).sl_hangnhat_chieu;
			tongvechieuhanghai = tongvechieuhanghai + (*(pkh+ i)).sl_hangnhi_chieu;
			tongvechieuhangba = tongvechieuhangba + (*(pkh+ i)).sl_hangba_chieu;
			tongvetoihangnhat = tongvetoihangnhat + (*(pkh+ i)).sl_hangnhat_toi;
			tongvetoihanghai = tongvetoihanghai + (*(pkh+ i)).sl_hangnhi_toi;
			tongvetoihangba = tongvetoihangba + (*(pkh+ i)).sl_hangba_toi;
			tongcongtienchuagiam = tongcongtienchuagiam + (*(pkh+ i)).tongtienchuagiam;
			tongcongtienduocgiam = tongcongtienduocgiam + (*(pkh+ i)).tongtienduocgiam;
		}
			soluongbantongcongchieu = tongvechieuhangnhat+tongvechieuhanghai+tongvechieuhangba;
			soluongbantongcongtoi = tongvetoihangnhat+tongvetoihanghai+tongvetoihangba;
			tylebanhetsoghechieu = (soluongbantongcongchieu *100)/300;
			tylebanhetsoghetoi = (soluongbantongcongtoi *100)/300;
			tongcongtienthuvao = tongcongtienchuagiam - tongcongtienduocgiam;
		
	
	
		printf("\n%40s%10s","BUOI CHIEU","BUOI TOI");
		printf("\nSo luong ve Hang nhat:%10d%13d ",tongvechieuhangnhat,tongvetoihangnhat);
		printf("\nSo luong ve Hang nhi: %10d%13d ",tongvechieuhanghai,tongvetoihanghai);
		printf("\nSo luong ve Hang ba:  %10d%13d ",tongvechieuhangba,tongvetoihangba);
		printf("\nTong so luong ban  :  %10d%13d ",soluongbantongcongchieu,soluongbantongcongtoi);
		printf("\nTy le ban          :  %10.2f%13.2f ",tylebanhetsoghechieu,tylebanhetsoghetoi);
		printf("\nTong cong tien chua giam(VND):%10d",tongcongtienchuagiam);
		printf("\nTong cong tien duoc giam(VND):%10d",tongcongtienduocgiam);
		printf("\nTong cong tien thu vao(VND)  :%10d",tongcongtienthuvao);
	
	
	}
 
	
	
}
void thoat(){
	system("exit");
}
int main(){
	// In Thuc Don
	int choice;
	do{
		printf("\n---------------------THUC DON------------------------\n");
		printf("\n1. Nhap gia ve buoi toi");
		printf("\n2. Nhap thong tin khach hang va yeu cau cua khach hang");
		printf("\n3. In hoa don cho khach hang");
		printf("\n4. In so lieu thong ke trong ngay");
		printf("\n5. Thoat");
		printf("\nMoi ban chon chuc nang(1,2,3,4,5): ");
		fflush(stdin);
		scanf("%d",&choice);
		switch(choice){
			case 1: nhapgiave(); break;
			case 2: nhapthongtinKH(); break;
			case 3: inhoadon(); break;
			case 4: inthongke(); break;
			case 5: thoat();
		}
		
	}while(choice != 5);
	system("pause");
	return 0;
}
