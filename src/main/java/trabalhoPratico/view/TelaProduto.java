package trabalhoPratico.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;
import trabalhoPratico.model.Produto;
import trabalhoPratico.persistence.ProdutoPersistence;
import trabalhoPratico.view.Tela;

/**
 *
 * @author Victor Brandão
 */
public class TelaProduto implements ActionListener{
    private JFrame telaTabela;
    private JPanel panelTabela;
    private final int WIDTH = 560;
    private final int HEIGHT = 680;
    private java.util.List<Produto> listaProdutos;
    
        
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
        String nome = "Sabao em po Ype";
        String preco= "R$ 300,00";
        String tipo= "Produto de limpeza";
        ProdutoPersistence prodPersis = new ProdutoPersistence();
        
        listaProdutos = prodPersis.read();
        
//        crie uma lista de objetos que tenham o mesmo nome 
//        essa lista será passada para nossas coluunas da tela
        
        Object[][] rowData = new Object[listaProdutos.size()][3];
        int cont = 0;
        for(Produto produto : listaProdutos)
        {
            if(nome.equals(produto.getName()))
            {
                rowData[cont++] = produto.getProdutoProperties();
            }        
        }
        
        
        Font fontTexto = new Font("sans-serif", Font.PLAIN, 15);
        
        
        
        
        JPanel back = new JPanel();
        back.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        back.setLayout(new MigLayout("top, center"));
        
        
        JLabel lbProduto = new JLabel("Produto: "+ nome);
        lbProduto.setFont(fontTexto);
        lbProduto.setPreferredSize(new Dimension(250, 20));
        
        
        JLabel lbPreco = new JLabel("Preco: " + preco);
        lbPreco.setFont(fontTexto);
        lbPreco.setPreferredSize(new Dimension(150, 20));
        
        
        JLabel lbTipo = new JLabel("Tipo: " + tipo);
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
        
        
        
        
        Object[] columnNames = {"Quantidade", "Lote", "Validade"}; 
        JTable table = new JTable(rowData, columnNames);  
        JScrollPane barraRolagem = new JScrollPane(table);
        barraRolagem.setPreferredSize(new Dimension(500,400));
        
        back.add(barraRolagem, "wrap"); 
        
        back.add(drawBut());
        
        
        
        
        
        telaTabela.getContentPane().add(back);
        
    }
    public JPanel drawBut(){
        Font fontButton = new Font("sans-serif", Font.BOLD, 15);
        
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
        panelBotoes.setPreferredSize(new Dimension(500,120));
        panelBotoes.setLayout(new MigLayout(
        "",
        "110[]40[]",
        "20[]20[]"
        ));
        panelBotoes.add(butAdiciona);
        panelBotoes.add(butEditar, "wrap");
        panelBotoes.add(butRemove, "span");
        
        
        butAdiciona.addActionListener(this::adiciona);
        butEditar.addActionListener(this::edita);
        butRemove.addActionListener(this);
        return panelBotoes;
    }

    public void adiciona (ActionEvent ActionEvent) {
        System.out.println("Entra na tela de adicionar Produto"); 
//        TelaAdicionaProduto();
    }
    public void edita (ActionEvent ActionEvent) {
        System.out.println("Produto sendo editado");  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Produto removido com sucesso");
    }
}