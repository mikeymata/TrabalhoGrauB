package biblio;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {
	public static void salvarLivro(String linha) {
        try {
            FileWriter arquivo = new FileWriter("livros.csv", true);
            arquivo.write(linha + "\n");
            arquivo.close();
            System.out.println("Lista salva!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo.");
        }
    }
}
