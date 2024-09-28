/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhoPratico.view;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import net.miginfocom.swing.MigLayout;
import trabalhoPratico.exception.EmptyStrException;
import trabalhoPratico.exception.InvalidDataException;
import trabalhoPratico.exception.NegativeNumberException;
import trabalhoPratico.model.Produto;

/**
 *
 * @author Victor Brandão
 */
public class TelaNovoProduto {
    private final int WIDTH = 560;
    private final int HEIGHT = 680;
    private Produto novoProduto;
    private JFrame telaAdiciona;
    private JPanel panel;
    private JPanel panelButtons;
    private int quantidade;
    private JTextField TextNome;
    private JTextField TextTipo;
    private JTextField TextPreco;
    private JTextField TextQuant;
    private JFormattedTextField TextLote;
    private JFormattedTextField validade;
    
    private TelaProduto telaProduto;
    
    
    public void draw(){
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
        
        JLabel JNome = new JLabel("Nome: ");
        JNome.setFont(fontTexto);
        TextNome = new JTextField();
        TextNome.setPreferredSize(new Dimension(100, 20));
        TextNome.setFont(fontTexto);
        
        
        JLabel JTipo = new JLabel("Tipo:");
        JTipo.setFont(fontTexto);
        JTipo.setPreferredSize(new Dimension(60,20));
        TextTipo = new JTextField();
        TextTipo.setPreferredSize(new Dimension(100, 20));
        TextTipo.setFont(fontTexto);
        
        
        JLabel JPreco = new JLabel("Preco: ");
        JPreco.setFont(fontTexto);
        JPreco.setPreferredSize(new Dimension(60,20));
        TextPreco = new JTextField();
        TextPreco.setFont(fontTexto);
        TextPreco.setPreferredSize(new Dimension(100,20));
        
        
        JLabel JQuant = new JLabel("Quantidade:");
        JQuant.setFont(fontTexto);
        JQuant.setPreferredSize(new Dimension(60,20));
        TextQuant = new JTextField();
        TextQuant.setPreferredSize(new Dimension(100, 20));
        TextQuant.setFont(fontTexto);
        
        
        JLabel JLote = new JLabel("Lote:");
        JLote.setFont(fontTexto);
        JLote.setPreferredSize(new Dimension(60,20));
        try {
            MaskFormatter lote = new MaskFormatter("#####");
            TextLote = new JFormattedTextField(lote);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Lote invalido", "Atenção", JOptionPane.ERROR_MESSAGE);
        }
        TextLote.setPreferredSize(new Dimension(100, 20));
        TextLote.setFont(fontTexto);
        
        
        JLabel JValid = new JLabel("Validade:");
        JValid.setFont(fontTexto);
        JValid.setPreferredSize(new Dimension(60,20));
        try {
            MaskFormatter date = new MaskFormatter("##/##/####");
            validade = new JFormattedTextField(date);
        } catch (ParseException e){
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
        panel.add(TextQuant, "wrap");
        
        panel.add(JLote);
        panel.add(TextLote, "wrap");
        
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
//      Prduto que recebe as informações e vai ser adicionado na lista
        novoProduto = new Produto();

        try {
            novoProduto.setName(TextNome.getText());
        } catch (EmptyStrException e) {
            JOptionPane.showMessageDialog(telaAdiciona, "O campo \"Nome\" deve ser preenchido",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            novoProduto.setCategory(TextTipo.getText());
        } catch (EmptyStrException e) {
            JOptionPane.showMessageDialog(telaAdiciona, "O campo \"Tipo\" deve ser preenchido",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try{
           novoProduto.setPrice(Double.valueOf(TextPreco.getText())); 
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(telaAdiciona, "O campo \"Preco\" aceita apenas números",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        } catch(NegativeNumberException e){
            JOptionPane.showMessageDialog(telaAdiciona, "Insira um valor positivo",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        try{
            novoProduto.setQuantity(Integer.parseInt(TextQuant.getText()));
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(telaAdiciona, "O campo \"quantidade\" aceita apenas números",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        } catch(NegativeNumberException e){
            JOptionPane.showMessageDialog(telaAdiciona, "Insira uma quantidade positiva",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            novoProduto.setLote(TextLote.getText());
        } catch (EmptyStrException e) {
            JOptionPane.showMessageDialog(telaAdiciona, "O campo \"Lote\" deve ser preenchido",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        try {
            novoProduto.setValidade(validade.getText());
        } catch (EmptyStrException e) {
            JOptionPane.showMessageDialog(telaAdiciona, "O campo \"validade\" deve ser preenchido",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (InvalidDataException e) {
            JOptionPane.showMessageDialog(telaAdiciona, "Data inválida",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
            escreve();
            JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
            telaAdiciona.dispose();
    }

    public void escreve() {
        System.out.println("TelaNovoProduto{" + ", TextNome=" + novoProduto.getName() + ", TextTipo=" + novoProduto.getCategory() + ", TextPreco=" + novoProduto.getPrice() + ", TextQuant=" + novoProduto.getQuantity() + ", TextLote=" + novoProduto.getLote() + ", validade=" + novoProduto.getValidade() + '}');
    }
}
