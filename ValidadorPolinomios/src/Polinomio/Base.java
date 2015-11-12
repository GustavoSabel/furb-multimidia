package Polinomio;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Base {
	public abstract Base getOrigem();

	public abstract void setOrigem(Base origem);

	public abstract double calcular(HashMap<Character, Integer> variaveis);

	public abstract String toString(boolean traduzido, HashMap<Character, Integer> variaveis);

	public abstract Base simplificar();

	public abstract Base ordenar();

	protected <T extends Base> Base ordernar (ArrayList<T> dados) {
		T baseAux = null;
		boolean teveTroca = false;
		int max = dados.size() - 1;
		for (int i = 0; i < dados.size(); i++) {
			teveTroca = false;
			for (int j = 0; j < max; j++) {
				if (dados.get(j).getPeso() > dados.get(j + 1).getPeso()) {
					baseAux = dados.remove(j);
					dados.add(dados.remove(j));
					dados.add(j + 1, baseAux);
					teveTroca = true;
				}
			}
			if (!teveTroca)
				break;
			max--;
		}

		return this;
	}

	public abstract int getPeso();

	@Override
	public String toString() {
		return toString(false, null);
	}
}
