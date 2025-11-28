package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ifpr.model.utils.Comentario;

public class ComentarioDAO {

    // public ArrayList<Comentario> selectComentariosDoPost(Comentario comentario){
    //     Connection con = ConnectionFactory.getConnection();

    //     ArrayList<Comentario> comentarios = new ArrayList<>();

    //     try{

    //         String sql = "SELECT post.descricao AS nomePost, comentario.texto as comentario, comentario.perfil_usuario_id as usuarioQueComentou FROM post join comentario on comentario.post_idPost = post.id where post.id = ?;";
    //         PreparedStatement ps = con.prepareStatement(sql);
            
    //         ResultSet rs = ps.executeQuery();

    //         while (rs.next()) {

    //             comentario.setId(rs.getInt("id"));
    //             comentario.setText(rs.getString("texto"));
    //             comentario.setIdUsuario(rs.getInt("perfil_usuario_id"));
    //             comentario.setIdPost(rs.getInt("post_idPost"));


    //             comentarios.add(comentario);
    //         }
    //     } catch(SQLException e){
    //         e.printStackTrace();
    //     }
    //     return comentarios;
    // }

    public ArrayList<Comentario> select(Comentario comentario){
        Connection con = ConnectionFactory.getConnection();

        ArrayList<Comentario> comentarios = new ArrayList<>();

        try{

            String sql = "SELECT post.descricao, comentario.texto, comentario.perfil_usuario_id FROM post join comentario on comentario.post_idPost = post.id;";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                comentario.setId(rs.getInt("id"));
                comentario.setText(rs.getString("texto"));
                comentario.setIdUsuario(rs.getInt("perfil_usuario_id"));
                comentario.setIdPost(rs.getInt("post_idPost"));
                ps.setInt(3, comentario.getIdPost());

                comentarios.add(comentario);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return comentarios;
    }

    public void insert(Comentario comentario) {

        Connection con = ConnectionFactory.getConnection();

        String sql = ("INSERT INTO comentario(texto, perfil_usuario_id, post_idPost) VALUES(?,?,?);");

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, comentario.getText());
            ps.setInt(2, comentario.getIdUsuario());
            ps.setInt(3, comentario.getIdPost());

            ps.executeUpdate();
            System.out.println("Post inserido cm sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
