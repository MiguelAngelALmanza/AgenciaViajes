package org.example.Controlador;

import org.example.Modelo.Destino;
import org.example.Modelo.DestinoDAO;
import org.example.Vista.CatalogoVista;

import java.util.ArrayList;

public class CatalogoControlador {

    private CatalogoVista vista;
    private DestinoDAO dao;

    public CatalogoControlador(CatalogoVista vista) {
        this.vista = vista;
        this.dao = new DestinoDAO();
        this.vista.setControlador(this);
        cargarDestinos(); // Cargar al abrir
    }

    public void cargarDestinos() {
        ArrayList<Destino> destinos = dao.obtenerDestinos();
        StringBuilder texto = new StringBuilder();

        for (Destino d : destinos) {
            texto.append("Destino: ").append(d.getCiudad()).append(", ").append(d.getPais()).append("\n")
                    .append("Categoría: ").append(d.getCategoria()).append("\n")
                    .append("Precio: $").append(d.getPrecio()).append("\n")
                    .append("Descripción: ").append(d.getDescripcion()).append("\n")
                    .append("----------\n");
        }

        vista.mostrarDestinos(texto.toString());
    }
}

