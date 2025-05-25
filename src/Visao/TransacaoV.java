package Visao;

import Controle.ContaC;
import Modelo.ContaM;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TransacaoV extends JFrame {

    private ContaC contaController;
    private JComboBox<ContaM> comboContaOrigem;
    private JComboBox<ContaM> comboContaDestino;
    private JTextField textValor;

    public TransacaoV() throws SQLException {
        contaController = new ContaC();
        initComponents();
    }

    private void initComponents() {
        setTitle("Transação - Gerenciamento de Gastos");
        setSize(450, 350);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        Color backgroundColor = Color.WHITE;
        Color tituloColor = new Color(33, 37, 41);
        Color borderColor = new Color(108, 117, 125);
        Color buttonColor = new Color(0, 123, 255);

        getContentPane().setBackground(backgroundColor);

        JLabel labelTitulo = new JLabel("Transação entre Contas");
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        labelTitulo.setForeground(tituloColor);

        JLabel labelContaOrigem = new JLabel("Conta de Origem:");
        JLabel labelContaDestino = new JLabel("Conta de Destino:");
        JLabel labelValor = new JLabel("Valor:");

        labelContaOrigem.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelContaDestino.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelValor.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        comboContaOrigem = new JComboBox<>();
        comboContaDestino = new JComboBox<>();
        textValor = new JTextField(10);
        textValor.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        carregarContas();

        JButton buttonTransferir = new JButton("Transferir");
        buttonTransferir.setFont(new Font("Segoe UI", Font.BOLD, 16));
        buttonTransferir.setBackground(buttonColor);
        buttonTransferir.setForeground(Color.WHITE);
        buttonTransferir.setFocusPainted(false);
        buttonTransferir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonTransferir.addActionListener(e -> realizarTransferencia());

        // Layout com GridBag
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(labelTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(labelContaOrigem, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(comboContaOrigem, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(labelContaDestino, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(comboContaDestino, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(labelValor, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(textValor, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonTransferir, gbc);

        // Borda opcional
        setBorderPadding();
    }

    private void setBorderPadding() {
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void carregarContas() {
        List<ContaM> contas = contaController.listarContasPorUsuario(1); // Substituir pelo ID do usuário logado
        for (ContaM conta : contas) {
            comboContaOrigem.addItem(conta);
            comboContaDestino.addItem(conta);
        }
    }

    private void realizarTransferencia() {
        ContaM contaOrigem = (ContaM) comboContaOrigem.getSelectedItem();
        ContaM contaDestino = (ContaM) comboContaDestino.getSelectedItem();
        double valor;

        try {
            valor = Double.parseDouble(textValor.getText());
            if (valor <= 0) {
                throw new NumberFormatException("O valor deve ser positivo.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean sucesso = contaController.transferir(contaOrigem.getId(), contaDestino.getId(), valor);
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Transferência realizada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao realizar a transferência. Verifique os dados e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TransacaoV transacao = new TransacaoV();
                transacao.setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
