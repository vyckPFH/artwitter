package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.edu.ifpr.model.utils.Usuario;

public class UsuarioDAO {

    public Usuario buscarPorNome(String nome) {

        Connection con = ConnectionFactory.getConnection();
        Usuario usuario = null;

        try {

            String sql = "SELECT * FROM usuario WHERE nome = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    /**
     * Seleciona um usuario do banco pelo id dele
     * 
     * @param id
     * @return o usuario do id
     */
    public Usuario selectPorId(int id) {

        Connection con = ConnectionFactory.getConnection();
        Usuario usuario = null;

        try {

            String sql = "SELECT * FROM usuario WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    /**
     * 
     * @return Registros da tabela usuario no banco
     * @author vyckPFH
     */
    public ArrayList<Usuario> select() {

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {

            String sql = "SELECT * FROM usuario";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * bota um usuario no banco
     * 
     * @param usuario
     */
    public void insert(Usuario usuario) {

        Connection con = ConnectionFactory.getConnection();

        String sql = ("INSERT INTO usuario (nome,email,senha) VALUES(?,?,?);");

        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());

            ps.executeUpdate();
            System.out.println("Usuario inserido cm sucesso");
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));  //preciso ver aq dps
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * deleta um usuario e tudo associado a ele pelo id
     * 
     * @param usuario
     */
    public void delete(Usuario usuario) {

        Connection con = ConnectionFactory.getConnection();

        String sql = "DELETE FROM usuario WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, usuario.getId());

            ps.executeUpdate();

            System.out.println("Usuário deletado cm sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza toda a tupla de um usuario registrado
     * 
     * @param usuario
     */
    public void update(Usuario usuario) {

        Connection con = ConnectionFactory.getConnection();

        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setInt(4, usuario.getId());

            ps.executeUpdate();

            System.out.println("Usuário atualizado cm sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
