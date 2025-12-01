package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.edu.ifpr.model.utils.Comentario;
import br.edu.ifpr.model.utils.Post;

/**
 * Classe responsável por realizar operações de CRUD e consultas.
 * relacionadas à entidade {@link Post} no banco de dados obviamente.
 */
public class PostDAO {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * bota um novo post no banco de dados.
     *
     * @param post objeto contendo os dados do post a ser botado.
     */
    public void insert(Post post) {
        Connection con = ConnectionFactory.getConnection();
        String sql = "INSERT INTO post (imagemURL, descricao, likes, perfil_usuario_id) VALUES (?,?,?,?)";
    
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    
            ps.setString(1, post.getImagemURL());
            ps.setString(2, post.getDescricao());
            ps.setInt(3, post.getLikes());
            ps.setInt(4, post.getUsuarioId());
    
            ps.executeUpdate();
            System.out.println("Post inserido cm sucesso");
    
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                post.setId(rs.getInt(1));
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    /**
     * Retorna todes os posts cadastrados no banco de dados.
     *
     * @return lista contendo tds os posts
     */
    public ArrayList<Post> select() {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Post> posts = new ArrayList<>();

        try {
            String sql = "SELECT * FROM post";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Post post = new Post();

                post.setId(rs.getInt("id"));
                post.setImagemURL(rs.getString("imagemURL"));
                post.setDescricao(rs.getString("descricao"));
                post.setLikes(rs.getInt("likes"));
                post.setUsuarioId(rs.getInt("perfil_usuario_id"));

                posts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    /**
     * Busca e retorna um post específico pelo ID.
     *
     * @param id identificador do post
     * @return o post encontrado ou null se não existir
     */
    public Post selectById(int id) {

        Connection con = ConnectionFactory.getConnection();
        Post post = null;

        try {
            String sql = "SELECT * FROM post WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                post = new Post();

                post.setId(rs.getInt("id"));
                post.setImagemURL(rs.getString("imagemURL"));
                post.setDescricao(rs.getString("descricao"));
                post.setLikes(rs.getInt("likes"));
                post.setUsuarioId(rs.getInt("perfil_usuario_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return post;
    }

    /**
     * Retorna todos os comentários relacionados a um post.
     *
     * @param post post do qual os comentários serão buscados
     * @return lista de comentários do post
     */
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
                comentario.setTexto(rs.getString("texto"));
                comentario.setIdUsuario(rs.getInt("perfil_usuario_id"));
                comentario.setIdPost(rs.getInt("post_idPost"));

                comentarios.add(comentario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comentarios;
    }

    /**
     * Atualiza os dados de um post
     *
     * @param post objeto contendo os novos dados do post
     */
    public void update(Post post) {

        Connection con = ConnectionFactory.getConnection();
        String sql = "UPDATE post SET imagemURL = ?, descricao = ?, likes = ?, perfil_usuario_id = ? WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, post.getImagemURL());
            ps.setString(2, post.getDescricao());
            ps.setInt(3, post.getLikes());
            ps.setInt(4, post.getUsuarioId());
            ps.setInt(5, post.getId());

            ps.executeUpdate();
            System.out.println("Post atualizado cm sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deleta um post, obvio.
     *
     * @param id identificador do post a ser removido
     */
    public void delete(int id) {

        Connection con = ConnectionFactory.getConnection();
        String sql = "DELETE FROM post WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Post deletado cm sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * adiciona um like no post, dãã~
     *
     * @param idPost identificador do post
     */
    public void darLike(int idPost) {

        Connection con = ConnectionFactory.getConnection();
        String sql = "UPDATE post SET likes = likes + 1 WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPost);
            ps.executeUpdate();

            System.out.println("Like adicionado");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * remove um like no post, dã-
     *
     * @param idPost identificador do post
     */
    public void removerLike(int idPost) {

        Connection con = ConnectionFactory.getConnection();
        String sql = "UPDATE post SET likes = likes - 1 WHERE id = ? AND likes > 0";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPost);
            ps.executeUpdate();

            System.out.println("Like removido");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza somente a descrição de um post.
     *
     * @param id            identificador do post
     * @param novaDescricao nova descrição a ser atribuída
     */
    public void updateDescricao(int id, String novaDescricao) {

        Connection con = ConnectionFactory.getConnection();
        String sql = "UPDATE post SET descricao = ? WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, novaDescricao);
            ps.setInt(2, id);

            ps.executeUpdate();
            System.out.println("Descrição atualizada cm sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza somente a imagem de um post.
     *
     * @param id            identificador do post
     * @param novaImagemURL URL da nova imagem
     */
    public void updateImagem(int id, String novaImagemURL) {

        Connection con = ConnectionFactory.getConnection();
        String sql = "UPDATE post SET imagemURL = ? WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, novaImagemURL);
            ps.setInt(2, id);

            ps.executeUpdate();
            System.out.println("Imagem atualizada cm sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
