package gerenciagastos;

import Visao.LoginV;

import javax.swing.*;
import java.sql.SQLException;

public class GerenciaGastos {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Inicia a tela de login
                LoginV login = new LoginV();
                login.setVisible(true);
            } catch (SQLException ex) {
                System.out.println("Erro ao iniciar a aplicação: " + ex.getMessage());
            }
        });
    }
}
