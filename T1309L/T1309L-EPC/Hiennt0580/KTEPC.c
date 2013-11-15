#include<stdio.h>
#include<string.h>
#include<stdlib.h>
 struct giave{
	int gvhangnhat;
	int gvhangnhi;
	int gvhangba;
};

 struct khachhang{
	char ten[20];
	char diachi[20];
	char tvclb;
	char tg_sau;
	char tg_sukien;
	int slhangnhat_chieu;
	int slhangnhi_chieu;
	int slhangba_chieu;
	int slhangnhat_toi;
	int slhangnhi_toi;
	int slhangba_toi;
	int tongtienchuagiam;
	int tongtienduocgiam;
	int tongtienphaitra;
	
};
struct giave price;
struct khachhang *pkh;
int KH = 0;
void nhapgv(){
	printf("\n----------------NHAP THONG TIN GIA VE BUOI TOI--------------------");
	printf("\n Nhap gia ve hang nhat: ");
    fflush(stdin);
	scanf("%d",&price.gvhangnhat);
	printf("\n Nhap gia ve hang nhi: ");
	fflush(stdin);
	scanf("%d",&price.gvhangnhi);
	printf("\n Nhap gia ve hang ba: "); 
	fflush(stdin);
	scanf("%d",&price.gvhangba);
}
int nhapthongtinKH(){
	int count;
	char Ntieptuc;
	printf("\n--------------------NHAP THONG TIN KHACH HANG-------------------");
	pkh = (struct khachhang*)malloc(sizeof(struct khachhang));
	do{
		count ++;
		if(count > 1){
			pkh = (struct khachhang*)realloc(pkh,count*sizeof(struct khachhang));
		}
		printf("\nNhap ho ten: "); 
		fflush(stdin); 
		scanf("%s",(*(pkh+ count -1)).ten);
		printf("\nNhap dia chi: "); 
		fflush(stdin); 
		scanf("%s",(*(pkh+ count -1)).diachi);
		printf("\nBan co phai thanh vien cau lac bo khong(c-k)): "); 
		fflush(stdin); 
		scanf("%c",&((*(pkh+ count -1)).tvclb));
		printf("\nBan co mua ve chieu khong(c-k)): "); 
		fflush(stdin); 
		scanf("%c",&((*(pkh+ count -1)).tg_sau));
		char tl1 = (*(pkh+ count -1)).tg_sau;
		if (tl1 == 'c'){
			printf("\nBan muon mua bao nhieu ve chieu hang nhat? "); 
			fflush(stdin); 
			scanf("%d",&((*(pkh+ count -1)).slhangnhat_chieu));
			printf("\nBan muon mua bao nhieu ve chieu hang nhi? "); 
			fflush(stdin); 
			scanf("%d",&((*(pkh+ count -1)).slhangnhi_chieu));
			printf("\nBan muon mua bao nhieu ve chieu hang ba? "); 
			fflush(stdin); 
			scanf("%d",&((*(pkh+ count -1)).slhangba_chieu));
		}
		printf("\nBan co mua ve toi khong(c-k)): "); fflush(stdin); scanf("%c",&((*(pkh+ count -1)).tg_sukien));
		char tl2 = (*(pkh+ count -1)).tg_sukien;
		if (tl2 == 'c'){
			printf("\nBan muon mua bao nhieu ve toi hang nhat? "); 
			fflush(stdin); 
			scanf("%d",&((*(pkh+ count -1)).slhangnhat_toi));
			printf("\nBan muon mua bao nhieu ve toi hang nhi? "); 
			fflush(stdin); 
			scanf("%d",&((*(pkh+ count -1)).slhangnhi_toi));
			printf("\nBan muon mua bao nhieu ve toi hang ba? "); 
			fflush(stdin); 
			scanf("%d",&((*(pkh+ count -1)).slhangba_toi));
		};
		
		printf(" Ban co muon tiep tuc khong?");
		fflush(stdin);
		scanf("%c",&Ntieptuc);
	}while( Ntieptuc != 'n');
	return count;
}int nhapttKH(){
	int count;
	char Ntieptuc;
	printf("\n--------------------NHAP THONG TIN KHACH HANG-------------------");
	pkh = (struct khachhang*)malloc(sizeof(struct khachhang));
	do{
		count ++;
		if(count > 1){
			pkh = (struct khachhang*)realloc(pkh,count*sizeof(struct khachhang));
		}
		printf("\nNhap ho ten: "); 
		fflush(stdin); 
		scanf("%s",(*(pkh+ count -1)).ten);
		printf("\nNhap dia chi: "); 
		fflush(stdin); 
		scanf("%s",(*(pkh+ count -1)).diachi);
		printf("\nBan co phai thanh vien cau lac bo khong(c-k)): "); 
		fflush(stdin); 
		scanf("%c",&((*(pkh+ count -1)).tvclb));
		printf("\nBan co mua ve chieu khong(c-k)): "); 
		fflush(stdin); 
		scanf("%c",&((*(pkh+ count -1)).tg_sau));
		char tl1 = (*(pkh+ count -1)).tg_sau;
		if (tl1 == 'c'){
			printf("\nBan muon mua bao nhieu ve chieu hang nhat? "); 
			fflush(stdin); 
			scanf("%d",&((*(pkh+ count -1)).slhangnhat_chieu));
			printf("\nBan muon mua bao nhieu ve chieu hang nhi? "); 
			fflush(stdin); 
			scanf("%d",&((*(pkh+ count -1)).slhangnhi_chieu));
			printf("\nBan muon mua bao nhieu ve chieu hang ba? "); 
			fflush(stdin); 
			scanf("%d",&((*(pkh+ count -1)).slhangba_chieu));
		}
		printf("\nBan co mua ve toi khong(c-k)): ");
		fflush(stdin); 
		scanf("%c",&((*(pkh+ count -1)).tg_sukien));
		char tl2 = (*(pkh+ count -1)).tg_sukien;
		if (tl2 == 'c'){
			printf("\nBan muon mua bao nhieu ve toi hang nhat? "); 
			fflush(stdin); 
			scanf("%d",&((*(pkh+ count -1)).slhangnhat_toi));
			printf("\nBan muon mua bao nhieu ve toi hang nhi? "); 
			fflush(stdin); 
			scanf("%d",&((*(pkh+ count -1)).slhangnhi_toi));
			printf("\nBan muon mua bao nhieu ve toi hang ba? "); 
			fflush(stdin); 
			scanf("%d",&((*(pkh+ count -1)).slhangba_toi));
		};
		
		printf(" Ban co muon tiep tuc khong?");
		fflush(stdin);
		scanf("%c",&Ntieptuc);
	}while( Ntieptuc != 'n');
	return count;
}
void inhoadon(){
	printf("\n-------------------------IN HOA DON-------------------------------");
	int i = 0;
	printf("%d",KH);
	for ( i = 0; i < KH; i ++){
		printf("\nMa khach hang: %d",i+1);
		printf("\nTen khach hang: %s",(*(pkh + i)).ten);
		printf("\nDia chi: %s",(*(pkh+ i)).diachi);
		printf("\nBan co phai thanh vien cau lac bo khong(c-k)):%c ",(*(pkh+ i)).tvclb);
		
		printf("\nBan co mua ve chieu khong(c-k)): %c",(*(pkh+ i)).tg_sau);
		char tl1 = (*(pkh+ i)).tg_sau;
		if (tl1 == 'c'){
			printf("\n So luong ve hang nhat: %d",(*(pkh+ i)).slhangnhat_chieu);
			printf("\n So luong ve hang nhi: %d",(*(pkh+ i)).slhangnhi_chieu);
			printf("\n So luong ve hang ba: %d",(*(pkh+ i)).slhangba_chieu);
		}
		printf("\nBan co mua ve toi khong(c-k)): %c",(*(pkh+ i)).tg_sukien);
		if (tl1 == 'c'){
			printf("\n So luong ve hang nhat: %d",(*(pkh+ i)).slhangnhat_toi);
			printf("\n So luong ve hang nhi: %d",(*(pkh+ i)).slhangnhi_toi);
			printf("\n So luong ve hang ba: %d",(*(pkh+ i)).slhangba_toi);
		}
	 	(*(pkh+ i)).tongtienchuagiam = ((*(pkh+ i)).slhangnhat_toi)*(price.gvhangnhat) + ((*(pkh+ i)).slhangnhi_toi)*(price.gvhangnhi)+((*(pkh+ i)).slhangba_toi)*(price.gvhangba)+ 0.8*(((*(pkh+ i)).slhangnhat_chieu)*(price.gvhangnhat) + ((*(pkh+ i)).slhangnhi_chieu)*(price.gvhangnhi)+((*(pkh+ i)).slhangba_chieu)*(price.gvhangba));
	 	char tl3 = (*(pkh+ i)).tvclb;
	 	if (tl3 == 'c'){
	 			(*(pkh+ i)).tongtienduocgiam = 0.1*((*(pkh+ i)).tongtienchuagiam);
	 			
	 	}else{
	 			(*(pkh+ i)).tongtienduocgiam = 0;
	 	}
		(*(pkh+ i)).tongtienphaitra = 	(*(pkh+ i)).tongtienchuagiam - (*(pkh+ i)).tongtienduocgiam;
		printf("\n Tong tien chua giam: %d",(*(pkh+ i)).tongtienchuagiam);
		printf("\n Tong tien duoc giam: %d",(*(pkh+ i)).tongtienduocgiam);
		printf("\n Tong tien phai tra: %d",(*(pkh+ i)).tongtienphaitra);
		
	}
	
}
void inthke(){
	printf("%20s\n","THONG KE");
	printf("%30s%10S\n","BUOI CHIEU","BUOI TOI");
	printf("%1s%10s%10s\n"," So luong ve Hang nhat","40","80" );
	printf("%1s%10s%10s\n"," So luong ve Hang nhi","50","100");
	printf("%1s%10s%10s\n"," So luong ve Hang ba","60","120");
	printf("%1s%10s%10s\n"," So luong ban tong cong)","150","300");
	printf("%1s%10s%10s\n\n"," Ty le ban het so ghe tong cong(x100)","50.0","100.0");
	printf("%1s%10s\n"," Tong tien chua giam(VND)","19880000" );
	printf("%1s%10s\n"," Tong tien duoc giam(VND)","9000000" );
	printf("%1s%10s\n"," Tong tien thu vao(VND)","18980000" );
	
}

int main(){
	// In Thuc Don
	int ok;
	do{
		printf("\n---------------------THUC DON------------------------\n");
		printf("\n1. Nhap gia ve buoi toi");
		printf("\n2. Nhap thong tin khach hang va yeu cau cua khach hang");
		printf("\n3. In hoa don cho khach hang");
		printf("\n4. In so lieu thong ke trong ngay");
		printf("\n5. Thoat");
		printf("\nMoi ban chon chuc nang(1,2,3,4,5): ");
		fflush(stdin);
		scanf("%d",&ok);
		switch(ok){
			case 1: nhapgv(); break;
			case 2: KH = nhapttKH(); break;
			case 3: inhoadon(); break;
			case 4: inthke(); break;
			case 5: exit(0);
			default: printf("Xin Chao");
		}
		
	}while(ok != 5);
	system("pause");
	return 0;
}

