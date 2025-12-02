package br.edu.ifpr.controller;

import java.util.ArrayList;

import br.edu.ifpr.model.dao.PostDAO;
import br.edu.ifpr.model.utils.Post;

/**
 * Controlador responsável por gerenciar operações de criação, leitura,
 * atualização e remoção de {@link Post}, aplicando regras de validação antes
 * de acessar a camada de persistência ({@link PostDAO}).
 */
public class PostController {

    private PostDAO dao;

    /**
     * Construtor padrão que inicializa um {@link PostDAO} para acesso ao banco.
     */
    public PostController() {
        this.dao = new PostDAO();
    }

    /**
     * Publica um novo post após validar seus dados obrigatórios.
     *
     * @param post objeto contendo os dados do post a ser inserido
     */
    public void postar(Post post) {

        if (post.getDescricao() == null || post.getDescricao().isBlank()) {
            System.out.println("A descrição não pode ser vazia.");
            return;
        }

        if (post.getImagemURL() == null || post.getImagemURL().isBlank()) {
            System.out.println("A imagem não pode ser vazia.");
            return;
        }

        if (post.getPostOwner().getPerfilOwner().getId() <= 0) {
            System.out.println("O post precisa pertencer a um usuário válido.");
            return;
        }

        dao.insert(post);
    }

    /**
     * Retorna todos os posts cadastrados.
     *
     * @return lista contendo todos os posts armazenados no banco
     */
    public ArrayList<Post> listar() {
        return dao.select();
    }

    /**
     * Busca um post específico pelo seu identificador.
     *
     * @param id identificador do post
     * @return o post correspondente ou {@code null} caso não exista
     */
    public Post buscarPorId(int id) {
        if (id <= 0) {
            System.out.println("ID inválido.");
            return null;
        }
        return dao.selectById(id);
    }

    /**
     * Atualiza todos os dados de um post existente.
     *
     * @param post objeto contendo os novos dados do post
     */
    public void atualizar(Post post) {
        if (post.getId() <= 0) {
            System.out.println("ID do post inválido.");
            return;
        }
        dao.update(post);
    }

    /**
     * Atualiza somente a descrição de um post.
     *
     * @param post        identificador do post
     * @param descricao nova descrição a ser atribuída
     */
    public void atualizarDescricao(Post post, String descricao) {
        if (descricao == null || descricao.isBlank()) {
            System.out.println("Descrição não pode ser vazia.");
            return;
        }
        dao.updateDescricao(post, descricao);
    }

    /**
     * Atualiza somente a imagem de um post.
     *
     * @param post         identificador do post
     * @param novaImagem URL da nova imagem
     */
    public void atualizarImagem(Post post, String novaImagem) {
        if (novaImagem == null || novaImagem.isBlank()) {
            System.out.println("Imagem não pode ser vazia.");
            return;
        }
         if (post.getId() <= 0 ) {
            System.out.println("Post deve existir.");
            return;
        }
        dao.updateImagem(post, novaImagem);
    }

    /**
     * Remove um post do banco de dados.
     *
     * @param post identificador do post a ser removido
     */
    public void deletar(Post post) {
        if (post.getId() <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        dao.delete(post);
    }

    /**
     * Incrementa o número de likes de um post.
     *
     * @param post identificador do post
     */
    public void darLike(Post post) {
        dao.darLike(post);
    }

    /**
     * Decrementa o número de likes de um post.
     *
     * @param post identificador do post
     */
    public void removerLike(Post post) {
        dao.removerLike(post);
    }
}
