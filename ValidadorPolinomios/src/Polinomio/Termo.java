package Polinomio;

import java.util.ArrayList;
import java.util.Dictionary;

public class Termo {
	private ArrayList<Elemento> elementos;
	private Elemento ultimoElemento;

	public Termo() {
		elementos = new ArrayList<>();
	}

	public ArrayList<Elemento> getElementos() {
		return elementos;
	}

	public void addElementos(Elemento elemento) {
		ultimoElemento = elemento;
		this.elementos.add(elemento);
	}

	public Elemento getUltimoElemento() {
		return ultimoElemento;
	}

	public double calcular() {
		double result = 0;
		boolean primeiro = true;
		for (Elemento elemento : elementos) {
			if (primeiro) {
				primeiro = false;
				result = elemento.calcular();
			} else {
				result *= elemento.calcular();
			}
		}
		return result;
	}	
}
