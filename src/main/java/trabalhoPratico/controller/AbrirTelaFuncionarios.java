/*
    FILIPE MOREIRA VIDAL - 202365510B
    LEONARDO PEREIRA DE FARIA PRATA  - 202365553C
    VICTOR ALBINO BRANDÃO SILVA - 202365558C
*/

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
