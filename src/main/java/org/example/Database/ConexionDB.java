package org.example.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:postgresql://25.23.173.150:5432/agencia_viajes";
    private static final String USER = "miguel";
    private static final String PASSWORD = "1234";
    /*private static final String URL = "jdbc:postgresql://localhost/AgenciadeViajes";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";*/

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public Connection obtenerConexion() throws SQLException {
        return conectar();
    }
}