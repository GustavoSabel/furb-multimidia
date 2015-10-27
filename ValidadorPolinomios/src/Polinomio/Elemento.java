package Polinomio;

public class Elemento {
	/**
	 * 1 para positivo
	 * -1 para negativo
	 */
	private int sinal;
	private double numero;
	private char variavel;
	private Expressao expressao;
	
	public Elemento() {
		setSinal(1);
		setVariavel(' ');
	}
	
	public int getSinal() {
		return sinal;
	}

	public void setSinal(int sinal) {
		this.sinal = sinal;
	}

	public double getNumero() {
		return numero;
	}

	public void setNumero(double numero) {
		this.numero = numero;
	}

	public char getVariavel() {
		return variavel;
	}

	public void setVariavel(char variavel) {
		this.variavel = variavel;
	}

	public Expressao getExpressao() {
		return expressao;
	}

	public void setExpressao(Expressao expressao) {
		this.expressao = expressao;
	}
}
