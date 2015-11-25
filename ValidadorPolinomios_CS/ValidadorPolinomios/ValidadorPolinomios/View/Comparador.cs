using Analisador;
using System;
using System.Collections.Generic;
using ValidadorPolinomios.Polinomio;

namespace View
{
    public class Comparador
    {
        public static void Main(string[] args)
        {
            try
            {
                //TODO: MUDAR TODA ESSA PORRA AQUI :)

                Teste.Testes.TestarPrograma();



                //Polinomio pol1;
                //Polinomio pol2;

                //verificaNumeroParametros(args, 2);

                //Operacao tipo = Operacao.valueOf(args[0]);
                
                //switch (tipo.Value)
                //{
                //    case Operacao.VerificaSeValido.Value:
                //        if (Polinomio.validar(args[1]))
                //        {
                //            Sair(MsgRetorno.Correto);
                //        }
                //        else
                //        {
                //            Sair(MsgRetorno.Incorreto);
                //        }
                //        goto case VerificaEquivalencia;
                //    case View.Comparador.Operacao.InnerEnum.VerificaEquivalencia:
                //        verificaNumeroParametros(args, 3);
                //        pol1 = Polinomio.criarPolinomio(args[1]);
                //        pol2 = Polinomio.criarPolinomio(args[2]);
                //        if (pol1.EhEquivalente(pol2))
                //        {
                //            Sair(MsgRetorno.Correto);
                //        }
                //        else
                //        {
                //            Sair(MsgRetorno.Incorreto);
                //        }
                //        goto case VerificaIgualdade;
                //    case View.Comparador.Operacao.InnerEnum.VerificaIgualdade:
                //        verificaNumeroParametros(args, 3);
                //        pol1 = Polinomio.criarPolinomio(args[1]).simplificar().ordenar();
                //        pol2 = Polinomio.criarPolinomio(args[2]).simplificar().ordenar();
                //        if (pol1.ToString().Equals(pol2.ToString()))
                //        {
                //            Sair(MsgRetorno.Correto);
                //        }
                //        else
                //        {
                //            Sair(MsgRetorno.Incorreto);
                //        }
                //        goto case VerificaFatoracao;
                //    case View.Comparador.Operacao.InnerEnum.VerificaFatoracao:
                //    case View.Comparador.Operacao.InnerEnum.VerificaProdutosNotaveis:
                //    case View.Comparador.Operacao.InnerEnum.VerificaSomaSubtracao:
                //    case View.Comparador.Operacao.InnerEnum.VerificaPropriedadeDistribuida:
                //        Sair(MsgRetorno.NaoImplementado);
                //        goto default;
                //    default:
                //        Sair(MsgRetorno.OperacaoInvalida);
                //        break;
                //}

            }
            catch (LexicalError)
            {
                Sair(MsgRetorno.ErroLexico);
            }
            catch (SyntaticError)
            {
                Sair(MsgRetorno.ErroSintatico);
            }
            catch (SemanticError)
            {
                Sair(MsgRetorno.ErroSemantico);
            }
            catch (Exception)
            {
                Sair(MsgRetorno.ErroOutro);
            }
            Sair(MsgRetorno.NaoDeviaTerChegoAqui);
        }

        private static void verificaNumeroParametros(string[] args, int tamanhoMinimo)
        {
            if (args.Length < tamanhoMinimo)
            {
                Sair(MsgRetorno.ParametrosIncorretos);
            }
        }

        public static void Sair(MsgRetorno msg)
        {
            Environment.Exit(msg.Value);
        }

        public sealed class Operacao
        {

            public static readonly Operacao VerificaSeValido = new Operacao("VerificaSeValido", InnerEnum.VerificaSeValido, 1);
            public static readonly Operacao VerificaEquivalencia = new Operacao("VerificaEquivalencia", InnerEnum.VerificaEquivalencia, 2);
            public static readonly Operacao VerificaIgualdade = new Operacao("VerificaIgualdade", InnerEnum.VerificaIgualdade, 3);
            public static readonly Operacao VerificaSomaSubtracao = new Operacao("VerificaSomaSubtracao", InnerEnum.VerificaSomaSubtracao, 4);
            public static readonly Operacao VerificaFatoracao = new Operacao("VerificaFatoracao", InnerEnum.VerificaFatoracao, 5);
            public static readonly Operacao VerificaPropriedadeDistribuida = new Operacao("VerificaPropriedadeDistribuida", InnerEnum.VerificaPropriedadeDistribuida, 6);
            public static readonly Operacao VerificaProdutosNotaveis = new Operacao("VerificaProdutosNotaveis", InnerEnum.VerificaProdutosNotaveis, 7);

