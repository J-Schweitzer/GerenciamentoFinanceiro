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

    private Connection conn;

    public ContaDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }

    // ✅ Cadastrar conta
    public boolean cadastrar(ContaM conta) {
        String sql = "INSERT INTO contas (nome, saldo, usuario_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, conta.getNome());
            stmt.setDouble(2, conta.getSaldo());
            stmt.setInt(3, conta.getUsuarioId());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar conta: " + e.getMessage());
            return false;
        }
    }

    // ✅ Buscar conta por ID
    public ContaM buscarPorId(int id) {
        String sql = "SELECT * FROM contas WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ContaM(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("saldo"),
                    rs.getInt("usuario_id")
                );
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar conta por ID: " + e.getMessage());
        }
        return null;
    }

    // ✅ Listar contas de um usuário
    public List<ContaM> listarPorUsuario(int usuarioId) {
        List<ContaM> lista = new ArrayList<>();
        String sql = "SELECT * FROM contas WHERE usuario_id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
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
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar contas: " + e.getMessage());
        }
        return lista;
    }

    // ✅ Atualizar conta
    public boolean atualizar(ContaM conta) {
        String sql = "UPDATE contas SET nome = ?, saldo = ? WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, conta.getNome());
            stmt.setDouble(2, conta.getSaldo());
            stmt.setInt(3, conta.getId());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar conta: " + e.getMessage());
            return false;
        }
    }

    // ✅ Remover conta
    public boolean remover(int id) {
        String sql = "DELETE FROM contas WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover conta: " + e.getMessage());
            return false;
        }
    }
}