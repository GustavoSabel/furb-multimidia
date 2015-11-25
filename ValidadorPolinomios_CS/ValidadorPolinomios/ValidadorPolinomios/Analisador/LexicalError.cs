namespace Analisador
{

	public class LexicalError : AnalysisError
	{
		public LexicalError(string msg, int position) : base(msg, position)
		{
		}

		public LexicalError(string msg) : base(msg)
		{
		}
	}

}