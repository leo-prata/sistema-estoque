package trabalhoPratico;

import trabalhoPratico.view.TelaLogin;
import trabalhoPratico.view.TelaProduto;

public class SistemaEstoque {

    public static void main(String[] args) {
        TelaLogin login = new TelaLogin();
        login.draw();
        TelaProduto produto = new TelaProduto();
        produto.produtoJanela();
    }
}
