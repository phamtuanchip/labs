#include <stdio.h>
	main (){
		char ch;          
		//clrscr ();
		printf ("\nEnter a lower cased   			alphabet (a - z) :");
		scanf("%c", &ch);

		if (ch < 'a' || ch > 'z')
	printf("\nCharacter not a lower cased alphabet");
else
	switch (ch) {
		case 'a' :                             
		case 'e' :
		case 'i' :
		case 'o' :
		case 'u' :
			printf("\nCharacter is a vowel");
			break;
		case 'z' :
			printf ("\nLast Alphabet (z) was entered");
			break;
		default :
			printf("\nCharacter is a consonant");
			break;
	}
}