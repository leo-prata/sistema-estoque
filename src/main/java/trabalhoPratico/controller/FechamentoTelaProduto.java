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
