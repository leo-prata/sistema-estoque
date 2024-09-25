/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhoPratico.controller;

/**
 *
 * @author leopp
 */

import trabalhoPratico.model.Categoria;
import trabalhoPratico.persistence.CategoriaPersistence;
import trabalhoPratico.persistence.Persistence;
import trabalhoPratico.view.TelaCategoria;

import javax.swing.*;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;

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
        categoriaPersistence.save(tela.getListaCategorias().getSelectedValuesList());
    }

    private void adicionaCategoria() {
        abrirFormularioCategoria(null);
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

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(tela, "O nome da categoria deve ser preenchido.");
                return;
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
            String nome = categoriaSelecionada.getNome();
            ((CategoriaPersistence) categoriaPersistence).remove(nome);
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