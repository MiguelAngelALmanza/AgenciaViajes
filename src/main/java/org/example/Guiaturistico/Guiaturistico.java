package org.example.Guiaturistico;

import org.example.Database.ConexionDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.HashMap;

public class Guiaturistico extends JFrame {
    private JList<String> listaGuias;
    private DefaultListModel<String> modeloLista;
    private HashMap<String, Integer> nombreToId; // Mapea nombre => idguia

    private JLabel lblNombre;
    private JTextArea txtExperiencia;
    private JLabel lblIdiomas;
    private JLabel lblContacto;

    public Guiaturistico() {
        setTitle("Guías Turísticos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // Lista de guías
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 5;
        modeloLista = new DefaultListModel<>();
        listaGuias = new JList<>(modeloLista);
        listaGuias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollLista = new JScrollPane(listaGuias);
        scrollLista.setPreferredSize(new Dimension(150, 250));
        add(scrollLista, gbc);

        // Panel de detalles
        JPanel panelDetalles = new JPanel(new GridBagLayout());
        GridBagConstraints gbcDet = new GridBagConstraints();
        gbcDet.insets = new Insets(5, 5, 5, 5);
        gbcDet.anchor = GridBagConstraints.WEST;

        // Nombre
        gbcDet.gridx = 0; gbcDet.gridy = 0;
        panelDetalles.add(new JLabel("Nombre:"), gbcDet);
        lblNombre = new JLabel("-");
        gbcDet.gridx = 1;
        panelDetalles.add(lblNombre, gbcDet);

        // Experiencia
        gbcDet.gridx = 0; gbcDet.gridy = 1;
        panelDetalles.add(new JLabel("Experiencia:"), gbcDet);
        txtExperiencia = new JTextArea(4, 20);
        txtExperiencia.setLineWrap(true);
        txtExperiencia.setWrapStyleWord(true);
        txtExperiencia.setEditable(false);
        JScrollPane scrollExp = new JScrollPane(txtExperiencia);
        gbcDet.gridx = 1;
        panelDetalles.add(scrollExp, gbcDet);

        // Idiomas
        gbcDet.gridx = 0; gbcDet.gridy = 2;
        panelDetalles.add(new JLabel("Idiomas:"), gbcDet);
        lblIdiomas = new JLabel("-");
        gbcDet.gridx = 1;
        panelDetalles.add(lblIdiomas, gbcDet);

        // Contacto
        gbcDet.gridx = 0; gbcDet.gridy = 3;
        panelDetalles.add(new JLabel("Contacto:"), gbcDet);
        lblContacto = new JLabel("-");
        gbcDet.gridx = 1;
        panelDetalles.add(lblContacto, gbcDet);

        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 1;
        add(panelDetalles, gbc);

        // Lógica para seleccionar guía
        listaGuias.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String nombreSeleccionado = listaGuias.getSelectedValue();
                if (nombreSeleccionado != null) {
                    int idGuia = nombreToId.get(nombreSeleccionado);
                    cargarDetallesGuia(idGuia);
                }
            }
        });

        cargarListaGuias();
    }

    private void cargarListaGuias() {
        nombreToId = new HashMap<>();
        modeloLista.clear();

        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT idguia, nombre FROM guiaturistico ORDER BY nombre ASC")) {

            while (rs.next()) {
                int id = rs.getInt("idguia");
                String nombre = rs.getString("nombre");
                modeloLista.addElement(nombre);
                nombreToId.put(nombre, id);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar guías: " + e.getMessage());
        }
    }

    private void cargarDetallesGuia(int idGuia) {
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT nombre, experiencia, idiomas, contacto FROM guiaturistico WHERE idguia = ?")) {

            ps.setInt(1, idGuia);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lblNombre.setText(rs.getString("nombre"));
                    txtExperiencia.setText(rs.getString("experiencia"));
                    lblIdiomas.setText(rs.getString("idiomas"));
                    lblContacto.setText(rs.getString("contacto"));
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos del guía: " + e.getMessage());
        }
    }


}
