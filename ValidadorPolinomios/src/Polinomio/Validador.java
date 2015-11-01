package Polinomio;

import java.io.StringReader;

import Analisador.LexicalError;
import Analisador.Lexico;
import Analisador.SemanticError;
import Analisador.Semantico;
import Analisador.SemanticoDummy;
import Analisador.Sintatico;
import Analisador.SyntaticError;

public class Validador {

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
}
