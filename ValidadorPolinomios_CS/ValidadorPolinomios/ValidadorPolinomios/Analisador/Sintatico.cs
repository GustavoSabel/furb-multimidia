using System.Collections;

namespace Analisador
{

	public class Sintatico : Constants
	{
		private Stack stack = new Stack();
		private Token currentToken;
		private Token previousToken;
		private Lexico scanner;
		private Semantico semanticAnalyser;

		private static bool isTerminal(int x)
		{
			return x < FIRST_NON_TERMINAL;
		}

		private static bool isNonTerminal(int x)
		{
			return x >= FIRST_NON_TERMINAL && x < FIRST_SEMANTIC_ACTION;
		}

		private static bool isSemanticAction(int x)
		{
			return x >= FIRST_SEMANTIC_ACTION;
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: private boolean step() throws LexicalError, SyntaticError, SemanticError
		private bool step()
		{
			if (currentToken == null)
			{
				int pos = 0;
				if (previousToken != null)
				{
					pos = previousToken.Position + previousToken.Lexeme.Length;
				}

				currentToken = new Token(Constants_Fields.DOLLAR, "$", pos);
			}

			int x = ((int?)stack.Pop()).Value;
			int a = currentToken.Id;

			if (x == Constants_Fields.EPSILON)
			{
				return false;
			}
			else if (isTerminal(x))
			{
				if (x == a)
				{
					if (stack.Count == 0)
					{
						return true;
					}
					else
					{
						previousToken = currentToken;
						currentToken = scanner.nextToken();
						return false;
					}
				}
				else
				{
					throw new SyntaticError(PARSER_ERROR[x], currentToken.Position);
				}
			}
			else if (isNonTerminal(x))
			{
				if (pushProduction(x, a))
				{
					return false;
				}
				else
				{
					throw new SyntaticError(PARSER_ERROR[x], currentToken.Position);
				}
			}
			else // isSemanticAction(x)
			{
				semanticAnalyser.executeAction(x - FIRST_SEMANTIC_ACTION, previousToken);
				return false;
			}
		}

		private bool pushProduction(int topStack, int tokenInput)
		{
			int p = PARSER_TABLE[topStack - FIRST_NON_TERMINAL][tokenInput - 1];
			if (p >= 0)
			{
				int[] production = PRODUCTIONS[p];
				//empilha a produ��o em ordem reversa
				for (int i = production.Length - 1; i >= 0; i--)
				{
					stack.Push(new int?(production[i]));
				}
				return true;
			}
			else
			{
				return false;
			}
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: public void parse(Lexico scanner, Semantico semanticAnalyser) throws LexicalError, SyntaticError, SemanticError
		public virtual void parse(Lexico scanner, Semantico semanticAnalyser)
		{
			this.scanner = scanner;
			this.semanticAnalyser = semanticAnalyser;

			stack.Clear();
			stack.Push(new int?(Constants_Fields.DOLLAR));
			stack.Push(new int?(START_SYMBOL));

			currentToken = scanner.nextToken();

			while (!step())
			{
				;
			}
		}
	}

}