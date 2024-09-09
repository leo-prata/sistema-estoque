package trabalhoPratico.view;

import javax.swing.*;
import java.awt.*;
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

    private Funcionario user;

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
        buttonAdicionarProduto.addActionListener(new AdicionarNovoProduto(this));
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
        ProdutoPersistence prodPersis = new ProdutoPersistence();
        
        listaProdutos = prodPersis.read();
        
        //transforma a lista em um vetor bidimensional de Object
        Object[][] rowData = new Object[listaProdutos.size()][6];
        int cont = 0;
        for(Produto produto : listaProdutos)
        {
            rowData[cont++] = produto.toArray();
        }
        
        //nome das colunas
        Object[] columnNames = {"Quantidade", "Nome", "Categoria", "Preço", "Validade", "Lote"};
        
        jtProdutos = new JTable(rowData, columnNames);
        
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

        JPanel panel = new JPanel(new MigLayout("fill"));
        panel.add(barraRolagem, "grow, wrap");
        
        tela.getContentPane().add(panel, "grow");
    }
    
    public void adicionarNovoProduto()
    {
        //ESTE CODIGO DEVE SER REMOVIDO E SUBSTITUIDO PELA CHAMADA DA TELA DE ADICIONAR NOVO PRODUTO
        System.out.println("adicionando produto");
    }
    
    public void informacoesProduto()
    {
        //ESTE CODIGO DEVE SER REMOVIDO E SUBSTITUIDO PELA CHAMADA DA TELA DE INFORMAÇÔES DO PRODUTO
        System.out.println(jtProdutos.getValueAt(jtProdutos.getSelectedRow(), 1).toString());
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
}
