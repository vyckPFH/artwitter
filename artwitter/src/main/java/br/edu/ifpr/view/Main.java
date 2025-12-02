package br.edu.ifpr.view;

import java.util.ArrayList;
import java.util.Scanner;

import br.edu.ifpr.controller.ComentarioController;
import br.edu.ifpr.controller.PerfilController;
import br.edu.ifpr.controller.PostController;
import br.edu.ifpr.controller.UsuarioController;
import br.edu.ifpr.model.utils.Comentario;
import br.edu.ifpr.model.utils.Perfil;
import br.edu.ifpr.model.utils.Post;
import br.edu.ifpr.model.utils.Usuario;


    //fzr um metodo de validação do nome, email, e senha e fzr try catch do erro de duplicate entry nome var unic 
    //arrumar metodo de atualizar o comentario pq ta dando pau ess bosta

public class Main {

    /*
     * Controlers baby
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

    final static Scanner LER = new Scanner(System.in);

    public static void main(String[] args) {
        boolean stop = false;
        // printUsuarios();
        // printPerfils();

        // Métodos e afins p/ MENU

        // while (!stop) {

        //     System.out.println("•·•Opções de Tela•·•");
        //     System.out.println("Press 1 - Criar conta");
        //     System.out.println("Press 2 - Entrar");
        //     System.out.println("Press 0 - Sair");

        //     int opcao = LER.nextInt();

        //     switch (opcao) {
        //         case 1:
        //             cadastrarUsuario();

        //             break;
        //         case 2:
        //             // metodo login e validação no BD e direcionamento para feed se sucesso
        //             break;
        //         case 0:
        //             stop = true;
        //             break;
        //         default:
        //             System.out.println("Opção inválida");
        //             pularLinha();
        //             break;
        //     }
        // }

        Usuario u1 = new Usuario("capybara", "capy@mail.com", "123");
        Usuario u2 = new Usuario("sayori", "sayori@mail.com", "123");
        Usuario u3 = new Usuario("clt", "clt@mail.com", "123");

        usuarioController.cadastrarUsuario(u1);
        usuarioController.cadastrarUsuario(u2);
        usuarioController.cadastrarUsuario(u3);

        System.out.println(usuarioController.listarUsuarios());

        Perfil p1 = new Perfil(u1, "sou uma capivara", "capyURL");
        Perfil p2 = new Perfil(u2, "sou a sayori", "sayURL");
        Perfil p3 = new Perfil(u3, "escravizado", "cltURL");

        perfilController.cadastrarPerfil(p1);
        perfilController.cadastrarPerfil(p2);
        perfilController.cadastrarPerfil(p3);

        System.out.println(perfilController.listarPerfis());

        Post post1 = new Post("img1URL", "primeiro post", p1);
        Post post2 = new Post("img2URL", "segundo post", p2);

        postController.postar(post1);
        postController.postar(post2);

        System.out.println(postController.listar());

        postController.darLike(post1);
        postController.darLike(post1);
        postController.removerLike(post1);
        postController.darLike(post2);

        System.out.println(postController.listar());

        Comentario c1 = new Comentario("legal!", post1.getId(), p1);
        Comentario c2 = new Comentario("massa!", post1.getId(), p2);
        Comentario c3 = new Comentario("arrasou!", post2.getId(), p3);

        comentarioController.comentar(c1);
        comentarioController.comentar(c2);
        comentarioController.comentar(c3);

        System.out.println(comentarioController.listarComentarios());

        // Atualizar usuário
        u1.setNome("capyAtualizada");
        u1.setEmail("novaCapy@mail.com");
        u1.setSenha("nova123");
        usuarioController.atualizarUsuario(u1);

        // Atualizar perfil
        perfilController.atualizarDescricao(p1, "capivara atualizada");
        perfilController.atualizarFoto(p1, "novaFotoURL");

        // Atualizar post
        postController.atualizarDescricao(post1, "post editado");
        postController.atualizarImagem(post1, "novaIMG");

        // Atualizar comentário (pega o primeiro do post)
        ArrayList<Comentario> comentariosPost1 = comentarioController.listarDePost(post1);
        if (!comentariosPost1.isEmpty()) {
            Comentario com = comentariosPost1.get(0);
            com.setTexto("editado!!!");
            comentariosPost1.set(0, com);
            comentarioController.atualizarComentario(com);
        }

        System.out.println("USUÁRIOS ATUALIZADOS: " + usuarioController.listarUsuarios());
        System.out.println("PERFIS ATUALIZADOS: " + perfilController.listarPerfis());
        System.out.println("POSTS ATUALIZADOS: " + postController.listar());
        System.out.println("COMENTÁRIOS ATUALIZADOS: " + comentarioController.listarComentarios());

        usuarioController.deletarUsuario(u1);
        usuarioController.deletarUsuario(u2);
        usuarioController.deletarUsuario(u3);

        System.out.println(usuarioController.listarUsuarios());
        System.out.println(perfilController.listarPerfis());
        System.out.println(postController.listar());
        System.out.println(comentarioController.listarComentarios());
    }

    public static void menuOpUsus() {
        System.out.println("•·•Menu - Opções do Usuário•·•");
        System.out.println("Press 1 - Cadastrar novo usuário");
        System.out.println("Press 2 - Alterar dados do usuário");
        System.out.println("Press 3 - Buscar por usuário");
        System.out.println("Press 4 - Deletar usuário existente");

        int eslha = LER.nextInt();
        switch (eslha) {
            case 1:
                // metodo insert
                break;
            case 2:
                // metodo update
                break;
            case 3:
                // metodo select
                break;
            case 4:
                // metodo delete
            default:
                break;
        }
    }

    // Método cadastro
    public static void cadastrarUsuario() {
        Usuario usuario = new Usuario();

        System.out.println("•·•Inicio de Cadastro•·•");

        System.out.println("Nome:");
        usuario.setNome(LER.next());

        System.out.println("Email:");
        usuario.setEmail(LER.next());

        System.out.println("Senha:");
        String senha = LER.next();
        usuario.setSenha(senha);

    }

    // Select
    // System.out.println("•·•Informações do Ususário•·•");
    // System.out.println("Nome: " + us.getNome());
    // System.out.println("Email: " + us.getEmail());
    // System.out.println("Senha: " + us.getSenha());

    // Método p pular linha
    public static void pularLinha() {
        System.out.println();
        System.out.println();
    }



    // public static void printUsuarios() {
    //     usuarios = usuarioController.listarUsuarios();
    //     for (Usuario u : usuarios) {
    //         System.out.println();
    //         System.out.println(u.getNome());
    //         System.out.println(u.getEmail());
    //         System.out.println(u.getId());
    //         System.out.println(u.getSenha());
    //         System.out.println();
    //     }
    // }
    // public static void printPerfils() {
    //     perfils = perfilController.listarPerfis();
    //     for (Perfil p : perfils) {
    //         System.out.println();
    //         System.out.println(usuarioController.buscarPorId(p.getUsuarioId()).getNome());
    //         System.out.println(p.getFoto());
    //         System.out.println(p.getDescricao());
    //         System.out.println();
    //     }
    // }

}
