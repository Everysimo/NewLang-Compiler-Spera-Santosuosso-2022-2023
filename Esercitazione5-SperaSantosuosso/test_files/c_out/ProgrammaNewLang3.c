#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int c = 1;
 


void stampa(char *messaggio);

float sommac(int a, int d,float b,char **size);

void esempio()
{
	int a = 1;
	float b = 2.2;
	int x = 3;
 
	char taglia[1024], ans1[1024];
 
	char ans[1024] = "no";
 
	float risultato = sommac(a,x,b,&taglia);
 
	printf("la somma di %d e %f incrementata di %d è %s",a,b,c,taglia);
	printf("ed è pari a %f",risultato);
	printf("vuoi continuare? (si/no) - inserisci due volte la risposta");
	printf("\n");
	scanf("%s%s",ans,ans1);
	while((ans == "si"))
	{
		printf("inserisci un intero:");
		scanf("%d", &a);
		printf("inserisci un reale:");
		scanf("%f", &b);
		risultato = sommac(a,x,b,&taglia);
		printf("la somma di %d e %f incrementata di %d è %s",a,b,c,taglia);
		printf("ed è pari a %f",risultato);
		printf("vuoi continuare? (si/no):");
		scanf("%s",ans);
	}
	printf("");
	printf("\n");
	printf("ciao");
}

int main()
{
	esempio();
}
void stampa(char *messaggio)
{
	int i;
	int a = i;
 
	
	for(int x = 4; x >= 1; x-- )
	{
		printf("");
		printf("\n");
	}
	printf("%s",messaggio);
	printf("\n");
}

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

