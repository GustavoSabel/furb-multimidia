package Teste;

import java.util.HashMap;

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
			TesteCalcularSemantico();
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

			if (!Validador.validar("x^2 + (-2)"))
				throw new Exception("Teste 7 falhou");

			if (!Validador.validar("x^+2 + (-2)"))
				throw new Exception("Teste 8 falhou");

			System.out.println("Validação terminou com sucesso!");

		} catch (Exception ex) {
			throw new Exception("Teste de validação falhou. " + ex.getMessage());
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

			HashMap<Character, Integer> mapa = new HashMap<>();

			mapa.put('X', 1);
			result = pol.calcular(mapa);
			if (result != 0)
				throw new Exception("Teste 1 falhou. Esperava '0', recebeu '" + result + "'");

			mapa.put('X', -5);
			result = pol.calcular(mapa);
			if (result != 48)
				throw new Exception("Teste 2 falhou. Esperava '48', recebeu '" + result + "'");

			mapa.put('X', 100);
			result = pol.calcular(mapa);
			if (result != 19998)
				throw new Exception("Teste 3 falhou. Esperava '19998', recebeu '" + result + "'");

			mapa.put('X', 0);
			result = pol.calcular(mapa);
			if (result != -2)
				throw new Exception("Teste 4 falhou. Esperava '-2', recebeu '" + result + "'");

			System.out.println("Calculo terminou com sucesso!");

		} catch (Exception ex) {
			throw new Exception("Teste de calculo falhou. " + ex.getMessage());
		}
	}

	public static void TesteCalcularSemantico() throws Exception {
		try {
			HashMap<Character, Integer> mapa = new HashMap<>();

			Polinomio pol = Polinomio.criarPolinomio("2x^2-2");

			double result = 0;

			mapa.put('X', 1);
			result = pol.calcular(mapa);
			if (result != 0)
				throw new Exception("Teste 1 falhou. Esperava '0', recebeu '" + result + "'");

			mapa.put('X', -5);
			result = pol.calcular(mapa);
			if (result != 48)
				throw new Exception("Teste 2 falhou. Esperava '48', recebeu '" + result + "'");

			mapa.put('X', 100);
			result = pol.calcular(mapa);
			if (result != 19998)
				throw new Exception("Teste 3 falhou. Esperava '19998', recebeu '" + result + "'");

			mapa.put('X', 0);
			result = pol.calcular(mapa);
			if (result != -2)
				throw new Exception("Teste 4 falhou. Esperava '-2', recebeu '" + result + "'");

			pol = Polinomio.criarPolinomio("2x^2-2(2*2^2x)");
			mapa.put('X', 0);
			int resultadoEsperado = 0;
			result = pol.calcular(mapa);
			if (result != resultadoEsperado)
				throw new Exception("Teste 5 falhou. Esperava '" + resultadoEsperado + "', recebeu '" + result + "'");

			System.out.println("Calculo utilizando a classe semantico terminou com sucesso!");

		} catch (Exception ex) {
			throw new Exception("Teste de calculo utilizando a classe semantico falhou. " + ex.getMessage());
		}
	}
}
