#include <stdio.h>

int main(){
    int a[100];
    int n,i;
    printf("nhap vao so luong phan tu:");
    scanf("%d", &n);
    for(i=0;i<n;i++)
                    {
                        printf("\nnhap vao phan tu thu a[%d]=",i);
                        scanf ("%d",&a[i]);                    
                    }

    printf("\n danh sach cac phan tu \n");             
    for (i=0;i<n;i++){
       
        if(a[i]%2==0){
                 printf("mang so chan %d\n",a[i]);
             } 
        else {
               printf("mang so le %d\n",a[i]);
               }
    }
  /*printf("\n mang so chan la:"); 
     for(i=0;i<n;i++) 
     { 
         if(a[i]%2==0) 
         printf("%d    ",a[i]); 
     } 
     printf("\n mang so le la:"); 
     for(i=0;i<n;i++) 
     { 
         if(a[i]%2!=0) 
         printf("%d    ",a[i]); 
     } 
    */
    getchar();
    return 0;
    }