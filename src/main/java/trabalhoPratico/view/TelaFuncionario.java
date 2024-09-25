/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhoPratico.view;

/**
 *
 * @author leopp
 */

import trabalhoPratico.model.Funcionario;
        
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaFuncionario extends JFrame {
    private DefaultListModel<Funcionario> listaModel;
    private JList<Funcionario> listaFuncionarios;
    private JButton btnAdicionar;
    private JButton btnRemover;
    private JButton btnEditar;

    public TelaFuncionario() {
        super("Gerenciamento de Funcionários");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 300);

        listaModel = new DefaultListModel<>();
        listaFuncionarios = new JList<>(listaModel);
        JScrollPane scrollPane = new JScrollPane(listaFuncionarios);
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

    public void carregaFuncionarios(List<Funcionario> funcionarios) {
        listaModel.clear();
        for (Funcionario funcionario : funcionarios) {
            listaModel.addElement(funcionario);
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

    public JList<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }
    
}
