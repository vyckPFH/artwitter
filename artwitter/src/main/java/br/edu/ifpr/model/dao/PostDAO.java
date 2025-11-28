package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ifpr.model.utils.Comentario;
import br.edu.ifpr.model.utils.Post;
import br.edu.ifpr.model.utils.Usuario;

public class PostDAO {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();


    public void insert(Post post) {

        Connection con = ConnectionFactory.getConnection();

        String sql = ("INSERT INTO post(imagemURL, descricao, likes, perfil_usuario_id) VALUES(?,?,?,?);");

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, post.getImagemURL());
            ps.setString(2, post.getDescricao());
            ps.setInt(3, post.getLikes());
            ps.setInt(4, post.getOwner().getId());

            ps.executeUpdate();
            System.out.println("Post inserido cm sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Post> select() {

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Post> posts = new ArrayList<>();

        try {

            String sql = "SELECT * FROM post";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Post post = new Post();
                ArrayList<Comentario> comentarios = new ArrayList<Comentario>();

                post.setId(rs.getInt("id"));
                post.setComentarios(comentarios);
                post.setImagemURL(rs.getString("imagemURL"));
                post.setLikes(rs.getInt("likes"));
                post.setDescricao(rs.getString("descricao"));
                int ownerId = rs.getInt("perfil_usuario_id");
                Usuario owner = usuarioDAO.selectById(ownerId);
                post.setOwner(owner);

                comentarios.addAll(selectComentariosDoPost(post));

                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public ArrayList<Comentario> selectComentariosDoPost(Post post) {
       
        Connection con = ConnectionFactory.getConnection();
        ArrayList<Comentario> comentarios = new ArrayList<>();

        try {
            String sql = "SELECT * FROM comentario WHERE post_idPost = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, post.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Comentario comentario = new Comentario();

                comentario.setId(rs.getInt("id"));
                comentario.setText(rs.getString("texto"));
                comentario.setIdUsuario(rs.getInt("perfil_usuario_id"));
                comentario.setIdPost(rs.getInt("post_idPost"));

                comentarios.add(comentario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comentarios;
    }

}
