package Polinomio;

import java.util.ArrayList;
import java.util.HashMap;

public class Termo implements Base {
	private Sinal sinal;
	private ArrayList<Elemento> elementos;
	private Elemento ultimoElemento;
	private Expressao origem;

	public Termo() {
		elementos = new ArrayList<>();
		this.setSinal(Sinal.Positivo);
	}

	public ArrayList<Elemento> getElementos() {
		return elementos;
	}

	public void addElementos(Elemento elemento) {
		ultimoElemento = elemento;
		elemento.setOrigem(this);
		this.elementos.add(elemento);
	}

	public Elemento getUltimoElemento() {
		return ultimoElemento;
	}

	public double calcular(HashMap<Character, Integer> variaveis) {
		double result = 0;
		boolean primeiro = true;
		for (Elemento elemento : elementos) {
			if (primeiro) {
				primeiro = false;
				result = elemento.calcular(variaveis);
			} else {
				result *= elemento.calcular(variaveis);
			}
		}
		result *= sinal.getValue();
		return result;
	}

	public Expressao getOrigem() {
		return origem;
	}

	@Override
	public void setOrigem(Base origem) {
		this.origem = (Expressao) origem;
	}

	public Sinal getSinal() {
		return sinal;
	}

	public void setSinal(Sinal sinal) {
		this.sinal = sinal;
	}

	@Override
	public String toString(boolean traduzido, HashMap<Character, Integer> variaveis) {
		String result = "";
		boolean primeiro = true;
		for (Elemento elemento : elementos) {
			if (primeiro) {
				primeiro = false;
				result += elemento.toString(traduzido, variaveis);
			} else {
				result += "*" + elemento.toString(traduzido, variaveis);
			}
		}
		if (sinal == Sinal.Negativo)
			result = "(" + getSinal().toString() + result + ")";
		return result;
	}

	public Termo simplificar() {
		
		// Retira os 1 desnecessários
		// Só irá retirar se a potencia dele for um número e for positiva
		if (elementos.size() > 1) {
			Elemento elementoAjustado = elementos.get(0);
			for (int i = 0; i < elementos.size(); i++) {
				Elemento eleAux = elementos.get(i);
				if (!eleAux.temNumero() || eleAux.getNumero() != 1) {
					elementoAjustado = eleAux;
					break;
				}
			}
			
			Sinal sinalAplicar = Sinal.Positivo;
			for (int i = 0; i < elementos.size(); i++) {
				Elemento eleRemovido = elementos.get(i);
				if (eleRemovido.temNumero() && eleRemovido.getNumero() == 1) {
					if (!eleRemovido.temPotencia() || (eleRemovido.getPotencia().temNumero()
							&& eleRemovido.getPotencia().getSinal() == Sinal.Positivo)) {

						if (eleRemovido != elementoAjustado) {
							sinalAplicar = sinalAplicar.multiplicar(eleRemovido.getSinal());
							elementos.remove(i);
							i--;
						}
					}
				}
			}
			elementoAjustado.setSinal(elementoAjustado.getSinal().multiplicar(sinalAplicar));
		}
		
		// Aplica o sinal negativo do termo nos seus elementos
		if (this.getSinal() == Sinal.Negativo) {
			for (Elemento elemento : elementos) {
				elemento.setSinal(elemento.getSinal().multiplicar(this.getSinal()));
			}
			this.setSinal(this.getSinal().inverter());
		}
		
		for (Elemento elemento : elementos) {
			elemento.simplificar();
			if (elemento.temPotencia())
				elemento.getPotencia().simplificar();
		}



		if (this.elementos.size() == 1) {
			ArrayList<Termo> termosSubstituidores = new ArrayList<>();
			Elemento elementoEliminado = this.elementos.get(0);
			Expressao expressao;
			if ((expressao = elementoEliminado.getExpressao()) != null) {
				termosSubstituidores.addAll(expressao.getTermos());
				for (Termo termo : termosSubstituidores) {
					termo.setSinal(
							termo.getSinal()
							.multiplicar(this.getSinal())
							.multiplicar(elementoEliminado.getSinal()));
				}
				this.origem.substituir(this, termosSubstituidores);
			}
		} else if (this.elementos.size() > 1) {
			ArrayList<Elemento> elementosSubstituidores = new ArrayList<>();
			for (int i = 0; i < this.elementos.size(); i++) {
				Elemento elemento = elementos.get(i);
				Expressao expressao;
				if ((expressao = elemento.getExpressao()) != null) {
					if (expressao.getTermos().size() == 1) {
						Termo termoEliminado = expressao.getTermos().get(0);
						elementosSubstituidores.addAll(termoEliminado.getElementos());
						for (Elemento elementoAux : elementosSubstituidores) {
							elementoAux.setSinal(
									elementoAux.getSinal()
									.multiplicar(elemento.getSinal())
									.multiplicar(termoEliminado.getSinal()));
						}
						this.substituir(elemento, elementosSubstituidores);
						i--;
					}
				}
			}
		}

		return this;
	}

	private void substituir(Elemento elementoSubstituido, ArrayList<Elemento> elementosSubstituidores) {
		int indexElementoSubstituido = this.elementos.indexOf(elementoSubstituido);
		this.elementos.remove(indexElementoSubstituido);

		for (Elemento elementoSubstituidor : elementosSubstituidores) {
			Sinal novoSinal = 
					elementoSubstituidor.getSinal().multiplicar(elementoSubstituido.getSinal());
			elementoSubstituidor.setSinal(novoSinal);
			elementoSubstituidor.setOrigem(this);
			this.elementos.add(indexElementoSubstituido++, elementoSubstituidor);
		}

	}
}
