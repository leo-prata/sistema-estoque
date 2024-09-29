package trabalhoPratico.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

import trabalhoPratico.controller.AbrirInformacoesProduto;
import trabalhoPratico.controller.AbrirTelaFuncionarios;
import trabalhoPratico.controller.AdicionarCategoria;
import trabalhoPratico.controller.AdicionarNovoProduto;
import trabalhoPratico.model.Funcionario;
import trabalhoPratico.model.Produto;
import trabalhoPratico.persistence.ProdutoPersistence;

import java.util.List;

public class TelaTabelaProdutos implements Tela {
    
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

    @Override
    public void draw()
    {
        tela = new JFrame("Estoque");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(new MigLayout("top, center, fillx"));
        tela.setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
        panel.setPreferredSize(new Dimension(WIDTH, 70));
        panel.setVisible(true);
        tela.getContentPane().add(panel, "grow, wrap");

        buttonAdicionarProduto = new JButton("Adicionar Produto");
        buttonAdicionarProduto.addActionListener(this::adicionarActioPerfomed);
        buttonAdicionarProduto.setPreferredSize(new Dimension(150, 50));
        panel.add(buttonAdicionarProduto, "center, center");
        
        buttonAdicionarCategoria = new JButton("Adicionar Categoria");
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
    public void adicionarProduto(Produto novo)
    {
        listaProdutos.add(novo);
    }
    
    public void informacoesProduto()
    {
        Produto produto = listaProdutos.get(jtProdutos.getSelectedRow());
        TelaProduto telaProduto = new TelaProduto(produto, this);

        telaProduto.draw();
    }
    
    public void adicionarCategoria()
    {
        //ESTE CODIGO DEVE SER REMOVIDO E SUBSTITUIDO PELA CHAMDA DA TELA DE ADICIONAR NOVA CATEGORIA
        System.out.println("Adicionando mova categoria");
    }

    public void telaFuncionarios()
    {
        //ESTE CODIGO DEVE SER REMOVIDO E SUBSTITUIDO PELA CHAMDA DA TELA DE ADICIONAR NOVA CATEGORIA
        System.out.println("Abrindo tela de funcionarios");
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
