package Polinomio;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import Analisador.LexicalError;
import Analisador.Lexico;
import Analisador.SemanticError;
import Analisador.Semantico;
import Analisador.Sintatico;
import Analisador.SyntaticError;

public class Polinomio implements Base {
	/**
	 * Variáveis que estão no polinômio utilizado para calcular o polinômio
	 * Antes de executar o calculo, deve-se atribuir um valor para cada vari�vel
	 * do dicion�rio
	 */
	public static HashMap<Character, Integer> Variaveis;
	private ArrayList<Expressao> expressoes;
	private Expressao ultimaExpressao;

	public Polinomio() {
		expressoes = new ArrayList<Expressao>();
		Polinomio.Variaveis = new HashMap<>();
	}

	public void addExpressao(Expressao expressao) {
		this.ultimaExpressao = expressao;
		expressao.setOrigem(this);
		this.expressoes.add(expressao);
	}

	public ArrayList<Expressao> getExpressoes() {
		return expressoes;
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
		System.out.print("Calcular: ");
		System.out.println(this.toString(true, variaveis));
		double result = 0;
		for (Expressao expressao : expressoes) {
			result += expressao.calcular(variaveis);
		}
		return result;
	}

	@Override
	public String toString(boolean traduzido, HashMap<Character, Integer> variaveis) {
		String result = "";
		for (Expressao expressao : expressoes) {
			result += expressao.toString(true, variaveis);
		}
		return result;
	}

	public static Polinomio criarPolinomio(String pol) {
		try {
			pol = pol.replaceAll("\\s+", "");

			StringReader read = new StringReader(pol);
			Lexico lex = new Lexico(read);
			Sintatico sintatico = new Sintatico();
			Semantico semantico = new Semantico();
			sintatico.parse(lex, semantico);

			return semantico.getPolinomio();

		} catch (LexicalError e) {
			e.printStackTrace();
		} catch (SyntaticError e) {
			e.printStackTrace();
		} catch (SemanticError e) {
			e.printStackTrace();
		}
		return null;
	}
}
