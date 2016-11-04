
/** Alunos : Leonardo Broch de Morais (leo_broch@hotmail.com) e Lucas Heim        Trabalho B    Lab 1    Prof. Aníbal   2016/2 */ 

public class PoupancaSaude extends Poupanca
{
    private double saldoVinculado,saldoFinanciado;
    private Dependente[] dependentes;

    public PoupancaSaude(int numConta,String nome)
    {
        super(numConta,nome);
        dependentes = new Dependente[5];
    }

    public int contaDependentes()
    {
        int contador = 0;
        for(int x = 0;x<5;x++)
        {
            if(dependentes[x] != null) contador++;
        }

        return contador;
    }

    @Override
    public void deposita (double valor) {
        int qtdDependentes = contaDependentes();
        double desconto = 0;

        if(qtdDependentes == 0)
        {
            desconto = 0.15*valor;
        }
        else if (qtdDependentes == 1 || qtdDependentes == 2)
        {
            desconto = 0.2 * valor;
        }
        else if (qtdDependentes == 3 || qtdDependentes == 4)
        {
            desconto = 0.3*valor;
        }
        else if (qtdDependentes == 5)
        {
            desconto = 0.5 * valor;
        }

        super.deposita(valor-desconto);
        saldoVinculado += desconto;
    }

    @Override
    public double creditaRendimento (double taxa) {
        double rendimento = taxa * saldoVinculado;
        saldoVinculado += rendimento;
        return rendimento + super.creditaRendimento(taxa);

    }

    public boolean insereDependente(Dependente a)
    {
        for(int x=0;x<dependentes.length;x++)
        {
            if(dependentes[x] == null) {dependentes[x] = a; return true;}
        }

        return false;
    }

    public int buscaDependente(String nome)
    {
        for(int x=0;x<dependentes.length;x++)
        {
            if(dependentes[x].getNome() == nome) return x;
        }

        return 99;
    }

    public Dependente retiraDependente(String nome)
    {
        int index = buscaDependente(nome);
        if(index!=99)
        {
            Dependente a = dependentes[index];
            dependentes[index] = null;
            return a;
        }
        return null;
    }

    public double retiraSaude(double valor)
    {
        Teclado t = new Teclado();
        double divida = valor;
        if(valor <= saldoVinculado)
        {
            saldoVinculado -= valor;
        }        

        else
        {
            divida = valor - saldoVinculado;
            saldoVinculado = 0;
            double a = 0;
            System.out.println("O seu saldo vinculado não foi o suficiente para abater a despesa.");
            System.out.println("Restam: R$" + divida);
            System.out.println("");
            System.out.println("O seu saldo livre possui R$" + super.getSaldoLivre());
            do
            {
                a = t.leDouble("Digite o valor que queres usar do saldo:");
                if(a>getSaldoLivre() || a>divida) System.out.println("Valor muito grande, redigite!");
            
            }while(a>getSaldoLivre() || a>divida);

            super.retira(a);
            divida -= a;
            if(divida > 0)
            {
                if(saldoFinanciado == 0.00) saldoFinanciado += (divida + 0.05*divida);
                else if(saldoFinanciado > 0.00 && saldoFinanciado <=500.00) saldoFinanciado += (divida + 0.1*divida);
                else saldoFinanciado += (divida + 0.15*divida);  
            }

        }

        if(saldoFinanciado == 0) return 0;
        return saldoFinanciado;
    }

    public double amortizaFinanciamento(double valor)
    {
        if(valor>saldoFinanciado) return 0;

        else
        {
            saldoFinanciado-=valor;

            if(saldoFinanciado == 0)
            {
                super.deposita(valor*0.05);
                return valor*0.05;
            }

        }

        return 0;
    }

    private void ordenaDependentes()
    {
        Dependente temp;
        
        for(int y = 0;y<dependentes.length;y++)
        {
            for (int x = 0;x<dependentes.length;x++)
            {
                if(dependentes[x].getNome().compareTo(dependentes[x+1].getNome()) < 0 || dependentes[x] == null)
                {
                    temp = dependentes[x];
                    dependentes[x] = dependentes[x+1];
                    dependentes[x+1] = temp;
                }
            }
        }
    }

    @Override
    public String toString()
    {
        String listaDependentes = "Lista de dependentes\n";
        int numDependentes = 0;
        ordenaDependentes();
        
        for (int x = 0;x<dependentes.length;x++)
        {
            if(dependentes[x] != null) listaDependentes = "Nome : " + dependentes[x].getNome() + "\nParentesco: "
                + dependentes[x].traduzParentesco() + "\n";
            numDependentes++;
        }

        if(numDependentes==0) listaDependentes = "";

        return super.toString() + "\n Saldo Vinculado: R$" + saldoVinculado + "\n Saldo Financiado : R$" + saldoFinanciado +
        listaDependentes;

    }
}
