package org.example.Controlador;

import org.example.Modelo.Destino;
import org.example.Modelo.DestinoDAO;
import org.example.Vista.CatalogoVista;
import org.example.Vista.DetalleDestinoVista;

import java.util.List;

public class CatalogoControlador {

    private CatalogoVista vista;
    private DestinoDAO destinoDAO;

    public CatalogoControlador(CatalogoVista vista) {
        this.vista = vista;
        this.destinoDAO = new DestinoDAO();
        this.vista.setControlador(this);
        cargarDestinos(); // Carga inicial
    }

    public void cargarDestinos() {
        String busqueda = vista.getCampoBusquedaTexto();
        String categoria = vista.getCategoriaSeleccionada();
        List<Destino> filtrados = destinoDAO.obtenerDestinosFiltrados(busqueda, categoria);
        vista.mostrarDestinos(filtrados);
    }

    public void verDetallesSeleccionado() {
        Destino seleccionado = vista.getDestinoSeleccionado();
        if (seleccionado != null) {
            new DetalleDestinoVista(seleccionado).setVisible(true);
        } else {
            javax.swing.JOptionPane.showMessageDialog(vista, "Selecciona un destino para ver los detalles.");
        }
    }

    public void verReseñasSeleccionadas() {
        // Implementar si lo necesitas más adelante
    }
}

