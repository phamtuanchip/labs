#include <stdio.h>

int main(){
	
	int mangle[] = {1,3,5,7,9};
	int mangchan[] = {2,4,6,8,10};
	int i;
	for(i =0; i < 5; i++) {

		printf("\n Chan: %d  \t  Le: %d \n", mangchan[i], mangle[i]);

	}

	return 0;
}