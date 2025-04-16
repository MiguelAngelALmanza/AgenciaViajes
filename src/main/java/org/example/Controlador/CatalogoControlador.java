package org.example.Controlador;

import org.example.Modelo.Destino;
import org.example.Modelo.DestinoDAO;
import org.example.Vista.CatalogoVista;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogoControlador {

    private CatalogoVista vista;
    private DestinoDAO dao;
    private ArrayList<Destino> destinos;

    public CatalogoControlador(CatalogoVista vista) {
        this.vista = vista;
        this.dao = new DestinoDAO();
        this.vista.setControlador(this);
        cargarDestinos(); // Cargar al inicio
    }

    public void cargarDestinos() {
        destinos = dao.obtenerDestinos();
        vista.mostrarDestinos(destinos);
    }

    public void aplicarFiltro(String texto, String categoria) {
        List<Destino> filtrados = destinos.stream()
                .filter(d -> (texto.isEmpty() || d.getCiudad().toLowerCase().contains(texto.toLowerCase())
                        || d.getPais().toLowerCase().contains(texto.toLowerCase()))
                        && (categoria.equals("Todos") || d.getCategoria().equalsIgnoreCase(categoria)))
                .collect(Collectors.toList());

        vista.mostrarDestinos(filtrados);
    }

    public void verDetallesSeleccionado() {
        Destino destinoSeleccionado = vista.getDestinoSeleccionado();

        if (destinoSeleccionado != null) {
            // Mostrar los detalles de este destino
            String detalles = "<html><h2>" + destinoSeleccionado.getCiudad() + ", " + destinoSeleccionado.getPais() + "</h2>"
                    + "<p><b>Descripción:</b> " + destinoSeleccionado.getDescripcion() + "</p>"
                    + "<p><b>Información Turística:</b> " + destinoSeleccionado.getInfoTuristica() + "</p>"
                    + "<p><b>Precio:</b> $" + destinoSeleccionado.getPrecio() + "</p>"
                    + "<p><b>Categoría:</b> " + destinoSeleccionado.getCategoria() + "</p>"
                    + "</html>";

            JOptionPane.showMessageDialog(vista, detalles, "Detalles del Destino", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista, "Por favor selecciona un destino para ver los detalles.");
        }
    }


    public void verReseñasSeleccionadas() {
        JOptionPane.showMessageDialog(vista, "Función de ver reseñas aún no implementada.");
    }
}
