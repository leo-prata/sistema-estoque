/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhoPratico.model;

/**
 *
 * @author leopp
 */

import trabalhoPratico.exception.EmptyStrException;

public class Categoria {
    private String nome;

    public Categoria(String nome) throws EmptyStrException {
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws EmptyStrException {
        if (nome == null || nome.isBlank()) {
            throw new EmptyStrException();
        }
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Categoria: " + nome;
    }

}
