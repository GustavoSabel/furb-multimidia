package Polinomio;

public class Expressao {
	private Termo termo;
	/**
	 * Pode ser NULL;
	 */
	private Expressao expressao;
	
	public Termo getTermo() {
		return termo;
	}
	
	public void setTermo(Termo termo) {
		this.termo = termo;
	}
	
	public Expressao getExpressao() {
		return expressao;
	}
	
	public void setExpressao(Expressao expressao) {
		this.expressao = expressao;
	}
}
