#include<stdio.h>

	typedef struct telephone
	{
		char *name;
		int number;
	}TELEPHONE;

	int main()
	{
		TELEPHONE index;
		TELEPHONE *ptr_myindex;

		ptr_myindex = &index;

		ptr_myindex->name = "Jane Doe";
		ptr_myindex->number = 12345;
		printf("Name: %s\n", ptr_myindex->name);
		printf("Telephone number: %d\n", ptr_myindex->number);

		return 0;
	}
