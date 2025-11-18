package br.edu.ifpr.model;

import java.util.List;

public class Perfil {

    private int id;
    private Usuario usuarioPerfil;
    private String descricao;
    private String foto;
    private List<Perfil> seguidores;
    private List<Perfil> seguindo;
    private List<Post> posts;

    
}
