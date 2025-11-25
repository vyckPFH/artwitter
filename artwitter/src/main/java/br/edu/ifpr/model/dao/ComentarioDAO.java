package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.ifpr.model.utils.Comentario;

public class ComentarioDAO {

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
