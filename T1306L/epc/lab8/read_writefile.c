
#include <stdio.h>

void openFile() {

	FILE *ifp, *ofp;
char *mode = "r";
char outputFilename[] = "out.list";

ifp = fopen("test.bin", mode);

if (ifp == NULL) {
  fprintf(stderr, "Can't open input file in.list!\n");
  //exit(1);
}

ofp = fopen(outputFilename, "w");

if (ofp == NULL) {
  fprintf(stderr, "Can't open output file %s!\n",
          outputFilename);
  //exit(1);
}
}

int main(){
FILE *fp;
fp=fopen("test.bin", "wb");
char x[10]="ABCDEFGHIJ";
fwrite(x, sizeof(x[0]), sizeof(x)/sizeof(x[0]), fp);
printf("save file success \n");

openFile();

getchar();
return 0;
}
