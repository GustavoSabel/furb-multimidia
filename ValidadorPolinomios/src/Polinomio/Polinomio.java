package Polinomio;

import java.util.Dictionary;
import java.util.Hashtable;

public class Polinomio {
	/**
	 * Variáveis que estão no polinômio
	 * É utilizado para calcular o polinômio
	 * Antes de executar o cálculo, deve-se atribuir um 
	 * valor para cada variável do dicionário
	 */
	private Dictionary<Character, Integer> variaveis;
	private Expressao expressao;
	
	public Polinomio() {
		expressao = new Expressao();
		variaveis = new Hashtable<>();
	}
	
	public Dictionary<Character, Integer> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(Dictionary<Character, Integer> variaveis) {
		this.variaveis = variaveis;
	}

	public Expressao getExpressao() {
		return expressao;
	}

	public void setExpressao(Expressao expressao) {
		this.expressao = expressao;
	}
	
	/**
	 * Resolve o polnômio com base nos valores da propriedade <b>variaveis</b>
	 */
	public void Calcular() {
		//TODO: Implementar
	}
}
