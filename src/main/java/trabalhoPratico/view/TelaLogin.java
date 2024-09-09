package trabalhoPratico.view;

import trabalhoPratico.persistence.FuncionarioPersistence;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.List;
import net.miginfocom.swing.MigLayout;
import trabalhoPratico.controller.EnterToLogin;
import trabalhoPratico.controller.FazerLogin;
import trabalhoPratico.exception.CpfException;
import trabalhoPratico.exception.EmptyStrException;
import trabalhoPratico.model.Funcionario;
import trabalhoPratico.model.Cpf;

/**
 *
 * @author filip
 */
public class TelaLogin implements Tela{

    private JFrame tela;
    
    private JFormattedTextField tfCPF;
    private JPasswordField pfPassword;
    
    private static Funcionario user;
    
    /**
     * Faz a janela aparecer na tela
     */
    @Override
    public void draw()
    {
        tela = new JFrame("Estoque");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(new MigLayout("center, center"));
        tela.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        tela.setVisible(true);

        drawInput();
        tela.pack();
    }
    
    private void  drawInput()
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(350, 400));
        panel.setMaximumSize(new Dimension(350, 400));
        panel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        tela.getContentPane().add(panel);
        panel.setLayout(new MigLayout("center, center"));
        
        Font font = new Font("sans-serif", Font.PLAIN, 20);
        
        JLabel labelCPF = new JLabel("CPF:");
        labelCPF.setFont(font);
        panel.add(labelCPF, "wrap");
        
        try {
            MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
            tfCPF = new JFormattedTextField(mascaraCpf);
        } catch (ParseException e) {}
        tfCPF.setBorder(BorderFactory.createLineBorder(Color.black));
        tfCPF.setColumns(20);
        tfCPF.setFont(font);
        tfCPF.addKeyListener(new EnterToLogin(this));
        panel.add(tfCPF, "wrap");
        
        JLabel labelPasword = new JLabel("Senha:");
        labelPasword.setFont(font);
        panel.add(labelPasword, "wrap");
        
        pfPassword = new JPasswordField(20);
        pfPassword.setBorder(BorderFactory.createLineBorder(Color.black));
        pfPassword.setFont(font);
        pfPassword.addKeyListener(new EnterToLogin(this));
        panel.add(pfPassword, "wrap");
        
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(new FazerLogin(this));
        panel.add(btnEntrar, "center");
    }
    
    public void entrar()
    {
        FuncionarioPersistence funcPersis = new FuncionarioPersistence();
        List<Funcionario> listaFunc = funcPersis.read();
        
        Cpf cpfLogin;
        try {
            cpfLogin = new Cpf(tfCPF.getText());
        } catch (NumberFormatException | CpfException | EmptyStrException e) {
            JOptionPane.showMessageDialog(tela, "CPF inválido.",
                    "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String passwordLogin = String.valueOf(pfPassword.getPassword());
        
        for(Funcionario func : listaFunc)
        {
            if(func.getCpf().equals(cpfLogin.getCpf()))
            {
                //se o cpf e a senha forem correspondentes, o login é efetuado e proxima tela é chamada
                if(func.getPassword().equals(passwordLogin))
                {
                user = func;
                tela.dispose();
                TelaTabelaProdutos tabelaProdutos = new TelaTabelaProdutos(user);
                tabelaProdutos.draw();
                return; 
                }
                
                //se o usuario existe, mas a senha é inválida, joga um pop up de erro
                JOptionPane.showMessageDialog(tela, "Senha iválida",
                        "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        //se o usuario não existe na base de dados, joga um pop up de erro
        JOptionPane.showMessageDialog(tela, "Usuário não encontrado",
                "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
    }
}
