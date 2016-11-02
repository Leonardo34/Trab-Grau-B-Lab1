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
            // DEBUG
            return -1;
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
            if (contas[i].getNumero() == numeroConta) {
                return i;
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
            }
            else if (opcao == 3) {
                //Retira
            }
            else if (opcao == 4) {
                //Retira para a saude
            }
            else if (opcao ==5) {
                //Amortiza para Financiamento
            }
            else if (opcao == 6) {
                //Emite extrato
            }
            else if (opcao == 7) {
                //Credita Rendimentos
            }
            else if (opcao == 8) {
                //Insere um dependente
            }
            else if (opcao == 9) {
                //Retira um dependente
            }
        } while (opcao != 10);
    }
    
}
