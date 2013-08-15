#include <stdlib.h>
#include <stdio.h>

 int people_count;
struct Person 
{
    char f_name[256];
    char l_name[256];
    int age;
};

void readStruct(){
    FILE* data;
    if ((data = fopen("data.bin", "rb")) == NULL)
    {
        printf("Error opening file\n");
         
    }

    struct Person* people;
    people = malloc(sizeof(*people)*people_count);
    fread(people, sizeof(struct Person) * 1/* Just read one person */, 3, data);
     int n;
    for (n = 0; n < people_count; n++)
    {
    printf("NAME : %s  %s\n", people[n].f_name, people[n].l_name);
    printf("Age : %d\n", people[n].age);
    
    }
    free(people);
    fclose(data);
    getchar();
}

void writeStruct()
{
    struct Person* people ;
   

    printf("How many people would you like to create: ");
    scanf("%i", &people_count);
    people = malloc(sizeof(struct Person) * people_count);  

    int n;
    for (n = 0; n < people_count; n++)
    {
        printf("Person %i's First Name: ", n);
        scanf("%s", people[n].f_name);

        printf("Person %i's Last Name: ", n);
        scanf("%s", people[n].l_name);

        printf("Person %i's Age: ", n);
        scanf("%i", &people[n].age);
    }

    FILE* data;
    if ( (data = fopen("data.bin", "wb")) == NULL )
    {
        printf("Error opening file\n");
        
    }

    fwrite(people, sizeof(struct Person) * people_count, 3, data);
    free(people);
    fclose(data);
    getchar();
     
}


int main(int argc, char* argv[])
{
    writeStruct();
    readStruct();
    getchar();
    return 0;

}
