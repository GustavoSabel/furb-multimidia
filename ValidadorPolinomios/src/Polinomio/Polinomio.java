package Polinomio;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Polinomio {
	/**
	 * Variáveis que estão no polinômio
	 * utilizado para calcular o polinômio
	 * Antes de executar o calculo, deve-se atribuir um 
	 * valor para cada vari�vel do dicion�rio
	 */
	public static Dictionary<Character, Integer> Variaveis;
	private ArrayList<Expressao> expressoes;
	private Expressao ultimaExpressao;
	
	public Polinomio() {
		expressoes = new ArrayList<Expressao>();
		Polinomio.Variaveis = new Hashtable<>();
	}

	public void addExpressao(Expressao expressao) {
		this.ultimaExpressao = expressao;
		this.expressoes.add(expressao);
	}
	
	public ArrayList<Expressao> getExpressoes() {
		return expressoes;
	}

	public Expressao getUltimaExpressao() {
		return ultimaExpressao;
	}

	/**
	 * Resolve o poln�mio com base nos valores da propriedade <b>variaveis</b>
	 */
	public double Calcular() {
		double result = 0;
		for (Expressao expressao : expressoes) {			
			result += expressao.calcular();
		}
		return result;
	}
}
