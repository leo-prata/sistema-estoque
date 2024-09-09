package trabalhoPratico.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import trabalhoPratico.view.TelaTabelaProdutos;

public class AbrirTelaFuncionarios implements ActionListener {

    private TelaTabelaProdutos tela;

    public AbrirTelaFuncionarios(TelaTabelaProdutos tela)
    {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tela.telaFuncionarios();
    }

}
