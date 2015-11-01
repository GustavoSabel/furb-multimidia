package Polinomio;

import java.util.ArrayList;
import java.util.HashMap;

public class Termo implements Base {
	private int sinal;
	private ArrayList<Elemento> elementos;
	private Elemento ultimoElemento;
	private Expressao origem;

	public Termo() {
		elementos = new ArrayList<>();
		this.setSinal(1);
	}

	public ArrayList<Elemento> getElementos() {
		return elementos;
	}

	public void addElementos(Elemento elemento) {
		ultimoElemento = elemento;
		elemento.setOrigem(this);
		this.elementos.add(elemento);
	}

	public Elemento getUltimoElemento() {
		return ultimoElemento;
	}

	public double calcular(HashMap<Character, Integer> variaveis) {
		double result = 0;
		boolean primeiro = true;
		for (Elemento elemento : elementos) {
			if (primeiro) {
				primeiro = false;
				result = elemento.calcular(variaveis);
			} else {
				result *= elemento.calcular(variaveis);
			}
		}
		result *= sinal;
		return result;
	}

	public Expressao getOrigem() {
		return origem;
	}

	@Override
	public void setOrigem(Base origem) {
		this.origem = (Expressao) origem;
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

	private String getSinalString() {
		if(sinal == 1)
			return "+";
		else
			return "-";
	}
	
	
	@Override
	public String toString(boolean traduzido, HashMap<Character, Integer> variaveis) {
		String result = "";
		boolean primeiro = true;
		for (Elemento elemento : elementos) {
			if (primeiro) {
				primeiro = false;
				result += elemento.toString(traduzido, variaveis);
			} else {
				result += "*" + elemento.toString(traduzido, variaveis);
			}
		}
		result = getSinalString() + result;
		return result;
	}
}
