package br.edu.ifpr.model.utils;

public class Comentario {

    private int id;
    private String text;
    private int idPost;
    private int idUsuario;



    
    public Comentario(String text, int idPost, int idUsuario) {
        this.text = text;
        this.idPost = idPost;
        this.idUsuario = idUsuario;
    }

    public Comentario() {
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
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
