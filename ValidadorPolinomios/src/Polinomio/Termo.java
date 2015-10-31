package Polinomio;

import java.util.ArrayList;
import java.util.HashMap;

public class Termo implements Base {
	private ArrayList<Elemento> elementos;
	private Elemento ultimoElemento;
	private Expressao origem;

	public Termo() {
		elementos = new ArrayList<>();
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
		return result;
	}

	public Expressao getOrigem() {
		return origem;
	}

	@Override
	public void setOrigem(Base origem) {
		this.origem = (Expressao) origem;
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
		return result;
	}
}
