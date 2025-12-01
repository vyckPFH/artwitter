package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.edu.ifpr.model.utils.Comentario;

/**
 * Classe responsável por realizar operações de CRUD
 * relacionadas à entidade {@link Comentario} no banco de dados.
 */
public class ComentarioDAO {


    
    public ArrayList<Comentario> selectComentariosPorPost(int postId) {
        ArrayList<Comentario> comentarios = new ArrayList<>();
    
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM comentario WHERE post_idPost = ?";
    
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, postId);
    
            ResultSet rs = ps.executeQuery();
    
            while (rs.next()) {
                Comentario c = new Comentario();
                    rs.getString("texto");
                    rs.getInt("post_idPost");
                    rs.getInt("perfil_usuario_id");
                    rs.getInt("id");
                comentarios.add(c);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return comentarios;
    }
    

    /**
     * Retorna todos os comentários cadastrados no banco de dados.
     *
     * @return lista com todos os comentários
     */
    public ArrayList<Comentario> select() {

        Connection con = ConnectionFactory.getConnection();
        ArrayList<Comentario> comentarios = new ArrayList<>();

        try {

            String sql = "SELECT * FROM comentario";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Comentario c = new Comentario();

                c.setId(rs.getInt("id"));
                c.setTexto(rs.getString("texto"));
                c.setIdUsuario(rs.getInt("perfil_usuario_id"));
                c.setIdPost(rs.getInt("post_idPost"));

                comentarios.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comentarios;
    }

    /**
     * Insere um novo comentário no banco de dados.
     *
     * @param comentario objeto contendo os dados do comentário a ser inserido
     */
    public void insert(Comentario comentario) {

        Connection con = ConnectionFactory.getConnection();
        String sql = "INSERT INTO comentario(texto, perfil_usuario_id, post_idPost) VALUES (?, ?, ?)";
    
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    
            ps.setString(1, comentario.getTexto());
            ps.setInt(2, comentario.getIdUsuario());
            ps.setInt(3, comentario.getIdPost());
    
            ps.executeUpdate();
            System.out.println("Comentário inserido com sucesso!");
    
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                comentario.setId(rs.getInt(1)); // <-- salva ID no objeto
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    /**
     * Busca e retorna um comentário pelo seu ID.
     *
     * @param id identificador do comentário
     * @return o comentário encontrado, ou null se não existir
     */
    public Comentario selectPorId(int id) {

        Connection con = ConnectionFactory.getConnection();

        try {
            String sql = "SELECT * FROM comentario WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Comentario c = new Comentario();

                c.setId(rs.getInt("id"));
                c.setTexto(rs.getString("texto"));
                c.setIdUsuario(rs.getInt("perfil_usuario_id"));
                c.setIdPost(rs.getInt("post_idPost"));

                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Atualiza os dados de um comentário existente.
     *
     * @param comentario objeto contendo os novos dados do comentário
     */
    public void update(Comentario comentario) {

        Connection con = ConnectionFactory.getConnection();

        String sql = "UPDATE comentario SET texto = ?, perfil_usuario_id = ?, post_idPost = ? WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, comentario.getTexto());
            ps.setInt(2, comentario.getIdUsuario());
            ps.setInt(3, comentario.getIdPost());
            ps.setInt(4, comentario.getId());

            ps.executeUpdate();
            System.out.println("Comentário atualizado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deleta um comentário do banco de dados pelo ID.
     *
     * @param id identificador do comentário a ser removido
     */
    public void delete(int id) {

        Connection con = ConnectionFactory.getConnection();

        String sql = "DELETE FROM comentario WHERE id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Comentário deletado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}