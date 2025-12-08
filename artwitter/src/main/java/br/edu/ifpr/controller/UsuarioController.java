package br.edu.ifpr.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import br.edu.ifpr.model.dao.UsuarioDAO;
import br.edu.ifpr.model.utils.Usuario;

/**
 * Controlador responsável por gerenciar operações relacionadas
 * a {@link Usuario}, realizando validações e repassando chamadas
 * para o {@link UsuarioDAO}.
 */
public class UsuarioController {

    final static Scanner LER = new Scanner(System.in);

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

    public boolean validarUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            System.out.println("Erro: Nome não pode ser nulo ou vazio.");
            return false;
        }
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            System.out.println("Nome inválido!");
            return false;
        }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            System.out.println("Erro: Email não pode ser nulo ou vazio.");
            return false;
        }
        if (!usuario.getEmail().contains("@")) {
            System.out.println("Email inválido!");
            return false;
        }

        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            System.out.println("Erro: Senha não pode ser nula ou vazia.");
            return false;
        }
        if (usuario.getSenha().length() < 6) {
            System.out.println("Senha muito curta! minimo 6 caracteres");
            return false;
        }

        return true;
    }

    public Usuario login(String nome, String senha) {
        ArrayList<Usuario> usuarios = dao.select();

        for (Usuario u : usuarios) {
            if (u.getNome().equals(nome) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Cadastra um usuário no sistema após validar seus dados.
     *
     * @param usuario objeto contendo as informações do usuário a ser cadastrado
     */
    public void cadastrarUsuario(Usuario usuario) {

        do {
            System.out.println("···Inicio de Cadastro···");

            System.out.print("Nome: ");
            usuario.setNome(LER.next());

            System.out.print("Email: ");
            usuario.setEmail(LER.next());

            System.out.print("Senha: ");
            usuario.setSenha(LER.next());

        } while (!validarUsuario(usuario));

        try {

            dao.insert(usuario);
            System.out.println("Conta criada com sucesso!");

        } catch (SQLException e) {

            if (e.getMessage().contains("Duplicate entry")) {
                // Esse erro acontece quando o nome é único no banco
                System.out.println("Erro: Este nome já está em uso!");
            } else {
                System.out.println("Erro no banco de dados: " + e.getMessage());
            }

        }
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
