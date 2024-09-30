/*
    FILIPE MOREIRA VIDAL - 202365510B
    LEONARDO PEREIRA DE FARIA PRATA  - 202365553C
    VICTOR ALBINO BRANDÃO SILVA - 202365558C
*/

package trabalhoPratico.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import trabalhoPratico.exception.CpfException;
import trabalhoPratico.exception.EmptyStrException;

/**
 *
 * @author leopp
 */
public class Funcionario {
 
    private String nome;
    private Cpf cpf;
    private Date dataNascimento;
    private double salario;
    private String role;
    private String password;
 
    public Funcionario(String nome, String cpf, Date dataNascimento, double salario, String role, String password) throws EmptyStrException, NumberFormatException, CpfException  {
        this.nome = nome;
        setCpf(cpf);
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        setRole(role);
        setPassword(password);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf.toString();
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public double getSalario() {
        return salario;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
     public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
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
    
    public void setCpf(String cpf) throws EmptyStrException, CpfException, NumberFormatException {
        Cpf cpfStr = new Cpf(cpf);
        this.cpf = cpfStr;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Cargo: " + role + ", CPF: " + cpf + ", Data de Nascimento: " +
            new SimpleDateFormat("dd/MM/yyyy").format(dataNascimento) + ", Salário: R$" + salario;
    }
}
