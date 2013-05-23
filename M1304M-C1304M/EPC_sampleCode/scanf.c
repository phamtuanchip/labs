//scanfを使うような対話型のプログラムはこのソフト内での実行はできません
//ターミナルで実行してください
//ファイル名が日本語だとターミナルで実行できませんので注意

#include <stdio.h>

int main (void)
{
	char s[100];

	printf("Input:");
	scanf("%s", s);
	printf("あなたが入力した文字列は、\n「%s」です。\n", s);
}