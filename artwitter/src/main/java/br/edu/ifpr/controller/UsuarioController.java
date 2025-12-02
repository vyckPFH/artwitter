package br.edu.ifpr.controller;

import java.util.ArrayList;

import br.edu.ifpr.model.dao.UsuarioDAO;
import br.edu.ifpr.model.utils.Usuario;

/**
 * Controlador responsável por gerenciar operações relacionadas
 * a {@link Usuario}, realizando validações e repassando chamadas
 * para o {@link UsuarioDAO}.
 */
public class UsuarioController {

    private UsuarioDAO dao;

    public Usuario buscarPorNome(String nome) {
        return dao.buscarPorNome(nome);
    }

    /**
     * Construtor que inicializa o controlador com uma instância de
     * {@link UsuarioDAO}.
     */
    public UsuarioController() {
        this.dao = new UsuarioDAO();
    }

    /**
     * Cadastra um usuário no sistema após validar seus dados.
     *
     * @param usuario objeto contendo as informações do usuário a ser cadastrado
     */
    public void cadastrarUsuario(Usuario usuario) {

        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            System.out.println("Erro: Nome não pode ser nulo ou vazio.");
            return;
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            System.out.println("Erro: Email não pode ser nulo ou vazio.");
            return;
        }
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            System.out.println("Erro: Senha não pode ser nula ou vazia.");
            return;
        }

        dao.insert(usuario);
    }

    /**
     * Retorna uma lista contendo todos os usuários cadastrados.
     *
     * @return lista de usuários
     */
    public ArrayList<Usuario> listarUsuarios() {
        return dao.select();
    }

    /**
     * Busca um usuário pelo seu ID.
     *
     * @param id identificador do usuário
     * @return o usuário encontrado ou null se não existir
     */
    public Usuario buscarPorId(int id) {
        return dao.selectPorId(id);
    }

    /**
     * Remove um usuário do sistema com base no seu ID.
     *
     * @param id identificador do usuário a ser deletado
     */
    public void deletarUsuario(Usuario usuario) {
        dao.delete(usuario);
    }

    /**
     * Atualiza os dados de um usuário existente.
     *
     * @param u objeto contendo os novos dados do usuário
     */
    public void atualizarUsuario(Usuario u) {
        dao.update(u);
    }
}
