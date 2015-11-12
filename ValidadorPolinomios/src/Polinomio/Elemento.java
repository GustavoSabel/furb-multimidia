package Polinomio;

import java.util.HashMap;

public class Elemento extends Base {
	public static final char NULL_VARIAVEL = 0;
	public static final double NULL_NUMERO = Double.MIN_VALUE;
	private Sinal sinal;
	private double numero;
	private char variavel;
	private Expressao expressao;
	private Elemento potencia;
	private Base origem;

	public Elemento() {
		setSinal(Sinal.Positivo);
		setNumero(NULL_NUMERO);
		setVariavel(NULL_VARIAVEL);
	}

	public Elemento(Sinal sinal, int numero, int potencia) {
		setSinal(sinal);
		this.potencia = new Elemento();
		this.potencia.setNumero(potencia);
		setNumero(numero);
		setVariavel(NULL_VARIAVEL);
	}

	public Elemento(Sinal sinal, char variavel, int potencia) {
		setSinal(sinal);
		this.potencia = new Elemento();
		this.potencia.setNumero(potencia);
		setNumero(NULL_NUMERO);
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

	public Sinal getSinal() {
		return sinal;
	}

	public void setSinal(Sinal sinal) {
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
		result = result * getSinal().getValue();
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
		String saida = "";
		if (getExpressao() != null) {
			saida += "[" + getExpressao().toString(traduzido, variaveis) + "]";
		} else if (getVariavel() != Elemento.NULL_VARIAVEL) {
			if (traduzido)
				saida += variaveis.get(getVariavel());
			else
				saida += String.valueOf(getVariavel());
		} else {
			saida += String.valueOf(getNumeroString());
		}
		if (this.potencia != null) {
			if (potencia.numero != 1 || potencia.potencia != null)
				saida += "^" + this.potencia.toString(traduzido, variaveis);
		}
		if (sinal == Sinal.Negativo)
			saida = "(" + sinal.toString() + saida + ")";
		return saida;
	}

	private String getNumeroString() {
		if ((this.numero % 1) == 0) {
		    return String.valueOf((int)Math.floor(this.numero));
		}
		else {
			return String.valueOf(this.numero);
		}
	}

	public Elemento simplificar() {
		if (this.expressao == null)
			return this;

		this.expressao.simplificar();

		if (this.sinal == Sinal.Negativo) {
			for (Termo termo : this.expressao.getTermos()) {
				termo.setSinal(termo.getSinal().multiplicar(this.sinal));
			}

			this.sinal = this.sinal.inverter();
		}

		return this;
	}

	public boolean temPotencia() {
		return this.potencia != null;
	}

	public boolean temNumero() {
		return this.getNumero() != Elemento.NULL_NUMERO;
	}
	
	private boolean temVariavel() {
		return this.variavel != NULL_VARIAVEL;
	}
	
	@Override
	public Base ordenar() {
		if(temPotencia())
			this.potencia.ordenar();
		if(expressao != null)
			this.expressao.ordenar();
		return null;
	}

	@Override
	public int getPeso() {
		int total = 0;
		if(this.expressao != null)
			total += 0;
		if(this.temVariavel())
			total += 100;
		if(this.temNumero())
			total += (int)this.numero; 
		if(this.temPotencia())
			total += 1;
		return total;
	}


}
