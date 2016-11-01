/** Alunos : Leonardo Broch de Morais (leo_broch@hotmail.com) e Lucas Heim        Trabalho B    Lab 1    Prof. Aníbal   2016/2 */ 

public class Agencia
{
    private Poupanca[] contas;
    private int totalContas;
    
    public Agencia (int maxContas) {
        this.contas = new Poupanca[maxContas];
    }
    
    public int abreConta () {
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
        } 
        else {
            // Instanciar poupança saude
        }
        //Debug
        return 0;
    }
}
