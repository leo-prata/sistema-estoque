/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhoPratico.view;

/**
 *
 * @author leopp
 */

import trabalhoPratico.model.Categoria;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaCategoria extends JFrame {
     private DefaultListModel<Categoria> listaModel;
    private JList<Categoria> listaCategorias;
    private JButton btnAdicionar;
    private JButton btnRemover;
    private JButton btnEditar;

    public TelaCategoria() {
        super("Gerenciamento de Categorias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 300);

        listaModel = new DefaultListModel<>();
        listaCategorias = new JList<>(listaModel);
        JScrollPane scrollPane = new JScrollPane(listaCategorias);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel();
        btnAdicionar = new JButton("Adicionar");
        btnRemover = new JButton("Remover");
        btnEditar = new JButton("Editar");
        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnRemover);
        panelBotoes.add(btnEditar);

        add(panelBotoes, BorderLayout.SOUTH);
    }

    public void carregaCategorias(List<Categoria> categorias) {
        listaModel.clear();
        for (Categoria categoria : categorias) {
            listaModel.addElement(categoria);
        }
    }

    public JButton getBtnAdicionar() {
        return btnAdicionar;
    }

    public JButton getBtnRemover() {
        return btnRemover;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JList<Categoria> getListaCategorias() {
        return listaCategorias;
    }
}
