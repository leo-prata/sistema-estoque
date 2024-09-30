/*
    FILIPE MOREIRA VIDAL - 202365510B
    LEONARDO PEREIRA DE FARIA PRATA  - 202365553C
    VICTOR ALBINO BRANDÃO SILVA - 202365558C
*/

package trabalhoPratico.view;

import javax.swing.*;

import java.awt.*;
import trabalhoPratico.controller.CancelaEdicoesLote;
import trabalhoPratico.controller.SalvaEdicoesLote;
import trabalhoPratico.model.Produto;
import net.miginfocom.swing.MigLayout;

public class TelaInformacoesLote {

    private JFrame tela;
    private JTextField tfQuantidade;
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
        int quantidade;
        try{
            quantidade = Integer.parseInt(tfQuantidade.getText());
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(tela, "Por favor, insira somente números",
             "ERRO", JOptionPane.ERROR_MESSAGE);
             return;
        }

        if(quantidade < 0){
            JOptionPane.showMessageDialog(tela, "Números negativos são inválidos",
             "ERRO", JOptionPane.ERROR_MESSAGE);
             return;
        }
        
        if(quantidade == 0){
            produto.removeLote(linha);
            telaProduto.atualiza();
            tela.dispose();
            return;
        }

        produto.getQuantity().set(linha, quantidade);
        telaProduto.atualiza();
        tela.dispose();
    }

    public void cancela()
    {
        tela.dispose();
    }
}
