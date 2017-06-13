#include<stdio.h>
#include<stdlib.h>

void error(char *m)
{
	fprintf(stderr, "%s\n",  m);
	exit(1);
}

void match(char t);
void F();
void T();
void E();
char lookhead;

int main()
{
	while (1)
	{
		printf("ready>");
		lookhead = getchar();
		E();
		printf("suucced\n");
	}
	
}

void match(char t)
{
	if (lookhead == t)
		lookhead = getchar();
	else
		error("sytax error");

}

void F()
{
	if (lookhead == '(')
	{
		match('(');
		E();
		match(')');
	}
	else if (lookhead == 'i')
		match('i');
	else
		error("syntax error");
}

void E()
{
	

	T();

	while (lookhead == '+')
	{
		match('+');
		T();
	}
}

void T()
{
	F();
	while(lookhead == '*')
	{
		match('*');
		F();
	}
}