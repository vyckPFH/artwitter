package br.edu.ifpr.controller;

import java.util.ArrayList;

import br.edu.ifpr.model.dao.PostDAO;
import br.edu.ifpr.model.utils.Perfil;
import br.edu.ifpr.model.utils.Post;
import br.edu.ifpr.model.utils.Usuario;

public class PostController {

    private PostDAO dao;
    private SeguindoController seguindoController = new SeguindoController();
    private PerfilController perfilController = new PerfilController();

    public PostController() {
        this.dao = new PostDAO();
    }

    public void postar(Post post) {
        if (post.getDescricao() == null || post.getDescricao().isBlank()) {
            System.out.println("A descrição não pode ser vazia.");
            return;
        }
        if (post.getImagemURL() == null || post.getImagemURL().isBlank()) {
            System.out.println("A imagem não pode ser vazia.");
            return;
        }
        if (post.getPostOwner() == null || post.getPostOwner().getPerfilOwner() == null
                || post.getPostOwner().getPerfilOwner().getId() <= 0) {
            System.out.println("O post precisa pertencer a um usuário válido.");
            return;
        }
        dao.insert(post);
    }

    public ArrayList<Post> listarFeed(Usuario usuario) {
        ArrayList<Post> feed = new ArrayList<>();
        Perfil meuPerfil = perfilController.buscarPorId(usuario.getId());
        if (meuPerfil == null)
            return feed;

        ArrayList<Usuario> usuariosSeguidos = seguindoController.listarSeguidos(usuario);
        for (Usuario u : usuariosSeguidos) {
            Perfil p = perfilController.buscarPorId(u.getId());
            if (p != null) feed.addAll(listarPorPerfil(p));
        }
        return feed;
    }

    public ArrayList<Post> listarPorPerfil(Perfil perfil) {
        return dao.selectByPerfil(perfil);
    }

    public ArrayList<Post> listar() {
        return dao.select();
    }

    public Post buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("ID inválido.");
            return null;
        }
        return dao.selectById(id);
    }

    public void atualizar(Post post) {
        if (post.getId() <= 0) {
            System.out.println("ID do post inválido.");
            return;
        }
        dao.update(post);
    }

    public void atualizarDescricao(Post post, String descricao) {
        if (descricao == null || descricao.isBlank()) {
            System.out.println("Descrição não pode ser vazia.");
            return;
        }
        dao.updateDescricao(post, descricao);
    }

    public void atualizarImagem(Post post, String novaImagem) {
        if (novaImagem == null || novaImagem.isBlank()) {
            System.out.println("Imagem não pode ser vazia.");
            return;
        }
        if (post.getId() <= 0) {
            System.out.println("Post deve existir.");
            return;
        }
        dao.updateImagem(post, novaImagem);
    }

    public void deletar(Post post) {
        if (post.getId() <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        dao.delete(post);
    }

    public void darLike(Post post) {
        dao.darLike(post);
    }

    public void removerLike(Post post) {
        dao.removerLike(post);
    }

}
