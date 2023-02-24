#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>








int menu();

float somma(float a, float b)
{
	return (a + b);
}

float differenza(float a,float b)
{
	return (a - b);
}

float prodotto(float a, float b)
{
	float prod = 0.0;
 
	int i = 1;
 
	while((i <= b))
	{
		prod = (prod + a);
		i = (i + 1);
	}
	return prod;
}

float divisione(float a, float b)
{
	return (a/b);
}

float potenza(float a, float b)
{
	return pow(a,b);
}

float succ_fibonacci(float i)
{
	if((i < 0))
	{
		return 0.0;
	}
	else 
	{
		if((i == 0))
		{
			return 0.0;
		}
		else 
		{
			if((i == 1))
			{
				return 1.0;
			}
		}
	}
	return (succ_fibonacci((i - 1)) + succ_fibonacci((i - 2)));
}

void esempio()
{
	int op, comando = (-1);
 
	float risultato, a, b;
 
	while((comando != 0))
	{
		printf("1) Visualizza Menù");
		printf("\n");
		printf("0) Termina");
		printf("\n");
		printf("Inserisci comando:");
		scanf("%d", &comando);
		if((comando == 1))
		{
			op = menu();
			if((op != 7))
			{
				printf("Inserisci il primo intero:");
				scanf("%f", &a);
				printf("Inserisci il secondo intero:");
				scanf("%f", &b);
			}
			else 
			{
				printf("Inserisci il primo intero:");
				scanf("%f", &a);
			}
			if((op == 2))
			{
				risultato = somma(a,b);
			}
			else 
			{
				if((op == 3))
				{
					risultato = differenza(a,b);
				}
				else 
				{
					if((op == 4))
					{
						risultato = prodotto(a,b);
					}
					else 
					{
						if((op == 5))
						{
							risultato = divisione(a,b);
						}
						else 
						{
							if((op == 6))
							{
								risultato = potenza(a,b);
							}
							else 
							{
								risultato = succ_fibonacci(a);
							}
						}
					}
				}
			}
			printf("Il risultato dell'operazione scelta è :%f",risultato);
			printf("\n");
		}
	}
	printf("Ciao");
	return ;
}

int main()
{
	esempio();
}
int menu()
{
	int op;
 
	printf("--------Menu--------");
	printf("\n");
	printf("2) Addizione");
	printf("\n");
	printf("3) Sottrazione");
	printf("\n");
	printf("4) Moltiplicazione");
	printf("\n");
	printf("5) Divisione");
	printf("\n");
	printf("6) Potenza");
	printf("\n");
	printf("7) Fibonacci");
	printf("\n");
	printf("Inserisci operazione:");
	scanf("%d", &op);
	while(((op < 2) || (op > 7)))
	{
		printf("Operazione non valida [2-7], inserisci operazione:");
		scanf("%d", &op);
	}
	return op;
}

