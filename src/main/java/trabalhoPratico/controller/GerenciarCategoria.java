/*
    FILIPE MOREIRA VIDAL - 202365510B
    LEONARDO PEREIRA DE FARIA PRATA  - 202365553C
    VICTOR ALBINO BRANDÃO SILVA - 202365558C
*/

package trabalhoPratico.controller;

import trabalhoPratico.model.Categoria;
import trabalhoPratico.persistence.CategoriaPersistence;
import trabalhoPratico.persistence.Persistence;
import trabalhoPratico.view.TelaCategoria;
import javax.swing.*;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.*;

/**
 *
 * @author leopp
 */
public class GerenciarCategoria implements WindowListener {

    private final TelaCategoria tela;
    private final Persistence<Categoria> categoriaPersistence;

    public GerenciarCategoria(TelaCategoria tela) {
        this.tela = tela;
        this.categoriaPersistence = new CategoriaPersistence();

        tela.getBtnAdicionar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionaCategoria();
            }
        });

        tela.getBtnRemover().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCategoria();
            }
        });

        tela.getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editaCategoria();
            }
        });
    }

    @Override
    public void windowOpened(WindowEvent e) {
        List<Categoria> all = categoriaPersistence.read();
        tela.carregaCategorias(all);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        List<Categoria> todasCategorias = getTodasCategorias();
        categoriaPersistence.save(todasCategorias);
    }

    private void adicionaCategoria() {
        abrirFormularioCategoria(null);
    }
    
    private List<Categoria> getTodasCategorias() {
        ListModel<Categoria> model = tela.getListaCategorias().getModel();
        List<Categoria> categorias = new ArrayList<>();

        for (int i = 0; i < model.getSize(); i++) {
            categorias.add(model.getElementAt(i));
        }

    return categorias;
}

    private void editaCategoria() {
        Categoria categoriaSelecionada = tela.getListaCategorias().getSelectedValue();
        if (categoriaSelecionada != null) {
            abrirFormularioCategoria(categoriaSelecionada);
        } else {
            JOptionPane.showMessageDialog(tela, "Selecione uma categoria para editar.");
        }
    }
    

    
    private void abrirFormularioCategoria(Categoria categoriaExistente) {
    JTextField nomeField = new JTextField();

    if (categoriaExistente != null) {
        nomeField.setText(categoriaExistente.getNome());
    }

    JPanel formPanel = new JPanel();
    formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
    formPanel.add(new JLabel("Nome da Categoria:"));
    formPanel.add(nomeField);

    int result = JOptionPane.showConfirmDialog(null, formPanel,
            (categoriaExistente == null ? "Adicionar Nova Categoria" : "Editar Categoria"), JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {
        String nome = nomeField.getText();
        nome = nome.strip();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "O nome da categoria deve ser preenchido.");
            return;
        }

        for(Categoria categoria: getTodasCategorias())
        {
            if(categoria.getNome().toLowerCase().equals(nome.toLowerCase())){
                JOptionPane.showMessageDialog(tela, "Categoria já existente");
                return;
            }
        }

        try {
            if (categoriaExistente == null) {
                
                Categoria novaCategoria = new Categoria(nome);
                ((CategoriaPersistence) categoriaPersistence).add(novaCategoria);
            } else {
               
                categoriaExistente.setNome(nome);
                ((CategoriaPersistence) categoriaPersistence).update(categoriaExistente);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(tela, "Erro ao salvar a categoria: " + e.getMessage());
            return;
        }

       
        tela.carregaCategorias(categoriaPersistence.read());
    }
}


    private void removeCategoria() {
        Categoria categoriaSelecionada = tela.getListaCategorias().getSelectedValue();
        if (categoriaSelecionada != null) {
            ((CategoriaPersistence) categoriaPersistence).remove(categoriaSelecionada.getNome());
            tela.carregaCategorias(categoriaPersistence.read());
        } else {
            JOptionPane.showMessageDialog(tela, "Selecione uma categoria para remover.");
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
