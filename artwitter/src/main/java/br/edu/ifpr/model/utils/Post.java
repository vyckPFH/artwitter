package br.edu.ifpr.model.utils;

public class Post {

    private int id;
    private String imagemURL;
    private String descricao;
    private int likes = 0;
    private int usuarioId; // veio de perfil_usuario_id

    @Override
    public String toString() {
        return "Post { " +
                "id=" + id +
                ", imgURL='" + imagemURL + '\'' +
                ", descricao='" + descricao + '\'' +
                ", usuarioId=" + usuarioId +
                ", likes=" + likes +
                " }";
    }

    public Post() {
    }

    public Post(String imagemURL, String descricao, int usuarioId) {
        this.imagemURL = imagemURL;
        this.descricao = descricao;
        this.usuarioId = usuarioId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
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

}
