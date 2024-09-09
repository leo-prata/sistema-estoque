package trabalhoPratico.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import trabalhoPratico.view.TelaTabelaProdutos;

public class AbrirInformacoesProduto implements MouseListener {

    private TelaTabelaProdutos tela;

    public AbrirInformacoesProduto(TelaTabelaProdutos tela)
    {
        this.tela = tela;
    }

    
    @Override
    public void mousePressed(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        if(e.getClickCount() == 2 && table.getSelectedRow() != -1)
            tela.informacoesProduto();    
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

}
