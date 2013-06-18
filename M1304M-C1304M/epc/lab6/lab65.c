#include <stdio.h>
int main ()
{
    int array[] = { 45, 67, 89 };
    int * array_ptr;
    array_ptr = array;
    printf(" first element: %i\n", *(array_ptr++));
    printf("second element: %i\n", *(array_ptr++));
    printf(" third element: %i\n", *array_ptr);
    return 0;
}