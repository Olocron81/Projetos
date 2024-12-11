package client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Author;
import model.Book;
import model.GutendexResponse;
import java.util.ArrayList;
import java.util.List;

public class HttpClientGutendex {

    private static final String BASE_URL = "https://gutendex.com/books/";
    private List<Book> catalogoLivros = new ArrayList<>();
    private List<Author> listaAutores = new ArrayList<>();

    public void getBooksData(String query) throws IOException, InterruptedException {
        // Configurando o cliente HTTP com timeout de conexão
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(java.time.Duration.ofSeconds(10))
                .build();

        // Criando a requisição HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?search=" + query))
                .header("Accept", "application/json")
                .timeout(java.time.Duration.ofMinutes(1))
                .GET()
                .build();

        // Enviando a requisição e obtendo a resposta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Verificar a resposta HTTP
        int statusCode = response.statusCode();
        System.out.println("Status Code: " + statusCode);
        System.out.println("Headers: " + response.headers().map());

        // Processar JSON com Jackson se o status for 200
        if (statusCode == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = response.body();
            GutendexResponse gutendexResponse = objectMapper.readValue(jsonResponse, GutendexResponse.class);
            List<Book> books = gutendexResponse.getResults();

        // Adicionar o primeiro livro ao catálogo
            if (!books.isEmpty()) {
                Book firstBook = books.get(0);
                catalogoLivros.add(firstBook);
                // Adicionar o primeiro autor da lista ao catálogo de autores
                if (!firstBook.getAuthors().isEmpty()) {
                    Author firstAuthor = firstBook.getAuthors().get(0);
                    listaAutores.add(firstAuthor);
                } System.out.println("Livro adicionado ao catálogo: " + firstBook);
            } else {
                System.out.println("Nenhum livro encontrado.");
            }
        } else if (statusCode == 404) {
            System.out.println("Recurso não encontrado.");
        } else {
            System.out.println("Erro: " + statusCode); }
    }

    public void listarTodosLivros() {
        if (catalogoLivros.isEmpty()) {
            System.out.println("O catálogo está vazio.");
        } else {
            System.out.println("Listagem de todos os livros:");
            for (Book book : catalogoLivros) {
                System.out.println(book);
            }
        }
    }

    public void listarLivrosPorIdioma(String idioma) {
        System.out.println("Listagem de livros no idioma: " + idioma);
        for (Book book : catalogoLivros) {
            if (!book.getLanguages().isEmpty() && book.getLanguages().get(0).equalsIgnoreCase(idioma)) {
                System.out.println(book);
            }
        }
    }

    public void listarAutores() {
        if (listaAutores.isEmpty()) {
            System.out.println("Não há autores disponíveis.");
        } else {
            System.out.println("Listagem de todos os autores:");
            for (Author author : listaAutores) {
                System.out.println(author);
            }
        }
    }

    public void listarAutoresVivosEmAno(int ano) {
        System.out.println("Listagem de autores vivos no ano: " + ano);
        for (Author author : listaAutores) {
            if (author.getBirthYear() <= ano && (author.getDeathYear() == 0 || author.getDeathYear() >= ano)) {
                System.out.println(author);
            }
        }
    }
}


