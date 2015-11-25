using System;
using System.Collections.Generic;

namespace ValidadorPolinomios.Polinomio
{
	public class Elemento : Base
	{
		public const char NULL_VARIAVEL = (char)0;
		public static readonly double NULL_NUMERO = double.Epsilon;
		private Sinal sinal;
		private double numero;
		private char variavel;
		private Expressao expressao;
		private Elemento potencia;
		private Base origem;

		public Elemento()
		{
			Sinal = Sinal.Positivo;
			Numero = NULL_NUMERO;
			Variavel = NULL_VARIAVEL;
		}

		public Elemento(Sinal sinal, int numero, int potencia)
		{
			Sinal = sinal;
			this.potencia = new Elemento();
			this.potencia.Numero = potencia;
			Numero = numero;
			Variavel = NULL_VARIAVEL;
		}

		public Elemento(Sinal sinal, char variavel, int potencia)
		{
			Sinal = sinal;
			this.potencia = new Elemento();
			this.potencia.Numero = potencia;
			Numero = NULL_NUMERO;
			Variavel = variavel;
		}

		public virtual Elemento Potencia
		{
			get
			{
				return potencia;
			}
			set
			{
				value.Origem = this;
				this.potencia = value;
			}
		}

		public virtual double getPotenciaCalculada(Dictionary<char, int> variaveis)
		{
			if (potencia == null)
			{
				return 1;
			}
			return potencia.calcular(variaveis);
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

		public virtual double Numero
		{
			get
			{
				return numero;
			}
			set
			{
				this.numero = value;
			}
		}

		public virtual char Variavel
		{
			get
			{
				return variavel;
			}
			set
			{
				this.variavel = char.ToUpper(value);
			}
		}

		public virtual Expressao Expressao
		{
			get
			{
				return expressao;
			}
			set
			{
				value.Origem = this;
				this.expressao = value;
			}
		}

		public override double calcular(Dictionary<char, int> variaveis)
		{
			double result = 0;
			if (Expressao != null)
			{
				result = Expressao.calcular(variaveis);
			}
			else if (Variavel != Elemento.NULL_VARIAVEL)
			{
				result = variaveis[Variavel];
			}
			else
			{
				result = Numero;
			}
			result = result * Sinal.Value;
			result = Math.Pow(result, getPotenciaCalculada(variaveis));
			return result;
		}

		public override string ToString(bool traduzido, Dictionary<char, int> variaveis)
		{
			string saida = "";
			if (Expressao != null)
			{
				saida += "[" + Expressao.ToString(traduzido, variaveis) + "]";
			}
			else if (Variavel != Elemento.NULL_VARIAVEL)
			{
				if (traduzido)
				{
					saida += variaveis[Variavel];
				}
				else
				{
					saida += Variavel.ToString();
				}
			}
			else
			{
				saida += NumeroString.ToString();
			}
			if (this.potencia != null)
			{
				if (potencia.numero != 1 || potencia.potencia != null)
				{
					saida += "^" + this.potencia.ToString(traduzido, variaveis);
				}
			}
			if (sinal == Sinal.Negativo)
			{
				saida = "(" + sinal.ToString() + saida + ")";
			}
			return saida;
		}

		private string NumeroString
		{
			get
			{
				if ((this.numero % 1) == 0)
				{
					return ((int)Math.Floor(this.numero)).ToString();
				}
				else
				{
					return this.numero.ToString();
				}
			}
		}

		public override Base simplificar()
		{
			if (this.expressao == null)
			{
				return this;
			}

			this.expressao.simplificar();

			if (this.sinal == Sinal.Negativo)
			{
				foreach (Termo termo in this.expressao.Termos)
				{
					termo.Sinal = termo.Sinal.multiplicar(this.sinal);
				}

				this.sinal = this.sinal.inverter();
			}

			return this;
		}

		public virtual bool temPotencia()
		{
			return this.potencia != null;
		}

		public virtual bool temNumero()
		{
			return this.Numero != Elemento.NULL_NUMERO;
		}

		private bool temVariavel()
		{
			return this.variavel != NULL_VARIAVEL;
		}

		public override Base ordenar()
		{
			if (temPotencia())
			{
				this.potencia.ordenar();
			}
			if (expressao != null)
			{
				this.expressao.ordenar();
			}
			return null;
		}

		public override int Peso
		{
			get
			{
				int total = 0;
				if (this.expressao != null)
				{
					total += 0;
				}
				if (this.temVariavel())
				{
					total += 100;
				}
				if (this.temNumero())
				{
					total += (int)this.numero;
				}
				if (this.temPotencia())
				{
					total += 1;
				}
				return total;
			}
		}

	}

}