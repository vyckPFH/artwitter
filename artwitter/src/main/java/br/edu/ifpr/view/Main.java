package br.edu.ifpr.view;

import java.util.ArrayList;

import br.edu.ifpr.controller.ComentarioController;
import br.edu.ifpr.controller.PostController;
import br.edu.ifpr.controller.UsuarioController;
import br.edu.ifpr.model.utils.Comentario;
import br.edu.ifpr.model.utils.Post;
import br.edu.ifpr.model.utils.Usuario;

public class Main {
    
    public static void main(String[] args) {
        PostController postController = new PostController();
        ComentarioController comentarioController = new ComentarioController();
        UsuarioController usuarioController = new UsuarioController();
        ArrayList<Usuario> usuarios = usuarioController.selectUsuario();
        //

        


        // Usuario novoUsuario = new Usuario();
        // novoUsuario.setNome("lukezin");
        // novoUsuario.setEmail("lukez@gmail");
        // novoUsuario.setSenha("lukezin");
        // usuarioController.cadastrarUsuario(novoUsuario);

    //    usuarios = usuarioController.selectUsuario(); // sempre que vc for usar vc for usar essa essa lista, você atualiza

    //     for (Usuario usuarioX : usuarios) {
            
    //         System.out.println("Usuario ID: " + usuarioX.getId());
    //         System.out.println("Usuario nome: " + usuarioX.getNome());
    //         System.out.println("Usuario email" + usuarioX.getEmail());
    //         System.out.println("Usuario senha: " + usuarioX.getSenha());
    //         System.out.println();
    //     }

        Post post = new Post();
        post.setComentario(new ArrayList<>());
        post.setOwner(usuarios.getFirst());
        post.setDescricao("eu sabo mucho");
        post.setImagemURL("imagem.jpg");
        post.setLikes(10);
        Comentario comentario = new Comentario();
        comentario.setIdPost(post.getId());
        comentario.setText("text");
        comentario.setIdUsuario(1);
        post.getComentario().add(comentario);
        postController.postar(post);
        

    }
}
