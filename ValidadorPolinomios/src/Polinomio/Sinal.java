package Polinomio;

public enum Sinal {
	Positivo(1), Negativo(-1);
	private int value;

	private Sinal(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		if (value == -1)
			return "-";
		return "+";
	}

	public static Sinal valueOf(int i) {
		if (i < 0)
			return Sinal.Negativo;
		return Sinal.Positivo;
	}

	public Sinal multiplicar(Sinal sinal) {
		return Sinal.valueOf(this.value * sinal.value);
	}

	public Sinal inverter() {
		if(this == Sinal.Positivo)
			return Sinal.Negativo;
		return Sinal.Positivo;
	}
}
