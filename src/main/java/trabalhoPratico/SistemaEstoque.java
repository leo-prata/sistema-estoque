package trabalhoPratico;

import java.util.List;
import trabalhoPratico.exception.CpfException;
import trabalhoPratico.exception.EmptyStrException;
import trabalhoPratico.model.Produto;
import trabalhoPratico.persistence.ProdutoPersistence;
import trabalhoPratico.view.*;

public class SistemaEstoque {

    public static void main(String[] args) throws CpfException, NumberFormatException, EmptyStrException {
//        TelaLogin login = new TelaLogin();
//        login.draw();

        TelaProduto produto = new TelaProduto();
        ProdutoPersistence prodPersis = new ProdutoPersistence();
        List<Produto> lista = prodPersis.read();
        produto.draw(lista.get(1));
        
    }
}
