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

    public static int lerInt() {
        int op = -1;
        while (op < 0) {
            try {
                op = LER.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Use apenas números para a seleção do menu.");
                LER.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return op;
    }

    public static void garantirPerfil(Usuario usuario) {
        Perfil p = perfilController.buscarPorId(usuario.getId());
        if (p == null) {
            // cria perfil vazio automaticamente
            Perfil novo = new Perfil(usuario, "", "");
            perfilController.cadastrarPerfil(novo);
            System.out.println("Perfil criado automaticamente!");
        }
    }

    public static void limparTela() {
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {

        while (true) {
            try {
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
                        System.out.println("\nSaindo do ARTwitter... valeu!");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Use apenas números para a seleção do menu.");
                LER.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void menuDeLogin() {

        while (true) {
            try {

                // limparTela();
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

                        Usuario usuarioLogado = usuarioController.login(nome, senha);

                        if (usuarioLogado == null) {
                            System.out.println("Nome ou senha incorretos.");
                        } else {
                            garantirPerfil(usuarioLogado);

                            System.out.println("Login realizado com sucesso. Bem-vindo, @"
                                    + usuarioLogado.getNome() + ".");
                            menuPosLogin(usuarioLogado);
                        }

                        break;

                    case 0:
                        return;

                    default:
                        System.out.println("Opção inválida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Use apenas números para a seleção do menu.");
                LER.nextLine();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void menuDeCadastro() {
        limparTela();

        System.out.println("\n··· Criar conta ···");
        Usuario usuario = new Usuario();
        usuarioController.cadastrarUsuario(usuario);
        System.out.println("Conta criada! Agora faça login.");
    }

    public static void menuPosLogin(Usuario usuarioLogado) {

        while (true) {
            limparTela();

            System.out.println("\n··· Menu ···");
            System.out.println("Logado como: @" + usuarioLogado.getNome());
            System.out.println("1 - Ver seu perfil");
            System.out.println("2 - Configurar perfil");
            System.out.println("3 - Criar post");
            System.out.println("4 - Ver seus posts");
            System.out.println("5 - Ver feed");
            System.out.println("6 - Logout");

            System.out.println("7 - Seguir alguém");
            System.out.println("8 - Ver seguidores / seguindo");

            System.out.print("Escolha: ");

            int op = lerInt();

            switch (op) {
                case 1:
                    limparTela();
                    mostrarPerfil(usuarioLogado);
                    break;
                case 2:
                    editarPerfil(usuarioLogado);
                    break;
                case 3:
                    criarPost(usuarioLogado);
                    break;
                case 4:
                    listarPostsUsuario(usuarioLogado);
                    break;
                case 5:
                    verFeed(usuarioLogado);
                    break;
                case 6:
                    System.out.println("Você saiu da conta @" + usuarioLogado.getNome() + ".");
                    return;

                case 7:
                    seguirUsuario(usuarioLogado); // ADICIONADO
                    break;

                case 8:
                    verSeguidoresSeguindo(usuarioLogado); // ADICIONADO
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public static void verSeguidoresSeguindo(Usuario usuarioLogado) {

        limparTela();

        System.out.println("\n··· Seguidores / Seguindo ···");

        ArrayList<Usuario> seguidores = seguindoController.listarSeguidores(usuarioLogado);
        ArrayList<Usuario> seguindo = seguindoController.listarSeguidos(usuarioLogado);

        System.out.println("\nSeguindo:");
        if (seguindo.isEmpty()) {
            System.out.println("(Você não segue ninguém)");
        } else {
            for (Usuario u : seguindo) {
                System.out.println("@" + u.getNome());
            }
        }

        System.out.println("\nSeguidores:");
        if (seguidores.isEmpty()) {
            System.out.println("(Ninguém te segue ainda)");
        } else {
            for (Usuario u : seguidores) {
                System.out.println("@" + u.getNome());
            }
        }
    }

    public static void seguirUsuario(Usuario usuarioLogado) {

        limparTela();

        System.out.println("\n··· Seguir usuário ···");
        System.out.print("Digite o nome do usuário que deseja seguir: ");
        String nome = LER.next();

        Usuario u = usuarioController.buscarPorNome(nome);

        if (u == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (u.getId() == usuarioLogado.getId()) {
            System.out.println("Você não pode seguir a si mesmo.");
            return;
        }

        // Apenas chama o controller — ele já imprime tudo lá
        seguindoController.seguir(usuarioLogado, u);
    }

    public static void mostrarPerfil(Usuario usuario) {
        Perfil p = perfilController.buscarPorId(usuario.getId());

        if (p == null) {
            System.out.println("\n··· Perfil ···");
            System.out.println("Você ainda não configurou seu perfil.");
            return;
        }

        System.out.println("\n··· Seu perfil ···");
        System.out.println("Usuário: @" + usuario.getNome());
        System.out.println("Bio: " + p.getDescricao());
        System.out.println("Foto (URL): " + p.getFoto());
    }

    public static void editarPerfil(Usuario usuarioLogado) {
        Perfil perfil = perfilController.buscarPorId(usuarioLogado.getId());
        if (perfil == null) {
            System.out.println("Você ainda não possui perfil.");
            return;
        }

        while (true) {
            System.out.println("\n··· Editar perfil ···");
            System.out.println("1 - Editar bio");
            System.out.println("2 - Editar foto");
            System.out.println("3 - Deletar conta");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            int op = lerInt();

            switch (op) {
                case 1:
                    System.out.print("Nova bio: ");
                    perfilController.atualizarDescricao(perfil, LER.next());
                    System.out.println("Bio atualizada.");
                    break;

                case 2:
                    System.out.print("Nova URL da foto: ");
                    perfilController.atualizarFoto(perfil, LER.next());
                    System.out.println("Foto atualizada.");
                    break;

                case 3:
                    System.out.println("Tem certeza que deseja deletar sua conta?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");
                    if (LER.nextInt() == 1) {
                        usuarioController.deletarUsuario(usuarioLogado);
                        System.out.println("Conta deletada. Até mais!");
                        System.exit(0);// encerra a execução e volta ao main na próxima execução
                        return;
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public static void criarPost(Usuario usuarioLogado) {
        Perfil perfil = perfilController.buscarPorId(usuarioLogado.getId());
        if (perfil == null) {
            System.out.println("Você não possui perfil. Crie um perfil antes de postar.");
            return;
        }

        System.out.println("\n··· Novo post ···");
        System.out.print("URL da imagem: ");
        String img = LER.next();

        System.out.print("Legenda: ");
        String desc = LER.next();

        Post p = new Post(img, desc, perfil);
        postController.postar(p);

        System.out.println("Post publicado!");
    }

    public static void listarPostsUsuario(Usuario u) {
        Perfil p = perfilController.buscarPorId(u.getId());
        ArrayList<Post> posts = postController.listarPorPerfil(p);

        System.out.println("\n··· Seus posts ···");

        if (posts.isEmpty()) {
            System.out.println("Você ainda não postou nada.");
            return;
        }

        for (Post post : posts) {
            System.out.println("\nPost #" + post.getId());
            System.out.println("Imagem: " + post.getImagemURL());
            System.out.println("Legenda: " + post.getDescricao());
            System.out.println("Likes: " + post.getLikes());
        }
    }

    public static void verFeed(Usuario usuarioLogado) {
        ArrayList<Post> feed = postController.listarFeed(usuarioLogado);

        if (feed.isEmpty()) {
            System.out.println("\nSeu feed está vazio. Siga pessoas ou faça posts.");
            return;
        }

        while (true) {
            System.out.println("\n··· Feed ···");

            for (int i = 0; i < feed.size(); i++) {
                Post post = feed.get(i);
                Perfil perfilDono = post.getPostOwner();
                Usuario dono = usuarioController.buscarPorId(perfilDono.getPerfilOwner().getId());

                System.out.println((i + 1) + " - @" + dono.getNome()
                        + " | Likes: " + post.getLikes()
                        + " | \"" + post.getDescricao() + "\"");
            }

            System.out.println("0 - Voltar");
            System.out.print("Escolha um post para abrir: ");
            int op = lerInt();

            if (op == 0)
                return;

            if (op > 0 && op <= feed.size()) {
                abrirPost(feed.get(op - 1), usuarioLogado);
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    public static void abrirPost(Post post, Usuario usuarioLogado) {
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
                System.out.println("(Nenhum comentário ainda)");
            } else {
                for (Comentario c : comentarios) {
                    System.out.println("- @" + c.getComentOwner().getPerfilOwner().getNome()
                            + ": " + c.getTexto());
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
                    System.out.println("Você curtiu esse post.");
                    break;

                case 2:
                    if (post.getLikes() > 0) {
                        postController.removerLike(post);
                        post.setLikes(post.getLikes() - 1);
                        System.out.println("Você removeu o like.");
                    } else {
                        System.out.println("Esse post já está com 0 likes.");
                    }
                    break;

                case 3:
                    System.out.print("Escreva seu comentário: ");
                    String texto = LER.nextLine();
                    Comentario c = new Comentario(texto, post.getId(),
                            perfilController.buscarPorId(usuarioLogado.getId()));
                    comentarioController.comentar(c);
                    System.out.println("Comentado!");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
