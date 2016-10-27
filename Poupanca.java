
/** Alunos : Leonardo Broch de Morais (leo_broch@hotmail.com) e Lucas Heim        Trabalho B    Lab 1    Prof. Aníbal   2016/2 */ 

public class Poupanca
{
    private int numero;
    private String cliente;
    private double saldoLivre;
    
    public Poupanca (int numero, String cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.saldoLivre = 0;
    }
    
    public void deposita (double valor) {
        saldoLivre += valor;
    }
    
    public boolean retira (double valor) {
        if (valor > saldoLivre) {
            return false;
        }
        else {
            saldoLivre -= valor;
            return true;
        }
    }
    
    public double creditaRendimento (double taxa) {
        double rendimento = taxa * saldoLivre;
        saldoLivre += rendimento;
        return rendimento;
    }
    
    @Override 
    public String toString () {
        return "Num: " + numero + " Cliente: " + cliente + " \nSaldo livre: R$ " + saldoLivre;
    }
    
    public String getCliente () {return cliente;}
    
    public int getNumero () {return numero;}
    
    public double getSaldoLivre () {return saldoLivre;}
}
