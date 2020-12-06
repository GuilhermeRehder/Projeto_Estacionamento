//Classe de acesso aos dados (SQL com Java)
package psv;

import java.sql.*;
import java.util.*;

public class UsuarioDAO {

    //Variavel para acesso a conexao (abrir e fechar o bd)
    private Connection con;

    //Criando o construtor da classe
    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    //Criando os métodos de acesso (getters e setters)
    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    //Criar o método insert 
    public String inserirUsuario(UsuarioBean usuario) {

        String sql = "insert into tbusuario(cpf, nome, senha, email, administrador) values(?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = getCon().prepareStatement(sql);

            //Inserir os valores do java no sql
            ps.setString(1, usuario.getCPF());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEmail());
            ps.setBoolean(5, usuario.getAdministrador());

            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Erro ao inserir.";
            }

        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    //Criar o método update
    public String alterarUsuario(UsuarioBean usuario) {

        //Alterando os registros inseridos no bd
        String sql = "update tbusuario set nome = ?, senha = ?, email = ?, administrador = ? where cpf = ?";

        try {

            PreparedStatement ps = getCon().prepareStatement(sql);
            
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getEmail());
            ps.setBoolean(4, usuario.getAdministrador());
            ps.setString(5, usuario.getCPF());
            

            if (ps.executeUpdate() > 0) {

                return "Alterado com sucesso.";

            } else {
                return "Erro ao alterar.";
            }
        } catch (SQLException ex) {
            return ex.getMessage();
        }

    }

    //Criar método delete
    public String excluirUsuario(UsuarioBean usuario) {

        String sql = "delete from tbusuario where cpf = ?";

        try {
            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setString(1, usuario.getCPF());

            if (ps.executeUpdate() > 0) {
                return "Excluido com sucesso.";
            } else {
                return "Erro ao excluir.";
            }

        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    //Criar o select
    public List<UsuarioBean> listarTodos() {

        String sql = "select * from tbusuario";

        //Criando um vetor ou matriz vazia para carregar os valores do bd
        List<UsuarioBean> listaUsuarios = new ArrayList<>();

        try {
            PreparedStatement ps = getCon().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs != null) {

                while (rs.next()) {

                    UsuarioBean ub = new UsuarioBean();
                    ub.setCPF(rs.getString(1));
                    ub.setNome(rs.getString(2));
                    ub.setSenha(rs.getString(3));
                    ub.setEmail(rs.getString(4));
                    ub.setAdministrador(rs.getBoolean(5));

                    listaUsuarios.add(ub);
                }
                return listaUsuarios;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            return null;
        }

    }
    
    public boolean validarUsuario(UsuarioBean usuario) {

        String sql = "SELECT * FROM tbusuario WHERE email = ? and senha = ?";

        try {

            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean verificarAdministrador(UsuarioBean usuario) {
        
        String sql = "SELECT * FROM tbusuario WHERE email = ? and senha = ? and administrador = ?";

        try {

            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());
            ps.setBoolean(3, true);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            return false;
        }
    }
    
}
