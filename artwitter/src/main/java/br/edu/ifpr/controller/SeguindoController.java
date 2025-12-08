package br.edu.ifpr.controller;

import java.util.ArrayList;

import br.edu.ifpr.model.dao.SeguindoDAO;
import br.edu.ifpr.model.utils.Seguindo;
import br.edu.ifpr.model.utils.Usuario;

public class SeguindoController {

    private SeguindoDAO dao;

    public SeguindoController() {
        this.dao = new SeguindoDAO();
    }

    public void seguir(Usuario seguidor, Usuario seguido) {

        if (seguidor.getId() == seguido.getId()) {
            System.out.println("Você não pode seguir a si mesmo.");
            return;
        }

        Seguindo s = new Seguindo(seguidor, seguido);

        if (dao.existe(s)) {
            System.out.println("Você já segue @" + seguido.getNome());
            return;
        }

        dao.insert(s);
        System.out.println("Agora você segue @" + seguido.getNome());
    }

    public void deixarDeSeguir(Usuario seguidor, Usuario seguido) {
        Seguindo s = new Seguindo(seguidor, seguido);

        if (!dao.existe(s)) {
            System.out.println("Você não segue @" + seguido.getNome());
            return;
        }

        dao.delete(s);
        System.out.println("Você deixou de seguir @" + seguido.getNome());
    }

    public ArrayList<Usuario> listarSeguidos(Usuario seguidor) {
        ArrayList<Seguindo> lista = dao.selectSeguidos(seguidor);

        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (Seguindo s : lista)
            usuarios.add(s.getSeguido());

        return usuarios;
    }

    public ArrayList<Usuario> listarSeguidores(Usuario seguido) {
        ArrayList<Seguindo> lista = dao.selectSeguidores(seguido);

        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (Seguindo s : lista)
            usuarios.add(s.getSeguidor());

        return usuarios;
    }
}
