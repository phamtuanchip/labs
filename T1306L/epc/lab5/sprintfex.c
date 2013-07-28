/* sprintf example */
#include <stdio.h>

int main ()
{
  char buffer [2];
  int n, a=5, b=3;
  n=sprintf (buffer, "%02d",a);
  printf ("[%s] is a string %d chars long\n",buffer,n);
  getch();
  return 0;
}
