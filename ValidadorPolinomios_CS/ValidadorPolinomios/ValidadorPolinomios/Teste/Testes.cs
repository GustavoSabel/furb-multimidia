using System;
using System.Collections.Generic;
using ValidadorPolinomios.Polinomio;

namespace Teste
{
	public class Testes
	{
        public static void TestarPrograma()
        {
            try
            {
                TesteValidar();
                TesteCalcular();
                TesteCalcularSemantico();
                TesteCalcularSemanticoSimplificado();
                TesteEquivalencia();

                Console.WriteLine("TESTE FINALIZADOS COM SUCESSO!!");
            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
                Console.Write(e.StackTrace);
            }

        }

        public static void TesteValidar()
		{
			try
			{
				if (!Polinomio.validar("x^2 + 2"))
				{
					throw new Exception("Teste 1 falhou");
				}

				if (!Polinomio.validar("x^2 + (2)"))
				{
					throw new Exception("Teste 2 falhou");
				}

				if (!Polinomio.validar("2x + 1"))
				{
					throw new Exception("Teste 3 falhou");
				}

				if (Polinomio.validar("3x^(3"))
				{
					throw new Exception("Teste 4 falhou");
				}

				if (!Polinomio.validar("10+2x^2+3x^10"))
				{
					throw new Exception("Teste 5 falhou");
				}

				if (!Polinomio.validar("2-2+10x+x150^2+x(42)x+xx^2x^3"))
				{
					throw new Exception("Teste 6 falhou");
				}

				if (!Polinomio.validar("x^2 + (-2)"))
				{
					throw new Exception("Teste 7 falhou");
				}

				if (!Polinomio.validar("x^+2 + (-2)"))
				{
					throw new Exception("Teste 8 falhou");
				}

				Console.WriteLine("Validação terminou com sucesso!");

			}
			catch (Exception ex)
			{
				throw new Exception("Teste de validação falhou. " + ex.Message);
			}
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: public static void TesteCalcular() throws Exception
		public static void TesteCalcular()
		{
			try
			{
				// 2x^2 - 2
				Polinomio pol = new Polinomio();
				Expressao expressao = new Expressao();

				pol.Expressao = expressao;
				Termo termo = new Termo();
				expressao.addTermo(termo);
				termo.addElementos(new Elemento(Sinal.Positivo, 2, 1));
				termo.addElementos(new Elemento(Sinal.Positivo, 'X', 2));

				termo = new Termo();
				expressao.addTermo(termo);
				termo.Sinal = Sinal.Negativo;
				termo.addElementos(new Elemento(Sinal.Positivo, 2, 1));

				double result = 0;

				var mapa = new Dictionary<char, int>();

				mapa['X'] = 1;
				result = pol.calcular(mapa);
				if (result != 0)
				{
					throw new Exception("Teste 1 falhou. Esperava '0', recebeu '" + result + "'");
				}

				mapa['X'] = -5;
				result = pol.calcular(mapa);
				if (result != 48)
				{
					throw new Exception("Teste 2 falhou. Esperava '48', recebeu '" + result + "'");
				}

				mapa['X'] = 100;
				result = pol.calcular(mapa);
				if (result != 19998)
				{
					throw new Exception("Teste 3 falhou. Esperava '19998', recebeu '" + result + "'");
				}

				mapa['X'] = 0;
				result = pol.calcular(mapa);
				if (result != -2)
				{
					throw new Exception("Teste 4 falhou. Esperava '-2', recebeu '" + result + "'");
				}

				Console.WriteLine("Calculo terminou com sucesso!");

			}
			catch (Exception ex)
			{
				throw new Exception("Teste de calculo falhou. " + ex.Message);
			}
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: public static void TesteCalcularSemantico() throws Exception
		public static void TesteCalcularSemantico()
		{
			try
			{
				var mapa = new Dictionary<char, int>();

				Polinomio pol = Polinomio.criarPolinomio("2x^2-2");

				double result = 0;

				mapa['X'] = 1;
				result = pol.calcular(mapa);
				if (result != 0)
				{
					throw new Exception("Teste 1 falhou. Esperava '0', recebeu '" + result + "'");
				}

				mapa['X'] = -5;
				result = pol.calcular(mapa);
				if (result != 48)
				{
					throw new Exception("Teste 2 falhou. Esperava '48', recebeu '" + result + "'");
				}

				mapa['X'] = 100;
				result = pol.calcular(mapa);
				if (result != 19998)
				{
					throw new Exception("Teste 3 falhou. Esperava '19998', recebeu '" + result + "'");
				}

				mapa['X'] = 0;
				result = pol.calcular(mapa);
				if (result != -2)
				{
					throw new Exception("Teste 4 falhou. Esperava '-2', recebeu '" + result + "'");
				}

				pol = Polinomio.criarPolinomio("2x^2-2(2*2^2x)");
				mapa['X'] = 0;
				double resultadoEsperado = 0;
				result = pol.calcular(mapa);
				if (result != resultadoEsperado)
				{
					throw new Exception("Teste 5 falhou. Esperava '" + resultadoEsperado + "', recebeu '" + result + "'");
				}

				pol = Polinomio.criarPolinomio("x^-2");
				mapa['X'] = 2;
				resultadoEsperado = 0.25;
				result = pol.calcular(mapa);
				if (result != resultadoEsperado)
				{
					throw new Exception("Teste 6 falhou. Esperava '" + resultadoEsperado + "', recebeu '" + result + "'");
				}

				pol = Polinomio.criarPolinomio("x^-(-2)");
				mapa['X'] = 2;
				resultadoEsperado = 4;
				result = pol.calcular(mapa);
				if (result != resultadoEsperado)
				{
					throw new Exception("Teste 7 falhou. Esperava '" + resultadoEsperado + "', recebeu '" + result + "'");
				}

				pol = Polinomio.criarPolinomio("x^-(-2)+10");
				mapa['X'] = 2;
				resultadoEsperado = 14;
				result = pol.calcular(mapa);
				if (result != resultadoEsperado)
				{
					throw new Exception("Teste 8 falhou. Esperava '" + resultadoEsperado + "', recebeu '" + result + "'");
				}

				pol = Polinomio.criarPolinomio("+10-10");
				resultadoEsperado = 0;
				result = pol.calcular(mapa);
				if (result != resultadoEsperado)
				{
					throw new Exception("Teste 9 falhou. Esperava '" + resultadoEsperado + "', recebeu '" + result + "'");
				}

				Console.WriteLine("Calculo utilizando a classe semantico terminou com sucesso!");

			}
			catch (Exception ex)
			{
				throw new Exception("Teste de calculo utilizando a classe semantico falhou. " + ex.Message);
			}
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: public static void TesteCalcularSemanticoSimplificado() throws Exception
		public static void TesteCalcularSemanticoSimplificado()
		{
			try
			{
				var mapa = new Dictionary<char, int>();

				Polinomio pol = Polinomio.criarPolinomio("2x^2-2");

				double result = 0;

				mapa['X'] = 1;
				result = pol.simplificar().calcular(mapa);
				if (result != 0)
				{
					throw new Exception("Teste 1 falhou. Esperava '0', recebeu '" + result + "'");
				}

				mapa['X'] = -5;
				result = pol.simplificar().calcular(mapa);
				if (result != 48)
				{
					throw new Exception("Teste 2 falhou. Esperava '48', recebeu '" + result + "'");
				}

				mapa['X'] = 100;
				result = pol.simplificar().calcular(mapa);
				if (result != 19998)
				{
					throw new Exception("Teste 3 falhou. Esperava '19998', recebeu '" + result + "'");
				}

				mapa['X'] = 0;
				result = pol.simplificar().calcular(mapa);
				if (result != -2)
				{
					throw new Exception("Teste 4 falhou. Esperava '-2', recebeu '" + result + "'");
				}

				pol = Polinomio.criarPolinomio("2x^2-2.(2*2^2x)");
				mapa['X'] = 0;
				double resultadoEsperado = 0;
				result = pol.simplificar().calcular(mapa);
				if (result != resultadoEsperado)
				{
					throw new Exception("Teste 5 falhou. Esperava '" + resultadoEsperado + "', recebeu '" + result + "'");
				}

				pol = Polinomio.criarPolinomio("x^-2");
				mapa['X'] = 2;
				resultadoEsperado = 0.25;
				result = pol.simplificar().calcular(mapa);
				if (result != resultadoEsperado)
				{
					throw new Exception("Teste 6 falhou. Esperava '" + resultadoEsperado + "', recebeu '" + result + "'");
				}

				pol = Polinomio.criarPolinomio("x^-(-2)");
				mapa['X'] = 2;
				resultadoEsperado = 4;
				result = pol.simplificar().calcular(mapa);
				if (result != resultadoEsperado)
				{
					throw new Exception("Teste 7 falhou. Esperava '" + resultadoEsperado + "', recebeu '" + result + "'");
				}

				pol = Polinomio.criarPolinomio("x^-(-2)+10");
				mapa['X'] = 2;
				resultadoEsperado = 14;
				result = pol.simplificar().calcular(mapa);
				if (result != resultadoEsperado)
				{
					throw new Exception("Teste 8 falhou. Esperava '" + resultadoEsperado + "', recebeu '" + result + "'");
				}

				pol = Polinomio.criarPolinomio("+10-10");
				resultadoEsperado = 0;
				result = pol.simplificar().calcular(mapa);
				if (result != resultadoEsperado)
				{
					throw new Exception("Teste 9 falhou. Esperava '" + resultadoEsperado + "', recebeu '" + result + "'");
				}

				Console.WriteLine("Calculo utilizando a classe semantico terminou com sucesso!");

			}
			catch (Exception ex)
			{
				throw new Exception("Teste de calculo utilizando a classe semantico falhou. " + ex.Message);
			}
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: public static void TesteEquivalencia() throws Exception
		public static void TesteEquivalencia()
		{
			try
			{
				Polinomio pol = Polinomio.criarPolinomio("(2+2x)");
				Polinomio pol2 = Polinomio.criarPolinomio("2+2x");
				if (!pol.EhEquivalente(pol2))
				{
					throw new Exception("Teste 0 falhou.");
				}

				pol = Polinomio.criarPolinomio("2(2+2x)");
				pol2 = Polinomio.criarPolinomio("4+4x");
				if (!pol.EhEquivalente(pol2))
				{
					throw new Exception("Teste 1 falhou.");
				}

				pol = Polinomio.criarPolinomio("2");
				pol2 = Polinomio.criarPolinomio("4");
				if (pol.EhEquivalente(pol2))
				{
					throw new Exception("Teste 2 falhou.");
				}

				pol = Polinomio.criarPolinomio("(2+2x)(2+2x)");
				pol2 = Polinomio.criarPolinomio("4+4x+4x+4x^2");
				if (!pol.EhEquivalente(pol2))
				{
					throw new Exception("Teste 3 falhou.");
				}

				pol = Polinomio.criarPolinomio("(2+2x)(2+2x)");
				pol2 = Polinomio.criarPolinomio("4+4x+4x+2x^2");
				if (pol.EhEquivalente(pol2))
				{
					throw new Exception("Teste 4 falhou.");
				}

				pol = Polinomio.criarPolinomio("3x-2x");
				pol2 = Polinomio.criarPolinomio("x");
				if (!pol.EhEquivalente(pol2))
				{
					throw new Exception("Teste 5 falhou.");
				}

				Console.WriteLine("Calculo utilizando a classe semantico terminou com sucesso!");

			}
			catch (Exception ex)
			{
				throw new Exception("Verificação de equivalencia falhou. " + ex.Message);
			}
		}
	}

}