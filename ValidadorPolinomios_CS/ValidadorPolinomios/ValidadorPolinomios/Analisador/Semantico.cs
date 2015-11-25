using ValidadorPolinomios.Polinomio;
using System.Collections;
using System;

namespace Analisador
{ 
	public class Semantico : Constants
	{
        Stack pilha;
		Polinomio polinomio;

		public Semantico()
		{
			pilha = new Stack();
			polinomio = new Polinomio();
			Expressao expressao = new Expressao();
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
			Elemento elemento = (Elemento) pilha.Peek();
			Elemento elementoPotencia = new Elemento();
			elemento.Potencia = elementoPotencia;
			pilha.Push(elementoPotencia);
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: private void acao31(Token token) throws Exception
		private void acao31(Token token)
		{
			Base obj = (Base)this.pilha.Pop();
			if (!(obj is Termo))
			{
//JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getName method:
				throw new Exception("Erro. Esperando tipo '" + typeof(Termo).FullName + "'. Encontrato o tipo '" + obj.GetType().FullName + "'.");
			}
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: private void acao30(Token token) throws Exception
		private void acao30(Token token)
		{
			Expressao expressao = (Expressao) pilha.Peek();
			Termo termo = new Termo();
			termo.Sinal = Sinal.valueOf(getSinal(token));
			expressao.addTermo(termo);
			pilha.Push(termo);
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: private void acao21(Token token) throws Exception
		private void acao21(Token token)
		{
			Base obj = (Base)this.pilha.Pop();
			if (!(obj is Elemento))
			{
//JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getName method:
				throw new Exception("Erro. Esperando tipo '" + typeof(Elemento).FullName + "'. Encontrato o tipo '" + obj.GetType().FullName + "'.");
			}
		}

		private void acao20(Token token)
		{
			Termo termo = (Termo) this.pilha.Peek();
			Elemento elemento = new Elemento();
			termo.addElementos(elemento);
			this.pilha.Push(elemento);
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: private void acao06(Token token) throws Exception
		private void acao06(Token token)
		{
			Base obj = (Base)this.pilha.Pop();
			if (!(obj is Expressao))
			{
//JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getName method:
				throw new Exception("Erro. Esperando tipo '" + typeof(Expressao).FullName + "'. Encontrato o tipo '" + obj.GetType().FullName + "'.");
			}
		}

		private void acao05(Token token)
		{
			Elemento elemento = (Elemento) this.pilha.Peek();
			Expressao expressao = new Expressao();
			expressao.Origem = elemento;
			elemento.Expressao = expressao;
			this.pilha.Push(expressao);
		}

		private void acao04(Token token)
		{
			Elemento elemento = (Elemento) this.pilha.Peek();
			elemento.Variavel = token.Lexeme[0];
			polinomio.addVariavel(token.Lexeme[0]);
		}

		private void acao03(Token token)
		{
			Elemento elemento = (Elemento) this.pilha.Peek();
			elemento.Numero = double.Parse(token.Lexeme);
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: private void acao02(Token token) throws SemanticError
		private void acao02(Token token)
		{
			Elemento elemento = (Elemento) this.pilha.Peek();
			elemento.Sinal = Sinal.valueOf(getSinal(token));
		}
	}

}