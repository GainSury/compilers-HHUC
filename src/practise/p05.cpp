#include<stdio.h>

char lexan()
{
	char t;
	t = getchar();
	return t;
}

char lex[100];
int num;

int main()
{
	char lookhead = lexan();
	lex[num++] = lookhead;
	//state 0
	if (lookhead == '0')
	{
		lookhead = lexan();
		lex[num++] = lookhead;
		//state 1
		if (lookhead >= '0' && lookhead <= '7')
		{
			//state 2
			lookhead = lexan();
			while (lookhead >= '0' && lookhead <= '7')
			{
				lex[num++] = lookhead;
				lookhead = lexan();
			}
			//state 3
		    lex[num++] = 0;
			printf("OCT ");
			for (int i = 0; i<num; i++)
				printf("%c", lex[i]);
			
		}
		else if (lookhead == 'x')
		{
		    //state 4
			lookhead = lexan();
			lex[num++] = lookhead;
			if ((lookhead >= '0' && lookhead <= '9') || (lookhead >= 'a' && lookhead <= 'f') || (lookhead >= 'A' && lookhead <= 'F'))
			{
				//state 5
				lookhead = lexan();

				while ((lookhead >= '0' && lookhead <= '9') || (lookhead >= 'a' && lookhead <= 'f') || (lookhead >= 'A' && lookhead <= 'F'))
				{
					lex[num++] = lookhead;
					lookhead = lexan();
				}
				//state 6
				lex[num++] = 0;
				printf("HEX ");
				for (int i = 0; i<num; i++)
					printf("%c", lex[i]);
			}
		}
		else
		{
			//state 8
			lex[num++] = 0;
			printf("DEC");
			for (int i = 0; i<num; i++)
				printf("%c", lex[i]);
		}
	}
	else if(lookhead >= '0' && lookhead <= '9')
	{
		//state 7
		lookhead = lexan();
		while (lookhead >= '0' && lookhead <= '9')
		{
			lex[num++] = lookhead;
			lookhead = lexan();
		}
		//state 8
		lex[num++] = 0;
		printf("DEC ");
		for(int i = 0;i<num;i++)
			printf("%c",lex[i]);
	}
	else
	{
		printf("ERR");
	}
	while (1) { ; }
}