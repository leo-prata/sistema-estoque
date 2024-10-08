/*
    FILIPE MOREIRA VIDAL - 202365510B
    LEONARDO PEREIRA DE FARIA PRATA  - 202365553C
    VICTOR ALBINO BRANDÃO SILVA - 202365558C
*/

package trabalhoPratico.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import trabalhoPratico.controller.AbrirInformacoesProduto;
import trabalhoPratico.controller.AbrirTelaFuncionarios;
import trabalhoPratico.controller.AdicionarCategoria;
import trabalhoPratico.controller.GerenciarCategoria;
import trabalhoPratico.controller.GerenciarFuncionarios;
import trabalhoPratico.model.Funcionario;
import trabalhoPratico.model.Produto;
import trabalhoPratico.persistence.ProdutoPersistence;

import java.util.List;

public class TelaTabelaProdutos {
    
    private JFrame tela;
    private JButton buttonAdicionarProduto;
    private JButton buttonAdicionarCategoria;
    private JButton buttonTelaFuncionarios;
    private List<Produto> listaProdutos;
    private JTable jtProdutos;
    private DefaultTableModel tableModel;
    private JPanel panelTable;
    private Funcionario user;
    private ProdutoPersistence prodPersis = new ProdutoPersistence();

    public TelaTabelaProdutos(Funcionario user)
    {
        this.user = user; 
    }

    public void draw()
    {
        tela = new JFrame("Estoque");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(new MigLayout("top, center, fillx"));
        tela.setPreferredSize(new Dimension(1280, 720));
        tela.setVisible(true);

        
        if(user.getRole().toLowerCase().equals("gerente"))
            drawButtons();
        
        panelTable = new JPanel(new MigLayout("fill"));
        tela.getContentPane().add(panelTable, "grow");
        drawTable();

        tela.pack();
    }

    private void drawButtons()
    {
        JPanel panel = new JPanel();
        MigLayout layout = new MigLayout("center, center, fillx");
        panel.setLayout(layout);
        panel.setPreferredSize(new Dimension(1280, 70));
        panel.setVisible(true);
        tela.getContentPane().add(panel, "grow, wrap");

        buttonAdicionarProduto = new JButton("Adicionar Produto");
        buttonAdicionarProduto.addActionListener(this::adicionarActioPerfomed);
        buttonAdicionarProduto.setPreferredSize(new Dimension(150, 50));
        panel.add(buttonAdicionarProduto, "center, center");
        
        buttonAdicionarCategoria = new JButton("Categorias");
        buttonAdicionarCategoria.addActionListener(new AdicionarCategoria(this));
        buttonAdicionarCategoria.setPreferredSize(new Dimension(150, 50));
        panel.add(buttonAdicionarCategoria, "center, center");
        
        buttonTelaFuncionarios = new JButton("Funcionarios");
        buttonTelaFuncionarios.addActionListener(new AbrirTelaFuncionarios(this));
        buttonTelaFuncionarios.setPreferredSize(new Dimension(150, 50));
        panel.add(buttonTelaFuncionarios, "center, center");

    }

    
    private void drawTable()
    {
        listaProdutos = prodPersis.read();
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Quantidade");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Categoria");
        tableModel.addColumn("Preço");

        for(Produto produto : listaProdutos)
            tableModel.addRow(produto.toArray());
        
        jtProdutos = new JTable(tableModel);
        
        //cuida da parte visual da tabela
        Font font = new Font("sans-serif", Font.PLAIN, 20);
        jtProdutos.setFont(font);
        jtProdutos.setRowHeight(30);
        jtProdutos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtProdutos.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtProdutos.getColumnModel().getColumn(1).setPreferredWidth(200);
        jtProdutos.setDefaultEditor(Object.class, null);
        
        jtProdutos.addMouseListener(new AbrirInformacoesProduto(this));
        
        JScrollPane barraRolagem = new JScrollPane(jtProdutos);
        barraRolagem.setBorder(BorderFactory.createEmptyBorder());

        panelTable.add(barraRolagem, "grow, wrap");
    }
    
    public void adicionarActioPerfomed(ActionEvent e)
    {
        TelaNovoProduto novoProduto = new TelaNovoProduto();
        novoProduto.draw(listaProdutos, this);
    }
    
    public void informacoesProduto()
    {
        Produto produto = listaProdutos.get(jtProdutos.getSelectedRow());
        TelaProduto telaProduto = new TelaProduto(produto, this, user);

        telaProduto.draw();
    }
    
    public void adicionarCategoria()
    {
        TelaCategoria telaCategoria = new TelaCategoria();
        GerenciarCategoria gerenciarCategoria = new GerenciarCategoria(telaCategoria);
        telaCategoria.addWindowListener(gerenciarCategoria);
        telaCategoria.setVisible(true);
    }

    public void telaFuncionarios()
    {
        TelaFuncionario telaFuncionario = new TelaFuncionario();
        GerenciarFuncionarios gerenciarFuncionarios = new GerenciarFuncionarios(telaFuncionario);
        telaFuncionario.addWindowListener(gerenciarFuncionarios);
        telaFuncionario.setVisible(true);
    }

    public void removeProduto(Produto produto){
        listaProdutos.remove(produto);
        salvaListaProdutos();
    }

    public void salvaListaProdutos()
    {
        prodPersis.save(listaProdutos);
    }

    public void atualizaTabela()
    {
        panelTable.setVisible(false);
        panelTable.removeAll();
        drawTable();
        panelTable.setVisible(true);
    }
}
