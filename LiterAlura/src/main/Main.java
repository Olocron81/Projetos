
package main;

import model.Livro;
import service.CatalogoService;
import client.HttpClientGutendex;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.exibirMenu();
    }

    public void run(String... args) throws Exception {
        exibirMenu();
    }

    private void exibirMenu() {
        CatalogoService catalogoService = new CatalogoService();
        HttpClientGutendex client = new HttpClientGutendex();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar livro ao catálogo");
            System.out.println("2. Listar livros no catálogo");
            System.out.println("3. Buscar livros na API Gutendex");
            System.out.println("4. Listar todos os livros buscados");
            System.out.println("5. Listar livros por idioma");
            System.out.println("6. Listar todos os autores");
            System.out.println("7. Listar autores vivos em determinado ano");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Digite o ISBN do livro: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Digite o ano de publicação do livro: ");
                    int anoPublicacao = scanner.nextInt();
                    scanner.nextLine();

                    Livro livro = new Livro(titulo, autor, isbn, anoPublicacao);
                    catalogoService.adicionarLivro(livro);
                    System.out.println("Livro adicionado com sucesso!");
                    break;

                case 2:
                    System.out.println("Listando livros no catálogo:");
                    catalogoService.listarLivros();
                    break;

                case 3:
                    System.out.print("Digite o termo de busca: ");
                    String query = scanner.nextLine();
                    try {
                        client.getBooksData(query);
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Erro ao buscar livros na API Gutendex: " + e.getMessage());
                    }
                    break;

                case 4:
                    client.listarTodosLivros();
                    break;

                case 5:
                    System.out.print("Digite o idioma para listar os livros: ");
                    String idioma = scanner.nextLine();
                    client.listarLivrosPorIdioma(idioma);
                    break;

                case 6:
                    client.listarAutores();
                    break;

                case 7:
                    System.out.print("Digite o ano para listar autores vivos: ");
                    int ano = scanner.nextInt();
                    client.listarAutoresVivosEmAno(ano);
                    break;

                case 8:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;

            }
        }
    }
}
