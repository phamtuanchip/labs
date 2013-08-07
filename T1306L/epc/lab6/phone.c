#include<stdio.h>

	struct telephone
	{
		char *name;
		int number;
	};

	int main()
	{
		struct telephone index;

		index.name = "Jane Doe";
		index.number = 12345;
		printf("Name: %s\n", index.name);
		printf("Telephone number: %d\n", index.number);
		
		return 0;
	}