package biblio;
import java.util.ArrayList;

public class Revista extends ItemBiblioteca {
    private int edicao;
    private String periodicidade;
    private ArrayList<Exemplar> exemplares;

    public Revista(int codigo, String titulo, int ano, int edicao, String periodicidade) {
        super(codigo, titulo, ano);
        this.edicao = edicao;
        this.periodicidade = periodicidade;
        this.exemplares = new ArrayList<>();
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
        System.out.println("Revista: " + titulo);
        System.out.println("Edição: " + edicao);
        System.out.println("Periodicidade: " + periodicidade);
    }
}
