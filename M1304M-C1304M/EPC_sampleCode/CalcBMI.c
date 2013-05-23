#include <stdio.h>

int main (void)
{
	float		height, weight, BMI;
	printf("身長は?(cm)");
	scanf("%f", &height);
	height/=100;
	printf("体重は?(Kg)");
	scanf("%f", &weight);
	
	BMI=weight/(height*height);
	printf("あなたのBMIは%.1fです\n", BMI);
	printf("あなたの標準体重は%.1fKgです\n", (height*height)*22);

	return 0;
}
