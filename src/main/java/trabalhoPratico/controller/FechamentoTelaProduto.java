/*
    FILIPE MOREIRA VIDAL - 202365510B
    LEONARDO PEREIRA DE FARIA PRATA  - 202365553C
    VICTOR ALBINO BRANDÃO SILVA - 202365558C
*/

package trabalhoPratico.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import trabalhoPratico.view.TelaTabelaProdutos;

public class FechamentoTelaProduto implements WindowListener{

    private TelaTabelaProdutos tela;

    public FechamentoTelaProduto(TelaTabelaProdutos tela)
    {
        this.tela = tela;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        tela.salvaListaProdutos();
        tela.atualizaTabela();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
