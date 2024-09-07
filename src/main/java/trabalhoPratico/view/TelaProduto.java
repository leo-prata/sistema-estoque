package trabalhoPratico.view;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Victor Brandão
 */
public class TelaProduto {
    private JFrame telaTabela;
    private JPanel panelTabela;
    private final int WIDTH = 500;
    private final int HEIGHT = 650;
    
    // linha provisoria, sera removida quando tivermos os produtos
    private String nomeProduto; 
    private String precoProduto;
    private String tipoProduto;
    
        
    public void draw()
    {
        telaTabela = new JFrame("Itens em Estoque");
//        telaTabela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaTabela.setLayout(null);
        telaTabela.setSize(WIDTH, HEIGHT);
        telaTabela.setVisible(true);
        telaTabela.setLocationRelativeTo(null);
//        telaTabela.setResizable(false);
        
        drawInput();
        
    }
    public void drawInput()
    {
        this.nomeProduto = "Sabao em po Tixan Ype";
        this.precoProduto = "R$ 100.00";
        this.tipoProduto = "Perecivel";
        
        Font fontTexto = new Font("sans-serif", Font.PLAIN, 15);
        Font fontButton = new Font("sans-serif", Font.BOLD, 15);
        
        
        JLabel lbProduto = new JLabel("Produto: "+ nomeProduto);
        lbProduto.setFont(fontTexto);
        lbProduto.setBounds(20, 20, 250, 20);
        
        
        JLabel lbPreco = new JLabel("Preco: " + precoProduto);
        lbPreco.setBounds(20, 45, 150, 20);
        lbPreco.setFont(fontTexto);
        
        
        JLabel lbTipo = new JLabel("Tipo: " + tipoProduto);
        lbTipo.setBounds(200, 45, 200, 20);
        lbTipo.setFont(fontTexto);
        
        
        JPanel panelInfo = new JPanel();
//        panelInfo.setBackground(Color.red);
        panelInfo.setBorder(BorderFactory.createLineBorder(Color.black));
        panelInfo.setBounds(0, 0, 500, 80);
        panelInfo.setLayout(null);
//        panelLabel.setPreferredSize(new Dimension(500, 20));
//        panelLabel.setMaximumSize(new Dimension(500, 20));
        panelInfo.add(lbProduto);
        panelInfo.add(lbPreco);
        panelInfo.add(lbTipo);
        telaTabela.getContentPane().add(panelInfo);
        
        
        
        
        //Tabela de Produtos
        JLabel lbFuturaTabela = new JLabel("Espaco para a tabela");
        lbFuturaTabela.setBounds(80, 40, 300, 100);
        
        panelTabela = new JPanel();
//        panelTabela.setBackground(Color.BLUE);
        panelTabela.setBorder(BorderFactory.createLineBorder(Color.black));
        panelTabela.setBounds(0, 80, 500, 300);
        panelTabela.setLayout(null);
        panelTabela.add(lbFuturaTabela);
        telaTabela.getContentPane().add (panelTabela);
        
//        panel.setSize(500, 500);
//        panel.setPreferredSize(new Dimension(500, 400));
//        panel.setMaximumSize(new Dimension(500, 400));


        JButton butAdiciona = new JButton("Adicionar");
        butAdiciona.setBounds(110, 40, 120, 30);
        butAdiciona.setFont(fontButton);
        
        JButton butEditar = new JButton("Editar");
        butEditar.setBounds(270, 40, 120, 30);
        butEditar.setFont(fontButton);
        
        JButton butRemove = new JButton("Remover Produto");
        butRemove.setBounds(110, 90, 280, 30);
        butRemove.setFont(fontButton);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setBorder(BorderFactory.createLineBorder(Color.black));
        panelBotoes.setBounds(0, 380, 500, 200);
//        panelBotoes.setBackground(Color.green);
        panelBotoes.setLayout(null);
        panelBotoes.add(butAdiciona);
        panelBotoes.add(butEditar);
        panelBotoes.add(butRemove);
        telaTabela.getContentPane().add(panelBotoes);
        
    }
    
}
