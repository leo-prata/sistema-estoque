package trabalhoPratico.view;

import trabalhoPratico.persistence.FuncionarioPersistence;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import net.miginfocom.swing.MigLayout;
import trabalhoPratico.controller.FazerLogin;
import trabalhoPratico.model.Funcionario;
import trabalhoPratico.model.Cpf;

/**
 *
 * @author filip
 */
public class TelaLogin {
    private JFrame tela;
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    private final int V_GAP = 10;
    private final int H_GAP = 5;
    
    private JTextField tfCPF;
    private JPasswordField pfPassword;
    
    private static Funcionario user;
    
    /**
     * Faz a janela aparecer na tela
     */
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
        
        tfCPF = new JTextField(20);
        tfCPF.setBorder(BorderFactory.createLineBorder(Color.black));
        tfCPF.setFont(font);
        panel.add(tfCPF, "wrap");
        
        JLabel labelPasword = new JLabel("Senha:");
        labelPasword.setFont(font);
        panel.add(labelPasword, "wrap");
        
        pfPassword = new JPasswordField(20);
        pfPassword.setBorder(BorderFactory.createLineBorder(Color.black));
        pfPassword.setFont(font);
        panel.add(pfPassword, "wrap");
        
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(new FazerLogin(this));
        panel.add(btnEntrar, "center");
    }
    
    public void entrar()
    {
        FuncionarioPersistence funcPersis = new FuncionarioPersistence();
        List<Funcionario> listaFunc = funcPersis.read();
        
        String cpfLogin = tfCPF.getText();
        if(!Cpf.cpfValido(cpfLogin))
        {
            JOptionPane.showMessageDialog(tela, "CPF inválido.",
                    "ERRO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String passwordLogin = String.valueOf(pfPassword.getPassword());
        
        for(Funcionario func : listaFunc)
        {
            if(func.getCpf().equals(cpfLogin))
            {
                if(func.getPassword().equals(passwordLogin))
                {
                user = func;
                System.out.println("Login efetuado. User: " + user.getName());
                //implementar chamada para a próxima tela
                return; 
                }
                
                JOptionPane.showMessageDialog(tela, "Senha iválida",
                        "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        JOptionPane.showMessageDialog(tela, "Usuário não encontrado",
                "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
    }
}
