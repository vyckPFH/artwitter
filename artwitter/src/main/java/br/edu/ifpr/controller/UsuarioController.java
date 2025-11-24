package br.edu.ifpr.controller;

import java.util.ArrayList;

import br.edu.ifpr.model.dao.UsuarioDAO;
import br.edu.ifpr.model.utils.Usuario;

public class UsuarioController {
    private UsuarioDAO dao;

    public UsuarioController(){
        this.dao = new UsuarioDAO();
    }

    public void cadastrarUsuario(Usuario usuario){
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            System.out.println("Nome não pode ser nulo/vazio");
            return;
        }
        dao.insert(usuario);
    }

    public ArrayList<Usuario> selectUsuario(){
        return dao.select();
    }
}
