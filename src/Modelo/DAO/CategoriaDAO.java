/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.CategoriaM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;




/**
 *
 * @author Joao
 */
public class CategoriaDAO {

    private final Connection conn;

    public CategoriaDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }

    // ✅ Cadastrar categoria
    public boolean cadastrar(CategoriaM categoria) {
        String sql = "INSERT INTO categorias (nome, tipo, usuario_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getTipo());
            stmt.setInt(3, categoria.getUsuarioId());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar categoria: " + e.getMessage());
            return false;
        }
    }

    // ✅ Buscar categoria por ID
    public CategoriaM buscarPorId(int id) {
        String sql = "SELECT * FROM categorias WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CategoriaM(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("tipo"),
                    rs.getInt("usuario_id")
                );
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar categoria por ID: " + e.getMessage());
        }
        return null;
    }

    public List<CategoriaM> listarPorUsuario(int usuarioId) {
        List<CategoriaM> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias WHERE usuario_id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CategoriaM categoria = new CategoriaM(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("tipo"),
                    rs.getInt("usuario_id")
                );
                lista.add(categoria);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        }
        return lista;
    }


    // ✅ Atualizar categoria
    public boolean atualizar(CategoriaM categoria) {
        String sql = "UPDATE categorias SET nome = ?, tipo = ? WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getTipo());
            stmt.setInt(3, categoria.getId());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar categoria: " + e.getMessage());
            return false;
        }
    }

    // ✅ Remover categoria
    public boolean remover(int id) {
        String sql = "DELETE FROM categorias WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover categoria: " + e.getMessage());
            return false;
        }
    }
}
