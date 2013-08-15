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

		ptr_myfile=fopen("test.bin","rb");
		if (!ptr_myfile)
		{
			printf("Unable to open file!");
			return 1;
		}

		fseek(ptr_myfile, sizeof(struct rec), SEEK_END);
		rewind(ptr_myfile);

		for ( counter=1; counter <= 10; counter++)
		{
			fread(&my_record,sizeof(struct rec),3,ptr_myfile);
			printf("%d\n",my_record.x);
			printf("%d\n",my_record.y);
		    printf("%d\n",my_record.z);
		}
		fclose(ptr_myfile);
		getchar();
		return 0;
	}
