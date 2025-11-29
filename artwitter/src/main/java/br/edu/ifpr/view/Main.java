package br.edu.ifpr.view;

import java.util.ArrayList;

import br.edu.ifpr.controller.ComentarioController;
import br.edu.ifpr.controller.PerfilController;
import br.edu.ifpr.controller.PostController;
import br.edu.ifpr.controller.UsuarioController;
import br.edu.ifpr.model.utils.Comentario;
import br.edu.ifpr.model.utils.Perfil;
import br.edu.ifpr.model.utils.Post;
import br.edu.ifpr.model.utils.Usuario;

public class Main {
    /*
     *Controlers baby 
     */
    private static ComentarioController comentarioController = new ComentarioController();
    private static PerfilController perfilController = new PerfilController();
    private static PostController postController = new PostController();
    private static UsuarioController usuarioController = new UsuarioController();
    /*
     * Lixtas babyy
     */
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Post> postsRealizados = new ArrayList<>();
    private static ArrayList<Perfil> perfils = new ArrayList<>();
    private static ArrayList<Comentario> comentarios = new ArrayList<>();

    public static void main(String[] args) {
    testeGeralzao();
    }

    public static void testeGeralzao() {
        Usuario usuario1 = new Usuario("capybara", "coconutDOGY", "ihaveaglockinmyarms");
        usuarioController.cadastrarUsuario(usuario1);
        Perfil perfil1 = new Perfil(, "seventeen years", "capivaraURL");
        perfilController.cadastrarPerfil(perfil1);
        Usuario usuario2 = new Usuario("sayori", "igentlyopenthedor@mail.com", "bite me mommy");
       // usuarioController.cadastrarUsuario(usuario2);

        Usuario usuario3 = new Usuario("CLT", "escravo@hotmail", "SOS");
       // usuarioController.cadastrarUsuario(usuario3);
        
        
        usuarios = usuarioController.listarUsuarios();        
        
        // Perfil perfil1 = new Perfil(usuarios.get(usuarios.indexOf(usuario1)).getId(), "seventeen years", "capivaraURL");
        // perfilController.cadastrarPerfil(perfil1);
        // Perfil perfil2 = new Perfil(usuarios.get(usuarios.indexOf(usuario2)).getId(), "guys give me money", "sayoriHappyURL");
        // perfilController.cadastrarPerfil(perfil2);
        // Perfil perfil3 = new Perfil(usuarios.get(usuarios.indexOf(usuario3)).getId(), ".", "cltURL");
        // perfilController.cadastrarPerfil(perfil3);

        perfils = perfilController.listarPerfis();

printUsuarios();
printPerfils();

usuarioController.deletarUsuario(usuarios.get(usuarios.indexOf(usuario1)).getId());
usuarioController.deletarUsuario(usuarios.get(usuarios.indexOf(usuario2)).getId());
usuarioController.deletarUsuario(usuarios.get(usuarios.indexOf(usuario3)).getId());

usuarios = usuarioController.listarUsuarios();
perfils = perfilController.listarPerfis();

    }

    public static void printUsuarios(){
        usuarios = usuarioController.listarUsuarios();
        for (Usuario u : usuarios) {
            System.out.println();

        System.out.println(u.getNome());
        System.out.println(u.getEmail());
        System.out.println(u.getId());
        System.out.println(u.getSenha());

            System.out.println();
        }
    }

    public static void printPerfils(){
        perfils = perfilController.listarPerfis();
        for (Perfil p : perfils) {
            System.out.println();

            System.out.println(usuarioController.buscarPorId(p.getUsuarioId()).getNome());
            System.out.println(p.getFoto());
            System.out.println(p.getDescricao());

            System.out.println();
        }
    }
}
