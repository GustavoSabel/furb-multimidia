using System;
using System.Collections.Generic;

namespace Polinomio
{


	using AnalysisError = Analisador.AnalysisError;
	using LexicalError = Analisador.LexicalError;
	using Lexico = Analisador.Lexico;
	using SemanticError = Analisador.SemanticError;
	using Semantico = Analisador.Semantico;
	using SemanticoDummy = Analisador.SemanticoDummy;
	using Sintatico = Analisador.Sintatico;
	using SyntaticError = Analisador.SyntaticError;

	public class Polinomio : Base
	{

		private Dictionary<char?, int?> variaveis;
		private Expressao expressao;

		public static readonly int[] PRIMOS = new int[] {2, 3, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131};

		public Polinomio()
		{
			variaveis = new Dictionary<>();
		}

		public virtual void addVariavel(char? variavel)
		{
			variaveis[char.ToUpper(variavel)] = int.MinValue;
		}

		public virtual Dictionary<char?, int?> Variaveis
		{
			get
			{
				return variaveis;
			}
		}

		public virtual Expressao Expressao
		{
			set
			{
				value.Origem = this;
				this.expressao = value;
			}
			get
			{
				return expressao;
			}
		}


		public override Base Origem
		{
			set
			{
				// Não deve ter value
			}
			get
			{
				return null;
			}
		}


		/// <summary>
		/// Resolve o poln�mio com base nos valores da propriedade <b>variaveis</b>
		/// </summary>
		public override double calcular(Dictionary<char?, int?> variaveis)
		{
			double result = expressao.calcular(variaveis);
			Console.WriteLine("Resultado: " + this.ToString(true, variaveis) + " = " + result);
			return result;
		}

		public override string ToString(bool traduzido, Dictionary<char?, int?> variaveis)
		{
			return expressao.ToString(traduzido, variaveis);
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: public static Polinomio criarPolinomio(String pol) throws Analisador.AnalysisError
		public static Polinomio criarPolinomio(string pol)
		{
			pol = pol.replaceAll("\\s+", "");
			pol = pol.replaceAll("\\.", "*");

			StringReader read = new StringReader(pol);
			Lexico lex = new Lexico(read);
			Sintatico sintatico = new Sintatico();
			Semantico semantico = new Semantico();
			sintatico.parse(lex, semantico);

			return semantico.Polinomio;
		}

		public virtual bool EhEquivalente(Polinomio pol)
		{
			// Verifica se possui a mesma quantidade de variáveis
			if (this.Variaveis.Count != pol.Variaveis.Count)
			{
				return false;
			}

			// Verifica se as variáveis são as mesmas
			foreach (KeyValuePair<char?, int?> entry in this.Variaveis.SetOfKeyValuePairs())
			{
				if (!pol.Variaveis.ContainsKey(entry.Key))
				{
					return false;
				}
			}

			int contador = 0;
			// Faz um teste substituindo as variáveis por números primos
			foreach (KeyValuePair<char?, int?> entry in this.Variaveis.SetOfKeyValuePairs())
			{
				entry.Value = PRIMOS[contador++];
			}
			if (this.calcular(this.Variaveis) != pol.calcular(this.Variaveis))
			{
				return false;
			}

			// Faz o mesmo teste com outros números primos
			foreach (KeyValuePair<char?, int?> entry in this.Variaveis.SetOfKeyValuePairs())
			{
				entry.Value = PRIMOS[contador++];
			}
			if (this.calcular(this.Variaveis) != pol.calcular(this.Variaveis))
			{
				return false;
			}
			// Faz o teste com tudo zero
			foreach (KeyValuePair<char?, int?> entry in this.Variaveis.SetOfKeyValuePairs())
			{
				entry.Value = 0;
			}
			if (this.calcular(this.Variaveis) != pol.calcular(this.Variaveis))
			{
				return false;
			}

			// Faz o teste com primos negativos
			foreach (KeyValuePair<char?, int?> entry in this.Variaveis.SetOfKeyValuePairs())
			{
				entry.Value = -PRIMOS[contador++];
			}
			if (this.calcular(this.Variaveis) != pol.calcular(this.Variaveis))
			{
				return false;
			}

			return true;
		}

		public static bool validar(string polinomio)
		{
			try
			{
				polinomio = polinomio.replaceAll("\\s+", "");

				StringReader read = new StringReader(polinomio);
				Lexico lex = new Lexico(read);
				Sintatico sintatico = new Sintatico();
				sintatico.parse(lex, new SemanticoDummy());

				return true;
			}
			catch (LexicalError e)
			{
				e.printStackTrace();
			}
			catch (SyntaticError e)
			{
				e.printStackTrace();
			}
			catch (SemanticError e)
			{
				e.printStackTrace();
			}
			return false;
		}


		public override Polinomio simplificar()
		{
			expressao.simplificar();
			return this;
		}

		public override Polinomio ordenar()
		{
			expressao.ordenar();
			return this;
		}

		public override int Peso
		{
			get
			{
				return expressao.Peso;
			}
		}
	}

}