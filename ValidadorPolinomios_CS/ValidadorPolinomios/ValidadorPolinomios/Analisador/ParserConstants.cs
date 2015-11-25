namespace Analisador
{

	public interface ParserConstants
	{
	}

	public static class ParserConstants_Fields
	{
		public const int START_SYMBOL = 10;
		public const int FIRST_NON_TERMINAL = 10;
		public const int FIRST_SEMANTIC_ACTION = 19;
		public static readonly int[][] PARSER_TABLE = new int[][]
		{
			new int[] {-1, 0, 0, 0, 0, -1, -1, 0, -1},
			new int[] {3, -1, -1, 1, 2, -1, -1, -1, 3},
			new int[] {-1, 4, 4, 4, 4, -1, -1, 4, -1},
			new int[] {6, 5, 5, 6, 6, 5, -1, 5, 6},
			new int[] {8, 8, 8, 8, 8, 8, 7, 8, 8},
			new int[] {-1, 11, 11, 9, 10, -1, -1, 11, -1},
			new int[] {-1, 12, 12, 12, 12, -1, -1, 12, -1},
			new int[] {-1, 13, 14, -1, -1, -1, -1, 15, -1},
			new int[] {-1, 17, 17, 17, 17, 16, -1, 17, -1}
		};
		public static readonly int[][] PRODUCTIONS = new int[][]
		{
			new int[] {49, 12, 50, 11},
			new int[] {4, 10},
			new int[] {5, 10},
			new int[] {0},
			new int[] {39, 16, 40, 13},
			new int[] {18, 12},
			new int[] {0},
			new int[] {7, 59, 16, 40},
			new int[] {0},
			new int[] {4, 21},
			new int[] {5, 21},
			new int[] {0},
			new int[] {15, 17, 14},
			new int[] {2, 22},
			new int[] {3, 23},
			new int[] {8, 24, 10, 25, 9},
			new int[] {6},
			new int[] {0}
		};
		public static readonly string[] PARSER_ERROR = new string[] {"", "Era esperado fim de programa", "Era esperado numero", "Era esperado variavel", "Era esperado \"+\"", "Era esperado \"-\"", "Era esperado \"*\"", "Era esperado \"^\"", "Era esperado \"(\"", "Era esperado \")\"", "<expressao> inv�lido", "<expressao2> inv�lido", "<termo> inv�lido", "<termo2> inv�lido", "<potencia> inv�lido", "<sinal> inv�lido", "<elemento> inv�lido", "<elemento2> inv�lido", "<multiplicacaoOpcional> inv�lido"};
	}

}