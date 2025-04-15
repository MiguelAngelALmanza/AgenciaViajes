package org.example.Modelo;

import java.time.LocalDate;
public class Reseña {
        private int id;
        private int idUsuario;
        private int idPaquete;
        private int rating;
        private String comentario;
        private LocalDate fecha;
        private int util;  // Nuevo campo

        // Constructor actualizado
        public Reseña(int id, int idUsuario, int idPaquete, int rating,
                      String comentario, LocalDate fecha, int util) {
            this.id = id;
            this.idUsuario = idUsuario;
            this.idPaquete = idPaquete;
            this.rating = rating;
            this.comentario = comentario;
            this.fecha = fecha;
            this.util = util;
        }
        public int getUtil() {return util;}public void setUtil(int util) {this.util = util;}
    public int getId() { return id; }
    public int getIdUsuario() { return idUsuario; }
    public int getIdPaquete() { return idPaquete; }
    public int getRating() { return rating; }
    public String getComentario() { return comentario; }
    public LocalDate getFecha() { return fecha; }
}
