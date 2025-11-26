package br.edu.ifpr.controller;

import br.edu.ifpr.model.dao.PostDAO;
import br.edu.ifpr.model.utils.Post;

public class PostController {
    private PostDAO dao;

    public PostController() {
        this.dao = new PostDAO();
    }

    public void postar(Post post){
            if ( (post.getDescricao() == null || post.getDescricao().isEmpty()) ||
             (post.getImagemURL().isEmpty() || post.getImagemURL().isBlank())) {
                System.out.println("Descrição do post ou imagem não pode ser nulo/vazio");
                return;
            }
            dao.insert(post);
        }

}
