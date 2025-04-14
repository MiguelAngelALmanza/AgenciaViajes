package org.example.Modelo;

import org.example.Database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;

public class DestinoDAO {

    public ArrayList<Destino> obtenerDestinos() {
        ArrayList<Destino> lista = new ArrayList<>();
        String sql = "SELECT * FROM destino";

        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Destino d = new Destino(
                        rs.getInt("iddestino"),
                        rs.getString("pais"),
                        rs.getString("ciudad"),
                        rs.getString("descripcion"),
                        rs.getString("infoTuristica"),
                        rs.getDouble("precio"),
                        rs.getString("categoria"),
                        rs.getString("ruta_imagen")
                );
                lista.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}

