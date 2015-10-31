package Teste;

import Polinomio.Elemento;
import Polinomio.Expressao;
import Polinomio.Polinomio;
import Polinomio.Termo;
import Polinomio.Validador;

public class Testes {
	public static void main(String[] args) {
		try {
			TesteValidar();
			TesteCalcular();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void TesteValidar() throws Exception {
		try {
			
			if (!Validador.validar("x^2 + 2"))
				throw new Exception("Teste 1 falhou");
			
			if (!Validador.validar("x^2 + (2)"))
				throw new Exception("Teste 2 falhou");
			
			if (!Validador.validar("2x + 1"))
				throw new Exception("Teste 3 falhou");
			
			if (Validador.validar("3x^(3"))
				throw new Exception("Teste 4 falhou");
			
			if (!Validador.validar("10+2x^2+3x^10"))
				throw new Exception("Teste 5 falhou");
			
			if (!Validador.validar("2-2+10x+x150^2+x(42)x+xx^2x^3"))
				throw new Exception("Teste 6 falhou");
			
		} catch (Exception ex) {
			throw new Exception("Teste de validação falou. " + ex.getMessage());
		}
	}

	public static void TesteCalcular() throws Exception {
		try {
			// 2x^2 - 2
			Polinomio pol = new Polinomio();
			pol.addExpressao(new Expressao());
			Termo termo = new Termo();
			pol.getUltimaExpressao().addTermo(termo);
			termo.addElementos(new Elemento(1, 2, 1));
			termo.addElementos(new Elemento(1, 'X', 2));
			
			pol.addExpressao(new Expressao());
			pol.getUltimaExpressao().setSinal(-1);
			termo = new Termo();
			pol.getUltimaExpressao().addTermo(termo);
			termo.addElementos(new Elemento(1, 2, 1));
			
			double result = 0;
			
			Polinomio.Variaveis.put('X', 1);
			result = pol.Calcular();
			if(result != 0)
				throw new Exception("Teste 1 falhou. Esperava '0', recebeu '"+ result +"'");
			
			Polinomio.Variaveis.put('X', -5);
			result = pol.Calcular();
			if(result != 48)
				throw new Exception("Teste 2 falhou. Esperava '48', recebeu '"+ result +"'");
			
			Polinomio.Variaveis.put('X', 100);
			result = pol.Calcular();
			if(result != 19998)
				throw new Exception("Teste 3 falhou. Esperava '19998', recebeu '"+ result +"'");
			
			Polinomio.Variaveis.put('X', 0);
			result = pol.Calcular();
			if(result != -2)
				throw new Exception("Teste 4 falhou. Esperava '-2', recebeu '"+ result +"'");
			
		} catch (Exception ex) {
			throw new Exception("Teste de calculo falou. " + ex.getMessage());
		}
	}
}
