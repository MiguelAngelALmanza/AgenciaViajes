package org.example.Modelo;

import java.util.ArrayList;
import java.util.List;

public class DestinoDAO {

    private List<Destino> destinos;

    public DestinoDAO() {
        destinos = new ArrayList<>();
        // Agregar algunos destinos para ejemplo
        destinos.add(new Destino(1, "Francia", "París", "Ciudad del amor con arquitectura icónica", "Torre Eiffel, Louvre, gastronomía", 890.00, "Romantico", "imagenes/paris.jpg"));
        destinos.add(new Destino(2, "Japón", "Tokio", "Metrópoli moderna con historia y tecnología avanzada", "Templo Senso-ji, Shibuya, sushi", 12000.00, "Aventura", "imagenes/tokio.jpg"));
        destinos.add(new Destino(3, "Italia", "Roma", "Capital histórica con ruinas romanas y arte renacentista", "Coliseo, Vaticano, Fontana di Trevi", 75000.00, "Cultural", "imagenes/roma.jpg"));
        destinos.add(new Destino(4, "Brasil", "Río de Janeiro", "Playas famosas y vida nocturna", "Cristo Redentor, Copacabana, carnaval", 650.00, "Playa", "imagenes/rio.jpg"));
        destinos.add(new Destino(5, "Egipto", "El Cairo", "Cuna de la civilización con pirámides milenarias", "Pirámides de Giza, museo egipcio", 700.00, "Aventura", "imagenes/elcairo.jpg"));
    }

    public List<Destino> obtenerDestinosFiltrados(String busqueda, String categoria) {
        List<Destino> destinosFiltrados = new ArrayList<>();

        // Iterar sobre todos los destinos y aplicar los filtros
        for (Destino destino : destinos) {
            boolean coincideBusqueda = destino.getCiudad().toLowerCase().contains(busqueda.toLowerCase()) ||
                    destino.getPais().toLowerCase().contains(busqueda.toLowerCase());
            boolean coincideCategoria = categoria.equals("Todos") || destino.getCategoria().equalsIgnoreCase(categoria);

            if (coincideBusqueda && coincideCategoria) {
                destinosFiltrados.add(destino);
            }
        }

        return destinosFiltrados;
    }

    // Obtener todos los destinos
    public List<Destino> obtenerDestinos() {
        return destinos;
    }
}



