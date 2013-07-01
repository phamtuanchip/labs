#include <stdio.h> 

void saveTextFile(){
FILE *fp;
fp=fopen("test.txt", "w");
fprintf(fp, "Testing...\n");
}
void saveFile(){
FILE *fp;
fp=fopen("test.bin", "wb");
char x[10]="ABCDEFGHIJ";
fwrite(x, sizeof(x[0]), sizeof(x)/sizeof(x[0]), fp);
}
int main(){

saveFile();
}
