/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Modelo.TransacaoM;
import Modelo.DAO.TransacaoDAO;
import java.sql.SQLException;

import java.util.List;
/**
 *
 * @author Joao
 */
public class TransacaoC {
    
    private TransacaoDAO transacaoDAO;

    public TransacaoC() throws SQLException {
        this.transacaoDAO = new TransacaoDAO();
    }

    // ✅ Cadastrar transação
    public boolean cadastrarTransacao(String descricao, double valor, String data, int categoriaId, int contaId, int usuarioId) {
        TransacaoM transacao = new TransacaoM(0, descricao, valor, data, categoriaId, contaId, usuarioId);
        return transacaoDAO.cadastrar(transacao);
    }

    // ✅ Buscar transação por ID
    public TransacaoM buscarPorId(int id) {
        return transacaoDAO.buscarPorId(id);
    }

    // ✅ Listar transações de um usuário
    public List<TransacaoM> listarPorUsuario(int usuarioId) {
        return transacaoDAO.listarPorUsuario(usuarioId);
    }

    // ✅ Listar transações por conta
    public List<TransacaoM> listarPorConta(int contaId) {
        return transacaoDAO.listarPorConta(contaId);
    }

    // ✅ Listar transações por categoria
    public List<TransacaoM> listarPorCategoria(int categoriaId) {
        return transacaoDAO.listarPorCategoria(categoriaId);
    }

    // ✅ Atualizar transação
    public boolean atualizarTransacao(int id, String descricao, double valor, String data, int categoriaId, int contaId, int usuarioId) {
        TransacaoM transacao = new TransacaoM(id, descricao, valor, data, categoriaId, contaId, usuarioId);
        return transacaoDAO.atualizar(transacao);
    }

    // ✅ Remover transação
    public boolean removerTransacao(int id) {
        return transacaoDAO.remover(id);
    }
    
}
