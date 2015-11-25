using System;
using System.Collections.Generic;

namespace Analisador
{

	using Base = Polinomio.Base;
	using Elemento = Polinomio.Elemento;
	using Expressao = Polinomio.Expressao;
	using Polinomio = Polinomio.Polinomio;
	using Sinal = Polinomio.Polinomio.Sinal;
	using Termo = Polinomio.Polinomio.Termo;

	public class Semantico : Constants
	{

		internal Stack<Polinomio.Base> pilha;
		internal Polinomio polinomio;

		public Semantico()
		{
			pilha = new Stack<>();
			polinomio = new Polinomio();
			Polinomio.Expressao expressao = new Polinomio.Expressao();
			polinomio.Expressao = expressao;
			pilha.Push(expressao);
		}

		public virtual Polinomio Polinomio
		{
			get
			{
				return polinomio;
			}
		}

		private int getSinal(Token token)
		{
			if (token != null && token.Lexeme.Equals("-"))
			{
				return -1;
			}
			return 1;
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: public void executeAction(int action, Token token) throws SemanticError
		public virtual void executeAction(int action, Token token)
		{
			//System.out.println("Ação #" + action + ", Token: " + token);
			try
			{
				switch (action)
				{
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

			}
			catch (Exception e)
			{
				Console.WriteLine(e.ToString());
				Console.Write(e.StackTrace);
			}
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: private void acao40(Token token) throws Exception
		private void acao40(Token token)
		{
			Polinomio.Elemento elemento = (Polinomio.Elemento) pilha.Peek();
			Polinomio.Elemento elementoPotencia = new Polinomio.Elemento();
			elemento.Potencia = elementoPotencia;
			pilha.Push(elementoPotencia);
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: private void acao31(Token token) throws Exception
		private void acao31(Token token)
		{
			Polinomio.Base obj = this.pilha.Pop();
			if (!(obj is Polinomio.Termo))
			{
//JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getName method:
				throw new Exception("Erro. Esperando tipo '" + typeof(Polinomio.Termo).FullName + "'. Encontrato o tipo '" + obj.GetType().FullName + "'.");
			}
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: private void acao30(Token token) throws Exception
		private void acao30(Token token)
		{
			Polinomio.Expressao expressao = (Polinomio.Expressao) pilha.Peek();
			Polinomio.Termo termo = new Polinomio.Termo();
			termo.Sinal = Polinomio.Sinal.valueOf(getSinal(token));
			expressao.addTermo(termo);
			pilha.Push(termo);
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: private void acao21(Token token) throws Exception
		private void acao21(Token token)
		{
			Polinomio.Base obj = this.pilha.Pop();
			if (!(obj is Polinomio.Elemento))
			{
//JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getName method:
				throw new Exception("Erro. Esperando tipo '" + typeof(Polinomio.Elemento).FullName + "'. Encontrato o tipo '" + obj.GetType().FullName + "'.");
			}
		}

		private void acao20(Token token)
		{
			Polinomio.Termo termo = (Polinomio.Termo) this.pilha.Peek();
			Polinomio.Elemento elemento = new Polinomio.Elemento();
			termo.addElementos(elemento);
			this.pilha.Push(elemento);
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: private void acao06(Token token) throws Exception
		private void acao06(Token token)
		{
			Polinomio.Base obj = this.pilha.Pop();
			if (!(obj is Polinomio.Expressao))
			{
//JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getName method:
				throw new Exception("Erro. Esperando tipo '" + typeof(Polinomio.Expressao).FullName + "'. Encontrato o tipo '" + obj.GetType().FullName + "'.");
			}
		}

		private void acao05(Token token)
		{
			Polinomio.Elemento elemento = (Polinomio.Elemento) this.pilha.Peek();
			Polinomio.Expressao expressao = new Polinomio.Expressao();
			expressao.Origem = elemento;
			elemento.Expressao = expressao;
			this.pilha.Push(expressao);
		}

		private void acao04(Token token)
		{
			Polinomio.Elemento elemento = (Polinomio.Elemento) this.pilha.Peek();
			elemento.Variavel = token.Lexeme[0];
			polinomio.addVariavel(token.Lexeme[0]);
		}

		private void acao03(Token token)
		{
			Polinomio.Elemento elemento = (Polinomio.Elemento) this.pilha.Peek();
			elemento.Numero = double.Parse(token.Lexeme);
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: private void acao02(Token token) throws SemanticError
		private void acao02(Token token)
		{
			Polinomio.Elemento elemento = (Polinomio.Elemento) this.pilha.Peek();
			elemento.Sinal = Polinomio.Sinal.valueOf(getSinal(token));
		}
	}

}