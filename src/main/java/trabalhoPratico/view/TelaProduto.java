package trabalhoPratico.view;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Victor Brandão
 */
public class TelaProduto {
    private JFrame telaTabela;
    private JPanel painel;
    private String nomeProduto; // linha provisoria, sera removida quando tivermos os produtos
    private final int WIDTH = 1000;
    private final int HEIGHT = 1000;
    
    private JTextField text;
        
    public void produtoJanela(){
        telaTabela = new JFrame("Itens em Estoque");
        telaTabela.setVisible(true);
        telaTabela.setSize(WIDTH, HEIGHT);
        telaTabela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaTabela.setLocationRelativeTo(null);
        
        produtoFormato();
        telaTabela.pack( ); 
    }
    public void produtoFormato(){
        this.nomeProduto = "Sabao em po Tixan Ype";
        painel = new JPanel();
        painel.setSize(WIDTH, HEIGHT);
        telaTabela.getContentPane().add (painel);
        painel.setPreferredSize(new Dimension(400, 700));
        painel.setMaximumSize(new Dimension(400, 700));
        
        Font font = new Font("Arial", Font.PLAIN, 15);
        
        JLabel lbProduto = new JLabel("Produto: ");
        lbProduto.setBounds(20, 20, 100, 20);
        lbProduto.setFont(font);
        painel.add(lbProduto);
        
        
        JLabel lbNomeProduto = new JLabel(nomeProduto);
        lbNomeProduto.setBounds(20, 20, 100, 20);
        lbProduto.setFont(font);
        painel.add(lbNomeProduto);
        
        
        
    }
    
}
