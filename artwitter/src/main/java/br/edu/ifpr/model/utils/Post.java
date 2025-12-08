package br.edu.ifpr.model.utils;

public class Post {

    private int id;
    private String imagemURL;
    private String descricao;
    private int likes = 0;
    private Perfil postOwner;
    // @Override
    // public String toString() {
    // return "Post { " +
    // "id=" + id +
    // ", imgURL='" + imagemURL + '\'' +
    // ", descricao='" + descricao + '\'' +
    // ", usuarioId=" + usuarioId +
    // ", likes=" + likes +
    // " }";
    // }

    public Post(int id, String imagemURL, String descricao, int likes, Perfil postOwner) {
        this.id = id;
        this.imagemURL = imagemURL;
        this.descricao = descricao;
        this.likes = likes;
        this.postOwner = postOwner;
    }

    public Post(String imagemURL, String descricao, Perfil postOwner) {
        this.imagemURL = imagemURL;
        this.descricao = descricao;
        this.likes = 0;
        this.postOwner = postOwner;
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

    public Perfil getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(Perfil postOwner) {
        this.postOwner = postOwner;
    }

}
