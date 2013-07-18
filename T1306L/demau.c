#include <stdio.h>
#include <stdlib.h>
#include <string.h>


struct khachhang{
       char ten[20];
       char diachi[20];
       int sodt;
};

struct sudung{
       int hgoidi;
       int dichvu;
};


void question1(){
     
     struct khachhang p;
     int t;
     char s[20];
     printf("\nTen khach hang : ");
     fflush(stdin);
     gets(s);
     strcpy(p.ten,s);
     printf("\nDia chi : ");
     fflush(stdin);
     gets(s);
     strcpy(p.diachi,s);
     printf("\nSo dien thoai : ");
     scanf("%d",&t);
     p.sodt = t;
}

void question2(struct sudung p[]){
     int i;
     for(i = 0; i < 12 ;i++){
             printf("\nThang %d ",i+1);
             printf("\nThoi gian goi di : ");
             scanf("%d",&p[i].hgoidi);
             printf("Thoi gian su dung dich vu 108: ");
             scanf("%d",&p[i].dichvu);
             
     }
     
}

void question3(struct sudung p[]){
     int i;
     
     int tongthoigian[12];
     int tongtien[12];
     
     int thoigiangioihan = 100;
     int tienthuebao = 60000;
     int tienvuottroi[12];
     int thoigianvuot[12];
     int tien108[12];
     int tienvat[12];
     for(i = 0; i < 12 ;i++){
           printf("\n\n\n\nHoa don su dung thang thu %d",i+1);
           printf("\n%-30s%-25s%-5s\n","STTChi phi","Thoi gian","Thanh tien");
           printf("--------------------------------------------------------------------");
          
           thoigianvuot[i]  = p[i].hgoidi - 100;
           tienvuottroi[i] = thoigianvuot[i]*100;
           
           
           tien108[i] = p[i].dichvu * 150;
           tienvat[i] = (int)((60000 + tienvuottroi[i] + tien108[i])*0.1);
           tongthoigian[i] = p[i].hgoidi + p[i].dichvu;
           
           tongtien[12] = 60000 + tienvuottroi[i] + tien108[i] +  tienvat[i];
           
           printf("\n1Chi phi chua co thue : ");
           printf("\n    Tien thue bao : ");
           printf("%15d%30d",thoigiangioihan,tienthuebao);
           printf("\n    Tien vuot troi : ");
           
           printf("%15d%30d",thoigianvuot[i],tienvuottroi[i]);
           printf("\n    Chi phi goi 108 : ");
           printf("%15d%30d",p[i].dichvu,tien108[i]);
           printf("\n Thue VAT :  ");
           printf("%50d",tienvat[i]);
           printf("\n--------------------------------------------------------------------");
           printf("\nTong chi phi = ");
           printf("%15d%30d",tongthoigian[i],tongtien[i]);
           
           
           
           
           
     }
}



main(){
       
    
    int ch;                     
    printf("1. Thong tin chi tiet khach hang. ");
    printf("\n2. Thong tin su dung dien thoai");
    printf("\n3. Hoa don hang thang");
    printf("\n4. Bao cao ca nam");
    printf("\n5. Bao cao 3 thang cao nhat(bonus)");
    printf("\n6. Thoat");
    
        
           
           
    struct sudung p[12];
    do{
           printf("\nNhap vao su lua chon :  ");
           scanf("%d",&ch);
           switch(ch){
                      case 1:
                           question1();
                           break;
                      case 2:
                           question2(p);
                           break;
                      case 3: 
                           question3(p);
                           break;
                      
                           
                      case 6: 
                           exit(0);
                           
           }      
         
    }while(1);
    
    getch();

}
