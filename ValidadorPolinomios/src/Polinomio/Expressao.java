package Polinomio;

import java.util.ArrayList;
import java.util.HashMap;

public class Expressao implements Base {
	private ArrayList<Termo> termos;
	private Termo ultimoTermo;
	private Base origem;

	public Expressao() {
		termos = new ArrayList<>();
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

	public double calcular(HashMap<Character, Integer> variaveis) {
		double result = 0;
		for (Termo termo : termos) {
			result += termo.calcular(variaveis);
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
		for (int i = 0; i < termos.size(); i++) {
			if(i > 0)
				result += "+";
			result += termos.get(i).toString(traduzido, variaveis);
		}
		return result;
	}

	public Expressao simplificar() {
		for (int i = 0; i < termos.size(); i++) {
			termos.get(i).simplificar();
		}
		return this;
	}

	public void substituir(Termo termoSubstituido, ArrayList<Termo> termosSubstituidores) {
		int indexTermoSubstituido = this.termos.indexOf(termoSubstituido);
		this.termos.remove(indexTermoSubstituido);

		for (Termo termoSubstituidor : termosSubstituidores) {
			Sinal novoSinal = Sinal.valueOf(termoSubstituidor.getSinal().getValue() * termoSubstituido.getSinal().getValue());
			termoSubstituidor.setSinal(novoSinal);
			termoSubstituidor.setOrigem(this);
			this.termos.add(indexTermoSubstituido++, termoSubstituidor);
		}
	}
}
