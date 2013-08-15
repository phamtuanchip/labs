#include <stdio.h>

struct cat {
	   char name[25];
       char author[20];
       int end ;
       double price;
};

struct cat book1 = {"sach 1","nha van 1", 2, 30000};
struct cat *pt ;
//struct cat book2[2] = {{"sach 1 nhom2", "nha van 1", 3, 28000}{"sach 2 nhom2", "nha van 1", 3, 28000}{"sach 3 nhom2", "nha van 1", 3, 28000}};
int main(){
	pt = &book1;
	printf("in sach 1 ten sach: %s  tac gia %s  so luong xuat ban %d  gia tien %f   \n", book1.name, book1.author, book1.end, book1.price);
//	gets(book1.name);
	//puts(book1.name);
	///book2 = book1;
	printf("in  sach 2 ten sach: %s  tac gia %s  so luong xuat ban %d  gia tien %f   \n", pt->name, pt->author, pt->end, (*pt).price);	
	(*pt).price = 60000;
	printf("gia moi %f:", book1.price);
	
	typedef float sothuc ;
	
	sothuc st1 ;
	
	typedef struct cat catnew;
	
	catnew book2; 
	
	
	
	
	
	getchar();
	return 0;
}
