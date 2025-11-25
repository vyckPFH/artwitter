package br.edu.ifpr.controller;

import br.edu.ifpr.model.dao.PostDAO;
import br.edu.ifpr.model.utils.Post;

public class PostController {
    private PostDAO dao;

    public PostController() {
        this.dao = dao;
    }

    public void postar(Post post){
            if (post.getDescricao() == null || post.getDescricao().isEmpty()) {
                System.out.println("Descrição do post não pode ser nulo/vazio");
                return;
            }
            dao.insert(post);
        }

}
