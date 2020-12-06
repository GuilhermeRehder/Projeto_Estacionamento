//Classe para acesso ao objeto
package psv;

public class UsuarioBean {
    
    //Atributos da classe
    
    private String cpf;
    private String nome;
    private String senha;
    private String email;
    private boolean administrador;
    
    //Criando o construtores

    public UsuarioBean() {
    }
    
    public UsuarioBean(String cpf, String nome, String senha, String email) {
    this.cpf = cpf;      
    this.nome = nome;
    this.senha = senha;      
    this.email = email;
    }
       
    //Criar os métodos getters e setters - alt+insert

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
    
}
