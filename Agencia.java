/** Alunos : Leonardo Broch de Morais (leo_broch@hotmail.com) e Lucas Heim        Trabalho B    Lab 1    Prof. Aníbal   2016/2 */ 

public class Agencia
{
    private Poupanca[] contas;
    private int totalContas;
    
    public Agencia (int maxContas) {
        this.contas = new Poupanca[maxContas];
    }
    
    /** 
     * Método que abre uma Poupanca e a aloca no array(contas)
     * 
     * @param void 
     * @return numero da conta instanciada ou codigo de erro (array lotado)
     */
    
    private int abreConta () {
        // Confere se há espaço livre no array para inserir uma nova conta
        int indexNovaConta = -1;
        for (int i =0; i < contas.length; i++){
            if (contas[i] == null) {
                indexNovaConta = i;
                break;
            }
        }
        // Se não Há espaço livre no array retorna Erro
        if (indexNovaConta < 0) {
            return -1;
        }
        
        // Se há espaço livre continua o processo de instanciar uma nova conta
        Teclado t = new Teclado();
        int opcao = t.leInt("1 - Poupanca Simples \n2 - Poupanca Saude");
        
        if (opcao == 1) {
            // Instanciar poupança normal
            contas[indexNovaConta] = new Poupanca (t.leInt("Digite o numero da Conta: "), t.leString("Digite o nome do cliente: "));
            totalContas++;
            return contas[indexNovaConta].getNumero();
        } 
        else {
            // Instanciar poupança saude
            contas[indexNovaConta] = new PoupancaSaude(t.leInt("Digite o numero da Conta: "), t.leString("Digite o nome do cliente: "));
            return contas[indexNovaConta].getNumero();
        }       
    }
    
    /** 
     * Método que recebe o numero de uma conta a ser procurada
     * e busca no array(contas) se existe alguma conta instanciada 
     * com este numero, caso exista retorna seu indice, se não existe 
     * retorna um codigo de erro
     * 
     * @param Numero da conta a ser procurada (numeroConta) 
     * @return indice da conta procurada no array (contas), ou caso não exista
     * retorna um codigo de erro
     */
    
    private int buscaConta (int numeroConta) {
        for (int i = 0; i < contas.length; i++) {
            if(contas[i] != null)
            {
            if (contas[i].getNumero() == numeroConta) {
                return i;
            }
            }
        }
        return -1;
    }
    
    /** 
     * Método que exibe e opera o sistema da agência
     * 
     * @param void 
     * @return void
     */
    
    public void menuDeTransacoes () {
        Teclado t = new Teclado();
        int opcao;
        
        do {
            System.out.println("1- Abre Conta");
            System.out.println("2- Deposita");
            System.out.println("3- Retira");
            System.out.println("4- Retira para Saude");
            System.out.println("5- Amortiza Financiamento");
            System.out.println("6- Emite extrato da conta");
            System.out.println("7- Credita rendimentos");
            System.out.println("8- Insere um dependente");
            System.out.println("9- Retira um dependente");
            System.out.println("10- Encerra");
            opcao = t.leInt("Digite a opcao escolhida: ");
            
            if (opcao == 1) {
                //Abre uma nova conta na agencia
                int protocolo = abreConta();
                if (protocolo < 0) {
                    System.out.println("Não pode abrir novas contas nessa Agencia");
                }
                else {
                    System.out.println("Conta aberta de numero: " + protocolo);
                }
            }
            else if (opcao == 2) {
                //Faz deposito
                int index = buscaConta(t.leInt("Digite o numero da conta que voce quer realizar o deposito"));
                
                if (index < 0) {
                    System.out.println("Conta inexistente");
                }
                else {
                    contas[index].deposita(t.leDouble("Digite o valor a ser depositado: "));
                }
            }
            else if (opcao == 3) {
                //Retira
                int index = buscaConta(t.leInt("Digite o numero da conta que voce quer realizar o saque"));
                
                if (index < 0) {
                    System.out.println("Conta inexistente");
                }
                else {
                    boolean sucess = contas[index].retira(t.leDouble("Digite o valor a ser retirado: "));
                    
                    if (!sucess) {
                        System.out.println("Saldo insuficiente");
                    }
                }
            }
            else if (opcao == 4) {
                //Retira para a saude
                int index = buscaConta(t.leInt("Digite o  numero da conta que você quer realizar o saque para saúde"));

                if(index<0) 
                {
                    System.out.println("Conta Inexistente");
                }

                else if(!(contas[index] instanceof PoupancaSaude))
                {
                    System.out.println("Não é uma conta saude");
                }

                else
                {
                    ((PoupancaSaude)contas[index]).retiraSaude(t.leDouble("Digite o valor a ser retirado: "));
                }
            }
            else if (opcao ==5) {
                //Amortiza para Financiamento
                int index = buscaConta(t.leInt("Digite o  numero da conta que você quer amortizar para financiamento"));

                if(index<0) 
                {
                    System.out.println("Conta Inexistente");
                }

                else if(!(contas[index] instanceof PoupancaSaude))
                {
                    System.out.println("Tipo de conta não aceita essa operação.");
                }

                else
                {
                    double sucess = ((PoupancaSaude)contas[index]).amortizaFinanciamento(t.leDouble("Digite o valor a ser pago: "));

                    if(sucess>0) System.out.println("Recebeu desconto-depósito de: " + sucess);
                }

            }
            else if (opcao == 6) {
                //Emite extrato
                
                int index = buscaConta(t.leInt("Digite o numero da conta que voce quer visualizar o extrato: "));
                
                if (index < 0) {
                    System.out.println("Conta inexistente");
                }
                else {
                    if(contas[index] instanceof Poupanca) System.out.println(contas[index]);
                    else System.out.println((PoupancaSaude)contas[index]);
                }
            }
            else if (opcao == 7) {
                //Credita Rendimentos
                
                double taxa = t.leDouble("Digite a taxa de rendimento");
                double rendimentoTotal = 0;
                
                for (int i = 0; i < contas.length; i++) {
                    if(contas[i] != null)
                    {
                        rendimentoTotal += contas[i].creditaRendimento(taxa);
                    }
                }
                
                System.out.println("Total creditado em todas as contas: R$: " + rendimentoTotal);
            }
            else if (opcao == 8) {
                //Insere um dependente
                int index = buscaConta(t.leInt("Digite o  numero da conta que você quer inserir um dependente"));

                if(index<0) 
                {
                    System.out.println("Conta Inexistente");
                }

                else if(!(contas[index] instanceof PoupancaSaude))
                {
                    System.out.println("Não é uma conta saude");
                }

                else
                {
                    Dependente a = new Dependente(t.leString("Digite o nome do dependente"),t.leChar("Escolha o parentesco:\n c - Conjuge \n f - Filho \n p - Progenitor \n o - Outro"));
                    boolean sucess = ((PoupancaSaude)contas[index]).insereDependente(a);
                    if(!sucess) System.out.println("Esta conta não admite mais dependentes");
                }
            }
            else if (opcao == 9) {
                int index = buscaConta(t.leInt("Digite o  numero da conta que você quer retirar um dependente"));

                if(index<0) 
                {
                    System.out.println("Conta Inexistente");
                }

                else if(!(contas[index] instanceof PoupancaSaude))
                {
                    System.out.println("Não é uma conta saude");
                }

                else
                {
                    Dependente sucess = ((PoupancaSaude)contas[index]).retiraDependente(t.leString("Digite o nome do dependente"));
                    if(sucess == null) System.out.println("Não existe dependente com esse nome");
                }
            }
        } while (opcao != 10);
    }
    
}
