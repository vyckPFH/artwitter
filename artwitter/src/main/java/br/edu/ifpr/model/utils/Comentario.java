package br.edu.ifpr.model.utils;

public class Comentario {

    private int id;
    private String texto;
    private int idPost;
    private int idUsuario;
    
    public Comentario(String texto, int idPost, int idUsuario) {
        this.texto = texto;
        this.idPost = idPost;
        this.idUsuario = idUsuario;
    }

    public Comentario() {}
    
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
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    
}
