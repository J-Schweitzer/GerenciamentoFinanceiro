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

    public boolean cadastrar(CategoriaM categoria) {
        String sql = "INSERT INTO categoria (nome, tipo, usuario_id) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getTipo());
            stmt.setInt(3, categoria.getUsuarioId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar categoria: " + e.getMessage());
            return false;
        }
    }

    public List<CategoriaM> listarPorUsuario(int usuarioId) {
        List<CategoriaM> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria WHERE usuario_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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

        } catch (SQLException e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        }

        return lista;
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM categoria WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao remover categoria: " + e.getMessage());
            return false;
        }
    }
    
}
