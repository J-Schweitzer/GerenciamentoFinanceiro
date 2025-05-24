/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Modelo.ContaM;
import Modelo.DAO.ContaDAO;
import java.sql.SQLException;

import java.util.List;
/**
 *
 * @author Joao
 */
public class ContaC 
{
    private ContaDAO contaDAO;

    public ContaC() throws SQLException {
        this.contaDAO = new ContaDAO();
    }

    // ✅ Cadastrar conta
    public boolean cadastrarConta(String nome, double saldo, int usuarioId) {
        ContaM conta = new ContaM(0, nome, saldo, usuarioId);
        return contaDAO.cadastrar(conta);
    }

    // ✅ Buscar conta por ID
    public ContaM buscarPorId(int id) {
        return contaDAO.buscarPorId(id);
    }

    // ✅ Listar todas as contas de um usuário
    public List<ContaM> listarContasPorUsuario(int usuarioId) {
        return contaDAO.listarPorUsuario(usuarioId);
    }

    // ✅ Atualizar conta
    public boolean atualizarConta(int id, String nome, double saldo, int usuarioId) {
        ContaM conta = new ContaM(id, nome, saldo, usuarioId);
        return contaDAO.atualizar(conta);
    }

    // ✅ Remover conta
    public boolean removerConta(int id) {
        return contaDAO.remover(id);
    }
}
