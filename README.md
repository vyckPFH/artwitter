https://prod.liveshare.vsengsaas.visualstudio.com/join?C70166EA882DC70941D01D4C8F4150615E9C

Link documento
https://docs.google.com/document/d/1so7kuPEv5khzN2Ed-kbaJltDUe46RjPzqPxtrhS8jNI/edit?usp=sharing


package br.edu.ifpr.model;

public class Comentario {

    private int id;
    private String text;
    private Post post;
    private Usuario usuarioComentario;

    
}

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


package br.edu.ifpr.model;

import java.util.List;

public class Post {

    private int id;
    private String imagemURL;
    private String descricao;
    private int likes;
    private List<Comentario> comentario;
    private Usuario owner;
    
    
   
}



package br.edu.ifpr.model;

import java.util.List;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private List<Post> feed;    

    
}
