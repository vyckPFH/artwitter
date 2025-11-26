package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ifpr.model.utils.Comentario;
import br.edu.ifpr.model.utils.Usuario;

public class ComentarioDAO {

    public ArrayList<> select(Comentario comentario){
          Connection con = ConnectionFactory.getConnection();

        ArrayList<Comentario> comentarios = new ArrayList<>();

        try{

            String sql = "SELECT post.descricao AS nomePost, comentario.texto as comentario, comentario.perfil_usuario_id as usuarioQueComentou FROM post join comentario on comentario.post_idPost = post.id;";
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

    public void insert(Comentario comentario) {

        Connection con = ConnectionFactory.getConnection();

        String sql = ("INSERT INTO comentario(texto, perfil_usuario_id, post_idPost) VALUES(?,?,?);");

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, comentario.getText());
            ps.setInt(2, comentario.getUsuarioComentario().getId());
            ps.setInt(3, comentario.getPost().getId());

            ps.executeUpdate();
            System.out.println("Post inserido cm sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
