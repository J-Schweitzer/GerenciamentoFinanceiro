/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.UsuarioM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
    *Connection getConnection()
 * @author Joao
 */
public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }

    // ✅ Cadastrar usuário
    public boolean cadastrar(UsuarioM usuario) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
            return false;
        }
    }

    // ✅ Buscar por ID
    public UsuarioM buscarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UsuarioM(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário por ID: " + e.getMessage());
        }
        return null;
    }

    // ✅ Buscar por email
    public UsuarioM buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UsuarioM(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário por email: " + e.getMessage());
        }
        return null;
    }

    // ✅ Autenticar (login)
    public UsuarioM autenticar(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UsuarioM(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro na autenticação: " + e.getMessage());
        }
        return null;
    }

    // ✅ Listar todos os usuários
    public List<UsuarioM> listarTodos() {
        List<UsuarioM> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioM u = new UsuarioM(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
                lista.add(u);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return lista;
    }

    // ✅ Atualizar usuário
    public boolean atualizar(UsuarioM usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ? WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getId());
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }

    // ✅ Remover usuário
    public boolean remover(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao remover usuário: " + e.getMessage());
            return false;
        }
    }
}