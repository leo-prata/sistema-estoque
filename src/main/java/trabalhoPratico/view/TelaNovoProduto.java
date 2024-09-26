/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhoPratico.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import net.miginfocom.swing.MigLayout;
import trabalhoPratico.model.Produto;

/**
 *
 * @author Victor Brandão
 */
public class TelaNovoProduto {
    private final int WIDTH = 560;
    private final int HEIGHT = 680;
    private Produto infoProdut;
    private JFrame telaAdiciona;
    private JPanel panel;
    private JPanel panelButtons;
    private JLabel JNome;
    private JLabel JTipo;
    private JLabel JPreco;
    private int quantidade;
    private JTextField TextPreco;
    private JFormattedTextField JTextQuant;
    private JFormattedTextField JTextLote;
    private JFormattedTextField validade;
    
    private TelaProduto telaProduto;
    
    
    public void draw(Produto produto, TelaProduto telaProduto){
        infoProdut = produto;
        this.telaProduto = telaProduto;
        telaAdiciona = new JFrame("Novo Produto");
        telaAdiciona.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaAdiciona.setSize(500,500);
        telaAdiciona.setLayout(new MigLayout("top, center"));
        telaAdiciona.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        telaAdiciona.setLocationRelativeTo(null);
        telaAdiciona.setVisible(true);
        drawTela();
        
    }
    
    private void drawTela(){
        
        
        
        Font fontTexto = new Font("sans-serif", Font.PLAIN, 15);
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(360, 250));
        panel.setLayout(new MigLayout(
                "",
                "40[]25[]",
                "40[]15[]15[]15[]15[]15[]"));
        
        JNome = new JLabel("Nome: ");
        JNome.setFont(fontTexto);
        JTextField  TextNome = new JTextField();
        TextNome.setPreferredSize(new Dimension(100, 20));
        TextNome.setFont(fontTexto);
        
        
        JTipo = new JLabel("Tipo:");
        JTipo.setFont(fontTexto);
        JTipo.setPreferredSize(new Dimension(60,20));
        JTextField  TextTipo = new JTextField();
        TextTipo.setPreferredSize(new Dimension(100, 20));
        TextTipo.setFont(fontTexto);
        
        
        JPreco = new JLabel("Preco: ");
        JPreco.setFont(fontTexto);
        JPreco.setPreferredSize(new Dimension(60,20));
        TextPreco = new JTextField();
        TextPreco.setFont(fontTexto);
        TextPreco.setPreferredSize(new Dimension(100,20));
        
        
        JLabel JQuant = new JLabel("Quantidade:");
        JQuant.setFont(fontTexto);
        JQuant.setPreferredSize(new Dimension(60,20));
        try {
            MaskFormatter Quant = new MaskFormatter("###");
            JTextQuant = new JFormattedTextField(Quant);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Quantidade invalida", "Atenção", JOptionPane.ERROR_MESSAGE);
        }
        JTextQuant.setPreferredSize(new Dimension(100, 20));
        JTextQuant.setFont(fontTexto);
        
        
        JLabel JLote = new JLabel("Lote:");
        JLote.setFont(fontTexto);
        JLote.setPreferredSize(new Dimension(60,20));
        try {
            MaskFormatter lote = new MaskFormatter("#####");
            JTextLote = new JFormattedTextField(lote);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Lote invalido", "Atenção", JOptionPane.ERROR_MESSAGE);
        }
        JTextLote.setPreferredSize(new Dimension(100, 20));
        JTextLote.setFont(fontTexto);
        
        
        JLabel JValid = new JLabel("Validade:");
        JValid.setFont(fontTexto);
        JValid.setPreferredSize(new Dimension(60,20));
        
        try {
            MaskFormatter date = new MaskFormatter("##/##/####");
            validade = new JFormattedTextField(date);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Data Invalida", "Atenção", JOptionPane.ERROR_MESSAGE);
        }
        
        validade.setPreferredSize(new Dimension(100, 20));
        validade.setFont(fontTexto);
        
        panel.add(JNome);
        panel.add(TextNome, "span");
        
        panel.add(JTipo);
        panel.add(TextTipo, "span");
        
        panel.add(JPreco);
        panel.add(TextPreco, "span");
        
        panel.add(JQuant);
        panel.add(JTextQuant, "wrap");
        
        panel.add(JLote);
        panel.add(JTextLote, "wrap");
        
        panel.add(JValid);
        panel.add(validade, "wrap");
        
        
        drawButtons();
        
        
        telaAdiciona.getContentPane().add(panel,"wrap");
        telaAdiciona.getContentPane().add(panelButtons);
    }
    private void drawButtons() {
        Font fontButton = new Font("sans-serif", Font.BOLD, 15);
        
        panelButtons = new JPanel();
        panelButtons.setPreferredSize(new Dimension(360, 100));
        panelButtons.setLayout(new MigLayout(
                "",
                "40[]40[]",
                "20[]"));
        
        JButton cancela = new JButton("Cancelar");
        cancela.setPreferredSize(new Dimension(120,30));
        cancela.setFont(fontButton);
        
        JButton adiciona = new JButton("Adicionar");
        adiciona.setPreferredSize(new Dimension(120,30));
        adiciona.setFont(fontButton);
        
        adiciona.addActionListener(this::adicionarActionPerfomed);
        cancela.addActionListener(this::fecharActionPerfomed);
        
        panelButtons.add(cancela);
        panelButtons.add(adiciona);
    }
    
    public void fecharActionPerfomed(ActionEvent e) {                                         
       	telaAdiciona.dispose();
    }

    public void adicionarActionPerfomed(ActionEvent x){
        if(!(TextPreco.getText().isEmpty()) ){
            
            try{
                int preco = Integer.parseInt(TextPreco.getText());
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Preco invalida", "Atenção", JOptionPane.ERROR_MESSAGE);
            }

            infoProdut.setPrice(TextPreco.getText());
            quantidade = Integer.parseInt(JTextQuant.getText());
            infoProdut.setQuantity(quantidade);
            infoProdut.setLote(JTextLote.getText());
            infoProdut.setValidade(validade.getText());
            telaProduto.atualizaTabela();
            JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
            telaAdiciona.dispose();
        }
        

     
        
    }
}
