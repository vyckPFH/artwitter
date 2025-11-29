package br.edu.ifpr.model.utils;

public class Perfil {

    private int usuarioId; // igual ao id do usuario
    private String descricao;
    private String foto;

    public Perfil() {}

    public Perfil(int usuarioId, String descricao, String foto) {
        this.usuarioId = usuarioId;
        this.descricao = descricao;
        this.foto = foto;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
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

}
