package trabalhoPratico.controller;

/**
 *
 * @author leopp
 */
import trabalhoPratico.model.Funcionario;
import trabalhoPratico.model.Cpf;
import trabalhoPratico.persistence.FuncionarioPersistence;
import trabalhoPratico.persistence.Persistence;
import trabalhoPratico.view.TelaFuncionario;

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

        tela.getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editaFuncionario();
            }
        });
    }
    

    @Override
    public void windowOpened(WindowEvent e) {
        List<Funcionario> all = funcionarioPersistence.read();
        tela.carregaFuncionarios(all);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        List<Funcionario> todosFuncionarios = getTodosFuncionarios();
        funcionarioPersistence.save(todosFuncionarios);
    }
    
    private List<Funcionario> getTodosFuncionarios() {
        ListModel<Funcionario> model = tela.getListaFuncionarios().getModel();
        List<Funcionario> funcionarios = new ArrayList<>();

        for (int i = 0; i < model.getSize(); i++) {
            funcionarios.add(model.getElementAt(i));
        }

        return funcionarios;
    }
    
    private void adicionaFuncionario() {
        abrirFormularioFuncionario(null);
    }

    private void editaFuncionario() {
        Funcionario funcionarioSelecionado = tela.getListaFuncionarios().getSelectedValue();
        if (funcionarioSelecionado != null) {
            abrirFormularioFuncionario(funcionarioSelecionado);
        } else {
            JOptionPane.showMessageDialog(tela, "Selecione um funcionário para editar.");
        }
    }

    private void abrirFormularioFuncionario(Funcionario funcionarioExistente) {
        JTextField nomeField = new JTextField();
        //JTextField cpfField = new JTextField();
        JTextField dataNascimentoField = new JTextField();
        JTextField salarioField = new JTextField();
        JTextField roleField = new JTextField(); 
        JTextField passwordField = new JTextField(); 

        if (funcionarioExistente != null) {
            nomeField.setText(funcionarioExistente.getNome());
            //cpfField.setText(funcionarioExistente.getCpf().toString());
            //cpfField.setEditable(false); 
            dataNascimentoField.setText(new SimpleDateFormat("dd/MM/yyyy").format(funcionarioExistente.getDataNascimento()));
            salarioField.setText(String.valueOf(funcionarioExistente.getSalario()));
            roleField.setText(funcionarioExistente.getRole());
            passwordField.setText(funcionarioExistente.getPassword());
        }

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        //formPanel.add(new JLabel("CPF:"));
        //formPanel.add(cpfField);
        formPanel.add(new JLabel("Data de Nascimento (dd/MM/yyyy):"));
        formPanel.add(dataNascimentoField);
        formPanel.add(new JLabel("Salário:"));
        formPanel.add(salarioField);
        formPanel.add(new JLabel("Role:")); 
        formPanel.add(roleField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);


        int result = JOptionPane.showConfirmDialog(null, formPanel, 
                (funcionarioExistente == null ? "Adicionar Novo Funcionário" : "Editar Funcionário"), JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            //String cpfStr = cpfField.getText();
            String dataNascimentoStr = dataNascimentoField.getText();
            String salarioStr = salarioField.getText();
            String role = roleField.getText();
            String password = passwordField.getText();
            
            if (nome.isEmpty() || dataNascimentoStr.isEmpty() || salarioStr.isEmpty() || role.isEmpty() || password.isEmpty()) {
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

            try {
                //Cpf cpf = new Cpf(cpfStr);
                if (funcionarioExistente == null) {
                    Funcionario novoFuncionario = new Funcionario(nome, dataNascimento, salario, role, password);
                    ((FuncionarioPersistence) funcionarioPersistence).add(novoFuncionario);
                } else {
                    funcionarioExistente.setNome(nome);
                    funcionarioExistente.setDataNascimento(dataNascimento);
                    //funcionarioExistente.setCpf(cpf);
                    funcionarioExistente.setSalario(salario);
                    funcionarioExistente.setRole(role);
                    funcionarioExistente.setPassword(password);
                    ((FuncionarioPersistence) funcionarioPersistence).update(funcionarioExistente);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(tela, "Erro ao salvar o funcionário: " + e.getMessage());
                return;
            }

            tela.carregaFuncionarios(funcionarioPersistence.read());
        }
    }

    private void removeFuncionario() {
        Funcionario funcionarioSelecionado = tela.getListaFuncionarios().getSelectedValue();
        if (funcionarioSelecionado != null) {
            String cpf = funcionarioSelecionado.getCpf();
            ((FuncionarioPersistence) funcionarioPersistence).remove(cpf);
            tela.carregaFuncionarios(funcionarioPersistence.read());
        } else {
            JOptionPane.showMessageDialog(tela, "Selecione um funcionário para remover.");
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