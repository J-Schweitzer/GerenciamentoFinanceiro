/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;
import Modelo.ContaM;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

    public boolean cadastrar(ContaM conta) {
        String sql = "INSERT INTO conta (nome, saldo, usuario_id) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, conta.getNome());
            stmt.setDouble(2, conta.getSaldo());
            stmt.setInt(3, conta.getUsuarioId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar conta: " + e.getMessage());
            return false;
        }
    }

    public List<ContaM> listarPorUsuario(int usuarioId) {
        List<ContaM> lista = new ArrayList<>();
        String sql = "SELECT * FROM conta WHERE usuario_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ContaM conta = new ContaM(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("saldo"),
                        rs.getInt("usuario_id")
                );
                lista.add(conta);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar contas: " + e.getMessage());
        }

        return lista;
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM conta WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao remover conta: " + e.getMessage());
            return false;
        }
    }
}