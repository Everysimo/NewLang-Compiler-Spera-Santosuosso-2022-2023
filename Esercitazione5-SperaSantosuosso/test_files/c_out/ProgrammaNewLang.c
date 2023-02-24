#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>














int sommaInt(int a, int b);

int fibonacci(int a);

int differenzaInt(int a, int b);

void divisioneInt(int a, int b,int *risultato);

void prodottoInt(int a, int b,int *risultato);

void prodottoSommaInt(int a, int b,int *risultato);

void potenzaInt(int a, int b,int *risultato);

float sommaFloat(float a, float b);

float differenzaFloat(float a, float b);

float divisioneFloat(float a, float b);

void prodottoFloat(float a, float b,float *risultato);

void prodottoSommaFloat(float a, float b,float *risultato);

void potenzaFloat(float a, float b,float *risultato);

void aritmeticOp()
{
	int continua = 1;
 
	int aInt = 0, bInt = 0, resultInt = 0, sceltaOp = 0, sceltaType = 0;
 
	float aFloat = 0.0, bFloat = 0.0, risultatoFloat = 0.0;
 
	while((continua == 1))
	{
		sceltaOp = 0;
		sceltaType = 0;
		aInt = 0;
		bInt = 0;
		resultInt = 0;
		aFloat = 0.0;
		bFloat = 0.0;
		risultatoFloat = 0.0;
		while((((((((sceltaOp != 1) && (sceltaOp != 2)) && (sceltaOp != 3)) && (sceltaOp != 4)) && (sceltaOp != 5)) && (sceltaOp != 6)) && (sceltaOp != 7)))
		{
			printf("Quale fra queste operazioni vuoi svolgere?");
			printf("\n");
			printf("1) Somma (a+b) ");
			printf("\n");
			printf("2) Sottrazione (a-b)");
			printf("\n");
			printf("3) Divisione (a/b)");
			printf("\n");
			printf("4) Prodotto (a*b)");
			printf("\n");
			printf("5) Moltiplicazione tramite Somma (a*b)");
			printf("\n");
			printf("6) Potenza tramite prodotto (a^b)");
			printf("\n");
			printf("7) Fibonacci");
			printf("\n");
			printf("");
			printf("\n");
			printf("Digita il numero corrispondente all operazione che vuoi eseguire: ");
			scanf("%d", &sceltaOp);
		}
		if((sceltaOp == 7))
		{
			sceltaType = 1;
		}
		while(((sceltaType != 1) && (sceltaType != 2)))
		{
			printf("Che tipo di numeri vuoi usare");
			printf("\n");
			printf("1) Interi ");
			printf("\n");
			printf("2) Reali ");
			printf("\n");
			printf("");
			printf("\n");
			printf("Digita il numero corrispondente al tipo di numeri che vuoi usare: ");
			scanf("%d", &sceltaType);
		}
		if((sceltaType == 1))
		{
			printf("inserisci valore primo operando a: ");
			scanf("%d", &aInt);
			if((sceltaOp != 7))
			{
				printf("inserisci valore secondo operando b: ");
				scanf("%d", &bInt);
			}
			if((sceltaOp == 1))
			{
				resultInt = sommaInt(aInt,bInt);
			}
			if((sceltaOp == 2))
			{
				resultInt = differenzaInt(aInt,bInt);
			}
			if((sceltaOp == 3))
			{
				divisioneInt(aInt,bInt,&resultInt);
			}
			if((sceltaOp == 4))
			{
				prodottoInt(aInt,bInt,&resultInt);
			}
			if((sceltaOp == 5))
			{
				prodottoSommaInt(aInt,bInt,&resultInt);
			}
			if((sceltaOp == 6))
			{
				potenzaInt(aInt,bInt,&resultInt);
			}
			if((sceltaOp == 7))
			{
				resultInt = fibonacci(aInt);
			}
			printf("RISULTATO : %d",resultInt);
			printf("\n");
		}
		if((sceltaType == 2))
		{
			printf("inserisci valore primo operando a: ");
			scanf("%f", &aFloat);
			printf("inserisci valore secondo operando b: ");
			scanf("%f", &bFloat);
			if((sceltaOp == 1))
			{
				risultatoFloat = sommaFloat(aFloat,bFloat);
			}
			if((sceltaOp == 2))
			{
				risultatoFloat = differenzaFloat(aFloat,bFloat);
			}
			if((sceltaOp == 3))
			{
				risultatoFloat = divisioneFloat(aFloat,bFloat);
			}
			if((sceltaOp == 4))
			{
				prodottoFloat(aFloat,bFloat,&risultatoFloat);
			}
			if((sceltaOp == 5))
			{
				prodottoSommaFloat(aFloat,bFloat,&risultatoFloat);
			}
			if((sceltaOp == 6))
			{
				potenzaFloat(aFloat,bFloat,&risultatoFloat);
			}
			printf("RISULTATO : %f",risultatoFloat);
			printf("\n");
		}
		continua = 2;
		printf("vuoi continuare? (1=si/0=no)");
		scanf("%d", &continua);
		while(((continua != 0) && (continua != 1)))
		{
			printf("Inserisci un valore valido");
			printf("\n");
			printf("vuoi continuare? (1=si/0=no)");
			scanf("%d", &continua);
		}
		if((continua == 0))
		{
			return ;
		}
	}
}

int main()
{
	aritmeticOp();
}
int sommaInt(int a, int b)
{
	return (a + b);
}

int fibonacci(int a)
{
	int rw;
 
	if((a == 0))
	{
		return 0;
	}
	if((a == 1))
	{
		return 1;
	}
	rw = (fibonacci((a - 1)) + fibonacci((a - 2)));
	return (fibonacci((a - 1)) + fibonacci((a - 2)));
}

int differenzaInt(int a, int b)
{
	return (a - b);
}

void divisioneInt(int a, int b,int *risultato)
{
	*risultato = (a/b);
	return ;
}

void prodottoInt(int a, int b,int *risultato)
{
	*risultato = (a * b);
	return ;
}

void prodottoSommaInt(int a, int b,int *risultato)
{
	int i = 0;
 
	while((i < b))
	{
		i = (i + 1);
		*risultato = (*risultato + a);
	}
	return ;
}

void potenzaInt(int a, int b,int *risultato)
{
	int i = 0;
 
	*risultato = 1;
	while((i < b))
	{
		i = (i + 1);
		*risultato = (*risultato * a);
	}
	return ;
}

float sommaFloat(float a, float b)
{
	return (a + b);
}

float differenzaFloat(float a, float b)
{
	return (a - b);
}

float divisioneFloat(float a, float b)
{
	return (a/b);
}

void prodottoFloat(float a, float b,float *risultato)
{
	*risultato = (a * b);
	return ;
}

void prodottoSommaFloat(float a, float b,float *risultato)
{
	int i = 0;
 
	while((i < b))
	{
		i = (i + 1);
		*risultato = (*risultato + a);
	}
	return ;
}

void potenzaFloat(float a, float b,float *risultato)
{
	*risultato = pow(a,b);
	return ;
}

