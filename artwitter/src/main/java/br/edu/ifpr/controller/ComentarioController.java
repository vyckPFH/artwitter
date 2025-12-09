package br.edu.ifpr.controller;

import java.util.ArrayList;

import br.edu.ifpr.model.dao.ComentarioDAO;
import br.edu.ifpr.model.utils.Comentario;
import br.edu.ifpr.model.utils.Perfil;
import br.edu.ifpr.model.utils.Post;
import br.edu.ifpr.model.utils.Usuario;

public class ComentarioController {

    private ComentarioDAO dao;

    public ComentarioController() {
        this.dao = new ComentarioDAO();
    }

    public ArrayList<Comentario> listarDePost(Post post) {
        return dao.selectComentariosPorPost(post);
    }

    public Comentario buscarPorId(int id) {
        return dao.selectPorId(id);
    }

    // Adiciona comentário com validação
    public boolean comentar(String texto, Post post, Perfil perfilUsuario) {
        if (texto == null || texto.isBlank()) {
            System.out.println("Comentário vazio, operação cancelada.");
            return false;
        }
        Comentario c = new Comentario(texto, post.getId(), perfilUsuario);
        dao.insert(c);
        System.out.println("Comentário publicado!");
        return true;
    }

    // Edita comentário com validação de dono
    public boolean editarComentario(int comentarioId, Usuario usuario, String novoTexto) {
        Comentario comentario = buscarPorId(comentarioId);
        if (comentario == null || comentario.getComentOwner() == null
            || comentario.getComentOwner().getPerfilOwner().getId() != usuario.getId()) {
            System.out.println("Comentário inválido ou você não tem permissão.");
            return false;
        }
        if (novoTexto == null || novoTexto.isBlank()) {
            System.out.println("Novo texto vazio, operação cancelada.");
            return false;
        }
        comentario.setTexto(novoTexto);
        dao.update(comentario);
        System.out.println("Comentário atualizado!");
        return true;
    }

    // Deleta comentário com validação de dono
    public boolean deletarComentario(int comentarioId, Usuario usuario) {
        Comentario comentario = buscarPorId(comentarioId);
        if (comentario == null || comentario.getComentOwner() == null
            || comentario.getComentOwner().getPerfilOwner().getId() != usuario.getId()) {
            System.out.println("Comentário inválido ou você não tem permissão.");
            return false;
        }
        dao.delete(comentario);
        System.out.println("Comentário deletado!");
        return true;
    }
}
