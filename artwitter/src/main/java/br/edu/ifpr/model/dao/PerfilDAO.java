package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ifpr.model.utils.Perfil;

/**
 * Classe responsável por executar operações de CRUD relacionadas à entidade
 * {@link Perfil}.
 */
public class PerfilDAO {

    /**
     * Insere um novo perfil no banco de dados.
     *
     * @param perfil objeto contendo os dados do perfil a ser inserido
     */
    public void insert(Perfil perfil) {
        Connection con = ConnectionFactory.getConnection();
        String sql = "INSERT INTO perfil (usuario_id, descricao, foto) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, perfil.getUsuarioId());
            ps.setString(2, perfil.getDescricao());
            ps.setString(3, perfil.getFoto());

            ps.executeUpdate();
            System.out.println("Perfil inserido cm sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna todos os perfis cadastrados no banco de dados.
     *
     * @return lista contendo todos os perfis encontrados
     */
    public ArrayList<Perfil> select() {
        Connection con = ConnectionFactory.getConnection();
        ArrayList<Perfil> perfis = new ArrayList<>();

        try {
            String sql = "SELECT * FROM perfil";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Perfil perfil = new Perfil();
                perfil.setUsuarioId(rs.getInt("usuario_id"));
                perfil.setDescricao(rs.getString("descricao"));
                perfil.setFoto(rs.getString("foto"));

                perfis.add(perfil);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return perfis;
    }

    /**
     * Busca um perfil específico pelo ID do usuário.
     *
     * @param usuarioId identificador do usuário
     * @return o perfil correspondente ou {@code null} caso não exista
     */
    public Perfil selectPorId(int usuarioId) {
        Connection con = ConnectionFactory.getConnection();

        try {
            String sql = "SELECT * FROM perfil WHERE usuario_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, usuarioId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Perfil perfil = new Perfil();
                perfil.setUsuarioId(rs.getInt("usuario_id"));
                perfil.setDescricao(rs.getString("descricao"));
                perfil.setFoto(rs.getString("foto"));

                return perfil;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Atualiza os dados de um perfil existente.
     *
     * @param perfil objeto contendo os novos dados do perfil
     */
    public void update(Perfil perfil) {
        Connection con = ConnectionFactory.getConnection();
        String sql = "UPDATE perfil SET descricao = ?, foto = ? WHERE usuario_id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, perfil.getDescricao());
            ps.setString(2, perfil.getFoto());
            ps.setInt(3, perfil.getUsuarioId());

            ps.executeUpdate();
            System.out.println("Perfil atualizado cm sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove um perfil do banco de dados pelo ID do usuário.
     *
     * @param usuarioId identificador do usuário
     */
    public void delete(int usuarioId) {
        Connection con = ConnectionFactory.getConnection();
        String sql = "DELETE FROM perfil WHERE usuario_id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, usuarioId);

            ps.executeUpdate();
            System.out.println("Perfil deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza apenas a descrição de um perfil existente.
     *
     * @param usuarioId     identificador do usuário
     * @param novaDescricao nova descrição a ser atribuída
     */
    public void updateDescricao(int usuarioId, String novaDescricao) {
        Connection con = ConnectionFactory.getConnection();
        String sql = "UPDATE perfil SET descricao = ? WHERE usuario_id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, novaDescricao);
            ps.setInt(2, usuarioId);
            ps.executeUpdate();
            System.out.println("Descrição atualizada cm sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza apenas a foto de um perfil existente.
     *
     * @param usuarioId identificador do usuário
     * @param novaFoto  URL da nova foto a ser atribuída
     */
    public void updateFoto(int usuarioId, String novaFoto) {
        Connection con = ConnectionFactory.getConnection();
        String sql = "UPDATE perfil SET foto = ? WHERE usuario_id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, novaFoto);
            ps.setInt(2, usuarioId);
            ps.executeUpdate();
            System.out.println("Foto atualizada cm sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
