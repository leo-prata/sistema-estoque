package trabalhoPratico.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import trabalhoPratico.controller.FechamentoTelaProduto;
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
    private JPanel panelInfo;
    private JPanel back;
    
    private JButton butAdiciona;
    private JButton butEditar;
    private JButton butRemove;
    private JPanel panelBotoes;
    
    
    private final int WIDTH = 540;
    private final int HEIGHT = 680;
    private java.util.List<Produto> listaProdutos;
    private DefaultTableModel tableModel;
    private JTable table;
    private Produto meuProduto;
    
    private JLabel total;

    private TelaTabelaProdutos telaTabelaProdutos;
    
        
    public void draw(Produto produto, TelaTabelaProdutos telaTabelaProdutos)
    {
        meuProduto = produto;
        this.telaTabelaProdutos = telaTabelaProdutos;
        
        telaTabela = new JFrame("Produto");
        telaTabela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaTabela.setLayout(new MigLayout("top, center"));
        telaTabela.setSize(WIDTH, HEIGHT);
        telaTabela.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        telaTabela.setVisible(true);
        telaTabela.setLocationRelativeTo(null);
        telaTabela.addWindowListener(new FechamentoTelaProduto(telaTabelaProdutos));
        
        drawInfo();
        telaTabela.pack();
    }
    private void drawInfo(){     
        Font fontTexto = new Font("sans-serif", Font.PLAIN, 15);
        
        back = new JPanel();
        back.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        back.setLayout(new MigLayout(
                "",
                "[]",
                "[][][][]"));
        
        
        JLabel lbProduto = new JLabel("Produto: "+ meuProduto.getName());
        lbProduto.setFont(fontTexto);
        lbProduto.setPreferredSize(new Dimension(250, 20));
        JLabel lbPreco = new JLabel("Preco: " + meuProduto.getPrice());
        lbPreco.setFont(fontTexto);
        lbPreco.setPreferredSize(new Dimension(150, 20));
        JLabel lbTipo = new JLabel("Tipo: " + meuProduto.getCategory());
        lbTipo.setFont(fontTexto);
        lbTipo.setPreferredSize(new Dimension(200, 20));
        
        panelInfo = new JPanel();
        panelInfo.setBorder(BorderFactory.createLineBorder(Color.black));
        panelInfo.setPreferredSize(new Dimension(500,80));
        panelInfo.setLayout(new MigLayout("top, left, fill"));
        panelInfo.add(lbProduto, "wrap");
        panelInfo.add(lbPreco);
        panelInfo.add(lbTipo);
        back.add(panelInfo, "wrap");
        drawTable();
    }
    
    
    private void drawTable()
    {
        ProdutoPersistence prodPersis = new ProdutoPersistence();
        Font fontTexto = new Font("sans-serif", Font.PLAIN, 15);
        
        listaProdutos = prodPersis.read();

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Quantidade");
        tableModel.addColumn("Lote");
        tableModel.addColumn("Validade");
        
        for(int i=0; i<meuProduto.getQuantidaddeLotes(); i++ )
        {
            tableModel.addRow(meuProduto.getProdutoProperties(i));
        }  
        table = new JTable(tableModel);  
        table.setDefaultEditor(Object.class, null);
        JScrollPane barraRolagem = new JScrollPane(table);
        table.setFont(fontTexto);
        barraRolagem.setBorder(BorderFactory.createEmptyBorder());
        barraRolagem.setPreferredSize(new Dimension(500,400));
        
        total = new JLabel("Total:"+meuProduto.getTotalQuantity());
        total.setPreferredSize(new Dimension(100, 30));
        total.setFont(fontTexto);
        
        
        back.add(barraRolagem, "span"); 
        back.add(total, "wrap");
        back.add(drawButtons());
        telaTabela.getContentPane().add(back);
    }
    
    
    private JPanel drawButtons()
    {
        Font fontButton = new Font("sans-serif", Font.BOLD, 15);
        
        butAdiciona = new JButton("Adicionar");
        butAdiciona.setPreferredSize(new Dimension(120,30));
        butAdiciona.setFont(fontButton);
        
        butEditar = new JButton("Editar quantidade");
        butEditar.setPreferredSize(new Dimension(120, 30));
        butEditar.setFont(fontButton);
        
        butRemove = new JButton("Remover Produto");
        butRemove.setPreferredSize(new Dimension(280, 30));
        butRemove.setFont(fontButton);

        panelBotoes = new JPanel();
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
        TelaAdicionaLote telaAdiciona = new TelaAdicionaLote();
        telaAdiciona.draw(meuProduto, this);
    }
    public void edita (ActionEvent ActionEvent) {
        int linha = table.getSelectedRow();
        TelaInformacoesLote telaLote = new TelaInformacoesLote(meuProduto, linha, this);
        telaLote.draw();
    }
    
    public void adicionaLinha()
    {
        Object[] novoLote = {
            meuProduto.getQuantity().get(meuProduto.getQuantidaddeLotes()-1),
            meuProduto.getLote().get(meuProduto.getQuantidaddeLotes()-1),
            meuProduto.getValidade().get(meuProduto.getQuantidaddeLotes()-1)};
        tableModel.addRow(novoLote);
        total.setText("Total:"+meuProduto.getTotalQuantity());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        telaTabelaProdutos.removeProduto(meuProduto);
        telaTabela.dispose();
        JOptionPane.showMessageDialog(null, "Produto removido com sucesso");
    }

    public void atualizaLinha(int linha)
    {
        table.setValueAt(meuProduto.getQuantity().get(linha), linha, 0);
    }
}