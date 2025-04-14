package org.example.Guiaturistico;

import org.example.Database.ConexionDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Guiaturistico extends JFrame {
    private JTextField txtIdGuia;
    private JButton btnBuscar;
    private JLabel lblNombre;
    private JTextArea txtExperiencia;
    private JLabel lblIdiomas;
    private JLabel lblContacto;

    public Guiaturistico() {
        setTitle("Guía Turístico");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Campo para ID
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("ID del Guía:"), gbc);

        txtIdGuia = new JTextField(10);
        gbc.gridx = 1;
        add(txtIdGuia, gbc);

        btnBuscar = new JButton("Buscar");
        gbc.gridx = 2;
        add(btnBuscar, gbc);

        // Nombre
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Nombre:"), gbc);
        lblNombre = new JLabel("-");
        gbc.gridx = 1;
        add(lblNombre, gbc);

        // Experiencia
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Experiencia:"), gbc);
        txtExperiencia = new JTextArea(4, 20);
        txtExperiencia.setLineWrap(true);
        txtExperiencia.setWrapStyleWord(true);
        txtExperiencia.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtExperiencia);
        gbc.gridx = 1;
        add(scroll, gbc);

        // Idiomas
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Idiomas:"), gbc);
        lblIdiomas = new JLabel("-");
        gbc.gridx = 1;
        add(lblIdiomas, gbc);

        // Contacto
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Contacto:"), gbc);
        lblContacto = new JLabel("-");
        gbc.gridx = 1;
        add(lblContacto, gbc);

        // Acción del botón
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarGuiaPorId();
            }
        });
    }

    private void buscarGuiaPorId() {
        String idTexto = txtIdGuia.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID para buscar.");
            return;
        }

        try {
            int idGuia = Integer.parseInt(idTexto);

            try (Connection conn = ConexionDB.conectar();
                 PreparedStatement ps = conn.prepareStatement("SELECT nombre, experiencia, idiomas, contacto FROM guiaturistico WHERE idguia = ?")) {

                ps.setInt(1, idGuia);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        lblNombre.setText(rs.getString("nombre"));
                        txtExperiencia.setText(rs.getString("experiencia"));
                        lblIdiomas.setText(rs.getString("idiomas"));
                        lblContacto.setText(rs.getString("contacto"));
                    } else {
                        JOptionPane.showMessageDialog(this, "Guía no encontrado.");
                        limpiarCampos();
                    }
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error en la base de datos: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        lblNombre.setText("-");
        txtExperiencia.setText("");
        lblIdiomas.setText("-");
        lblContacto.setText("-");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Guiaturistico().setVisible(true);
        });
    }
}
