package Analisador;

import java.util.Stack;

import Polinomio.Base;
import Polinomio.Elemento;
import Polinomio.Expressao;
import Polinomio.Polinomio;
import Polinomio.Termo;

public class Semantico implements Constants {

	Stack<Base> pilha;
	Polinomio polinomio;

	public Semantico() {
		pilha = new Stack<>();
		polinomio = new Polinomio();
		pilha.push(polinomio);
	}

	public Polinomio getPolinomio(){
		return polinomio;
	}

	public void executeAction(int action, Token token) throws SemanticError {
		//System.out.println("Ação #" + action + ", Token: " + token);
		try {
			switch (action) {
			case 1:
				acao01(token);
				break;
			case 2:
				acao02(token);
				break;
			case 3:
				acao03(token);
				break;
			case 4:
				acao04(token);
				break;
			/*
			 * case 5: acao05(token); break; case 6: acao06(token); break;
			 */
			/*
			 * case 7: acao07(token); break;
			 */
			case 8:
				acao08(token);
				break;
			case 20:
				acao20(token);
				break;
			case 21:
				acao21(token);
				break;
			case 30:
				acao30(token);
				break;
			case 31:
				acao31(token);
				break;
			/*
			 * case 32: acao32(token); break;
			 */
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * private void acao32(Token token) { ((Expressao)
	 * this.pilha.peek()).setSinal(-1); }
	 * 
	 * private void acao31(Token token) { ((Expressao)
	 * this.pilha.peek()).setSinal(1); }
	 */

	private void acao31(Token token) throws Exception {
		Base obj = this.pilha.pop();
		if (!(obj instanceof Expressao)) {
			throw new Exception("Erro. Esperando tipo '" + Expressao.class.getName() + "'. Encontrato o tipo '"
					+ obj.getClass().getName() + "'.");
		}
	}

	private void acao30(Token token) throws Exception {
		Expressao expressao = new Expressao();
		
		Object obj = this.pilha.peek();
		if(obj instanceof Polinomio){
			((Polinomio)obj).addExpressao(expressao);
		}
		else if(obj instanceof Elemento){
			((Elemento)obj).setExpressao(expressao);
		}
		else{
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("Erro. Esperando tipo '");
			strBuilder.append(Polinomio.class.getName());
			strBuilder.append("' ou do tipo '");
			strBuilder.append(Elemento.class.getName());
			strBuilder.append("'. Encontrato o tipo '");
			strBuilder.append(obj.getClass().getName());
			strBuilder.append("'.");
			throw new Exception(strBuilder.toString());
		}
		
		this.pilha.peek().setOrigem(expressao);
		this.pilha.push(expressao);
		expressao.setSinal(getSinal(token));
	}

	private void acao21(Token token) throws Exception {
		Base obj = this.pilha.pop();
		if (!(obj instanceof Termo)) {
			throw new Exception("Erro. Esperando tipo '" + Termo.class.getName() + "'. Encontrato o tipo '"
					+ obj.getClass().getName() + "'.");
		}
	}

	private void acao20(Token token) {
		Expressao expressao = (Expressao) this.pilha.peek();
		Termo termo = new Termo();
		expressao.addTermo(termo);
		this.pilha.push(termo);
	}

	private void acao08(Token token) throws Exception {
		Base obj = this.pilha.pop();
		if (!(obj instanceof Elemento)) {
			throw new Exception("Erro. Esperando tipo '" + Elemento.class.getName() + "'. Encontrato o tipo '"
					+ obj.getClass().getName() + "'.");
		}
	}

	/*
	 * private void acao07(Token token) { Elemento elemento = (Elemento)
	 * this.pilha.peek(); Elemento potencia = new Elemento();
	 * elemento.setPotencia(potencia); pilha.push(potencia);
	 * 
	 * }
	 */

	/*
	 * private void acao06(Token token) throws Exception { Object obj =
	 * this.pilha.pop(); if (!(this.pilha.pop() instanceof Expressao)) { throw
	 * new Exception("Erro. Esperando tipo '" + Expressao.class.getName() +
	 * "'. Encontrato o tipo '" + obj.getClass().getName() + "'."); } }
	 * 
	 * private void acao05(Token token) { Elemento elemento = (Elemento)
	 * this.pilha.peek(); Expressao expressao = new Expressao();
	 * elemento.setExpressao(expressao); this.pilha.push(expressao); }
	 */

	private void acao04(Token token) {
		Elemento elemento = (Elemento) this.pilha.peek();
		elemento.setVariavel(token.getLexeme().charAt(0));
	}

	private void acao03(Token token) {
		Elemento elemento = (Elemento) this.pilha.peek();
		elemento.setNumero(Double.parseDouble(token.getLexeme()));
	}

	private void acao02(Token token) throws SemanticError {
		Elemento elemento = (Elemento) this.pilha.peek();
		elemento.setSinal(getSinal(token));
	}

	void acao01(Token token) throws SemanticError {
		Base obj = this.pilha.peek();
		if (obj instanceof Elemento && token.getLexeme().equals("^")) {
			Elemento elemento = (Elemento) obj;
			Elemento potencia = new Elemento();
			elemento.setPotencia(potencia);
			pilha.push(potencia);
		} else {
			Termo termo = (Termo) obj;
			Elemento elemento = new Elemento();
			termo.addElementos(elemento);
			this.pilha.push(elemento);
		}
	}

	private int getSinal(Token token) {
		if(token != null && token.getLexeme().equals("-"))
			return -1;
		return 1;
	}

	// private void acao32(Token token) {
	// expressao.setSinal(-1);
	// }
	//
	// private void acao31(Token token) {
	// expressao.setSinal(1);
	// }
	//
	// private void acao30(Token token) {
	// if (expressao == null)
	// expressao = new Expressao();
	// }
	//
	// private void acao20(Token token) {
	// termo = new Termo();
	// expressao.addTermo(termo);
	// }
	//
	// private void acao08(Token token) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// private void acao07(Token token) {
	// Elemento potencia = new Elemento();
	// this.elemento.setPotencia(potencia);
	// this.elemento = potencia;
	// }
	//
	// private void acao06(Token token) {
	// this.expressao = (Expressao)this.elemento.getOrigem().getOrigem();
	// }
	//
	// private void acao05(Token token) {
	// this.expressao = new Expressao();
	// this.elemento.setExpressao(expressao);
	// }
	//
	// private void acao04(Token token) {
	// this.elemento.setVariavel(token.getLexeme().charAt(0));
	// }
	//
	// private void acao03(Token token) {
	// this.elemento.setNumero(Double.parseDouble(token.getLexeme()));
	// }
	//
	// private void acao02(Token token) throws SemanticError {
	// if(token.getLexeme() == "-")
	// elemento.setSinal(-1);
	// else
	// elemento.setSinal(1);
	// }
	//
	// void acao01(Token token) throws SemanticError {
	// elemento = new Elemento();
	// this.termo.addElementos(elemento);
	// }
}
