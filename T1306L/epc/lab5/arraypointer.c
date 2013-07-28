#include<stdio.h>
void poray1(){
	static int ary[10] ={1,2,3,4,5,6,7,8,9,10};
	int i;
	for (i= 0;i<10;i++){	  	
        printf("\ni=%d,aryi]=%d,*(ary+i)=%d",i,ary[i],*(ary + i));
        printf("&ary[i]=%X,ary+i=%X",&ary[i],ary+i);
	/*%X gives unsigned hexadecimal*/
 
	}
}

#include <stdio.h>
#include <string.h>
void main (){
	char a, str[81], *ptr;
	printf("\nEnter a sentence:");
	gets(str);
	printf("\nEnter character to search for:");
	a = getche();
	ptr = strchr(str,a);
	/* return pointer to char*/
	printf("\nString starts at address: %u",str);
	printf("\nFirst occurrence of the character is at address: %u ",ptr);
	//printf("\n Position of first occurrence(starting from 	0)is: % d", ptr_str);
	getch();
	return 0;
}

