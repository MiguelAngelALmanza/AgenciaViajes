package org.example.Modelo;


import org.example.Database.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public boolean autenticar(String email, String contraseña) {
        String sql = "SELECT * FROM Usuario WHERE email = ? AND contraseña = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, contraseña);

            ResultSet rs = stmt.executeQuery();

            return rs.next(); // true si existe el usuario

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int obtenerIdUsuario(String email) {
        String sql = "SELECT idUsuario FROM Usuario WHERE email = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return rs.getInt("idUsuario");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // si no se encuentra
    }
}

