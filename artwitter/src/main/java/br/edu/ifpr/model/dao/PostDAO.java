package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ifpr.model.utils.Post;


public class PostDAO {
    

 public void insert(Post post){
        
        Connection con = ConnectionFactory.getConnection();

        String sql = ("INSERT INTO post(imagemURL, descricao, likes, perfil_usuario_id) VALUES(?,?,?,?);");
        
        try{

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, post.getImagemURL());
            ps.setString(2, post.getDescricao());
            ps.setInt(3, post.getLikes());
            ps.setInt(4, post.getOwner().getId());

            ps.executeUpdate();
            System.out.println("Post inserido cm sucesso");

            }catch (SQLException e){
                e.printStackTrace();
            }
            
        }
   
         public ArrayList<Post> select(){

        Connection con = ConnectionFactory.getConnection();

        ArrayList<Post> posts = new ArrayList<>();

        try{

            String sql = "SELECT * FROM post";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Post post = new Post();

                
                post.setId(rs.getInt("id"));
                post.setComentario(
               
                
                usuarios.add(usuario);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }

}
