package br.edu.ifpr.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.edu.ifpr.controller.ComentarioController;
import br.edu.ifpr.controller.PerfilController;
import br.edu.ifpr.controller.PostController;
import br.edu.ifpr.controller.SeguindoController;
import br.edu.ifpr.controller.UsuarioController;
import br.edu.ifpr.model.utils.Comentario;
import br.edu.ifpr.model.utils.Perfil;
import br.edu.ifpr.model.utils.Post;
import br.edu.ifpr.model.utils.Usuario;

public class Main {

    private static ComentarioController comentarioController = new ComentarioController();
    private static PerfilController perfilController = new PerfilController();
    private static PostController postController = new PostController();
    private static UsuarioController usuarioController = new UsuarioController();
    private static SeguindoController seguindoController = new SeguindoController();

    final static Scanner LER = new Scanner(System.in);

    // Ferramentas
    public static int lerInt() {
        while (true) {
            try {
                return LER.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Use apenas números.");
                LER.nextLine();
            }
        }
    }

    public static void limparTela() {
        System.out.print("\n\n\n\n\n");
    }

    public static void garantirPerfil(Usuario usuario) {
        if (perfilController.buscarPorId(usuario.getId()) == null) {
            Perfil p = new Perfil(usuario, "", "");
            perfilController.cadastrarPerfil(p);
            System.out.println("Perfil criado automaticamente!");
        }
    }

