package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación Muchos a Uno con la entidad Publicacion
    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;

    // Atributos del comentario
    private String mensaje;
    private Date fecha;

    // Constructor con parámetros
    public Comentario(String mensaje, Publicacion publicacion, Date fecha) {
        this.mensaje = mensaje;
        this.publicacion = publicacion;
        this.fecha = fecha;
    }

    // Constructor vacío requerido por JPA
    public Comentario() {}

    // Métodos getters y setters para acceder y modificar los atributos de la entidad
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
