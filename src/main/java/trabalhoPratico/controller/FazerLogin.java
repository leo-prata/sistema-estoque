package trabalhoPratico.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import trabalhoPratico.view.TelaLogin;


/**
 *
 * @author filip
 */
public class FazerLogin implements ActionListener{
    
    private final TelaLogin tela;
    
    public FazerLogin(TelaLogin tela)
    {
        this.tela = tela;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.tela.entrar();
    }
    
}
