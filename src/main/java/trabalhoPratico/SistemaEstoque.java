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
//        TelaLogin login = new TelaLogin();
//        login.draw();
        Funcionario funcionario1 = new Funcionario(
                "admin", "21267489014", "admin", "Gerente");
        List<Funcionario> listaFuncionarios = new ArrayList();
        listaFuncionarios.add(funcionario1);
        
        FuncionarioPersistence persistence = new FuncionarioPersistence();
        persistence.save(listaFuncionarios);
        List<Funcionario> lista2 = persistence.read();
        
        for(Funcionario func: lista2)
        {
            System.out.println(func.getName());
        }
    }
}
