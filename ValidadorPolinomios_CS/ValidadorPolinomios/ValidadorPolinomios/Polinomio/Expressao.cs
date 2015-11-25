using System.Collections.Generic;

namespace ValidadorPolinomios.Polinomio
{
    public class Expressao : Base
    {
        private List<Termo> termos;
        private Termo ultimoTermo;
        private Base origem;

        public Expressao()
        {
            termos = new List<Termo>();
        }

        public virtual void addTermo(Termo termo)
        {
            ultimoTermo = termo;
            termo.Origem = this;
            termos.Add(termo);
        }

        public virtual List<Termo> Termos
        {
            get
            {
                return termos;
            }
        }

        public virtual Termo UltimoTermo
        {
            get
            {
                return ultimoTermo;
            }
        }

        public override double calcular(Dictionary<char, int> variaveis)
        {
            double result = 0;
            foreach (Termo termo in termos)
            {
                result += termo.calcular(variaveis);
            }
            return result;
        }

        public override string ToString(bool traduzido, Dictionary<char, int> variaveis)
        {
            string result = "";
            for (int i = 0; i < termos.Count; i++)
            {
                if (i > 0)
                {
                    result += "+";
                }
                result += termos[i].ToString(traduzido, variaveis);
            }
            return result;
        }

        public override Base simplificar()
        {
            for (int i = 0; i < termos.Count; i++)
            {
                termos[i].simplificar();
            }
            return this;
        }

        public virtual void substituir(Termo termoSubstituido, List<Termo> termosSubstituidores)
        {
            int indexTermoSubstituido = this.termos.IndexOf(termoSubstituido);
            this.termos.RemoveAt(indexTermoSubstituido);

            foreach (Termo termoSubstituidor in termosSubstituidores)
            {
                Sinal novoSinal = Sinal.valueOf(termoSubstituidor.Sinal.Value * termoSubstituido.Sinal.Value);
                termoSubstituidor.Sinal = novoSinal;
                termoSubstituidor.Origem = this;
                this.termos.Insert(indexTermoSubstituido++, termoSubstituidor);
            }
        }

        public override Base ordenar()
        {
            foreach (Termo termo in termos)
                termo.ordenar();

            this.ordernar(termos);
            return this;
        }

        public override int Peso
        {
            get
            {
                int total = 0;
                foreach (Termo termo in termos)
                {
                    total += termo.Peso;
                }
                return total;
            }
        }
    }

}