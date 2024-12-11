
package service;
import model.Livro;
import java.util.ArrayList;
import java.util.List;

public class CatalogoService {
    private List<Livro> catalogo;

    public CatalogoService() {
        this.catalogo = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        catalogo.add(livro);
    }

    public void listarLivros() {
        for (Livro livro : catalogo) {
            System.out.println(livro);
        }

    }
}