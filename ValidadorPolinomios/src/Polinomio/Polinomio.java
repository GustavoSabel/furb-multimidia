package Polinomio;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import Analisador.AnalysisError;
import Analisador.LexicalError;
import Analisador.Lexico;
import Analisador.SemanticError;
import Analisador.Semantico;
import Analisador.SemanticoDummy;
import Analisador.Sintatico;
import Analisador.SyntaticError;

public class Polinomio implements Base {

	private HashMap<Character, Integer> variaveis;
	private Expressao expressao;
	private Expressao ultimaExpressao;

	public static final int[] PRIMOS = { 2, 3, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
			79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131 };

	public Polinomio() {
		variaveis = new HashMap<>();
	}

	public void addVariavel(Character variavel) {
		variaveis.put(Character.toUpperCase(variavel), Integer.MIN_VALUE);
	}

	public HashMap<Character, Integer> getVariaveis() {
		return variaveis;
	}

	public void setExpressao(Expressao expressao) {
		this.ultimaExpressao = expressao;
		expressao.setOrigem(this);
		this.expressao = expressao;
	}

	public Expressao getExpressao() {
		return expressao;
	}

	public Expressao getUltimaExpressao() {
		return ultimaExpressao;
	}

	@Override
	public void setOrigem(Base origem) {
		// Não deve ter origem
	}

	@Override
	public Base getOrigem() {
		return null;
	}

	/**
	 * Resolve o poln�mio com base nos valores da propriedade <b>variaveis</b>
	 */
	@Override
	public double calcular(HashMap<Character, Integer> variaveis) {
		double result = expressao.calcular(variaveis);
		System.out.println("Resultado: " + this.toString(true, variaveis) + " = " + result);
		return result;
	}

	@Override
	public String toString(boolean traduzido, HashMap<Character, Integer> variaveis) {
		return expressao.toString(traduzido, variaveis);
	}

	public static Polinomio criarPolinomio(String pol) throws AnalysisError {
		pol = pol.replaceAll("\\s+", "");

		StringReader read = new StringReader(pol);
		Lexico lex = new Lexico(read);
		Sintatico sintatico = new Sintatico();
		Semantico semantico = new Semantico();
		sintatico.parse(lex, semantico);

		return semantico.getPolinomio();
	}

	public boolean EhEquivalente(Polinomio pol) {
		// Verifica se possui a mesma quantidade de variáveis
		if (this.getVariaveis().size() != pol.getVariaveis().size())
			return false;

		// Verifica se as variáveis são as mesmas
		for (Map.Entry<Character, Integer> entry : this.getVariaveis().entrySet()) {
			if (!pol.getVariaveis().containsKey(entry.getKey()))
				return false;
		}

		int contador = 0;
		// Faz um teste substituindo as variáveis por números primos
		for (Map.Entry<Character, Integer> entry : this.getVariaveis().entrySet()) {
			entry.setValue(PRIMOS[contador++]);
		}
		if (this.calcular(this.getVariaveis()) != pol.calcular(this.getVariaveis()))
			return false;

		// Faz o mesmo teste com outros números primos
		for (Map.Entry<Character, Integer> entry : this.getVariaveis().entrySet()) {
			entry.setValue(PRIMOS[contador++]);
		}
		if (this.calcular(this.getVariaveis()) != pol.calcular(this.getVariaveis()))
			return false;
		// Faz o teste com tudo zero
		for (Map.Entry<Character, Integer> entry : this.getVariaveis().entrySet()) {
			entry.setValue(0);
		}
		if (this.calcular(this.getVariaveis()) != pol.calcular(this.getVariaveis()))
			return false;

		// Faz o teste com primos negativos
		for (Map.Entry<Character, Integer> entry : this.getVariaveis().entrySet()) {
			entry.setValue(-PRIMOS[contador++]);
		}
		if (this.calcular(this.getVariaveis()) != pol.calcular(this.getVariaveis()))
			return false;

		return true;
	}

	public static boolean validar(String polinomio) {
		try {
			polinomio = polinomio.replaceAll("\\s+", "");

			StringReader read = new StringReader(polinomio);
			Lexico lex = new Lexico(read);
			Sintatico sintatico = new Sintatico();
			sintatico.parse(lex, new SemanticoDummy());
			
			return true;
		} catch (LexicalError e) {
			e.printStackTrace();
		} catch (SyntaticError e) {
			e.printStackTrace();
		} catch (SemanticError e) {
			e.printStackTrace();
		}
		return false;
	}
}
