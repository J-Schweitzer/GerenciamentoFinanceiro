/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Modelo.UsuarioM;
import Modelo.DAO.UsuarioDAO;
import java.sql.SQLException;

import java.util.List;

/**
 *
 * @author Joao
 */
public class UsuarioC {
    
    private UsuarioDAO usuarioDAO;

    public UsuarioC() throws SQLException {
        this.usuarioDAO = new UsuarioDAO();
    }

    // ✅ Cadastrar usuário
    public boolean cadastrarUsuario(String nome, String email, String senha) {
        UsuarioM usuario = new UsuarioM(0, nome, email, senha);
        return usuarioDAO.cadastrar(usuario);
    }

    // ✅ Buscar usuário por ID
    public UsuarioM buscarPorId(int id) {
        return usuarioDAO.buscarPorId(id);
    }

    // ✅ Buscar usuário por e-mail (útil para login e verificações)
    public UsuarioM buscarPorEmail(String email) {
        return usuarioDAO.buscarPorEmail(email);
    }

    // ✅ Autenticar (login)
    public UsuarioM autenticar(String email, String senha) {
        return usuarioDAO.autenticar(email, senha);
    }

    // ✅ Listar todos os usuários (opcional, dependendo do sistema)
    public List<UsuarioM> listarTodos() {
        return usuarioDAO.listarTodos();
    }

    // ✅ Atualizar dados do usuário
    public boolean atualizarUsuario(int id, String nome, String email, String senha) {
        UsuarioM usuario = new UsuarioM(id, nome, email, senha);
        return usuarioDAO.atualizar(usuario);
    }

    // ✅ Remover usuário
    public boolean removerUsuario(int id) {
        return usuarioDAO.remover(id);
    }
}
