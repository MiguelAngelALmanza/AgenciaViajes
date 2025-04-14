package org.example.Modelo;

import java.util.List;

public class Paquetebasico {
    private String destino;
    private double precio;
    private int duracionDias;
    private List<String> servicios;

    public Paquetebasico(String destino, double precio, int duracionDias, List<String> servicios) {
        this.destino = destino;
        this.precio = precio;
        this.duracionDias = duracionDias;
        this.servicios = servicios;
    }

    public String getDestino() { return destino; }
    public double getPrecio() { return precio; }
    public int getDuracionDias() { return duracionDias; }
    public List<String> getServicios() { return servicios; }

    @Override
    public String toString() {
        return destino + " - $" + precio + " - " + duracionDias + " días";
    }
}