            private static readonly IList<Operacao> valueList = new List<Operacao>();

            static Operacao()
            {
                valueList.Add(VerificaSeValido);
                valueList.Add(VerificaEquivalencia);
                valueList.Add(VerificaIgualdade);
                valueList.Add(VerificaSomaSubtracao);
                valueList.Add(VerificaFatoracao);
                valueList.Add(VerificaPropriedadeDistribuida);
                valueList.Add(VerificaProdutosNotaveis);
            }

            public enum InnerEnum
            {
                VerificaSeValido,
                VerificaEquivalencia,
                VerificaIgualdade,
                VerificaSomaSubtracao,
                VerificaFatoracao,
                VerificaPropriedadeDistribuida,
                VerificaProdutosNotaveis
            }

            private readonly string nameValue;
            private readonly int ordinalValue;
            private readonly InnerEnum innerEnumValue;
            private static int nextOrdinal = 0;

            private int value;

            internal Operacao(string name, InnerEnum innerEnum, int valor)
            {
                value = valor;

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

            public static IList<Operacao> values()
            {
                return valueList;
            }

            public int ordinal()
            {
                return ordinalValue;
            }

            public override string ToString()
            {
                return nameValue;
            }

            public static Operacao valueOf(string name)
            {
                foreach (Operacao enumInstance in Operacao.values())
                {
                    if (enumInstance.nameValue == name)
                    {
                        return enumInstance;
                    }
                }
                throw new System.ArgumentException(name);
            }
        }

        public sealed class MsgRetorno
        {

            public static readonly MsgRetorno Correto = new MsgRetorno("Correto", InnerEnum.Correto, 1);
            public static readonly MsgRetorno Incorreto = new MsgRetorno("Incorreto", InnerEnum.Incorreto, 2);
            public static readonly MsgRetorno ParametrosIncorretos = new MsgRetorno("ParametrosIncorretos", InnerEnum.ParametrosIncorretos, 100);
            public static readonly MsgRetorno ErroLexico = new MsgRetorno("ErroLexico", InnerEnum.ErroLexico, 101);
            public static readonly MsgRetorno ErroSintatico = new MsgRetorno("ErroSintatico", InnerEnum.ErroSintatico, 102);
            public static readonly MsgRetorno ErroSemantico = new MsgRetorno("ErroSemantico", InnerEnum.ErroSemantico, 103);
            public static readonly MsgRetorno OperacaoInvalida = new MsgRetorno("OperacaoInvalida", InnerEnum.OperacaoInvalida, 104);
            public static readonly MsgRetorno NaoDeviaTerChegoAqui = new MsgRetorno("NaoDeviaTerChegoAqui", InnerEnum.NaoDeviaTerChegoAqui, 105);
            public static readonly MsgRetorno NaoImplementado = new MsgRetorno("NaoImplementado", InnerEnum.NaoImplementado, 106);
            public static readonly MsgRetorno ErroOutro = new MsgRetorno("ErroOutro", InnerEnum.ErroOutro, 199);

            private static readonly IList<MsgRetorno> valueList = new List<MsgRetorno>();

            static MsgRetorno()
            {
                valueList.Add(Correto);
                valueList.Add(Incorreto);
                valueList.Add(ParametrosIncorretos);
                valueList.Add(ErroLexico);
                valueList.Add(ErroSintatico);
                valueList.Add(ErroSemantico);
                valueList.Add(OperacaoInvalida);
                valueList.Add(NaoDeviaTerChegoAqui);
                valueList.Add(NaoImplementado);
                valueList.Add(ErroOutro);
            }

            public enum InnerEnum
            {
                Correto,
                Incorreto,
                ParametrosIncorretos,
                ErroLexico,
                ErroSintatico,
                ErroSemantico,
                OperacaoInvalida,
                NaoDeviaTerChegoAqui,
                NaoImplementado,
                ErroOutro
            }

            private readonly string nameValue;
            private readonly int ordinalValue;
            private readonly InnerEnum innerEnumValue;
            private static int nextOrdinal = 0;

            internal int value;

            internal MsgRetorno(string name, InnerEnum innerEnum, int valor)
            {
                value = valor;

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

            public static IList<MsgRetorno> values()
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

            public override string ToString()
            {
                return nameValue;
            }

            public static MsgRetorno valueOf(string name)
            {
                foreach (MsgRetorno enumInstance in MsgRetorno.values())
                {
                    if (enumInstance.nameValue == name)
                    {
                        return enumInstance;
                    }
                }
                throw new System.ArgumentException(name);
            }
        }
    }

}