namespace Analisador
{

	public class SyntaticError : AnalysisError
	{
		public SyntaticError(string msg, int position) : base(msg, position)
		{
		}

		public SyntaticError(string msg) : base(msg)
		{
		}
	}

}