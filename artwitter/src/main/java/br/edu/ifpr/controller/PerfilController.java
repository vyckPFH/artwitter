package br.edu.ifpr.controller;

import java.util.ArrayList;

import br.edu.ifpr.model.dao.PerfilDAO;
import br.edu.ifpr.model.utils.Perfil;

public class PerfilController {

    private PerfilDAO dao;

    public PerfilController() {
        this.dao = new PerfilDAO();
    }

    public void cadastrarPerfil(Perfil perfil) {
        if (perfil.getPerfilOwner() == null || perfil.getPerfilOwner().getId() <= 0) {
            System.out.println("ID do perfil inválido");
            return;
        }
        dao.insert(perfil);
    }

    public ArrayList<Perfil> listarPerfis() {
        return dao.select();
    }

    public Perfil buscarPorId(int perfilId) {
        return dao.selectPorId(perfilId);
    }

    public void atualizarPerfil(Perfil perfil) {
        if (perfil.getPerfilOwner() == null || perfil.getPerfilOwner().getId() <= 0) {
            System.out.println("ID do perfil inválido");
            return;
        }
        dao.update(perfil);
    }

    public void atualizarDescricao(Perfil perfil, String novaDescricao) {
        if (novaDescricao == null || novaDescricao.isBlank()) {
            System.out.println("Descrição não pode ser nula ou vazia");
            return;
        }
        dao.updateDescricao(perfil, novaDescricao);
    }

    public void atualizarFoto(Perfil perfil, String novaFoto) {
        if (novaFoto == null || novaFoto.isBlank()) {
            System.out.println("Foto não pode ser nula ou vazia");
            return;
        }
        dao.updateFoto(perfil, novaFoto);
    }

    public void deletarPerfil(Perfil perfil) {
        dao.delete(perfil);
    }
}
