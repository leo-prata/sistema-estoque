package trabalhoPratico.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import trabalhoPratico.view.TelaInformacoesLote;

public class CancelaEdicoesLote implements ActionListener{
    TelaInformacoesLote tela;

    public CancelaEdicoesLote(TelaInformacoesLote tela)
    {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tela.cancela();
    }
}
