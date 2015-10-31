package Polinomio;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import Analisador.LexicalError;
import Analisador.Lexico;
import Analisador.SemanticError;
import Analisador.Semantico;
import Analisador.SemanticoDummy;
import Analisador.Sintatico;
import Analisador.SyntaticError;

public class Validador {

	public static final int[] PRIMOS = { 2, 3, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79,
			83, 89, 97, 101, 103, 107, 109, 113, 127, 131 };

	public static boolean validar(String polinomio) {
		try {

			polinomio = polinomio.replaceAll("\\s+", "");

			StringReader read = new StringReader(polinomio);
			Lexico lex = new Lexico(read);
			Sintatico sintatico = new Sintatico();
			sintatico.parse(lex, new SemanticoDummy());

			System.out.println(polinomio + " é válido!");

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

	public static double calcular(String polinomio, HashMap<Character, Integer> variaveis) {
		try {
			polinomio = polinomio.replaceAll("\\s+", "");

			StringReader read = new StringReader(polinomio);
			Lexico lex = new Lexico(read);
			Sintatico sintatico = new Sintatico();
			Semantico semantico = new Semantico();
			sintatico.parse(lex, semantico);

			System.out.println(polinomio + " é válido!");

			Polinomio.Variaveis = variaveis;

			Polinomio pol = semantico.getPolinomio();
			return pol.calcular();

		} catch (LexicalError e) {
			e.printStackTrace();
		} catch (SyntaticError e) {
			e.printStackTrace();
		} catch (SemanticError e) {
			e.printStackTrace();
		}
		return Integer.MIN_VALUE;
	}
	
	public static double calcular(String polinomio) {
		int contador = 0;
		for (Map.Entry<Character, Integer> entry : Polinomio.Variaveis.entrySet()) {
			entry.setValue(PRIMOS[contador++]);
		}
		
		return Validador.calcular(polinomio, Polinomio.Variaveis);
	}

}
