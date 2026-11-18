package biblio;

public class Usuario {
    private int codigo;
    private String nome;

    public Usuario(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

