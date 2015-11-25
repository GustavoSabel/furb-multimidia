using System.Collections.Generic;

namespace Polinomio
{


	public class Termo : Base
	{
		private Sinal sinal;
		private List<Elemento> elementos;
		private Elemento ultimoElemento;
		private Expressao origem;

		public Termo()
		{
			elementos = new List<>();
			this.Sinal = Sinal.Positivo;
		}

		public virtual List<Elemento> Elementos
		{
			get
			{
				return elementos;
			}
		}

		public virtual void addElementos(Elemento elemento)
		{
			ultimoElemento = elemento;
			elemento.Origem = this;
			this.elementos.Add(elemento);
		}

		public virtual Elemento UltimoElemento
		{
			get
			{
				return ultimoElemento;
			}
		}

		public override double calcular(Dictionary<char?, int?> variaveis)
		{
			double result = 0;
			bool primeiro = true;
			foreach (Elemento elemento in elementos)
			{
				if (primeiro)
				{
					primeiro = false;
					result = elemento.calcular(variaveis);
				}
				else
				{
					result *= elemento.calcular(variaveis);
				}
			}
			result *= sinal.Value;
			return result;
		}

		public override Expressao getOrigem()
		{
			return origem;
		}

		public override void setOrigem(Base origem)
		{
			this.origem = (Expressao) origem;
		}

		public virtual Sinal Sinal
		{
			get
			{
				return sinal;
			}
			set
			{
				this.sinal = value;
			}
		}


		public override string ToString(bool traduzido, Dictionary<char?, int?> variaveis)
		{
			string result = "";
			bool primeiro = true;
			foreach (Elemento elemento in elementos)
			{
				if (primeiro)
				{
					primeiro = false;
					result += elemento.ToString(traduzido, variaveis);
				}
				else
				{
					result += "*" + elemento.ToString(traduzido, variaveis);
				}
			}
			if (sinal == Sinal.Negativo)
			{
				result = "(" + Sinal.ToString() + result + ")";
			}
			return result;
		}

		public override Termo simplificar()
		{

			// Retira os 1 desnecessários
			// Só irá retirar se a potencia dele for um número e for positiva
			if (elementos.Count > 1)
			{
				Elemento elementoAjustado = elementos[0];
				for (int i = 0; i < elementos.Count; i++)
				{
					Elemento eleAux = elementos[i];
					if (!eleAux.temNumero() || eleAux.Numero != 1)
					{
						elementoAjustado = eleAux;
						break;
					}
				}

				Sinal sinalAplicar = Sinal.Positivo;
				for (int i = 0; i < elementos.Count; i++)
				{
					Elemento eleRemovido = elementos[i];
					if (eleRemovido.temNumero() && eleRemovido.Numero == 1)
					{
						if (!eleRemovido.temPotencia() || (eleRemovido.Potencia.temNumero() && eleRemovido.Potencia.Sinal == Sinal.Positivo))
						{

							if (eleRemovido != elementoAjustado)
							{
								sinalAplicar = sinalAplicar.multiplicar(eleRemovido.Sinal);
								elementos.RemoveAt(i);
								i--;
							}
						}
					}
				}
				elementoAjustado.Sinal = elementoAjustado.Sinal.multiplicar(sinalAplicar);
			}

			// Aplica o sinal negativo do termo nos seus elementos
			if (this.Sinal == Sinal.Negativo)
			{
				foreach (Elemento elemento in elementos)
				{
					elemento.Sinal = elemento.Sinal.multiplicar(this.Sinal);
				}
				this.Sinal = this.Sinal.inverter();
			}

			foreach (Elemento elemento in elementos)
			{
				elemento.simplificar();
				if (elemento.temPotencia())
				{
					elemento.Potencia.simplificar();
				}
			}



			if (this.elementos.Count == 1)
			{
				List<Termo> termosSubstituidores = new List<Termo>();
				Elemento elementoEliminado = this.elementos[0];
				Expressao expressao;
				if ((expressao = elementoEliminado.Expressao) != null)
				{
					termosSubstituidores.AddRange(expressao.Termos);
					foreach (Termo termo in termosSubstituidores)
					{
						termo.Sinal = termo.Sinal.multiplicar(this.Sinal).multiplicar(elementoEliminado.Sinal);
					}
					this.origem.substituir(this, termosSubstituidores);
				}
			}
			else if (this.elementos.Count > 1)
			{
				List<Elemento> elementosSubstituidores = new List<Elemento>();
				for (int i = 0; i < this.elementos.Count; i++)
				{
					Elemento elemento = elementos[i];
					Expressao expressao;
					if ((expressao = elemento.Expressao) != null)
					{
						if (expressao.Termos.Count == 1)
						{
							Termo termoEliminado = expressao.Termos[0];
							elementosSubstituidores.AddRange(termoEliminado.Elementos);
							foreach (Elemento elementoAux in elementosSubstituidores)
							{
								elementoAux.Sinal = elementoAux.Sinal.multiplicar(elemento.Sinal).multiplicar(termoEliminado.Sinal);
							}
							this.substituir(elemento, elementosSubstituidores);
							i--;
						}
					}
				}
			}

			return this;
		}

		private void substituir(Elemento elementoSubstituido, List<Elemento> elementosSubstituidores)
		{
			int indexElementoSubstituido = this.elementos.IndexOf(elementoSubstituido);
			this.elementos.RemoveAt(indexElementoSubstituido);

			foreach (Elemento elementoSubstituidor in elementosSubstituidores)
			{
				Sinal novoSinal = elementoSubstituidor.Sinal.multiplicar(elementoSubstituido.Sinal);
				elementoSubstituidor.Sinal = novoSinal;
				elementoSubstituidor.Origem = this;
				this.elementos.Insert(indexElementoSubstituido++, elementoSubstituidor);
			}

		}

		public override Base ordenar()
		{
			foreach (Elemento elemento in elementos)
			{
				elemento.ordenar();
			}
			this.ordernar(elementos);
			return this;
		}

		public override int Peso
		{
			get
			{
				int total = 0;
				foreach (Elemento elemento in elementos)
				{
					total += elemento.Peso;
				}
				return total;
			}
		}
	}

}