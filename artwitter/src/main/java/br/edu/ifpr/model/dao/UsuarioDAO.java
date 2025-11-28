package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ifpr.model.utils.Usuario;


public class UsuarioDAO {

    public Usuario selectById(int id) {

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

    public void insert(Usuario usuario){
        
        Connection con = ConnectionFactory.getConnection();

        String sql = ("INSERT INTO usuario (nome,email,senha) VALUES(?,?,?);");
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.executeUpdate();
            System.out.println("Usuario inserido cm sucesso");

            }catch (SQLException e){
                e.printStackTrace();
            }
            
        }
            

    public ArrayList<Usuario> select(){

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Usuario> usuarios = new ArrayList<>();

        try{

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
        } catch(SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }
}
