package br.edu.ifpr.model;

import java.util.List;

public class Post {

    private int id;
    private String imagemURL;
    private String descricao;
    private int likes;
    private List<Comentario> comentario;
    private Usuario owner;

}
