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

    private final Connection conn;

    public TransacaoDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }

    // ✅ Cadastrar transação
    public boolean cadastrar(TransacaoM transacao) {
        String sql = "INSERT INTO transacoes (descricao, valor, data, categoria_id, conta_id, usuario_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, transacao.getDescricao());
            stmt.setDouble(2, transacao.getValor());
            stmt.setDate(3, Date.valueOf(transacao.getData())); // Certifique-se de que a data está no formato correto
            stmt.setInt(4, transacao.getCategoriaId());
            stmt.setInt(5, transacao.getContaId());
            stmt.setInt(6, transacao.getUsuarioId());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar transação: " + e.getMessage());
            return false;
        }
    }

    // ✅ Buscar transação por ID
    public TransacaoM buscarPorId(int id) {
        String sql = "SELECT * FROM transacoes WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return criarTransacao(rs);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar transação: " + e.getMessage());
        }
        return null;
    }

    // ✅ Listar transações por usuário
    public List<TransacaoM> listarPorUsuario(int usuarioId) {
        List<TransacaoM> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacoes WHERE usuario_id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(criarTransacao(rs));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar transações por usuário: " + e.getMessage());
        }
        return lista;
    }

    // ✅ Listar transações por conta
    public List<TransacaoM> listarPorConta(int contaId) {
        List<TransacaoM> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacoes WHERE conta_id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, contaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(criarTransacao(rs));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar transações por conta: " + e.getMessage());
        }
        return lista;
    }

    // ✅ Listar transações por categoria
    public List<TransacaoM> listarPorCategoria(int categoriaId) {
        List<TransacaoM> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacoes WHERE categoria_id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, categoriaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(criarTransacao(rs));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar transações por categoria: " + e.getMessage());
        }
        return lista;
    }

    // ✅ Atualizar transação
    public boolean atualizar(TransacaoM transacao) {
        String sql = "UPDATE transacoes SET descricao = ?, valor = ?, data = ?, categoria_id = ?, conta_id = ? WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, transacao.getDescricao());
            stmt.setDouble(2, transacao.getValor());
            stmt.setDate(3, Date.valueOf(transacao.getData()));
            stmt.setInt(4, transacao.getCategoriaId());
            stmt.setInt(5, transacao.getContaId());
            stmt.setInt(6, transacao.getId());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar transação: " + e.getMessage());
            return false;
        }
    }

    // ✅ Remover transação
    public boolean remover(int id) {
        String sql = "DELETE FROM transacoes WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover transação: " + e.getMessage());
            return false;
        }
    }

    // 🔧 Método auxiliar para construir uma transação a partir do ResultSet
    private TransacaoM criarTransacao(ResultSet rs) throws SQLException {
        return new TransacaoM(
                rs.getInt("id"),
                rs.getString("descricao"),
                rs.getDouble("valor"),
                rs.getDate("data").toString(),
                rs.getInt("categoria_id"),
                rs.getInt("conta_id"),
                rs.getInt("usuario_id")
        );
    }
}
