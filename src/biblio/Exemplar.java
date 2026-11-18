package biblio;

public class Exemplar {
    private int codigo;
    private boolean disponivel;

    public Exemplar(int codigo) {
        this.codigo = codigo;
        this.disponivel = true;
    }
    
    public Exemplar(int codigo, boolean disponivel) {
        this.codigo = codigo;
        this.disponivel = disponivel;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public boolean verificarDisponibilidade() {
        return disponivel;
    }

    public void emprestar() {
        disponivel = false;
    }

    public void devolver() {
        disponivel = true;
    }
}

