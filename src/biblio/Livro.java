package biblio;
import java.util.ArrayList;

public class Livro extends ItemBiblioteca {
    private String isbn;
    private String genero;
    private ArrayList<Exemplar> exemplares;

    public Livro(int codigo, String titulo, int ano, String isbn, String genero) {
        super(codigo, titulo, ano);
        this.isbn = isbn;
        this.genero = genero;
        this.exemplares = new ArrayList<>();
    }
    
    public String getIsbn() {
        return isbn;
    }

    public String getGenero() {
        return genero;
    }

    public ArrayList<Exemplar> getExemplares() {
        return exemplares;
    }
    
    public Livro(int codigo, String titulo, int ano) {
        this(codigo, titulo, ano, "Não informado", "Não informado");
    }

    public void adicionarExemplar(Exemplar exemplar) {
        exemplares.add(exemplar);
    }
    
    public Exemplar buscarExemplarDisponivel() {
        for (Exemplar exemplar : exemplares) {
            if (exemplar.verificarDisponibilidade()) {
                return exemplar;
            }
        }
        return null;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Livro: " + titulo);
        System.out.println("ISBN: " + isbn);
        System.out.println("Gênero: " + genero);
        System.out.println("Ano: " + ano);
    }
}
