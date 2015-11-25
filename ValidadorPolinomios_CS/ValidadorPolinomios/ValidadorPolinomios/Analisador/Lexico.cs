using System.Text;

namespace Analisador
{

	public class Lexico : Constants
	{
		private int position;
		private string input;

		public Lexico() : this(new java.io.StringReader(""))
		{
		}

		public Lexico(java.io.Reader input)
		{
			Input = input;
		}

		public virtual java.io.Reader Input
		{
			set
			{
				StringBuilder bfr = new StringBuilder();
				try
				{
					int c = value.read();
					while (c != -1)
					{
						bfr.Append((char)c);
						c = value.read();
					}
					this.input = bfr.ToString();
				}
				catch (java.io.IOException e)
				{
					Console.WriteLine(e.ToString());
					Console.Write(e.StackTrace);
				}
    
				Position = 0;
			}
		}

		public virtual int Position
		{
			set
			{
				position = value;
			}
		}

//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: public Token nextToken() throws LexicalError
		public virtual Token nextToken()
		{
			if (!hasInput())
			{
				return null;
			}

			int start = position;

			int state = 0;
			int lastState = 0;
			int endState = -1;
			int end = -1;

			while (hasInput())
			{
				lastState = state;
				state = nextState(nextChar(), state);

				if (state < 0)
				{
					break;
				}

				else
				{
					if (tokenForState(state) >= 0)
					{
						endState = state;
						end = position;
					}
				}
			}
			if (endState < 0 || (endState != state && tokenForState(lastState) == -2))
			{
				throw new LexicalError(ScannerConstants_Fields.SCANNER_ERROR[lastState], start);
			}

			position = end;

			int token = tokenForState(endState);

			if (token == 0)
			{
				return nextToken();
			}
			else
			{
				string lexeme = input.Substring(start, end - start);
				return new Token(token, lexeme, start);
			}
		}

		private int nextState(char c, int state)
		{
			int start = ScannerConstants_Fields.SCANNER_TABLE_INDEXES[state];
			int end = ScannerConstants_Fields.SCANNER_TABLE_INDEXES[state+1] - 1;

			while (start <= end)
			{
				int half = (start + end) / 2;

				if (ScannerConstants_Fields.SCANNER_TABLE[half][0] == c)
				{
					return ScannerConstants_Fields.SCANNER_TABLE[half][1];
				}
				else if (ScannerConstants_Fields.SCANNER_TABLE[half][0] < c)
				{
					start = half + 1;
				}
				else //(SCANNER_TABLE[half][0] > c)
				{
					end = half - 1;
				}
			}

			return -1;
		}

		private int tokenForState(int state)
		{
			if (state < 0 || state >= ScannerConstants_Fields.TOKEN_STATE.Length)
			{
				return -1;
			}

			return ScannerConstants_Fields.TOKEN_STATE[state];
		}

		private bool hasInput()
		{
			return position < input.Length;
		}

		private char nextChar()
		{
			if (hasInput())
			{
				return input[position++];
			}
			else
			{
				return (char) - 1;
			}
		}
	}

}