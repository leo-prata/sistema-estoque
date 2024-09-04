package trabalhoPratico.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import trabalhoPratico.exception.CpfException;
import trabalhoPratico.exception.EmptyStrException;

/**
 *
 * @author filip
 */
public final class Funcionario {
    private String name; 
    private Cpf cpf;
    private String password;
    private String role;
    
    public Funcionario()
    {
    
    }
    
    public Funcionario(String name, String cpf, String password, String role) throws EmptyStrException {
        setName(name);
        setCpf(cpf);
        setPassword(password);
        setRole(role);
    }

    public String getName() {
        return name;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) throws EmptyStrException {
        if(name.isBlank())
            throw new EmptyStrException();
        this.name = name;
    }

    public void setCpf(String cpf) throws EmptyStrException {
        if(cpf.isBlank())
            throw new EmptyStrException();
        try {
            this.cpf.setCpf(cpf);
        } catch (CpfException ex) {
            Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPassword(String password) throws EmptyStrException {
        if(password.isBlank())
            throw new EmptyStrException();
        this.password = password;
    }

    public void setRole(String role) throws EmptyStrException {
        if(role.isBlank())
            throw new EmptyStrException();
        this.role = role;
    }
}
