/*
    FILIPE MOREIRA VIDAL - 202365510B
    LEONARDO PEREIRA DE FARIA PRATA  - 202365553C
    VICTOR ALBINO BRANDÃO SILVA - 202365558C
*/

package trabalhoPratico.view;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.List;
import net.miginfocom.swing.MigLayout;
import trabalhoPratico.exception.EmptyStrException;
import trabalhoPratico.exception.InvalidDataException;
import trabalhoPratico.exception.NegativeNumberException;
import trabalhoPratico.model.Categoria;
import trabalhoPratico.model.Produto;
import trabalhoPratico.persistence.CategoriaPersistence;
import trabalhoPratico.persistence.ProdutoPersistence;

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
    private JTextField TextNome;
    private JList<String> listaCategoria;
    private JTextField TextPreco;
    private JTextField TextQuant;
    private JFormattedTextField TextLote;
    private JFormattedTextField validade;
    private List<Produto> listaProdutos;
    private TelaTabelaProdutos tabelaProduto;
    
    public void draw(List<Produto> lista, TelaTabelaProdutos tabelaProduto){
        
        this.listaProdutos = lista;
        this.tabelaProduto = tabelaProduto;
        
        
        telaAdiciona = new JFrame("Novo Produto");
        telaAdiciona.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaAdiciona.setSize(500,500);
        telaAdiciona.setLayout(new MigLayout("center, center"));
        telaAdiciona.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        telaAdiciona.setLocationRelativeTo(null);
        telaAdiciona.setVisible(true);
        drawTela();
        
    }
    
    private void drawTela(){
        Font fontTexto = new Font("sans-serif", Font.PLAIN, 15);
        
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(360, 250));
        panel.setLayout(new MigLayout("center, center"));
        
        JLabel JNome = new JLabel("Nome: ");
        JNome.setFont(fontTexto);
        TextNome = new JTextField();
        TextNome.setPreferredSize(new Dimension(100, 20));
        TextNome.setFont(fontTexto);
        
        
        JLabel JTipo = new JLabel("Categoria:");
        JTipo.setFont(fontTexto);
        
        CategoriaPersistence categPersis = new CategoriaPersistence();
        List<Categoria> listaCategorias = categPersis.read();
        String[] categorias = new String[listaCategorias.size()];

        for(int i=0; i<listaCategorias.size(); i++)
            categorias[i] = listaCategorias.get(i).getNome();


        listaCategoria = new JList<> (categorias);
        listaCategoria.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane (listaCategoria);
        scroll.setPreferredSize(new Dimension(100,100));
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        

        JLabel JPreco = new JLabel("Preco: ");
        JPreco.setFont(fontTexto);
        JPreco.setPreferredSize(new Dimension(60,20));
        TextPreco = new JTextField();
        TextPreco.setFont(fontTexto);
        TextPreco.setPreferredSize(new Dimension(100,20));
        
        
        JLabel JQuant = new JLabel("Quantidade:");
        JQuant.setFont(fontTexto);
        JQuant.setPreferredSize(new Dimension(80,20));
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
        panel.add(TextNome, "wrap");
        
        panel.add(JTipo);
        panel.add(scroll, "wrap");
        
        panel.add(JPreco);
        panel.add(TextPreco, "wrap");
        
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
            novoProduto = null;
            return;
        }
        try {
            novoProduto.setCategory(listaCategoria.getSelectedValue().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(telaAdiciona, "O campo \"Categoria\" deve ser preenchido",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            novoProduto = null;
            return;
        }
        
        try{
           novoProduto.setPrice(Double.valueOf(TextPreco.getText())); 
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(telaAdiciona, "O campo \"Preco\" aceita apenas números",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            novoProduto = null;
            return;
        } catch(NegativeNumberException e){
            JOptionPane.showMessageDialog(telaAdiciona, "O campo \"Preco\" aceita apenas números positivos",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            novoProduto = null;
            return;
        }
        
        
        try{
            novoProduto.setQuantity(Integer.parseInt(TextQuant.getText()));
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(telaAdiciona, "O campo \"Quantidade\" aceita apenas números",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            novoProduto = null;
            return;
        } catch(NegativeNumberException e){
            JOptionPane.showMessageDialog(telaAdiciona, "O campo \"Quantidade\" aceita apenas números positivos",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            novoProduto = null;
            return;
        }
        
        try {
            novoProduto.setLote(TextLote.getText());
        } catch (EmptyStrException e) {
            JOptionPane.showMessageDialog(telaAdiciona, "O campo \"Lote\" deve ser preenchido",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            novoProduto = null;
            return;
        }
        
        
        try {
            novoProduto.setValidade(validade.getText());
        } catch (EmptyStrException e) {
            JOptionPane.showMessageDialog(telaAdiciona, "O campo \"validade\" deve ser preenchido",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            novoProduto = null;
            return;
        } catch (InvalidDataException e) {
            JOptionPane.showMessageDialog(telaAdiciona, "Data inválida",
                "ERRO", JOptionPane.ERROR_MESSAGE);
            novoProduto = null;
            return;
        }
        
            listaProdutos.add(novoProduto);
            ProdutoPersistence prodPersist = new ProdutoPersistence();
            prodPersist.save(listaProdutos);
            tabelaProduto.atualizaTabela();
            JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
            telaAdiciona.dispose();
    }
}
