#include<stdio.h>

	/* Our structure */
	struct rec
	{
		int x,y,z;
	};

	int main()
	{
		int counter;
		FILE *ptr_myfile;
		struct rec my_record;

		ptr_myfile=fopen("test.bin","wb");
		if (!ptr_myfile)
		{
			printf("Unable to open file!");
			return 1;
		}
		for ( counter=1; counter <= 10; counter++)
		{
			my_record.x= counter;
			my_record.y= counter +1;
			my_record.z= counter  + 2;
			fwrite(&my_record, sizeof(struct rec), 3, ptr_myfile);
		}
		
		
		fclose(ptr_myfile);
		return 0;
	}
