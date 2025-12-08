package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ifpr.model.utils.Seguindo;
import br.edu.ifpr.model.utils.Usuario;

public class SeguindoDAO {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void insert(Seguindo seguindo) {
        Connection con = ConnectionFactory.getConnection();
        String sql = "INSERT INTO listSeguido (seguidor_id, seguido_id) VALUES (?, ?)";

        try (
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, seguindo.getSeguidor().getId());
            ps.setInt(2, seguindo.getSeguido().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao seguir: " + e.getMessage());
        }
    }

    public void delete(Seguindo seguindo) {
        String sql = "DELETE FROM listSeguido WHERE seguidor_id = ? AND seguido_id = ?";
        Connection con = ConnectionFactory.getConnection();

        try (
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, seguindo.getSeguidor().getId());
            ps.setInt(2, seguindo.getSeguido().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deixar de seguir: " + e.getMessage());
        }
    }

    public boolean existe(Seguindo seguindo) {
        String sql = "SELECT * FROM listSeguido WHERE seguidor_id = ? AND seguido_id = ?";
        Connection con = ConnectionFactory.getConnection();

        try (
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, seguindo.getSeguidor().getId());
            ps.setInt(2, seguindo.getSeguido().getId());

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            return false;
        }
    }

    public ArrayList<Seguindo> selectSeguidos(Usuario seguidor) {
        ArrayList<Seguindo> lista = new ArrayList<>();

        String sql = "SELECT seguido_id FROM listSeguido WHERE seguidor_id = ?";

        Connection con = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, seguidor.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario seguido = usuarioDAO.selectPorId(rs.getInt("seguido_id"));
                lista.add(new Seguindo(seguidor, seguido));
            }

            // rs.close();
            // ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public ArrayList<Seguindo> selectSeguidores(Usuario seguido) {
        ArrayList<Seguindo> lista = new ArrayList<>();
        Connection con = ConnectionFactory.getConnection();

        String sql = "SELECT seguidor_id FROM listSeguido WHERE seguido_id = ?";

        try (
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, seguido.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario seguidor = usuarioDAO.selectPorId(rs.getInt("seguidor_id"));
                lista.add(new Seguindo(seguidor, seguido));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
