package Visao;

import Controle.UsuarioC;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CadastroV extends JFrame {

    private UsuarioC usuarioController;

    public CadastroV() throws SQLException {
        usuarioController = new UsuarioC();
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro - Gerenciamento de Gastos");
        setSize(450, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        Color primaryColor = new Color(0, 123, 255);
        Color backgroundColor = Color.WHITE;
        Color textColor = new Color(33, 37, 41);

        getContentPane().setBackground(backgroundColor);

        JLabel tituloLabel = new JLabel("Criar Conta");
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        tituloLabel.setForeground(primaryColor);

        JLabel subTituloLabel = new JLabel("Preencha seus dados abaixo");
        subTituloLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subTituloLabel.setForeground(new Color(100, 100, 100));

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelNome.setForeground(textColor);

        JTextField textNome = new JTextField(18);
        textNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));

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

        JButton buttonCadastrar = new JButton("Cadastrar");
        estilizarBotao(buttonCadastrar, new Color(40, 167, 69)); // Verde

        JButton buttonVoltar = new JButton("Voltar");
        estilizarBotao(buttonVoltar, primaryColor);

        buttonCadastrar.addActionListener(e -> {
            String nome = textNome.getText();
            String email = textEmail.getText();
            String senha = new String(textSenha.getPassword());

            if (usuarioController.cadastrarUsuario(nome, email, senha)) {
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
                try {
                    LoginV login = new LoginV();
                    login.setVisible(true);
                    this.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao abrir login: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao realizar cadastro. Tente novamente.");
            }
        });

        buttonVoltar.addActionListener(e -> {
            try {
                LoginV login = new LoginV();
                login.setVisible(true);
                this.dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao abrir login: " + ex.getMessage());
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
        add(labelNome, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(textNome, gbc);

        gbc.gridx = 0;
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
        add(buttonCadastrar, gbc);

        gbc.gridy++;
        add(buttonVoltar, gbc);
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
