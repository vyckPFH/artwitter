package br.edu.ifpr.model.utils;

import java.util.List;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private List<Post> feed;

    public Usuario(int id, String nome, String email, String senha, List<Post> feed) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.feed = feed;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Post> getFeed() {
        return feed;
    }

    public void setFeed(List<Post> feed) {
        this.feed = feed;
    }

}
