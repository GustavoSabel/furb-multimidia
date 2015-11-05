package Teste;

import java.util.HashMap;

import Polinomio.Elemento;
import Polinomio.Expressao;
import Polinomio.Polinomio;
import Polinomio.Termo;

public class Testes {
	public static void main(String[] args) {
		try {
			
			Polinomio pol = Polinomio.criarPolinomio("2x^2/");
			
			TesteValidar();
			TesteCalcular();
			TesteCalcularSemantico();
			TesteEquivalencia();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void TesteValidar() throws Exception {
		try {

			if (!Polinomio.validar("x^2 + 2"))
				throw new Exception("Teste 1 falhou");

			if (!Polinomio.validar("x^2 + (2)"))
				throw new Exception("Teste 2 falhou");

			if (!Polinomio.validar("2x + 1"))
				throw new Exception("Teste 3 falhou");

			if (Polinomio.validar("3x^(3"))
				throw new Exception("Teste 4 falhou");

			if (!Polinomio.validar("10+2x^2+3x^10"))
				throw new Exception("Teste 5 falhou");

			if (!Polinomio.validar("2-2+10x+x150^2+x(42)x+xx^2x^3"))
				throw new Exception("Teste 6 falhou");

			if (!Polinomio.validar("x^2 + (-2)"))
				throw new Exception("Teste 7 falhou");

			if (!Polinomio.validar("x^+2 + (-2)"))
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
			Expressao expressao = new Expressao();
			
			pol.setExpressao(expressao);
			Termo termo = new Termo();
			expressao.addTermo(termo);
			termo.addElementos(new Elemento(1, 2, 1));
			termo.addElementos(new Elemento(1, 'X', 2));

			termo = new Termo();
			expressao.addTermo(termo);
			termo.setSinal(-1);
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
			double resultadoEsperado = 0;
			result = pol.calcular(mapa);
			if (result != resultadoEsperado)
				throw new Exception("Teste 5 falhou. Esperava '" + resultadoEsperado + "', recebeu '" + result + "'");
			
			pol = Polinomio.criarPolinomio("x^-2");
			mapa.put('X', 2);
			resultadoEsperado = 0.25;
			result = pol.calcular(mapa);
			if (result != resultadoEsperado)
				throw new Exception("Teste 6 falhou. Esperava '" + resultadoEsperado + "', recebeu '" + result + "'");

			pol = Polinomio.criarPolinomio("x^-(-2)");
			mapa.put('X', 2);
			resultadoEsperado = 4;
			result = pol.calcular(mapa);
			if (result != resultadoEsperado)
				throw new Exception("Teste 7 falhou. Esperava '" + resultadoEsperado + "', recebeu '" + result + "'");

			pol = Polinomio.criarPolinomio("x^-(-2)+10");
			mapa.put('X', 2);
			resultadoEsperado = 14;
			result = pol.calcular(mapa);
			if (result != resultadoEsperado)
				throw new Exception("Teste 8 falhou. Esperava '" + resultadoEsperado + "', recebeu '" + result + "'");

			System.out.println("Calculo utilizando a classe semantico terminou com sucesso!");

		} catch (Exception ex) {
			throw new Exception("Teste de calculo utilizando a classe semantico falhou. " + ex.getMessage());
		}
	}
	
	
	public static void TesteEquivalencia() throws Exception {
		try {
			Polinomio pol = Polinomio.criarPolinomio("(2+2x)");
			Polinomio pol2 = Polinomio.criarPolinomio("2+2x");
			if (!pol.EhEquivalente(pol2))
				throw new Exception("Teste 0 falhou.");
			
			pol = Polinomio.criarPolinomio("2(2+2x)");
			pol2 = Polinomio.criarPolinomio("4+4x");
			if (!pol.EhEquivalente(pol2))
				throw new Exception("Teste 1 falhou.");
			
			pol = Polinomio.criarPolinomio("2");
			pol2 = Polinomio.criarPolinomio("4");
			if (pol.EhEquivalente(pol2))
				throw new Exception("Teste 2 falhou.");

			pol = Polinomio.criarPolinomio("(2+2x)(2+2x)");
			pol2 = Polinomio.criarPolinomio("4+4x+4x+4x^2");
			if (!pol.EhEquivalente(pol2))
				throw new Exception("Teste 3 falhou.");
			
			pol = Polinomio.criarPolinomio("(2+2x)(2+2x)");
			pol2 = Polinomio.criarPolinomio("4+4x+4x+2x^2");
			if (pol.EhEquivalente(pol2))
				throw new Exception("Teste 4 falhou.");
			
			System.out.println("Calculo utilizando a classe semantico terminou com sucesso!");

		} catch (Exception ex) {
			throw new Exception("Verificação de equivalencia falhou. " + ex.getMessage());
		}
	}
}
