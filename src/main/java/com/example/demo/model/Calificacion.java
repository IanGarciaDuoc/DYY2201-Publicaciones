package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Relación Muchos a Uno con la entidad Publicacion
    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;
    // Atributo de la calificación
    private Integer puntuacion;
    // Constructor con parámetros
    public Calificacion(Integer puntuacion, Publicacion publicacion) {
        this.puntuacion = puntuacion;
        this.publicacion = publicacion;
    }
    // Constructor vacío requerido por JPA
    public Calificacion() {}
    // Métodos getters y setters para acceder y modificar los atributos de la entidad
    public Publicacion getPublicacion() {
        return publicacion;
    }
    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }
    public Integer getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
