//入力した数字の平方根を計算します
//scanfを使うような対話型のプログラムはこのソフト内での実行はできません
//ターミナルで実行してください
//ファイル名が日本語だとターミナルで実行できませんので注意

#include <stdio.h>
#include <math.h>

int main (void)
{
	long	a;
	
	printf("平方根を計算します。0を入力すると終了します。\n\n");

	do
	{
		scanf("%d", &a);
		if(a!=0)
			printf("%dの平方根は「%f」です。\n", a, sqrt(a) );
	}
	while( a!=0 );

	printf("終了します。\n\n");

	return 0;
}
