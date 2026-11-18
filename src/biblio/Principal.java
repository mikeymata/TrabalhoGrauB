package biblio;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
    	Scanner entrada = new Scanner(System.in);
    	int opcao;
    	ArrayList<ItemBiblioteca> itens = Arquivo.carregarItens();
    	
    	if (itens.isEmpty()) {
    	    Livro livro1 = new Livro(1, "Dom Casmurro", 1899, "978-85-00-00000-0", "Romance");
    	    Revista revista1 = new Revista(2, "Revista Ciência", 2024, 15, "Mensal");
    	    LivroDigital livro2 = new LivroDigital(3, "O Hobbit", 1937, "978-85-00-00000-1", "Fantasia", "PDF", 5.2);

    	    livro1.adicionarExemplar(new Exemplar(101));
    	    revista1.adicionarExemplar(new Exemplar(102));
    	    livro2.adicionarExemplar(new Exemplar(201));

    	    itens.add(livro1);
    	    itens.add(revista1);
    	    itens.add(livro2);
    	}
        
        Usuario usuario = new Usuario(1, "Mike");
        
        do {
            System.out.println("\n=== BIBLIOTECA ===");
            System.out.println("1 - Exibir itens");
            System.out.println("2 - Verificar disponibilidade");
            System.out.println("3 - Pegar emprestado");
            System.out.println("4- Salvar lista de livros no acervo");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = entrada.nextInt();

            if (opcao == 1) {
            	for (ItemBiblioteca item : itens) {
            	        item.exibirDetalhes();
            	    }
            }

            else if (opcao == 2 || opcao == 3) {
                System.out.println("\nEscolha um item:");
                for (int i = 0; i < itens.size(); i++) {
                    System.out.println((i + 1) + " - " + itens.get(i).getTitulo());
                }
                int escolha = entrada.nextInt();
                if (escolha < 1 || escolha > itens.size()) {
                    System.out.println("Item inválido!");
                    continue;
                }

                ItemBiblioteca itemEscolhido = itens.get(escolha - 1);
                Exemplar exemplar = null;

                if (itemEscolhido instanceof LivroDigital) {
                    exemplar = ((LivroDigital) itemEscolhido).buscarExemplarDisponivel();
                } else if (itemEscolhido instanceof Livro) {
                	exemplar = ((Livro) itemEscolhido).buscarExemplarDisponivel();
                } else if (itemEscolhido instanceof Revista) {
                    exemplar = ((Revista) itemEscolhido).buscarExemplarDisponivel();
                }

                if (exemplar == null) {
                    System.out.println("Item indisponível!");

                } else if (opcao == 2) {
                    System.out.println("Item disponível!");
                } else {
                    Emprestimo emprestimo = new Emprestimo(usuario, exemplar, "18/11/2026");
                    System.out.println("Empréstimo realizado!");
                }
            }
            
            else if (opcao == 4) {
            	Arquivo.salvarItens(itens);
            }

        } while (opcao != 0);

        Arquivo.salvarItens(itens);
        entrada.close();
    }
}
