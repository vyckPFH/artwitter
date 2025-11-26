package br.edu.ifpr.controller;

import br.edu.ifpr.model.dao.ComentarioDAO;
import br.edu.ifpr.model.utils.Comentario;

public class ComentarioController {
    private ComentarioDAO dao;

    public ComentarioController() {
        this.dao = dao;
    }

    public void comentar(Comentario comentario){
        if (comentario.getText() == null || comentario.getText().isEmpty()) {
            System.out.println("Comentario não pode ser nulo/vazio");
            return;
        }
        dao.insert(comentario);
    }
}
