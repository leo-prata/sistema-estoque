package trabalhoPratico;

import trabalhoPratico.exception.CpfException;
import trabalhoPratico.exception.EmptyStrException;
import trabalhoPratico.view.*;

public class SistemaEstoque {

    public static void main(String[] args) throws CpfException, NumberFormatException, EmptyStrException {
        TelaLogin login = new TelaLogin();
        login.draw();

    }
}
