package org.example.Controlador;

import org.example.Modelo.Destino;
import org.example.Modelo.DestinoDAO;
import org.example.Vista.CatalogoVista;
import org.example.Vista.ReseñasApp;

import javax.swing.*;
import java.util.ArrayList;

public class CatalogoControlador {

    private CatalogoVista vista;
    private DestinoDAO dao;
    private ArrayList<Destino> destinos;

    public CatalogoControlador(CatalogoVista vista) {
        this.vista = vista;
        this.dao = new DestinoDAO();
        this.vista.setControlador(this);
        cargarDestinos(); // Cargar al abrir
    }

    public void cargarDestinos() {
        destinos = dao.obtenerDestinos();
        StringBuilder texto = new StringBuilder();

        for (Destino d : destinos) {
            texto.append("ID: ").append(d.getId()).append(" - ") // Mostrar el ID para reseñas
                    .append("Destino: ").append(d.getCiudad()).append(", ").append(d.getPais()).append("\n")
                    .append("Categoría: ").append(d.getCategoria()).append("\n")
                    .append("Precio: $").append(d.getPrecio()).append("\n")
                    .append("Descripción: ").append(d.getDescripcion()).append("\n")
                    .append("----------\n");
        }

        vista.mostrarDestinos(texto.toString());
    }

    public void verDetallesSeleccionado() {
        // Aquí podrías mostrar una ventana con info detallada
        JOptionPane.showMessageDialog(vista, "Función de ver detalles aún no implementada.");
    }

    public void verReseñasSeleccionadas() {
        JTextArea area = vista.getAreaDestinos();
        String texto = area.getSelectedText();

        if (texto == null || texto.isBlank()) {
            JOptionPane.showMessageDialog(vista, "Por favor selecciona un destino del texto primero.");
            return;
        }

        // Buscar el ID en el texto seleccionado
        int idSeleccionado = extraerIdDesdeTexto(texto);

        if (idSeleccionado != -1) {
            SwingUtilities.invokeLater(() -> new ReseñasApp(idSeleccionado).setVisible(true));
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo identificar el destino seleccionado.");
        }
    }

    private int extraerIdDesdeTexto(String texto) {
        try {
            String[] lineas = texto.split("\n");
            for (String linea : lineas) {
                if (linea.startsWith("ID: ")) {
                    return Integer.parseInt(linea.substring(4).split(" ")[0]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