    // main
    public static void main(String[] args) {

        while (true) {
            limparTela();
            System.out.println("\n··· ARTwitter ···");
            System.out.println("1 - Entrar");
            System.out.println("2 - Criar conta");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int op = lerInt();

            switch (op) {
                case 1:
                    menuDeLogin();
                    break;

                case 2:
                    menuDeCadastro();
                    break;

                case 0:
                    System.out.println("\nSaindo... valeu!");
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
1
    // login
    public static void menuDeLogin() {
        while (true) {
            System.out.println("\n··· Login ···");
            System.out.println("1 - Fazer login");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            int op = lerInt();

            switch (op) {
                case 1:
                    System.out.print("Nome de usuário: ");
                    String nome = LER.next();

                    System.out.print("Senha: ");
                    String senha = LER.next();

                    Usuario u = usuarioController.login(nome, senha);

                    if (u == null) {
                        System.out.println("Nome ou senha incorretos.");
                    } else {
                        garantirPerfil(u);
                        System.out.println("Bem-vindo @" + u.getNome());
                        menuPosLogin(u);
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public static void menuDeCadastro() {
        limparTela();

        System.out.println("\n··· Criar conta ···");

        Usuario novo = new Usuario();
        usuarioController.cadastrarUsuario(novo);

        System.out.println("Conta criada! Agora faça login.");
    }

    // menu principal
    public static void menuPosLogin(Usuario user) {

        while (true) {
            limparTela();

            System.out.println("\n··· Menu ···");
            System.out.println("Logado como: @" + user.getNome());
            System.out.println("1 - Ver perfil");
            System.out.println("2 - Editar perfil");
            System.out.println("3 - Criar post");
            System.out.println("4 - Ver meus posts");
            System.out.println("5 - Ver feed");
            System.out.println("6 - Logout");
            System.out.println("7 - Seguir alguém");
            System.out.println("8 - Ver seguidores / seguindo");
            System.out.print("Escolha: ");

            int op = lerInt();

            switch (op) {
                case 1:
                    mostrarPerfil(user);
                    break;
                case 2:
                    editarPerfil(user);
                    break;
                case 3:
                    criarPost(user);
                    break;
                case 4:
                    listarPostsUsuario(user);
                    break;
                case 5:
                    verFeed(user);
                    break;
                case 6:
                    System.out.println("Você saiu da conta @" + user.getNome());
                    return;

                case 7:
                    seguirUsuario(user);
                    break;

                case 8:
                    verSeguidoresSeguindo(user);
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // perfil
    public static void mostrarPerfil(Usuario usuario) {
        Perfil p = perfilController.buscarPorId(usuario.getId());

        System.out.println("\n··· Seu perfil ···");
        System.out.println("Usuário: @" + usuario.getNome());
        System.out.println("Bio: " + p.getDescricao());
        System.out.println("Foto: " + p.getFoto());
    }

    public static void editarPerfil(Usuario usuario) {
        Perfil perfil = perfilController.buscarPorId(usuario.getId());

        while (true) {
            System.out.println("\n··· Editar perfil ···");
            System.out.println("1 - Editar bio");
            System.out.println("2 - Editar foto");
            System.out.println("3 - Deletar conta");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            int op = lerInt();
            LER.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nova bio: ");
                    perfilController.atualizarDescricao(perfil, LER.nextLine());
                    break;

                case 2:
                    System.out.print("Nova foto URL: ");
                    perfilController.atualizarFoto(perfil, LER.nextLine());
                    break;

                case 3:
                    System.out.println("Tem certeza?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");
                    if (lerInt() == 1) {
                        usuarioController.deletarUsuario(usuario);
                        System.out.println("Conta deletada.");
                        System.exit(0);
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // posts
    public static void criarPost(Usuario usuario) {
        Perfil p = perfilController.buscarPorId(usuario.getId());

        System.out.print("\nURL da imagem: ");
        String img = LER.next();

        System.out.print("Legenda: ");
        String desc = LER.next();

        Post post = new Post(img, desc, p);
        postController.postar(post);

        System.out.println("Post publicado!");
    }

    public static void listarPostsUsuario(Usuario u) {
        Perfil p = perfilController.buscarPorId(u.getId());
        ArrayList<Post> posts = postController.listarPorPerfil(p);

        System.out.println("\n··· Seus posts ···");

        for (Post post : posts) {
            System.out.println("\nPost #" + post.getId());
            System.out.println("Imagem: " + post.getImagemURL());
            System.out.println("Legenda: " + post.getDescricao());
            System.out.println("Likes: " + post.getLikes());
        }
    }

    // feed
    public static void verFeed(Usuario usuario) {
        ArrayList<Post> feed = postController.listarFeed(usuario);

        if (feed.isEmpty()) {
            System.out.println("Seu feed está vazio.");
            return;
        }

        while (true) {
            System.out.println("\n··· Feed ···");

            for (int i = 0; i < feed.size(); i++) {
                Post p = feed.get(i);
                Perfil donoPerfil = p.getPostOwner();
                Usuario dono = usuarioController.buscarPorId(donoPerfil.getPerfilOwner().getId());

                System.out.println((i + 1) + " - @" + dono.getNome() +
                        " | Likes: " + p.getLikes() +
                        " | \"" + p.getDescricao() + "\"");
            }

            System.out.println("0 - Voltar");
            System.out.print("Escolha um post: ");

            int op = lerInt();

            if (op == 0)
                return;

            if (op > 0 && op <= feed.size()) {
                abrirPost(feed.get(op - 1), usuario);
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    public static void abrirPost(Post post, Usuario usuario) {
        while (true) {
            System.out.println("\n··· Post #" + post.getId() + " ···");

            Perfil p = post.getPostOwner();
            Usuario dono = usuarioController.buscarPorId(p.getPerfilOwner().getId());

            System.out.println("Autor: @" + dono.getNome());
            System.out.println("Imagem: " + post.getImagemURL());
            System.out.println("Legenda: " + post.getDescricao());
            System.out.println("Likes: " + post.getLikes());

            ArrayList<Comentario> comentarios = comentarioController.listarDePost(post);
            System.out.println("\nComentários:");
            if (comentarios.isEmpty()) {
                System.out.println("(Nenhum comentário)");
            } else {
                for (Comentario c : comentarios) {
                    System.out.println("- @" +
                            c.getComentOwner().getPerfilOwner().getNome() +
                            ": " + c.getTexto());
                }
            }

            System.out.println("\nAções:");
            System.out.println("1 - Curtir");
            System.out.println("2 - Remover like");
            System.out.println("3 - Comentar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            int op = lerInt();
            LER.nextLine();

            switch (op) {
                case 0:
                    return;

                case 1:
                    postController.darLike(post);
                    post.setLikes(post.getLikes() + 1);
                    break;

                case 2:
                    if (post.getLikes() > 0) {
                        postController.removerLike(post);
                        post.setLikes(post.getLikes() - 1);
                    }
                    break;

                case 3:
                    System.out.print("Comentário: ");
                    String texto = LER.nextLine();
                    Comentario c = new Comentario(texto, post.getId(),
                            perfilController.buscarPorId(usuario.getId()));
                    comentarioController.comentar(c);
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // seguir
    public static void seguirUsuario(Usuario usuario) {
        System.out.print("Nome do usuário: ");
        String nome = LER.next();

        Usuario u = usuarioController.buscarPorNome(nome);

        if (u == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (u.getId() == usuario.getId()) {
            System.out.println("Você não pode seguir a si mesmo.");
            return;
        }

        seguindoController.seguir(usuario, u);
    }

    public static void verSeguidoresSeguindo(Usuario usuario) {

        System.out.println("\n··· Seguidores / Seguindo ···");

        ArrayList<Usuario> seguidores = seguindoController.listarSeguidores(usuario);
        ArrayList<Usuario> seguindo = seguindoController.listarSeguidos(usuario);

        System.out.println("\nSeguindo:");
        if (seguindo.isEmpty())
            System.out.println("(Você não segue ninguém)");
        else
            seguindo.forEach(u -> System.out.println("@" + u.getNome()));

        System.out.println("\nSeguidores:");
        if (seguidores.isEmpty())
            System.out.println("(Ninguém te segue ainda)");
        else
            seguidores.forEach(u -> System.out.println("@" + u.getNome()));
    }
}
