package br.edu.ifpr.model;

public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private Post feed;

    


    public Usuario(int id, String nome, String senha, Post feed) {
        this.id = id;
        this.nome = nome;
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
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Post getFeed() {
        return feed;
    }
    public void setFeed(Post feed) {
        this.feed = feed;
    }

}
