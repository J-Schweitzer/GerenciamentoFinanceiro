package Visao;

import Controle.CategoriaC;
import Controle.ContaC;
import Controle.TransacaoC;
import Modelo.CategoriaM;
import Modelo.ContaM;
import Modelo.TransacaoM;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class DashboardV extends JFrame {

    private JLabel tituloLabel;
    private JButton btnDadosConta;
    private JButton btnHistorico;
    private JButton btnRelatorioCategoria;
    private JButton btnTransferencia;
    private JButton btnCadastrarGastos;
    private CategoriaC categoriaController;
    private ContaC contaController;
    private TransacaoC transacaoController;
    private int usuarioId;

    public DashboardV(int usuarioId) {
        this.usuarioId = usuarioId;

        try {
            contaController = new ContaC();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao inicializar ContaC: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            contaController = null;
        }

        try {
            transacaoController = new TransacaoC();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao inicializar TransacaoC: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            transacaoController = null;
        }

        try {
            categoriaController = new CategoriaC();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao inicializar CategoriaC: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            categoriaController = null;
        }

        setTitle("Dashboard - Gerenciamento de Gastos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Color backgroundColor = Color.WHITE;
        Color tituloColor = new Color(33, 37, 41);
        Color borderColor = new Color(108, 117, 125);

        // Painel superior com título
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(backgroundColor);

        tituloLabel = new JLabel("Sistema de Gerenciamento de Gastos", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        tituloLabel.setForeground(tituloColor);
        topPanel.add(tituloLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Painel principal central
        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        mainPanel.setBackground(backgroundColor);

        // Painel de consultas
        JPanel consultaPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        consultaPanel.setBackground(backgroundColor);
        consultaPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(borderColor), "Área de Consultas"));

        btnDadosConta = criarBotao("Mostrar Dados da Conta");
        btnDadosConta.addActionListener(e -> mostrarDadosConta());
        consultaPanel.add(btnDadosConta);

        btnHistorico = criarBotao("Mostrar Histórico");
        btnHistorico.addActionListener(e -> mostrarHistorico());
        consultaPanel.add(btnHistorico);

        btnRelatorioCategoria = criarBotao("Relatório por Categoria");
        btnRelatorioCategoria.addActionListener(e -> mostrarRelatorioPorCategoria());
        consultaPanel.add(btnRelatorioCategoria);

        // Painel de transações
        JPanel transacaoPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        transacaoPanel.setBackground(backgroundColor);
        transacaoPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(borderColor), "Área de Transações"));

        btnTransferencia = criarBotao("Realizar Transferência");
        btnTransferencia.addActionListener(e -> {
            try {
                TransacaoV transacao = new TransacaoV();
                transacao.setVisible(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao abrir tela de transferência: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        transacaoPanel.add(btnTransferencia);

        btnCadastrarGastos = criarBotao("Cadastrar Gastos");
        btnCadastrarGastos.addActionListener(e -> {
            try {
                CadastrarGastosV cadastrarGastos = new CadastrarGastosV(usuarioId);
                cadastrarGastos.setVisible(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao abrir tela de cadastro de gastos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        transacaoPanel.add(btnCadastrarGastos);

        mainPanel.add(consultaPanel);
        mainPanel.add(transacaoPanel);

        add(mainPanel, BorderLayout.CENTER);

        // Espaçamento nas bordas
        ((JPanel) getContentPane()).setBackground(backgroundColor);
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setBackground(new Color(0, 123, 255));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return botao;
    }

    private void mostrarDadosConta() {
        if (contaController == null) {
            JOptionPane.showMessageDialog(this, "Controlador de conta não inicializado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            List<ContaM> contas = contaController.listarContasPorUsuario(usuarioId);
            StringBuilder dados = new StringBuilder("Dados da Conta:\n");
            for (ContaM conta : contas) {
                dados.append("ID: ").append(conta.getId())
                        .append(", Nome: ").append(conta.getNome())
                        .append(", Saldo: R$ ").append(conta.getSaldo()).append("\n");
            }
            JOptionPane.showMessageDialog(this, dados.toString(), "Dados da Conta", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados da conta: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarHistorico() {
        if (transacaoController == null) {
            JOptionPane.showMessageDialog(this, "Controlador de transação não inicializado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            List<TransacaoM> transacoes = transacaoController.listarPorUsuario(usuarioId);
            StringBuilder historico = new StringBuilder("Histórico de Transações:\n");
            for (TransacaoM transacao : transacoes) {
                historico.append("ID: ").append(transacao.getId())
                        .append(", Descrição: ").append(transacao.getDescricao())
                        .append(", Valor: R$ ").append(transacao.getValor())
                        .append(", Data: ").append(transacao.getData()).append("\n");
            }
            if (historico.length() == "Histórico de Transações:\n".length()) {
                historico.append("Nenhuma transação registrada.");
            }
            JOptionPane.showMessageDialog(this, historico.toString(), "Histórico de Transações", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar histórico: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarRelatorioPorCategoria() {
        if (transacaoController == null) {
            JOptionPane.showMessageDialog(this, "Controlador de transação não inicializado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            List<CategoriaM> categorias = categoriaController.listarPorUsuario(usuarioId);
            StringBuilder relatorio = new StringBuilder("Relatório por Categoria:\n");

            for (CategoriaM categoria : categorias) {
                List<TransacaoM> transacoes = transacaoController.listarPorCategoria(categoria.getId());
                relatorio.append("Categoria: ").append(categoria.getNome()).append("\n");
                for (TransacaoM transacao : transacoes) {
                    relatorio.append(" - ID: ").append(transacao.getId())
                            .append(", Descrição: ").append(transacao.getDescricao())
                            .append(", Valor: R$ ").append(transacao.getValor())
                            .append(", Data: ").append(transacao.getData()).append("\n");
                }
            }

            if (relatorio.length() == "Relatório por Categoria:\n".length()) {
                relatorio.append("Nenhuma transação registrada.");
            }

            JOptionPane.showMessageDialog(this, relatorio.toString(), "Relatório por Categoria", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar relatório: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardV dashboard = new DashboardV(1);
            dashboard.setVisible(true);
        });
    }
}
