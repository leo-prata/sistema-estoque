/*
    FILIPE MOREIRA VIDAL - 202365510B
    LEONARDO PEREIRA DE FARIA PRATA  - 202365553C
    VICTOR ALBINO BRANDÃO SILVA - 202365558C
*/

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
