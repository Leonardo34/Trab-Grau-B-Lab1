
/** Alunos : Leonardo Broch de Morais (leo_broch@hotmail.com) e Lucas Heim        Trabalho B    Lab 1    Prof. Aníbal   2016/2 */ 

public class Dependente
{
    private String nome;
    private char parentesco;
    
    public Dependente (String nome, char parentesco) {
        this.nome = nome;
        setParentesco(parentesco);
    }
    
    public void setParentesco (char parentesco) {
        if (parentesco != 'c' && parentesco != 'f' && parentesco != 'p') {
            this.parentesco = 'o';
        }
        else {
            this.parentesco = parentesco;
        }
    }
    
    public String traduzParentesco () {
        if (this.parentesco == 'p') {
            return "Progenitor";
        }
        else if (this.parentesco == 'f') {
            return "Filho";
        }
        else if (this.parentesco == 'c') {
            return "Conjuge";
        }
        else {
            return "Outro"; 
        }
    }
    
    @Override
    public String toString () {
        return "Nome: " + this.nome + " Parentesco: " + this.traduzParentesco();
    }
}
