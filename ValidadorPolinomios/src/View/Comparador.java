package View;

import Polinomio.Polinomio;

public class Comparador {

	public static void main(String[] args) {
//		Espera 2 polinômios como argumentos
//		Retorna 3 se o número de argumentos estiver errado
//		Retorna 2 se os polinômios não forem equivalentes
//		Retorna 1 se os polinômios forem equivalentes
		
		if (args.length != 2){
			System.exit(3);
		}

		Polinomio pol1 = Polinomio.criarPolinomio(args[0]);
		Polinomio pol2 = Polinomio.criarPolinomio(args[1]);

		if (!pol1.EhEquivalente(pol2))
			System.exit(2);
		
		System.exit(1);
	}
}
