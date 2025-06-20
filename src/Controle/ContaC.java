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
    
    public boolean transferir(int contaOrigemId, int contaDestinoId, double valor) {
        // Verificar se o valor é positivo
        if (valor <= 0) {
            System.out.println("O valor da transferência deve ser positivo.");
            return false;
        }
        // Buscar contas
        ContaM contaOrigem = buscarPorId(contaOrigemId);
        ContaM contaDestino = buscarPorId(contaDestinoId);
        // Verificar se as contas existem
        if (contaOrigem == null || contaDestino == null) {
            System.out.println("Uma das contas não existe.");
            return false;
        }
        // Verificar se a conta de origem tem saldo suficiente
        if (contaOrigem.getSaldo() < valor) {
            System.out.println("Saldo insuficiente na conta de origem.");
            return false;
        }
        // Realizar a transferência
        contaOrigem.setSaldo(contaOrigem.getSaldo() - valor); // Debitar da conta de origem
        contaDestino.setSaldo(contaDestino.getSaldo() + valor); // Creditar na conta de destino
        // Atualizar as contas no banco de dados
        boolean atualizadoOrigem = contaDAO.atualizar(contaOrigem);
        boolean atualizadoDestino = contaDAO.atualizar(contaDestino);
        return atualizadoOrigem && atualizadoDestino; 
    }
}
