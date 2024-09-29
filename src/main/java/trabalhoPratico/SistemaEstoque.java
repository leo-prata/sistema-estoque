package trabalhoPratico;

import trabalhoPratico.exception.CpfException;
import trabalhoPratico.exception.EmptyStrException;
import trabalhoPratico.view.*;
import trabalhoPratico.controller.GerenciarFuncionarios;
import trabalhoPratico.controller.GerenciarCategoria;

import trabalhoPratico.persistence.Archive;

public class SistemaEstoque {

    public static void main(String[] args) throws CpfException, NumberFormatException, EmptyStrException {
        //TelaLogin login = new TelaLogin();
        //login.draw();
        // TelaFuncionario tela = new TelaFuncionario();
        // GerenciarFuncionarios gerenciador = new GerenciarFuncionarios(tela);
        // tela.addWindowListener(gerenciador);
        // tela.setVisible(true);

        TelaCategoria tela = new TelaCategoria();
        GerenciarCategoria gerenciador = new GerenciarCategoria(tela);
        tela.addWindowListener(gerenciador);
        tela.setVisible(true);
      
    }
}
