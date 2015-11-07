package Polinomio;

import java.util.HashMap;

public interface Base {
	public Base getOrigem();

	public void setOrigem(Base origem);

	public double calcular(HashMap<Character, Integer> variaveis);

	public String toString(boolean traduzido, HashMap<Character, Integer> variaveis);
	
	public Base simplificar();
}
