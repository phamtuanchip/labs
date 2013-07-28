#include <stdio.h>
#include <stdlib.h> 	
/*required for the malloc and free functions*/
int main(){
  int number;
  int *ptr;
  int i;
  printf("How many ints would you like store? ");
  scanf("%d", &number);
  ptr = (int *) malloc (number*sizeof(int)); 
	/*allocate memory */ 
	if(ptr!=NULL) {
     for(i=0 ; i<number ; i++){
      *(ptr+i) = i;
  	  }
	  for(i=number ; i>0 ; i--) {
    		printf("%d\n",*(ptr+(i-1))); 
		/* print out in reverse order */
	   }
    	free(ptr); /* free allocated memory */
    	 
     } else {
      printf("\nMemory allocation failed - 		not enough memory.\n");
    
    } 
    
  getch();
  
  return 0;
}
