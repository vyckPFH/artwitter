package br.edu.ifpr.model.utils;

public class Comentario {

    private int id;
    private String texto;
    private int idPost;
    private Perfil comentOwner;

    // @Override
    // public String toString() {
    // return "Comentario { " +
    // "id=" + id +
    // ", texto='" + texto + '\'' +
    // ", idPost=" + idPost +
    // ", usuarioId=" + idUsuario +
    // " }";
    // }

    public Comentario(int id, String texto, int idPost, Perfil comentOwner) {
        this.id = id;
        this.texto = texto;
        this.idPost = idPost;
        this.comentOwner = comentOwner;
    }

    public Comentario(String texto, int idPost, Perfil comentOwner) {
        this.texto = texto;
        this.idPost = idPost;
        this.comentOwner = comentOwner;
    }

    public Comentario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public Perfil getComentOwner() {
        return comentOwner;
    }

    public void setComentOwner(Perfil comentOwner) {
        this.comentOwner = comentOwner;
    }

}
