package org.example.Modelo;

import java.time.LocalDate;

public class Reseña {
    private Integer id;
    private Integer idUsuario;
    private Integer idPaquete;
    private Integer rating;
    private String comentario;
    private LocalDate fecha;
    private Integer util;
    private String nombreUsuario;

    public Reseña() {
    }

    public Reseña(Integer id, Integer idUsuario, Integer idPaquete, Integer rating,
                  String comentario, LocalDate fecha, Integer util) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPaquete = idPaquete;
        this.rating = rating;
        this.comentario = comentario;
        this.fecha = fecha;
        this.util = util;
    }

    public Reseña(Integer idUsuario, Integer idPaquete, Integer rating,
                  String comentario, LocalDate fecha) {
        this(null, idUsuario, idPaquete, rating, comentario, fecha, 0);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        if (rating != null && (rating < 1 || rating > 5)) {
            throw new IllegalArgumentException("El rating debe estar entre 1 y 5");
        }
        this.rating = rating;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getUtil() {
        return util;
    }

    public void setUtil(Integer util) {
        this.util = util;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void incrementarUtil() {
        this.util = (this.util == null) ? 1 : this.util + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reseña reseña = (Reseña) o;
        return id != null && id.equals(reseña.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format(
                "Reseña [ID: %d, Usuario: %d (%s), Paquete: %d, Rating: %d/5, Útil: %d, Fecha: %s]%nComentario: %s",
                id,
                idUsuario,
                nombreUsuario != null ? nombreUsuario : "Anónimo",
                idPaquete,
                rating,
                util,
                fecha != null ? fecha.toString() : "N/A",
                comentario != null ? comentario : "(Sin comentario)"
        );
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private Integer idUsuario;
        private Integer idPaquete;
        private Integer rating;
        private String comentario;
        private LocalDate fecha;
        private Integer util;
        private String nombreUsuario;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder idUsuario(Integer idUsuario) {
            this.idUsuario = idUsuario;
            return this;
        }

        public Builder idPaquete(Integer idPaquete) {
            this.idPaquete = idPaquete;
            return this;
        }

        public Builder rating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder comentario(String comentario) {
            this.comentario = comentario;
            return this;
        }

        public Builder fecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder util(Integer util) {
            this.util = util;
            return this;
        }

        public Builder nombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
            return this;
        }

        public Reseña build() {
            Reseña reseña = new Reseña();
            reseña.setId(id);
            reseña.setIdUsuario(idUsuario);
            reseña.setIdPaquete(idPaquete);
            reseña.setRating(rating);
            reseña.setComentario(comentario);
            reseña.setFecha(fecha);
            reseña.setUtil(util);
            reseña.setNombreUsuario(nombreUsuario);
            return reseña;
        }
    }
}