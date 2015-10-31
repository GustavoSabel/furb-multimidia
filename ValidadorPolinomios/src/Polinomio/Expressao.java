package Polinomio;

import java.util.ArrayList;

public class Expressao {
	private int sinal;
	private ArrayList<Termo> termos;
	private Termo ultimoTermo;

	public Expressao() {
		termos = new ArrayList<>();
		this.setSinal(1);
	}

	public void addTermo(Termo termo) {
		ultimoTermo = termo;
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

	public double calcular() {
		double result = 0;
		for (Termo termo : termos) {
			result += termo.calcular() * this.getSinal();
		}
		return result;
	}
}
