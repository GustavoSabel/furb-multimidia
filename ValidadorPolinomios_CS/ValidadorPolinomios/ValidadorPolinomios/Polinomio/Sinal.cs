using System.Collections.Generic;

namespace Polinomio
{

	public sealed class Sinal
	{
		public static readonly Sinal Positivo = new Sinal("Positivo", InnerEnum.Positivo, 1);
		public static readonly Sinal Negativo = new Sinal("Negativo", InnerEnum.Negativo, -1);

		private static readonly IList<Sinal> valueList = new List<Sinal>();

		static Sinal()
		{
			valueList.Add(Positivo);
			valueList.Add(Negativo);
		}

		public enum InnerEnum
		{
			Positivo,
			Negativo
		}

		private readonly string nameValue;
		private readonly int ordinalValue;
		private readonly InnerEnum innerEnumValue;
		private static int nextOrdinal = 0;
		private int value;

		private Sinal(string name, InnerEnum innerEnum, int value)
		{
			this.value = value;

			nameValue = name;
			ordinalValue = nextOrdinal++;
			innerEnumValue = innerEnum;
		}

		public int Value
		{
			get
			{
				return value;
			}
		}

		public override string ToString()
		{
			if (value == -1)
			{
				return "-";
			}
			return "+";
		}

		public static Sinal valueOf(int i)
		{
			if (i < 0)
			{
				return Sinal.Negativo;
			}
			return Sinal.Positivo;
		}

		public Sinal multiplicar(Sinal sinal)
		{
			return Sinal.valueOf(this.value * sinal.value);
		}

		public Sinal inverter()
		{
			if (this == Sinal.Positivo)
			{
				return Sinal.Negativo;
			}
			return Sinal.Positivo;
		}

		public static IList<Sinal> values()
		{
			return valueList;
		}

		public InnerEnum InnerEnumValue()
		{
			return innerEnumValue;
		}

		public int ordinal()
		{
			return ordinalValue;
		}
	}

}