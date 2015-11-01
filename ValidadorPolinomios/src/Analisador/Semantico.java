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
		Expressao expressao = new Expressao();
		polinomio.setExpressao(expressao);
		pilha.push(expressao);
	}

	public Polinomio getPolinomio() {
		return polinomio;
	}

	private int getSinal(Token token) {
		if (token != null && token.getLexeme().equals("-"))
			return -1;
		return 1;
	}

	public void executeAction(int action, Token token) throws SemanticError {
		//System.out.println("Ação #" + action + ", Token: " + token);
		try {
			switch (action) {
			case 2:
				acao02(token);
				break;
			case 3:
				acao03(token);
				break;
			case 4:
				acao04(token);
				break;
			case 5:
				acao05(token);
				break;
			case 6:
				acao06(token);
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
			case 40:
				acao40(token);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void acao40(Token token) throws Exception {
		Elemento elemento = (Elemento) pilha.peek();
		Elemento elementoPotencia = new Elemento();
		elemento.setPotencia(elementoPotencia);
		pilha.push(elementoPotencia);
	}

	private void acao31(Token token) throws Exception {
		Base obj = this.pilha.pop();
		if (!(obj instanceof Termo)) {
			throw new Exception("Erro. Esperando tipo '" + Termo.class.getName() + "'. Encontrato o tipo '"
					+ obj.getClass().getName() + "'.");
		}
	}

	private void acao30(Token token) throws Exception {
		Expressao expressao = (Expressao) pilha.peek();
		Termo termo = new Termo();
		termo.setSinal(getSinal(token));
		expressao.addTermo(termo);
		pilha.push(termo);
	}

	private void acao21(Token token) throws Exception {
		Base obj = this.pilha.pop();
		if (!(obj instanceof Elemento)) {
			throw new Exception("Erro. Esperando tipo '" + Elemento.class.getName() + "'. Encontrato o tipo '"
					+ obj.getClass().getName() + "'.");
		}
	}

	private void acao20(Token token) {
		Termo termo = (Termo) this.pilha.peek();
		Elemento elemento = new Elemento();
		termo.addElementos(elemento);
		this.pilha.push(elemento);
	}

	private void acao06(Token token) throws Exception {
		Base obj = this.pilha.pop();
		if (!(obj instanceof Expressao)) {
			throw new Exception("Erro. Esperando tipo '" + Expressao.class.getName() + "'. Encontrato o tipo '"
					+ obj.getClass().getName() + "'.");
		}
	}

	private void acao05(Token token) {
		Elemento elemento = (Elemento) this.pilha.peek();
		Expressao expressao = new Expressao();
		expressao.setOrigem(elemento);
		elemento.setExpressao(expressao);
		this.pilha.push(expressao);
	}

	private void acao04(Token token) {
		Elemento elemento = (Elemento) this.pilha.peek();
		elemento.setVariavel(token.getLexeme().charAt(0));
		polinomio.addVariavel(token.getLexeme().charAt(0));
	}

	private void acao03(Token token) {
		Elemento elemento = (Elemento) this.pilha.peek();
		elemento.setNumero(Double.parseDouble(token.getLexeme()));
	}

	private void acao02(Token token) throws SemanticError {
		Elemento elemento = (Elemento) this.pilha.peek();
		elemento.setSinal(getSinal(token));
	}
}
