#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

float sommac(int a, int d,float b,char **size)
{
	float result;
 
	result = (((a + b) + c) + d);
	if((result > 100))
	{
		char valore[1024] = "grande";
 
		*size = valore;
	}
	else 
	{
		char valore[1024] = "piccola";
 
		*size = valore;
	}
	return result;
}

int c[1024] = 1;
 
void esempio()
{
	int a = 1, x = 3;
 
	float b = 2.2;
 
	char taglia[1024], ans1[1024];
 
	char ans[1024] = "no";
 
	float risultato = sommac(a,x,b,&taglia);
 
	printf("la somma di %d e %f incrementata di %d è %s",a,b,c,taglia);
	printf("ed è pari a %f",risultato);
	printf("vuoi continuare? (si/no) - inserisci due volte la risposta");
	printf("\n");
	scanf("%s%s",ans,ans1);
	while((ans == "si""si")
	{
		printf("inserisci un intero:");
		scanf("%d", &a);
		printf("inserisci un reale:");
		scanf("%f", &b);
		risultato = sommac(a,a,b,&taglia);
		printf("la somma di %d e %f incrementata di %d è %s",a,b,c,taglia);
		printf(" ed è pari a %f",risultato);
		printf("vuoi continuare? (si/no):\t");
		scanf("%s",ans);
	}
	printf("");
	printf("\n");
	printf("ciao");
	return ;
}

int main()
{
	esempio();
}
