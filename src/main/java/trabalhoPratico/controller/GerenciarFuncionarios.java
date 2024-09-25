/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author leopp
 */
import model.Funcionario;
import persistence.FuncionarioPersistence;
import persistence.Persistence;
import view.TelaFuncionario;

import javax.swing.*;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class GerenciarFuncionarios implements WindowListener {

    private final TelaFuncionario tela;
    private final Persistence<Funcionario> funcionarioPersistence;

    public GerenciarFuncionarios(TelaFuncionario tela) {
        this.tela = tela;
        this.funcionarioPersistence = new FuncionarioPersistence();

        tela.getBtnAdicionar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionaFuncionario();
            }
        });

        tela.getBtnRemover().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFuncionario();
            }
        });
    }

    @Override
    public void windowOpened(WindowEvent e) {
        List<Funcionario> all = funcionarioPersistence.findAll();
        tela.carregaFuncionarios(all);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        funcionarioPersistence.save(tela.getListaFuncionarios().getSelectedValuesList());
    }

    private void adicionaFuncionario() {
         // Criação dos campos de entrada
        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField dataNascimentoField = new JTextField();
        JTextField salarioField = new JTextField();

        // Criação do painel de formulário
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("CPF:"));
        formPanel.add(cpfField);
        formPanel.add(new JLabel("Data de Nascimento (dd/MM/yyyy):"));
        formPanel.add(dataNascimentoField);
        formPanel.add(new JLabel("Salário:"));
        formPanel.add(salarioField);

        // Exibição do formulário em uma janela de diálogo
        int result = JOptionPane.showConfirmDialog(null, formPanel, 
                "Adicionar Novo Funcionário", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Captura das entradas
            String nome = nomeField.getText();
            String cpf = cpfField.getText();
            String dataNascimentoStr = dataNascimentoField.getText();
            String salarioStr = salarioField.getText();

            // Validação dos campos de entrada
            if (nome.isEmpty() || cpf.isEmpty() || dataNascimentoStr.isEmpty() || salarioStr.isEmpty()) {
                JOptionPane.showMessageDialog(tela, "Todos os campos devem ser preenchidos.");
                return;
            }

            Date dataNascimento = null;
            try {
                dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimentoStr);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(tela, "Formato de data inválido. Use dd/MM/yyyy.");
                return;
            }

            double salario;
            try {
                salario = Double.parseDouble(salarioStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(tela, "Salário inválido. Deve ser um número.");
                return;
            }

            // Criação do novo funcionário
            Funcionario novoFuncionario = new Funcionario(nome, cpf, dataNascimento, salario);
            ((FuncionarioPersistence) funcionarioPersistence).add(novoFuncionario);

            // Atualização da lista de funcionários na interface
            tela.carregaFuncionarios(funcionarioPersistence.findAll());
        }
    }

    private void removeFuncionario() {
        // Remove o funcionário selecionado
        Funcionario funcionarioSelecionado = tela.getListaFuncionarios().getSelectedValue();
        String cpf = funcionarioSelecionado.getCpf();
        if (funcionarioSelecionado != null) {
            ((FuncionarioPersistence) funcionarioPersistence).remove(cpf);
            tela.carregaFuncionarios(funcionarioPersistence.findAll());
        } else {
            JOptionPane.showMessageDialog(tela, "Selecione um funcionário para remover.");
        }
    }

    // Outros métodos do WindowListener...

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
