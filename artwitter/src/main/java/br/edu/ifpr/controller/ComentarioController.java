package br.edu.ifpr.controller;

import java.util.ArrayList;

import br.edu.ifpr.model.dao.ComentarioDAO;
import br.edu.ifpr.model.utils.Comentario;

/**
 * Controlador responsável por gerenciar operações relacionadas
 * à entidade {@link Comentario}. Realiza validações básicas e
 * delega operações ao {@link ComentarioDAO}.
 */
public class ComentarioController {

    private ComentarioDAO dao;

    /**
     * Construtor que inicializa o controlador com uma instância de {@link ComentarioDAO}.
     */
    public ComentarioController() {
        this.dao = new ComentarioDAO();
    }

    /**
     * Adiciona um novo comentário ao sistema após validar seu conteúdo.
     *
     * @param comentario objeto contendo o texto e as informações do comentário
     */
    public void comentar(Comentario comentario) {
        if (comentario.getTexto() == null || comentario.getTexto().isEmpty()) {
            System.out.println("Comentário não pode ser nulo/vazio");
            return;
        }

        dao.insert(comentario);
    }

    /**
     * Retorna uma lista contendo todos os comentários cadastrados.
     *
     * @return lista de comentários
     */
    public ArrayList<Comentario> listarComentarios() {
        return dao.select();
    }

    /**
     * Busca um comentário pelo seu ID.
     *
     * @param id identificador do comentário
     * @return o comentário encontrado ou null caso não exista
     */
    public Comentario buscarPorId(int id) {
        return dao.selectPorId(id);
    }

    /**
     * Atualiza os dados de um comentário existente.
     *
     * @param comentario objeto contendo os novos valores do comentário
     */
    public void atualizarComentario(Comentario comentario) {
        dao.update(comentario);
    }

    /**
     * Remove um comentário do sistema com base no seu ID.
     *
     * @param id identificador do comentário a ser removido
     */
    public void deletarComentario(int id) {
        dao.delete(id);
    }
}
