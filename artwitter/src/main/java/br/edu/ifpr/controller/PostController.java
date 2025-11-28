package br.edu.ifpr.controller;

import java.util.ArrayList;

import br.edu.ifpr.model.dao.PostDAO;
import br.edu.ifpr.model.utils.Comentario;
import br.edu.ifpr.model.utils.Post;

public class PostController {
    private PostDAO dao;

    public PostController() {
        this.dao = new PostDAO();
    }

    public ArrayList<Comentario> acessarComentariosDoPost(Post post) {
        if (post == null || post.getId() <= 0 || post.getOwner() == null || post.getOwner().getId() <= 0) {
            System.out.println("Comentário deve pertencer a um post e ter um dono");
            return new ArrayList<>();
        }
        return dao.selectComentariosDoPost(post);
    }

    public void postar(Post post) {
        if ((post.getDescricao() == null || post.getDescricao().isEmpty()) ||
                (post.getImagemURL().isEmpty() || post.getImagemURL().isBlank())) {
            System.out.println("Descrição do post ou imagem não pode ser nulo/vazio");
            return;
        }
        dao.insert(post);
    }

}
