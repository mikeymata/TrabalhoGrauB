package biblio;

public class LivroDigital extends Livro {
    private String formato;
    private double tamanhoArquivo;

    public LivroDigital(int codigo, String titulo, int ano, String isbn, String genero, String formato, double tamanhoArquivo) {
        super(codigo, titulo, ano, isbn, genero);
        this.formato = formato;
        this.tamanhoArquivo = tamanhoArquivo;
    }
    
    public String getFormato() {
        return formato;
    }

    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Formato: " + formato);
        System.out.println("Tamanho: " + tamanhoArquivo + " MB");
    }
}
