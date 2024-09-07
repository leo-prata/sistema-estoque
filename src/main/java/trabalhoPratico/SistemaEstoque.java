package trabalhoPratico;

import java.util.ArrayList;
import java.util.List;
import trabalhoPratico.exception.CpfException;
import trabalhoPratico.exception.EmptyStrException;
import trabalhoPratico.model.Cpf;
import trabalhoPratico.model.Funcionario;
import trabalhoPratico.persistence.FuncionarioPersistence;
import trabalhoPratico.view.TelaLogin;

public class SistemaEstoque {

    public static void main(String[] args) throws CpfException, NumberFormatException, EmptyStrException {
        TelaLogin login = new TelaLogin();
        login.draw();
    }
}
