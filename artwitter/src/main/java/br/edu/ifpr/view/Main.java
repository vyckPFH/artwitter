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
    // controllers staticos
    private static PerfilController perfilController = new PerfilController();
    private static PostController postController = new PostController();
    private static UsuarioController usuarioController = new UsuarioController();
    private static SeguindoController seguindoController = new SeguindoController();
    private static ComentarioController comentarioController = new ComentarioController();

    final static Scanner LER = new Scanner(System.in);

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
    //funções plus
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
    
    //Menus
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
    }

    public static void menuPosLogin(Usuario user) {

        while (true) {
            limparTela();
            System.out.println("\n··· Menu ···");
            System.out.println("Logado como: @" + user.getNome());
            System.out.println("1 - Abrir seu perfil");
            System.out.println("2 - Editar seu perfil");
            System.out.println("3 - Criar um post");
            System.out.println("4 - Deletar um post");
            System.out.println("5 - Ver seu feed");
            System.out.println("6 - Seguir alguém");
            System.out.println("7 - Ver seguidores / seguindo");
            System.out.println("8 - Logout");
            System.out.print("Escolha: ");

            int op = lerInt();

            switch (op) {
                case 1:
                    mostrarPerfil(user);
                    listarPostsUsuario(user);
                    break;
                case 2:
                    editarPerfil(user);
                    break;
                case 3:
                    criarPost(user);
                    break;
                case 4:
                    listarPostsUsuario(user);
                    System.out.print("Selecione o id do post que deseja deletar(0 - voltar): ");
                    int id = lerInt();
                    if (id != 0) {
                        Post p = postController.buscarPorId(id);
                        postController.deletar(p);
                    }
                    break;
                case 5:
                    verFeed(user);
                    break;
                case 6:
                    seguirUsuario(user);
                    break;
                case 7:
                    verSeguidoresSeguindo(user);
                    break;
                case 8:
                    System.out.println("Você saiu da conta @" + user.getNome());
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    
    //funções geraix
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
                    String bio = LER.nextLine();
                    perfilController.atualizarDescricao(perfil, bio);
                    break;
                case 2:
                    System.out.print("Nova foto URL: ");
                    String foto = LER.nextLine();
                    perfilController.atualizarFoto(perfil, foto);
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

    public static void criarPost(Usuario usuario) {
        Perfil p = perfilController.buscarPorId(usuario.getId());
        System.out.print("\nURL da imagem: ");
        String img = LER.next();
        System.out.print("Legenda: ");
        LER.nextLine();
        String desc = LER.nextLine();

        Post post = new Post(img, desc, p);
        postController.postar(post);
        System.out.println("Post publicado!");
    }

    public static void listarPostsUsuario(Usuario usuario) {
        Perfil p = perfilController.buscarPorId(usuario.getId());
        ArrayList<Post> posts = postController.listarPorPerfil(p);
        System.out.println("\n··· Seus posts ···");
        for (Post post : posts) {
            System.out.println("\nPost #" + post.getId());
            System.out.println("Imagem: " + post.getImagemURL());
            System.out.println("Legenda: " + post.getDescricao());
            System.out.println("Likes: " + post.getLikes());
        }
    }

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
                Usuario dono = p.getPostOwner().getPerfilOwner();
                System.out.println((i + 1) + " - @" + dono.getNome() + " | Likes: " + p.getLikes() + " | \""
                        + p.getDescricao() + "\"");
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
            Usuario dono = post.getPostOwner().getPerfilOwner();
            System.out.println("Autor: @" + dono.getNome());
            System.out.println("Imagem: " + post.getImagemURL());
            System.out.println("Legenda: " + post.getDescricao());
            System.out.println("Likes: " + post.getLikes());

            System.out.println("\nAções:");
            System.out.println("1 - Curtir");
            System.out.println("2 - Remover like");
            System.out.println("3 - Ver comentários");
            System.out.println("4 - Adicionar comentário");
            System.out.println("5 - Editar comentário");
            System.out.println("6 - Deletar comentário");
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
                    System.out.println("Post curtido!");
                    break;
                case 2:
                    if (post.getLikes() > 0) {
                        postController.removerLike(post);
                        post.setLikes(post.getLikes() - 1);
                        System.out.println("Like removido!");
                    } else {
                        System.out.println("O post ainda não possui likes.");
                    }
                    break;
                case 3:
                    verComentarios(post);
                    break;
                case 4:
                    adicionarComentario(post, usuario);
                    break;
                case 5:
                    editarComentario(usuario, post);
                    break;
                case 6:
                    deletarComentario(usuario, post);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

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
        ArrayList<Usuario> seguidores = seguindoController.listarSeguidores(usuario);
        ArrayList<Usuario> seguindo = seguindoController.listarSeguidos(usuario);

        System.out.println("\nSeguindo:");
        if (seguindo.isEmpty())
            System.out.println("(Você não segue ninguém)");
        else
            for (Usuario u : seguindo)
                System.out.println("@" + u.getNome());

        System.out.println("\nSeguidores:");
        if (seguidores.isEmpty())
            System.out.println("(Ninguém te segue ainda)");
        else
            for (Usuario u : seguidores)
                System.out.println("@" + u.getNome());
    }

    public static void adicionarComentario(Post post, Usuario usuario) {
        System.out.print("\nDigite seu comentário: ");
        String texto = LER.nextLine();
        Perfil perfilUsuario = perfilController.buscarPorId(usuario.getId());
        comentarioController.comentar(texto, post, perfilUsuario);
    }

    public static void editarComentario(Usuario usuario, Post post) {
        ArrayList<Comentario> comentarios = comentarioController.listarDePost(post);
        boolean temComentario = false;
        for (Comentario c : comentarios) {
            if (c.getComentOwner().getPerfilOwner().getId() == usuario.getId()) {
                System.out.println("ID: " + c.getId() + " | " + c.getTexto());
                temComentario = true;
            }
        }
        if (!temComentario) {
            System.out.println("Você não tem comentários neste post.");
            return;
        }

        System.out.print("Digite o ID do comentário que deseja editar (0 para cancelar): ");
        int id = lerInt();
        if (id == 0)
            return;

        System.out.print("Digite o novo texto: ");
        String novoTexto = LER.nextLine();
        comentarioController.editarComentario(id, usuario, novoTexto);
    }

    public static void deletarComentario(Usuario usuario, Post post) {
        ArrayList<Comentario> comentarios = comentarioController.listarDePost(post);
        boolean temComentario = false;
        for (Comentario c : comentarios) {
            if (c.getComentOwner().getPerfilOwner().getId() == usuario.getId()) {
                System.out.println("ID: " + c.getId() + " | " + c.getTexto());
                temComentario = true;
            }
        }
        if (!temComentario) {
            System.out.println("Você não tem comentários neste post.");
            return;
        }

        System.out.print("Digite o ID do comentário que deseja deletar (0 para cancelar): ");
        int id = lerInt();
        if (id == 0)
            return;

        comentarioController.deletarComentario(id, usuario);
    }

    public static void verComentarios(Post post) {
        ArrayList<Comentario> comentarios = comentarioController.listarDePost(post);
        System.out.println("\n··· Comentários ···");
        if (comentarios.isEmpty()) {
            System.out.println("Nenhum comentário.");
            return;
        }
        for (Comentario c : comentarios) {
            System.out.println("@" + c.getComentOwner().getPerfilOwner().getNome() + ": " + c.getTexto());
        }
    }
}
