package Polinomio;

import java.util.ArrayList;
import java.util.HashMap;

public class Expressao implements Base {
	private int sinal;
	private ArrayList<Termo> termos;
	private Termo ultimoTermo;
	private Base origem;

	public Expressao() {
		termos = new ArrayList<>();
		this.setSinal(1);
	}

	public void addTermo(Termo termo) {
		ultimoTermo = termo;
		termo.setOrigem(this);
		termos.add(termo);
	}

	public ArrayList<Termo> getTermos() {
		return termos;
	}

	public Termo getUltimoTermo() {
		return ultimoTermo;
	}

	public int getSinal() {
		return sinal;
	}

	/**
	 * Define o sinal da expressão
	 * 
	 * @param sinal
	 *            -1=Negativo;1=Positivo
	 */
	public void setSinal(int sinal) {
		this.sinal = sinal;
	}

	public double calcular(HashMap<Character, Integer> variaveis) {
		double result = 0;
		for (Termo termo : termos) {
			result += termo.calcular(variaveis) * this.getSinal();
		}
		return result;
	}
	
	public Base getOrigem() {
		return origem;
	}

	public void setOrigem(Base origem) {
		this.origem = origem;
	}
	
	@Override
	public String toString(boolean traduzido, HashMap<Character, Integer> variaveis) {
		String result = "";
		for (Termo termo : termos) {
			result += this.getSinalString() + termo.toString(traduzido, variaveis);
		}
		return result;
	}

	private String getSinalString() {
		if(sinal == 1)
			return "+";
		else
			return "-";
	}
}
