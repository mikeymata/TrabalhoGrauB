package biblio;

public class Emprestimo {
    private Usuario usuario;
    private Exemplar exemplar;
    private String dataEmprestimo;
    private String dataDevolucao;

    public Emprestimo(Usuario usuario, Exemplar exemplar, String dataEmprestimo) {
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = "";
        exemplar.emprestar();
    }

    public void registrarDevolucao(String data) {
        dataDevolucao = data;
        exemplar.devolver();
    }
}

