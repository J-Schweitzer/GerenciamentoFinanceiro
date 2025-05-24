package Visao;

import javax.swing.*;
import Controle.UsuarioC;
import java.sql.SQLException;

public class LoginV extends JFrame {
    
    private final UsuarioC usuarioController;

    public LoginV() throws SQLException {
        usuarioController = new UsuarioC();
        initComponents();
    }

    private void initComponents() {
        // Inicialização dos componentes da interface gráfica
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Adicione campos de texto, botões, etc.
        // Exemplo:
        JLabel labelEmail = new JLabel("Email:");
        JTextField textEmail = new JTextField(20);
        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField textSenha = new JPasswordField(20);
        JButton buttonLogin = new JButton("Login");

        // Adicione ação ao botão de login
        buttonLogin.addActionListener(e -> {
            String email = textEmail.getText();
            String senha = new String(textSenha.getPassword());
            // Chame o método de autenticação do controlador
            if (usuarioController.autenticar(email, senha) != null) {
                // Login bem-sucedido
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
                // Redirecionar para a tela principal
            } else {
                // Login falhou
                JOptionPane.showMessageDialog(this, "Email ou senha incorretos.");
            }
        });

        // Layout da interface
        JPanel panel = new JPanel();
        panel.add(labelEmail);
        panel.add(textEmail);
        panel.add(labelSenha);
        panel.add(textSenha);
        panel.add(buttonLogin);
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginV login = null;
            try {
                login = new LoginV();
            } catch (SQLException ex) {
                System.getLogger(LoginV.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            login.setVisible(true);
        });
    }
}
