package trabalhoPratico.view;

import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;

public class TelaTabelaProdutos {
    
    private JFrame tela;
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    private final int V_GAP = 10;
    private final int H_GAP = 5;

    public void draw()
    {
        tela = new JFrame("Estoque");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.getContentPane().setLayout(new MigLayout("center, center"));
        tela.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        tela.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("center, center"));
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setVisible(true);
        tela.getContentPane().add(panel);

        panel.add(new JTextField("Tabela de produtos"));

        tela.pack();
    }
}
