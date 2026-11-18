package biblio;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
    private static final String CAMINHO = "livros.csv";

    public static void salvarItens(List<ItemBiblioteca> itens) {
        try (BufferedWriter arquivo =
                new BufferedWriter(new FileWriter(CAMINHO, false))) {
            for (ItemBiblioteca item : itens) {
                String[] dados = new String[11];
                for (int i = 0; i < dados.length; i++) {
                    dados[i] = "";
                }

                dados[1] = String.valueOf(item.getCodigo());
                dados[2] = item.getTitulo();
                dados[3] = String.valueOf(item.getAno());
                
                if (item instanceof LivroDigital) {
                    LivroDigital livro = (LivroDigital) item;
                    dados[0] = "D";
                    dados[4] = livro.getIsbn();
                    dados[5] = livro.getGenero();
                    dados[8] = livro.getFormato();
                    dados[9] = String.valueOf(livro.getTamanhoArquivo());
                    dados[10] = serializarExemplares(livro.getExemplares());
                } else if (item instanceof Livro) {
                    Livro livro = (Livro) item;
                    dados[0] = "L";
                    dados[4] = livro.getIsbn();
                    dados[5] = livro.getGenero();
                    dados[10] = serializarExemplares(livro.getExemplares());
                } else if (item instanceof Revista) {
                    Revista revista = (Revista) item;
                    dados[0] = "R";
                    dados[6] = String.valueOf(revista.getEdicao());
                    dados[7] = revista.getPeriodicidade();
                    dados[10] = serializarExemplares(revista.getExemplares());
                }
                arquivo.write(String.join(";", dados));
                arquivo.newLine();
            }

            System.out.println("Acervo salvo com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo.");
        }
    }
    private static String serializarExemplares(List<Exemplar> exemplares) {
        StringBuilder resultado = new StringBuilder();
        for (Exemplar exemplar : exemplares) {
            if (resultado.length() > 0) {
                resultado.append(",");
            }
            resultado.append(exemplar.getCodigo()).append(":").append(exemplar.isDisponivel());
        }
        return resultado.toString();
    }

    public static ArrayList<ItemBiblioteca> carregarItens() {
        ArrayList<ItemBiblioteca> itens = new ArrayList<>();
        File arquivo = new File(CAMINHO);
        if (!arquivo.exists()) {
            return itens;
        }
        try (BufferedReader leitor =
                new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";", -1);
                if (dados.length != 11) {
                    continue;
                }
                int codigo = Integer.parseInt(dados[1]);
                String titulo = dados[2];
                int ano = Integer.parseInt(dados[3]);
                ItemBiblioteca item = null;
                if (dados[0].equals("L")) {
                    item = new Livro(codigo, titulo, ano, dados[4], dados[5]);
                } else if (dados[0].equals("D")) {
                    item = new LivroDigital(codigo, titulo, ano, dados[4], dados[5], dados[8], Double.parseDouble(dados[9]));
                } else if (dados[0].equals("R")) {
                    item = new Revista(codigo, titulo, ano, Integer.parseInt(dados[6]), dados[7]);
                }
                if (item != null) {
                    adicionarExemplares(item, dados[10]);
                    itens.add(item);
                }
            }
            System.out.println("Acervo carregado com sucesso!");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao carregar arquivo.");
        }
        return itens;
    }
    private static void adicionarExemplares(ItemBiblioteca item, String dados) {
        if (dados.isEmpty()) {
            return;
        }
        String[] exemplares = dados.split(",");
        for (String registro : exemplares) {
            String[] partes = registro.split(":");
            int codigo = Integer.parseInt(partes[0]);
            boolean disponivel =
                    Boolean.parseBoolean(partes[1]);
            Exemplar exemplar =
                    new Exemplar(codigo, disponivel);
            if (item instanceof LivroDigital) {
                ((LivroDigital) item).adicionarExemplar(exemplar);
            } else if (item instanceof Livro) {
                ((Livro) item).adicionarExemplar(exemplar);
            } else if (item instanceof Revista) {
                ((Revista) item).adicionarExemplar(exemplar);
            }
        }
    }
}
