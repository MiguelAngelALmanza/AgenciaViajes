package org.example.Modelo;

public class Destino {
    private int idDestino;
    private String pais;
    private String ciudad;
    private String descripcion;
    private String infoTuristica;
    private double precio;
    private String categoria;
    private String rutaImagen;

    public Destino(int idDestino, String pais, String ciudad, String descripcion, String infoTuristica,
                   double precio, String categoria, String rutaImagen) {
        this.idDestino = idDestino;
        this.pais = pais;
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.infoTuristica = infoTuristica;
        this.precio = precio;
        this.categoria = categoria;
        this.rutaImagen = rutaImagen;
    }

    public int getIdDestino() { return idDestino; }
    public String getPais() { return pais; }
    public String getCiudad() { return ciudad; }
    public String getDescripcion() { return descripcion; }
    public String getInfoTuristica() { return infoTuristica; }
    public double getPrecio() { return precio; }
    public String getCategoria() { return categoria; }
    public String getRutaImagen() { return rutaImagen; }
}

