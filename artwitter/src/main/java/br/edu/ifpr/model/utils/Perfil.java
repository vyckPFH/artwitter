package br.edu.ifpr.model.utils;

import java.util.List;

public class Perfil {

    private Usuario usuarioPerfil;
    private String descricao;
    private String foto;
    private List<Perfil> seguidores;// se usuario 2 segue usuario 1, entao usuario 2 esta na lista de seguidores do usuario 1
    private List<Post> posts;

    public Perfil(Usuario usuarioPerfil, String descricao, String foto, List<Perfil> seguidores, List<Post> posts) {
        this.usuarioPerfil = usuarioPerfil;
        this.descricao = descricao;
        this.foto = foto;
        this.seguidores = seguidores;
        this.posts = posts;
    }

    public Perfil() {
    }

    public Usuario getUsuarioPerfil() {
        return usuarioPerfil;
    }

    public void setUsuarioPerfil(Usuario usuarioPerfil) {
        this.usuarioPerfil = usuarioPerfil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Perfil> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Perfil> seguidores) {
        this.seguidores = seguidores;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
