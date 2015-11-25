namespace Analisador
{

	public class SemanticoDummy : Semantico, Constants
	{
//JAVA TO C# CONVERTER WARNING: Method 'throws' clauses are not available in .NET:
//ORIGINAL LINE: public void executeAction(int action, Token token) throws SemanticError
		public override void executeAction(int action, Token token)
		{
			//System.out.println("Ação #" + action + ", Token: " + token);
		}
	}

}