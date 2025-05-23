/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gerenciagastos;

import Modelo.DAO.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
            
/**
 *
 * @author Joao
 */
public class GerenciaGastos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Conexao bem-sucedida com o banco de dados!");
        } catch (SQLException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }

    
    }
    
}
