/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Modelo.CategoriaM;
import Modelo.DAO.CategoriaDAO;
import java.sql.SQLException;

import java.util.List;

/**
 *
 * @author Joao
 */
public class CategoriaC {
    
    private final CategoriaDAO categoriaDAO;

    public CategoriaC() throws SQLException {
        this.categoriaDAO = new CategoriaDAO();
    }

    // ✅ Cadastrar categoria
    public boolean cadastrarCategoria(String nome, String tipo, int usuarioId) {
        CategoriaM categoria = new CategoriaM(0, nome, tipo, usuarioId);
        return categoriaDAO.cadastrar(categoria);
    }

    // ✅ Buscar categoria por ID
    public CategoriaM buscarPorId(int id) {
        return categoriaDAO.buscarPorId(id);
    }

    // ✅ Listar todas as categorias de um usuário
    public List<CategoriaM> listarPorUsuario(int usuarioId) {
        return categoriaDAO.listarPorUsuario(usuarioId);
    }

    // ✅ Atualizar categoria
    public boolean atualizarCategoria(int id, String nome, String tipo, int usuarioId) {
        CategoriaM categoria = new CategoriaM(id, nome, tipo, usuarioId);
        return categoriaDAO.atualizar(categoria);
    }

    // ✅ Remover categoria
    public boolean removerCategoria(int id) {
        return categoriaDAO.remover(id);
    }
}
