package Visao;

import Controle.UsuarioC;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginV extends JFrame {

    private UsuarioC usuarioController;

    public LoginV() throws SQLException {
        usuarioController = new UsuarioC();
        initComponents();
    }

    private void initComponents() {
        setTitle("Login - Gerenciamento de Gastos");
        setSize(450, 350);
        setResizable(false);  // Desativa o redimensionamento da janela
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        Color primaryColor = new Color(0, 123, 255);
        Color backgroundColor = Color.WHITE;
        Color textColor = new Color(33, 37, 41);

        getContentPane().setBackground(backgroundColor);

        JLabel tituloLabel = new JLabel("Bem-vindo!");
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        tituloLabel.setForeground(primaryColor);

        JLabel subTituloLabel = new JLabel("Acesse sua conta para continuar");
        subTituloLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subTituloLabel.setForeground(new Color(100, 100, 100));

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelEmail.setForeground(textColor);

        JTextField textEmail = new JTextField(18);
        textEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelSenha.setForeground(textColor);

        JPasswordField textSenha = new JPasswordField(18);
        textSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton buttonLogin = new JButton("Entrar");
        estilizarBotao(buttonLogin, primaryColor);

        JButton buttonCadastrar = new JButton("Cadastrar");
        estilizarBotao(buttonCadastrar, new Color(40, 167, 69)); // Verde

        // Ações dos botões
        buttonLogin.addActionListener(e -> {
            String email = textEmail.getText();
            String senha = new String(textSenha.getPassword());
            if (usuarioController.autenticar(email, senha) != null) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");

                int usuarioId = usuarioController.buscarPorEmail(email).getId();
                DashboardV dashboard = new DashboardV(usuarioId);
                dashboard.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Email ou senha incorretos.");
            }
        });

        buttonCadastrar.addActionListener(e -> {
            try {
                CadastroV cadastro = new CadastroV();
                cadastro.setVisible(true);
                this.dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "Erro ao abrir a tela de cadastro: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Layout com GridBag
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(tituloLabel, gbc);

        gbc.gridy++;
        add(subTituloLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(labelEmail, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(textEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(labelSenha, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(textSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonLogin, gbc);

        gbc.gridy++;
        add(buttonCadastrar, gbc);
    }

    private void estilizarBotao(JButton botao, Color corFundo) {
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setBackground(corFundo);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(corFundo.darker()),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
