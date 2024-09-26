package trabalhoPratico.view;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.text.ParseException;
import trabalhoPratico.controller.CancelaEdicoesLote;
import trabalhoPratico.controller.SalvaEdicoesLote;
import trabalhoPratico.model.Produto;
import net.miginfocom.swing.MigLayout;

public class TelaInformacoesLote {

    private JFrame tela;
    private JTextField tfQuantidade;
    private JFormattedTextField tfLote;
    private JFormattedTextField tfValidade;

    private Produto produto;
    private int linha;
    private TelaProduto telaProduto;

    public TelaInformacoesLote(Produto produto, int linha, TelaProduto telaProduto)
    {
        this.produto = produto;
        this.linha = linha;
        this.telaProduto = telaProduto;
    }

    public void draw()
    {
        tela = new JFrame("Lote: " + produto.getLote().get(linha));
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tela.getContentPane().setLayout(new MigLayout("center, center, fillx"));
        tela.setPreferredSize(new Dimension(540, 100));
        tela.setVisible(true);

        drawFields();
        drawButtons();

        tela.pack();
    }

    public void drawFields()
    {
        Font fontTexto = new Font("sans-serif", Font.PLAIN, 15);

        JLabel lbQuantidade = new JLabel("Quantidade:");
        lbQuantidade.setFont(fontTexto);
        lbQuantidade.setPreferredSize(new Dimension(60,20));
        tfQuantidade = new JTextField();
        tfQuantidade.setPreferredSize(new Dimension(100, 20));
        tfQuantidade.setFont(fontTexto);
        tfQuantidade.setText(produto.getQuantity().get(linha).toString());

        tela.getContentPane().add(lbQuantidade);
        tela.getContentPane().add(tfQuantidade);
    }

    public void drawButtons()
    {
        Font fontButton = new Font("sans-serif", Font.BOLD, 15);

        JButton jbCancelar = new JButton("Cancelar");
        jbCancelar.setPreferredSize(new Dimension(120,30));
        jbCancelar.setFont(fontButton);
        jbCancelar.addActionListener(new CancelaEdicoesLote(this));
        tela.getContentPane().add(jbCancelar);
        
        JButton jbSalvar = new JButton("Salvar");
        jbSalvar.setPreferredSize(new Dimension(120,30));
        jbSalvar.setFont(fontButton);
        jbSalvar.addActionListener(new SalvaEdicoesLote(this));
        tela.getContentPane().add(jbSalvar);
    }

    public void salva()
    {
        produto.getQuantity().set(linha, Integer.parseInt(tfQuantidade.getText()));
        telaProduto.atualizaLinha(linha);
        tela.dispose();
    }

    public void cancela()
    {
        tela.dispose();
    }
}
