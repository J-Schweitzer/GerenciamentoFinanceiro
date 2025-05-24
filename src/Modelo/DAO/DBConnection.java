/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Joao
 */
public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/financeiro"; // Banco chamado "financeiro"
    private static final String USER = "postgres"; // Seu usuário do PostgreSQL
    private static final String PASSWORD = "91257590"; // Sua senha do PostgreSQL

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // Carrega o driver
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do PostgreSQL não encontrado!", e);
        }
    }

}
