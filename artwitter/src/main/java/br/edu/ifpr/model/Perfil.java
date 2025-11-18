package br.edu.ifpr.model;

import java.util.List;

public class Perfil {

    private int id;
    private Usuario usuarioPerfil;
    private String descricao;
    private String foto;
    private List<Perfil> seguidores;
    private List<Perfil> seguindo;
    private List<Post> posts;

    public Perfil(int id, Usuario usuarioPerfil, String descricao, String foto, List<Perfil> seguidores,
            List<Perfil> seguindo, List<Post> posts) {
        this.id = id;
        this.usuarioPerfil = usuarioPerfil;
        this.descricao = descricao;
        this.foto = foto;
        this.seguidores = seguidores;
        this.seguindo = seguindo;
        this.posts = posts;
    }

    public Perfil() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Perfil> getSeguindo() {
        return seguindo;
    }

    public void setSeguindo(List<Perfil> seguindo) {
        this.seguindo = seguindo;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
