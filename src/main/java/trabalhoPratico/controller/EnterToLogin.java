package trabalhoPratico.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import trabalhoPratico.view.TelaLogin;

public class EnterToLogin implements KeyListener{

    private final TelaLogin tela;

    public EnterToLogin(TelaLogin tela)
    {
        this.tela = tela;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == '\n')
            tela.entrar();
    }

}
