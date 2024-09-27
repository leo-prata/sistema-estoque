package trabalhoPratico.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import trabalhoPratico.view.TelaInformacoesLote;

public class SalvaEdicoesLote implements ActionListener {

    private TelaInformacoesLote tela;

    public SalvaEdicoesLote(TelaInformacoesLote tela)
    {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tela.salva();
    }
}
