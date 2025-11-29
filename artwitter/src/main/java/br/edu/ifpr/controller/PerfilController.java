package br.edu.ifpr.controller;

import java.util.ArrayList;

import br.edu.ifpr.model.dao.PerfilDAO;
import br.edu.ifpr.model.utils.Perfil;

/**
 * Controlador responsável por gerenciar operações de criação, leitura,
 * atualização e remoção de {@link Perfil}, aplicando regras de validação antes
 * de acessar a camada de persistência ({@link PerfilDAO}).
 */
public class PerfilController {

    private PerfilDAO dao;

    /**
     * Construtor padrão que inicializa um {@link PerfilDAO} para acesso ao banco.
     */
    public PerfilController() {
        this.dao = new PerfilDAO();
    }

    /**
     * Cadastra um novo perfil após validar o ID do usuário.
     *
     * @param perfil objeto contendo os dados do perfil a ser inserido
     */
    public void cadastrarPerfil(Perfil perfil) {
        if (perfil.getUsuarioId() <= 0) {
            System.out.println("ID do usuário inválido");
            return;
        }
        dao.insert(perfil);
    }

    /**
     * Retorna todos os perfis cadastrados no banco de dados.
     *
     * @return lista contendo todos os perfis
     */
    public ArrayList<Perfil> listarPerfis() {
        return dao.select();
    }

    /**
     * Busca um perfil pelo ID do usuário.
     *
     * @param usuarioId identificador do usuário
     * @return perfil correspondente ou {@code null} caso não exista
     */
    public Perfil buscarPerfil(int usuarioId) {
        return dao.selectPorId(usuarioId);
    }

    /**
     * Atualiza todos os dados de um perfil existente.
     *
     * @param perfil objeto contendo os novos dados do perfil
     */
    public void atualizarPerfil(Perfil perfil) {
        if (perfil.getUsuarioId() <= 0) {
            System.out.println("ID do usuário inválido");
            return;
        }
        dao.update(perfil);
    }

    /**
     * Atualiza apenas a descrição de um perfil.
     *
     * @param usuarioId     identificador do usuário
     * @param novaDescricao nova descrição a ser atribuída
     */
    public void atualizarDescricao(int usuarioId, String novaDescricao) {
        if (novaDescricao == null || novaDescricao.isBlank()) {
            System.out.println("Descrição não pode ser nula ou vazia");
            return;
        }
        dao.updateDescricao(usuarioId, novaDescricao);
    }

    /**
     * Atualiza apenas a foto de um perfil.
     *
     * @param usuarioId identificador do usuário
     * @param novaFoto  URL da nova foto a ser atribuída
     */
    public void atualizarFoto(int usuarioId, String novaFoto) {
        if (novaFoto == null || novaFoto.isBlank()) {
            System.out.println("Foto não pode ser nula ou vazia");
            return;
        }
        dao.updateFoto(usuarioId, novaFoto);
    }

    /**
     * Remove um perfil do banco de dados pelo ID do usuário.
     *
     * @param usuarioId identificador do usuário
     */
    public void deletarPerfil(int usuarioId) {
        dao.delete(usuarioId);
    }
}
