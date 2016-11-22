/** Alunos : Leonardo Broch de Morais (leo_broch@hotmail.com) e Lucas Heim (lucasheim1983@gmail.com) Trabalho B    Lab 1    Prof. Aníbal   2016/2 */ 

public class PoupancaSaude extends Poupanca
{
    private double saldoVinculado,saldoFinanciado;
    private Dependente[] dependentes;

    public PoupancaSaude(int numConta,String nome)
    {
        super(numConta,nome);
        dependentes = new Dependente[5];
    }

    /** 
     * Método que retorna o número de dependentes já instanciados
     * 
     * @param void 
     * @return quantidade de dependentes
     */

    public int contaDependentes()
    {
        int contador = 0;
        for(int x = 0;x<dependentes.length;x++)
        {
            if(dependentes[x] != null) contador++;
        }

        return contador;
    }

    /** 
     * Método sobrescrito da classe Poupança. Realiza um depósito na Poupança Saúde.
     * 
     * @param valor a ser depositado(valor)
     * @return void
     */

    @Override
    public void deposita (double valor) {
        int qtdDependentes = contaDependentes();
        double desconto;

        switch(qtdDependentes)
        {
            case 0:
            desconto = 0.15 * valor;
            break;
            case 1:
            desconto = 0.2 * valor;
            break;
            case 2:
            desconto = 0.2 * valor;
            break;
            case 3:
            desconto = 0.3 * valor;
            break;
            case 4:
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

    /** 
     * Método sobrescrito da classe Poupança. Credita os rendimentos.
     * 
     * @param taxa de crédito a ser aplicada (taxa)
     * @return soma de todos os rendimentos adicionados.
     */

    @Override
    public double creditaRendimento (double taxa) {
        double rendimento = taxa * saldoVinculado;
        saldoVinculado += rendimento;
        return rendimento + super.creditaRendimento(taxa);

    }
    
    /** 
     * Método que insere um novo dependente na Poupanca Saude.
     * 
     * @param dependente a ser adicionado (a)
     * @return true se inseriu com sucesso, false se o array está lotado.
     */

    public boolean insereDependente(Dependente a)
    {
        for(int x=0;x<dependentes.length;x++)
        {
            if(dependentes[x] == null) {dependentes[x] = a; return true;}
        }
        return false;
    }

    /** 
     * Método que busca o índice de um certo dependente no array através do nome.
     * 
     * @param nome do dependente a ser buscado (nome)
     * @return índice do array ou, caso não encontre, 99.
     */

    public int buscaDependente(String nome)
    {
        for(int x = 0;x<dependentes.length;x++)
        {
            if(dependentes[x] != null) {
                if(dependentes[x].getNome().equals(nome)) return x;
            }    
        }
        return 99;
    }


    /** 
     * Método que retira um dependente do array, buscando pelo nome.
     * 
     * @param nome do dependente a ser retirado (nome)
     * @return objeto que foi retirado ou, se não encontrou, null.
     */

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

    /** 
     * Método que faz a retirada de um certo valor da Poupanca Saude.
     * 
     * @param valor a ser retirado (valor)
     * @return valor que foi financiado.
     */

    public double retiraSaude(double valor)
    {
        Teclado t = new Teclado();
        double divida = valor;
        if(valor <= saldoVinculado)
        {
            saldoVinculado -= valor;
            return 0;
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
            if(divida - a > 0) 
            {
                if(saldoFinanciado==0) a = (divida - a) + 0.05*(divida - a);
                else if(saldoFinanciado>0 && saldoFinanciado<=500.00) a = (divida - a) + 0.1*(divida - a);
                else a = (divida - a)+ 0.15*(divida - a);
            
                saldoFinanciado += a;
                return a;
            }
        }
        
        return 0;
    }

    /** 
     * Método para quitar a dívida do financiamento.
     * 
     * @param valor a ser pago (valor)
     * @return desconto ganho.
     */

    public double amortizaFinanciamento(double valor)
    {
        if(valor > saldoFinanciado)
        {
            return 0;
        }

        else
        {
            saldoFinanciado -= valor;

            if(saldoFinanciado == 0)
            {
                super.deposita(valor*0.05);
                return valor*0.05;
            }

            return 0;
        }
    }

    /** 
     * Método que ordena o array dos dependentes pelo nome.
     * 
     * @param void
     * @return void
     */

    private void ordenaDependentes()
    {
        Dependente temp;

        for(int x = 0;x<dependentes.length - 1;x++)
        {
            for(int y = x + 1;y<dependentes.length;y++)
            {
                if(dependentes[x] != null && dependentes[y] != null)
                {
                    if(dependentes[x].getNome().compareTo(dependentes[y].getNome())>0)
                    {
                        temp = dependentes[x];
                        dependentes[x] = dependentes[y];
                        dependentes[y] = temp;
                    }
                }
                
                else if(dependentes[x] == null && dependentes[y]!=null)
                {
                    dependentes[x] = dependentes[y];
                    dependentes[y] = null;
                }
            }
        }
    }

    /** 
     * Método que imprime as informações da conta na tela..
     * 
     * @param void
     * @return String
     */

    @Override
    public String toString()
    {
        String valores = "\nSaldo Vinculado: " + saldoVinculado + "\nSaldo Financiado: " + saldoFinanciado;
        String dep = "\nLista de Dependentes: \n";
        ordenaDependentes();

        for(int x = 0;x<dependentes.length;x++)
        {
            if(dependentes[x] != null)
            {
                dep += x+1 + " - " + dependentes[x].getNome() + "\n";
            }
        }


        return super.toString() + valores + dep;
    }
}
