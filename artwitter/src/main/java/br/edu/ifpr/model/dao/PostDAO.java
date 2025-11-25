package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
   

}
