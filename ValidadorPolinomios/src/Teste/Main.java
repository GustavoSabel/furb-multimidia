package Teste;

import java.io.StringReader;

import Validador.LexicalError;
import Validador.Lexico;
import Validador.SemanticError;
import Validador.Semantico;
import Validador.Sintatico;
import Validador.SyntaticError;

public class Main {
	public static void main(String[] args) {
		try {
			//String pol1 = "10+2x^2+3x^10";
			String pol1 = "2-2+10x+x150^2+x(42)x+xx^2x^3";
			StringReader read = new StringReader(pol1);
			Lexico lex = new Lexico(read);
	
			Sintatico sintatico = new Sintatico();
			sintatico.parse(lex, new Semantico());
			
			System.out.println("É válido!");
			
		} catch (LexicalError e) {
			e.printStackTrace();
		} catch (SyntaticError e) {
			e.printStackTrace();
		} catch (SemanticError e) {
			e.printStackTrace();
		}
	}
}
