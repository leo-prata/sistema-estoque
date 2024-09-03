package trabalhoPratico.view;

import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;

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
    private JTextField tfPassword;
    
    public void draw()
    {
        tela = new JFrame("Estoque");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(new MigLayout("align center"));
        tela.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        tela.setVisible(true);
        
        
        drawInput();
        tela.pack();
    }
    
    public void  drawInput()
    {
        JPanel panel = new JPanel();
        panel.add(new JLabel("CPF:"));
        tfCPF = new JTextField();
        tfCPF.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(tfCPF);
        
        panel.add(new JLabel("Senha:"));
        tfPassword = new JTextField();
        tfPassword.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(tfPassword);
        
        
        panel.setPreferredSize(new Dimension(400, 500));
        panel.setMaximumSize(new Dimension(400, 500));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        tela.getContentPane().add(panel);
        
        
    }
}
