package br.edu.ifpr.view;
import br.edu.ifpr.controller.UsuarioController;
import br.edu.ifpr.model.utils.Usuario;

public class metodoBanco {

        public void criaBan(Usuario usuario){
            Usuario novoUsuario = new Usuario();
            UsuarioController usuarioController = new UsuarioController();

            novoUsuario.setNome("luke");
            novoUsuario.setEmail("luke@gmail");
            novoUsuario.setSenha("luke");
            usuarioController.cadastrarUsuario(novoUsuario);

            novoUsuario.setNome("Bunny");
            novoUsuario.setEmail("bunny@gmail");
            novoUsuario.setSenha("zoo");
            usuarioController.cadastrarUsuario(novoUsuario);

            novoUsuario.setNome("Wolf");
            novoUsuario.setEmail("wolf@gmail");
            novoUsuario.setSenha("auau");
            usuarioController.cadastrarUsuario(novoUsuario);

            novoUsuario.setNome("Victoria");
            novoUsuario.setEmail("cristinans@gmail");
            novoUsuario.setSenha("yuri69");
            usuarioController.cadastrarUsuario(novoUsuario);

            novoUsuario.setNome("Venus");
            novoUsuario.setEmail("venus@gmail");
            novoUsuario.setSenha("sorte123");
            usuarioController.cadastrarUsuario(novoUsuario);
    }
}
