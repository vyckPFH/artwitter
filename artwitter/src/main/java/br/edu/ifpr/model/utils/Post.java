package br.edu.ifpr.model.utils;

import java.util.List;

public class Post {

    private int id;
    private String imagemURL;
    private String descricao;
    private int likes;
    private List<Comentario> comentarios;
    private Usuario owner;

    public Post(int id, String imagemURL, String descricao, int likes, List<Comentario> comentarios, Usuario owner) {
        this.id = id;
        this.imagemURL = imagemURL;
        this.descricao = descricao;
        this.likes = likes;
        this.comentarios = comentarios;
        this.owner = owner;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagemURL() {
        return imagemURL;
    }

    public void setImagemURL(String imagemURL) {
        this.imagemURL = imagemURL;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentario) {
        this.comentarios = comentario;
    }

    public Usuario getOwner() {
        return owner;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
    }

}
