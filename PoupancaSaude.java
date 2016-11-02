
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
        double desconto;

        switch(qtdDependentes)
        {
            case 0:
            desconto = 0.15 * valor;
            break;
            case 1 || 2:
            desconto = 0.2 * valor;
            break;
            case 3 || 4:
            desconto = 0.3 * valor;
            break;
            case 5:
            desconto = 0.5 * valor;
            break;
            default:
            desconto = 0;
            break;
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
        for(x=0;x<dependentes.length;x++)
        {
            if(dependentes[x] == null) {dependentes[x] = a; return true;}
            return false;

        }
    }

    public int buscaDependente(String nome)
    {
        for(x=0;x<dependentes.length;x++)
        {
            if(dependentes[x].getCliente() == nome) return x;
            return 99;
        }
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
            System.out.println("O seu saldo vinculado não foi o suficiente para abater a despesa.")
            System.out.println("Restam: R$" + divida);
            System.out.println("");
            System.out.println("O seu saldo livre possui R$" + super.getSaldoLivre());
            do
            {
                a = t.leDouble("Digite o valor que queres usar do saldo:");
                if(a>getSaldoLivre() || a>divida) System.out.println("Valor muito grande, redigite!");
            
            }while(a>getSaldoLivre() || a>divida);

            if(divida - a > 0)

        }
    }
}
