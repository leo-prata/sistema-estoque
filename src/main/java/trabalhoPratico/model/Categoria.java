
package trabalhoPratico.model;

/**
 *
 * @author leopp
 */

import java.util.UUID;
import trabalhoPratico.exception.EmptyStrException;

public class Categoria {
    private String nome;
    private String id;
    
    public Categoria(String nome) throws EmptyStrException {
        setNome(nome);
        this.id = UUID.randomUUID().toString();
    }
    public Categoria(String id, String nome) {
        this.id = id != null ? id : UUID.randomUUID().toString(); 
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) throws EmptyStrException {
        if (nome == null || nome.isBlank()) {
            throw new EmptyStrException();
        }
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}
