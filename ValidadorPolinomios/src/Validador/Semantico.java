package Validador;

public class Semantico implements Constants {
	
	public void executeAction(int action, Token token) throws SemanticError {
		System.out.println("Ação #" + action + ", Token: " + token);

		switch (action) {
		case 1:
			acao01(token);
			break;
		}
	}

	void acao01(Token token) throws SemanticError {
		System.out.println("Sinal");
	}
}
