package biblio;

public abstract class ItemBiblioteca {
    protected int codigo;
    protected String titulo;
    protected int ano;

    public ItemBiblioteca(int codigo, String titulo, int ano) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.ano = ano;
    }

    public abstract void exibirDetalhes();
}

