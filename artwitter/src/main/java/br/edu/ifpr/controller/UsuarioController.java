package br.edu.ifpr.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import br.edu.ifpr.model.dao.UsuarioDAO;
import br.edu.ifpr.model.utils.Usuario;

public class UsuarioController {

    final static Scanner LER = new Scanner(System.in);
    private UsuarioDAO dao;

    public UsuarioController() {
        this.dao = new UsuarioDAO();
    }

    public boolean validarUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            System.out.println("Nome inválido!");
            return false;
        }
        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            System.out.println("Email inválido!");
            return false;
        }
        if (usuario.getSenha() == null || usuario.getSenha().length() < 6) {
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
                System.out.println("Erro: Este nome já está em uso!");
            } else {
                System.out.println("Erro no banco de dados: " + e.getMessage());
            }
        }
    }

    public ArrayList<Usuario> listarUsuarios() {
        return dao.select();
    }

    public Usuario buscarPorId(int id) {
        return dao.selectPorId(id);
    }

    public Usuario buscarPorNome(String nome) {
        return dao.buscarPorNome(nome);
    }

    public void deletarUsuario(Usuario usuario) {
        dao.delete(usuario);
    }

    public void atualizarUsuario(Usuario u) {
        dao.update(u);
    }
}
