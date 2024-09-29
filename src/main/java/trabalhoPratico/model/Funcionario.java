/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalhoPratico.model;

/**
 *
 * @author leopp
 */
import java.util.Date;
import java.util.UUID;
import trabalhoPratico.exception.CpfException;
import trabalhoPratico.exception.EmptyStrException;


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
        return cpf.getCpf();
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
        return "Nome: " + nome + ", CPF: " + cpf + ", Data de Nascimento: " + dataNascimento + ", Salário: " + salario;
    }
}
