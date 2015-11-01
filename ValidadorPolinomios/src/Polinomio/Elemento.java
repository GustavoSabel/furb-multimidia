package Polinomio;

import java.util.HashMap;

public class Elemento implements Base {
	public static final char NULL_VARIAVEL = 0;
	/**
	 * 1 para positivo -1 para negativo
	 */
	private int sinal;
	private double numero;
	private char variavel;
	private Expressao expressao;
	private Elemento potencia;
	private Base origem;

	public Elemento() {
		setSinal(1);
		setNumero(Integer.MIN_VALUE);
		setVariavel(NULL_VARIAVEL);
	}

	public Elemento(int sinal, int numero, int potencia) {
		setSinal(sinal);
		this.potencia = new Elemento();
		this.potencia.setNumero(potencia);
		setNumero(numero);
		setVariavel(NULL_VARIAVEL);
	}

	public Elemento(int sinal, char variavel, int potencia) {
		setSinal(sinal);
		this.potencia = new Elemento();
		this.potencia.setNumero(potencia);
		setNumero(Integer.MIN_VALUE);
		setVariavel(variavel);
	}

	public Elemento getPotencia() {
		return potencia;
	}

	public double getPotenciaCalculada(HashMap<Character, Integer> variaveis) {
		if (potencia == null)
			return 1;
		return potencia.calcular(variaveis);
	}

	public void setPotencia(Elemento potencia) {
		potencia.setOrigem(this);
		this.potencia = potencia;
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
		this.variavel = Character.toUpperCase(variavel);
	}

	public Expressao getExpressao() {
		return expressao;
	}

	public void setExpressao(Expressao expressao) {
		expressao.setOrigem(this);
		this.expressao = expressao;
	}

	public double calcular(HashMap<Character, Integer> variaveis) {
		double result = 0;
		if (getExpressao() != null) {
			result = getExpressao().calcular(variaveis);
		} else if (getVariavel() != Elemento.NULL_VARIAVEL) {
			result = variaveis.get(getVariavel());
		} else {
			result = getNumero();
		}
		result = result * getSinal();
		result = Math.pow(result, getPotenciaCalculada(variaveis));
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
		String saida = "(" + getSinalString();
		if (getExpressao() != null) {
			saida += "(" + getExpressao().toString(traduzido, variaveis) + ")";
		} else if (getVariavel() != Elemento.NULL_VARIAVEL) {
			if (traduzido)
				saida += variaveis.get(getVariavel());
			else
				saida += getVariavel() + "";
		} else {
			saida += String.valueOf(getNumero());
		}
		if (this.potencia != null) {
			if (potencia.numero != 1 && potencia.potencia != null)
				saida += "^" + this.potencia.toString(true, variaveis);
		}
		saida += ")";
		return saida;
	}

	private String getSinalString() {
		if (sinal == 1)
			return "+";
		else
			return "-";
	}
}
