#include <stdio.h>

int main ()
{
    /* x is an integer variable. */
    int x = 42;
    /* x_ptr is a pointer to an integer variable. */
    int * x_ptr = & x;
    printf ("x = %d\n", x);
    printf ("x_ptr = %p\n", x_ptr);
    return 0;
}