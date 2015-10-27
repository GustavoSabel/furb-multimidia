package Polinomio;

import java.util.Dictionary;
import java.util.Hashtable;

public class Polinomio {
	/**
	 * Vari�veis que est�o no polin�mio
	 * � utilizado para calcular o polin�mio
	 * Antes de executar o c�lculo, deve-se atribuir um 
	 * valor para cada vari�vel do dicion�rio
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
	 * Resolve o poln�mio com base nos valores da propriedade <b>variaveis</b>
	 */
	public void Calcular() {
		//TODO: Implementar
	}
}
