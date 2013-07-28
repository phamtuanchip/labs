  #include <stdio.h>
  #include <stdlib.h>
  #include <string.h>
  #include <time.h>
 
 
int main(){
    int iSecret[7], iGuess[2];

      /* initialize random seed: */
        srand ( time(NULL) );

      /* generate secret number: */
        int i;
       for(i=0; i < 7; i++){
        iSecret[i] = rand() % 100 + 1;
       }
       int loto[100],  *pt[100] ;
      
     
       
       for(i=0; i < 100; i++){
        
        loto[i] = i;
        pt[i] = &loto[i];
         
         
       }
       
       for(i=0; i < 100; i++){
        
        printf("[%d] ", *pt[i]);
       
         
       }
       
        
       printf("\n\n Your guess, choice 2: ");
       scanf("%d",&iGuess[0]);
       fflush(stdin);
       scanf("%d",&iGuess[1]);
       fflush(stdin);
       
       printf("\n\n KQ: ", time(NULL));
       for(i=0; i < 7; i++){
        printf("[%d] ", iSecret[i]);
       }
       printf("\n\n Your guessed: ");
       for(i=0; i < 2; i++){
        printf("[%d] ", iGuess[i]);
       }
      
       
         
        
getch();
return 0;    
}
