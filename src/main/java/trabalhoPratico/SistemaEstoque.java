package trabalhoPratico;

import java.util.List;
import trabalhoPratico.exception.CpfException;
import trabalhoPratico.exception.EmptyStrException;
import trabalhoPratico.model.Produto;
import trabalhoPratico.persistence.ProdutoPersistence;
import trabalhoPratico.view.*;
import trabalhoPratico.controller.GerenciarFuncionarios;
import trabalhoPratico.controller.GerenciarCategoria;

import trabalhoPratico.persistence.Archive;

public class SistemaEstoque {

    public static void main(String[] args) throws CpfException, NumberFormatException, EmptyStrException {
       TelaLogin login = new TelaLogin();
       login.draw();
    }
}
