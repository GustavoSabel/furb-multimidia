using System.Collections.Generic;

namespace Polinomio
{


	public abstract class Base
	{
		public abstract Base Origem {get;set;}


		public abstract double calcular(Dictionary<char?, int?> variaveis);

		public abstract string ToString(bool traduzido, Dictionary<char?, int?> variaveis);

		public abstract Base simplificar();

		public abstract Base ordenar();

		protected internal virtual List<T> ordernar<T>(List<T> dados) where T : Base
		{
			T baseAux = null;
			bool teveTroca = false;
			int max = dados.Count - 1;
			for (int i = 0; i < dados.Count; i++)
			{
				teveTroca = false;
				for (int j = 0; j < max; j++)
				{
					if (dados[j].Peso > dados[j + 1].Peso)
					{
						baseAux = dados.Remove(j);
						dados.Add(dados.Remove(j));
						dados.Insert(j + 1, baseAux);
						teveTroca = true;
					}
				}
				if (!teveTroca)
				{
					break;
				}
				max--;
			}

			return dados;
		}

		public abstract int Peso {get;}

		public override string ToString()
		{
			return ToString(false, null);
		}
	}

}