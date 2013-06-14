#include <stdio.h>
#include <stdlib.h>

int mainMenu(void);

int main(int argc, char *argv[])
{
   while(mainMenu() != 4);

   return 0;
}

void registerObject(){
   printf("Registered !");
}

void unregisterObject(){
   printf("Unregistered !");
}

void query(){
    printf("Quering ....");
}
int mainMenu(void)
{
   int option = -1;

   //system("clear");

   printf("Welcome\n");
   printf("1. Register\n");
   printf("2. Unregister\n");
   printf("3. Query\n");
   printf("4. Exit\n");
   printf("\nOption: ");
   scanf("%d", &option);

   switch (option)
   {
      case 1:
         registerObject();
         break;
      case 2:
         unregisterObject();
         break;
      case 3:
         query();
         break;
      case 4:
         printf("All done!\n");
         break;
      default:
         // no point printing message; system("clear") will erase it.
         //printf("Enter a valid Option, 1 - 4");

         // this will flush the input stream
         while (getchar() != '\n');
         break;
   }

   return option;
}