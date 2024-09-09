package trabalhoPratico.view;

import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;
import trabalhoPratico.controller.FazerLogin;

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
    
    public void draw()
    {
        tela = new JFrame("Estoque");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(new MigLayout("center, center"));
        tela.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        tela.setVisible(true);
//        tela.setLocationRelativeTo(null);
        
        
        drawInput();
        tela.pack();
    }
    
    public void  drawInput()
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(350, 400));
        panel.setMaximumSize(new Dimension(350, 400));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
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
}
