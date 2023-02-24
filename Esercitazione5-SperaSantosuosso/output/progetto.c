#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>


int a;
 
int test(int a);


int ciao()
{
	char y[1024] = "ciao";
 
	char x[1024] = "ciao";
 
	if((5 < 4))
	{
		y = "ciao2";
		printf("%s",y);
	}
	if((5 < 4))
	{
		x = "ciao2";
		printf("%s",x);
	}
	printf("%s",x);
	return 1;
}

int main()
{
	ciao();
}
int test(int a)
{
	int i = 3;
 
	return i;
}

