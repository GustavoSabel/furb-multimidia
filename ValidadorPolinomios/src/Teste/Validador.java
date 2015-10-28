package Teste;

import java.io.StringReader;
import Validador.LexicalError;
import Validador.Lexico;
import Validador.SemanticError;
import Validador.Semantico;
import Validador.Sintatico;
import Validador.SyntaticError;

public class Validador {

    public static boolean validar(String polinomio) {
	try {
	    
	    polinomio = polinomio.replaceAll("\\s+", "");
	    
	    StringReader read = new StringReader(polinomio);
	    Lexico lex = new Lexico(read);
	    Sintatico sintatico = new Sintatico();
	    sintatico.parse(lex, new Semantico());
	    
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
