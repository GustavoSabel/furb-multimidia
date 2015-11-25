namespace Analisador
{

	public class SemanticError : AnalysisError
	{
		public SemanticError(string msg, int position) : base(msg, position)
		{
		}

		public SemanticError(string msg) : base(msg)
		{
		}
	}

}