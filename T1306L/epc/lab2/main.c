#include <stdio.h>

int main(){
    float vl,hh,sh,s;
    float tb;
    printf("Diem vat ly, hoa hoc, sinh hoc: ");
    scanf("%f %f %f",&vl, &hh, &sh);
    
    s= vl + hh + sh;
    tb= s/3;
    printf("tong %f \n",s);
    printf("trung binh %2.2f\n", tb);
    
    getchar();
    return 0;
}