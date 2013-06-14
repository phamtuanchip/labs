#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void option1()
{    printf("You have selected option 1\n");}

void option2()
{    printf("You have selected option 2\n");}

void option3()
{    printf("You have selected option 3\n");}

void option4()
{    printf("You have selected option 4\n");}

int get_option()
{
    printf("Menu:\n\n");
    printf("1) One\n");
    printf("2) Two\n");
    printf("3) Three\n");
    printf("4) Four\n");
    printf("5) Five\n");
    printf("Please select option 1-5:  ");
    int opt;
    scanf("%d", &opt);
    return opt;
}

int main()
{
    int i = get_option();  
    while (i < 1 || i > 5)
    {
        printf("You have entered an invalid input character.  Please try again.\n\n");
        i = get_option();
    } 
     
    if (i == 1)
        option1();
    else if (i == 2)
        option2(); 
    else if (i == 3)
        option3();
    else if (i == 4)
        option4();
    else if (i == 5)
        exit(1);
           
//system("pause");
return 0;
}