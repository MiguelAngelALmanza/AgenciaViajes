package org.example.Database;

import org.example.Modelo.Reseña;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/agencia_viajes";
    private static final String USER = "gustavo";
    private static final String PASSWORD = "root";

    public static List<Reseña> getReseñas(int idPaquete, String filtro) {
        List<Reseña> reseñas = new ArrayList<>();

        String query = "{ call obtener_reseñas(?, ?) }";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             CallableStatement cstmt = conn.prepareCall(query)) {

            cstmt.setInt(1, idPaquete);
            cstmt.setString(2, filtro.equals("top") ? "top" : "recent");

            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {
                Reseña reseña = new Reseña(
                        rs.getInt("idreseña"),
                        rs.getInt("idusuario"),
                        rs.getInt("idpaquete"),
                        rs.getInt("rating"),
                        rs.getString("comentario"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("util")
                );
                reseñas.add(reseña);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reseñas;
    }

    // Método corregido para marcar una reseña como útil
    public static boolean marcarReseñaUtil(int idReseña) {
        String query = "{ ? = call marcar_reseña_util(?) }";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             CallableStatement cstmt = conn.prepareCall(query)) {

            cstmt.registerOutParameter(1, Types.BOOLEAN);
            cstmt.setInt(2, idReseña);
            cstmt.execute();

            return cstmt.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}