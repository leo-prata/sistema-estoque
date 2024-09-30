/*
    FILIPE MOREIRA VIDAL - 202365510B
    LEONARDO PEREIRA DE FARIA PRATA  - 202365553C
    VICTOR ALBINO BRANDÃO SILVA - 202365558C
*/

package trabalhoPratico.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import trabalhoPratico.controller.FechamentoTelaProduto;
import trabalhoPratico.model.Funcionario;
import trabalhoPratico.model.Produto;

/**
 *
 * @author Victor Brandão
 */
public class TelaProduto implements ActionListener{
    
    private JFrame telaTabela;
    private JPanel panelInfo;
    private JPanel back;
    private JButton butAdiciona;
    private JButton butEditar;
    private JButton butRemove;
    private JPanel panelBotoes;
    private final int WIDTH = 540;
    private final int HEIGHT = 680;
    private DefaultTableModel tableModel;
    private JTable table;
    private Produto meuProduto;
    private JLabel total;
    private TelaTabelaProdutos telaTabelaProdutos;
    private Funcionario user;

    public TelaProduto(Produto produto, TelaTabelaProdutos telaTabelaProdutos, Funcionario user)
    {
        meuProduto = produto;
        this.telaTabelaProdutos = telaTabelaProdutos;
        this.user = user;
    }
        
    public void draw()
    {   
        telaTabela = new JFrame(meuProduto.getName());
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

        JLabel lbPreco = new JLabel("Preco: R$ " + meuProduto.getPrice());
        lbPreco.setFont(fontTexto);
        lbPreco.setPreferredSize(new Dimension(150, 20));

        JLabel lbTipo = new JLabel("Categoria: " + meuProduto.getCategory());
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
        Font fontTexto = new Font("sans-serif", Font.PLAIN, 15);

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
        
        total = new JLabel("Total:"+meuProduto.totalQuantity());
        total.setPreferredSize(new Dimension(100, 30));
        total.setFont(fontTexto);
        
        back.add(barraRolagem, "span"); 
        back.add(total, "wrap");

        if(!user.getRole().equals("estoquista"))
            back.add(drawButtons());

        telaTabela.getContentPane().add(back);
    }
    
    
    private JPanel drawButtons()
    {
        Font fontButton = new Font("sans-serif", Font.BOLD, 15);

        panelBotoes = new JPanel();
        panelBotoes.setBorder(BorderFactory.createLineBorder(Color.black));
        panelBotoes.setPreferredSize(new Dimension(500,120));
        
        butEditar = new JButton("Editar");
        butEditar.setPreferredSize(new Dimension(120, 30));
        butEditar.setFont(fontButton);
        butEditar.addActionListener(this::edita);
        
        if(user.getRole().equals("gerente"))
        {
            butAdiciona = new JButton("Adicionar");
            butAdiciona.setPreferredSize(new Dimension(120,30));
            butAdiciona.setFont(fontButton);
            butAdiciona.addActionListener(this::adiciona);
            
            
            butRemove = new JButton("Remover Produto");
            butRemove.setPreferredSize(new Dimension(280, 30));
            butRemove.setFont(fontButton);
            butRemove.addActionListener(this);
            
            panelBotoes.setLayout(new MigLayout("", "110[]40[]", "20[]20[]"));
            
            panelBotoes.add(butAdiciona);
            panelBotoes.add(butEditar, "wrap");   
            panelBotoes.add(butRemove, "span");
        }
        else
        {
            panelBotoes.setLayout(new MigLayout("center, center"));
            panelBotoes.add(butEditar);
        }
        
        return panelBotoes;
    }

    public void adiciona (ActionEvent ActionEvent) {
        TelaAdicionaLote telaNovoLote = new TelaAdicionaLote();
        telaNovoLote.draw(meuProduto, this);
    }
    public void edita (ActionEvent ActionEvent) {
        int linha = table.getSelectedRow();

        if(linha<0){
            JOptionPane.showMessageDialog(telaTabela, "Selecione uma linha",
            "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }

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
        total.setText("Total:"+meuProduto.totalQuantity());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        telaTabelaProdutos.removeProduto(meuProduto);
        telaTabelaProdutos.atualizaTabela();
        telaTabela.dispose();
        JOptionPane.showMessageDialog(null, "Produto removido com sucesso");
    }

    public void atualiza()
    {
        telaTabela.setVisible(false);
        telaTabela.removeAll();
        draw();
        telaTabela.setVisible(true);
    }
}