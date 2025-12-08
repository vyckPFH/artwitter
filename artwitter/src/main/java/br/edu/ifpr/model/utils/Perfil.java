package br.edu.ifpr.model.utils;

public class Perfil {

    private Usuario perfilOwner;
    private String descricao;
    private String foto;

    // @Override
    // public String toString() {
    // return "Perfil { " +
    // "usuarioId=" + usuarioId +
    // ", descricao='" + descricao + '\'' +
    // ", fotoURL='" + foto + '\'' +
    // " }";
    // }

    public Perfil(Usuario perfilOwner, String descricao, String foto) {
        this.perfilOwner = perfilOwner;
        this.descricao = descricao;
        this.foto = foto;
    }

    public Perfil() {
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

    public Usuario getPerfilOwner() {
        return perfilOwner;
    }

    public void setPerfilOwner(Usuario perfilOwner) {
        this.perfilOwner = perfilOwner;
    }

}
