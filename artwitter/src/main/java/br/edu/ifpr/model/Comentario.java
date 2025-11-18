package br.edu.ifpr.model;

public class Comentario {

    private int id;
    private String text;
    private Post post;
    private Usuario usuarioComentario;

    public Comentario(int id, String text, Post post, Usuario usuarioComentario) {
        this.id = id;
        this.text = text;
        this.post = post;
        this.usuarioComentario = usuarioComentario;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Usuario getUsuarioComentario() {
        return usuarioComentario;
    }

    public void setUsuarioComentario(Usuario usuarioComentario) {
        this.usuarioComentario = usuarioComentario;
    }
    
}
