/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.TransacaoM;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Joao
 */
public class TransacaoDAO {
    
        public boolean cadastrar(TransacaoM transacao) {
        String sql = "INSERT INTO transacao (descricao, valor, data, tipo, conta_id, categoria_id, usuario_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transacao.getDescricao());
            stmt.setDouble(2, transacao.getValor());
            stmt.setDate(3, Date.valueOf(transacao.getData()));
            stmt.setString(4, transacao.getTipo());
            stmt.setInt(5, transacao.getContaId());
            stmt.setInt(6, transacao.getCategoriaId());
            stmt.setInt(7, transacao.getUsuarioId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar transação: " + e.getMessage());
            return false;
        }
    }

    public List<TransacaoM> listarPorUsuario(int usuarioId) {
        List<TransacaoM> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacao WHERE usuario_id = ? ORDER BY data DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TransacaoM transacao = new TransacaoM(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        rs.getDate("data").toLocalDate(),
                        rs.getString("tipo"),
                        rs.getInt("conta_id"),
                        rs.getInt("categoria_id"),
                        rs.getInt("usuario_id")
                );
                lista.add(transacao);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar transações: " + e.getMessage());
        }

        return lista;
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM transacao WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao remover transação: " + e.getMessage());
            return false;
        }
    }
}

