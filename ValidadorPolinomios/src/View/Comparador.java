package View;

import Analisador.LexicalError;
import Analisador.SemanticError;
import Analisador.SyntaticError;
import Polinomio.Polinomio;

public class Comparador {

	public static void main(String[] args) {
		try {
			Polinomio pol1;
			Polinomio pol2;
			
			verificaNumeroParametros(args, 2);

			Operacao tipo = Operacao.valueOf(args[0]);
			switch (tipo) {
			case VerificaSeValido:
				if (Polinomio.validar(args[1]))
					Sair(MsgRetorno.Correto);
				else
					Sair(MsgRetorno.Incorreto);
			case VerificaEquivalencia:
				verificaNumeroParametros(args, 3);
				pol1 = Polinomio.criarPolinomio(args[1]);
				pol2 = Polinomio.criarPolinomio(args[2]);
				if (!pol1.EhEquivalente(pol2))
					Sair(MsgRetorno.Correto);
				else
					Sair(MsgRetorno.Incorreto);
			case VerificaIgualdade:
				verificaNumeroParametros(args, 3);
				pol1 = Polinomio.criarPolinomio(args[1]).simplificar();
				pol2 = Polinomio.criarPolinomio(args[2]).simplificar();
				if (!pol1.EhEquivalente(pol2))
					Sair(MsgRetorno.Correto);
				else
					Sair(MsgRetorno.Incorreto);
			case VerificaFatoracao:
			case VerificaProdutosNotaveis:
			case VerificaSomaSubtracao:
			case VerificaPropriedadeDistribuida:
				Sair(MsgRetorno.NaoImplementado);
			default:
				Sair(MsgRetorno.OperacaoInvalida);
				break;
			}

		} catch (LexicalError e) {
			Sair(MsgRetorno.ErroLexico);
		} catch (SyntaticError e) {
			Sair(MsgRetorno.ErroSintatico);
		} catch (SemanticError e) {
			Sair(MsgRetorno.ErroSemantico);
		} catch (Exception e) {
			Sair(MsgRetorno.ErroOutro);
		}
		Sair(MsgRetorno.NaoDeviaTerChegoAqui);
	}

	private static void verificaNumeroParametros(String[] args, int tamanhoMinimo) {
		if (args.length < tamanhoMinimo) {
			Sair(MsgRetorno.ParametrosIncorretos);
		}
	}

	public static void Sair(MsgRetorno msg) {
		System.exit(msg.getValue());
	}

	public enum Operacao {

		VerificaSeValido(1), //
		VerificaEquivalencia(2), //
		VerificaIgualdade(3), //
		VerificaSomaSubtracao(4), //
		VerificaFatoracao(5), //
		VerificaPropriedadeDistribuida(6), //
		VerificaProdutosNotaveis(7);

		private int value;

		private Operacao(int valor) {
			value = valor;
		}

		public int getValue() {
			return value;
		}
	}

	public enum MsgRetorno {

		Correto(1), //
		Incorreto(2), //
		ParametrosIncorretos(100), //
		ErroLexico(101), //
		ErroSintatico(102), //
		ErroSemantico(103), //
		OperacaoInvalida(104), //
		NaoDeviaTerChegoAqui(105), //
		NaoImplementado(106), //
		ErroOutro(199);

		private int value;

		private MsgRetorno(int valor) {
			value = valor;
		}

		public int getValue() {
			return value;
		}
	}
}
