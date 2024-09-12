package trabalhoPratico.view;

import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Victor Brandão
 */
public class TelaProduto {
    private JFrame telaTabela;
    private JPanel panelTabela;
    private final int WIDTH = 560;
    private final int HEIGHT = 710;
    
    // linha provisoria, sera removida quando tivermos os produtos
    private String nomeProduto; 
    private String precoProduto;
    private String tipoProduto;
    
        
    public void draw()
    {
        telaTabela = new JFrame("Produto");
        telaTabela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaTabela.setLayout(new MigLayout("top, center"));
        telaTabela.setSize(WIDTH, HEIGHT);
        telaTabela.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        telaTabela.setVisible(true);
        telaTabela.setLocationRelativeTo(null);
//        telaTabela.setResizable(false);
        
        drawInput();
        telaTabela.pack();
    }
    public void drawInput()
    {
        this.nomeProduto = "Sabao em po Tixan Ype";
        this.precoProduto = "R$ 100.00";
        this.tipoProduto = "Perecivel";
        
        Font fontTexto = new Font("sans-serif", Font.PLAIN, 15);
        Font fontButton = new Font("sans-serif", Font.BOLD, 15);
        
        
        
        JPanel back = new JPanel();
//        back.setSize(WIDTH, HEIGHT);
        back.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        back.setLayout(new MigLayout("top, center"));
        
        
        JLabel lbProduto = new JLabel("Produto: "+ nomeProduto);
        lbProduto.setFont(fontTexto);
        lbProduto.setPreferredSize(new Dimension(250, 20));
        
        
        JLabel lbPreco = new JLabel("Preco: " + precoProduto);
//        lbPreco.setBounds(20, 45, 150, 20);
        lbPreco.setFont(fontTexto);
        lbPreco.setPreferredSize(new Dimension(150, 20));
        
        
        JLabel lbTipo = new JLabel("Tipo: " + tipoProduto);
//        lbTipo.setBounds(200, 45, 200, 20);
        lbTipo.setFont(fontTexto);
        lbTipo.setPreferredSize(new Dimension(200, 20));
        
        
        JPanel panelInfo = new JPanel();
        panelInfo.setBorder(BorderFactory.createLineBorder(Color.black));
        panelInfo.setPreferredSize(new Dimension(500,80));
        panelInfo.setLayout(new MigLayout("top, left, fill"));
        panelInfo.add(lbProduto, "wrap");
        panelInfo.add(lbPreco);
        panelInfo.add(lbTipo);
        back.add(panelInfo, "wrap");
        
        
        
        
        
        
        //Tabela de Produtos
        Object[][] dados = {
            {"5", "984542", "09/09/2024"},
            {"3", "345276", "02/10/2024"},
            {"12", "945257", "17/10/2024"}  
        };
        Object[] columnNames = {"Quantidade", "Lote", "Validade"};
//        
        JTable table = new JTable(dados, columnNames);
//        
        JScrollPane barraRolagem = new JScrollPane(table);
        
        
        barraRolagem.setPreferredSize(new Dimension(500,200));
        
//        panelTabela = new JPanel();
//        
//        panelTabela.setPreferredSize(new Dimension(500, 300));
//        panelTabela.setLayout(new MigLayout());
//        panelTabela.add(table);
//        panelTabela.add(barraRolagem);
        back.add(barraRolagem, "wrap"); 
        
//        panel.setSize(500, 500);
//        panel.setPreferredSize(new Dimension(500, 400));
//        panel.setMaximumSize(new Dimension(500, 400));




        JButton butAdiciona = new JButton("Adicionar");
        butAdiciona.setPreferredSize(new Dimension(120,30));
        butAdiciona.setFont(fontButton);
        
        JButton butEditar = new JButton("Editar");
        butEditar.setPreferredSize(new Dimension(120, 30));
        butEditar.setFont(fontButton);
        
        JButton butRemove = new JButton("Remover Produto");
        butRemove.setPreferredSize(new Dimension(280, 30));
        butRemove.setFont(fontButton);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setBorder(BorderFactory.createLineBorder(Color.black));
        panelBotoes.setPreferredSize(new Dimension(500,200));
//        panelBotoes.setBackground(Color.green);
        panelBotoes.setLayout(new MigLayout(
        "",
        "110[]40[]",
        "40[]40[]"
        ));
        panelBotoes.add(butAdiciona);
        panelBotoes.add(butEditar, "wrap");
        panelBotoes.add(butRemove, "span");
        back.add(panelBotoes);
        
        telaTabela.getContentPane().add(back);
        
    }
}