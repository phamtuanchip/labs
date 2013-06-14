#include <stdio.h>
 
static int out_of_range ( int val, int lower, int upper )
{
  return val < lower || upper < val;
}
 
static int menu ( void )
{
  int option = 0;
   
  while ( out_of_range ( option, 1, 4 ) ) {
    printf ( "Please chose from the options below:\n" );
    printf ( "[1] Start\n" );
    printf ( "[2] About\n" );
    printf ( "[3] Help\n" );
    printf ( "[4] Exit\n\n" );
     
    /* Always check the return value of input functions */
    if ( scanf ( " %d", &option ) != 1 ) {
      /* Tidy up */
      int ch;
 
      while ( ( ch = getchar() ) != '\n' && ch != EOF )
        ;
    }
     
    if ( out_of_range ( option, 1, 4 ) )
      printf("Invalid entry, please try again.\n");
  }
   
  return option;
}
 
int main ( void )
{
  switch ( menu() ) {
  case 1:
    printf ( "Starting...\n" );
    break;
  case 2:
    printf ( "Menu Program -- ver. 1.00\n" );
    break;
  case 3:
    printf ( "No help available\n" );
    break;
  case 4:
    printf ( "Have a nice day!\n" );
    break;
  }
   
  return 0;
}