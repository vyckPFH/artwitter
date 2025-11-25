package br.edu.ifpr.view;

import java.util.ArrayList;

import br.edu.ifpr.controller.UsuarioController;
import br.edu.ifpr.model.utils.Usuario;

public class Main {
    
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        ArrayList<Usuario> usuarios = usuarioController.selectUsuario();

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome("lukezin");
        novoUsuario.setEmail("lukez@gmail");
        novoUsuario.setSenha("lukezin");
        usuarioController.cadastrarUsuario(novoUsuario);

       usuarios = usuarioController.selectUsuario();// sempre que vc for usar vc for usar essa essa lista, você atualiza

        for (Usuario usuarioX : usuarios) {
            
            System.out.println("Usuario ID: " + usuarioX.getId());
            System.out.println("Usuario nome: " + usuarioX.getNome());
            System.out.println("Usuario email" + usuarioX.getEmail());
            System.out.println("Usuario senha: " + usuarioX.getSenha());
            System.out.println();
        }

    }
}
