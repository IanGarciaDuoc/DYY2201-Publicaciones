package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String contenido;

    // Constructor con parámetros
    public Publicacion(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }
    // Constructor vacío requerido por JPA
    public Publicacion() {
    }
    // Relación Uno a Muchos con la entidad Comentario
    @JsonIgnore
    @OneToMany(mappedBy = "publicacion")
    private List<Comentario> comentarios;

    // Relación Uno a Muchos con la entidad Calificacion
    @JsonIgnore
    @OneToMany(mappedBy = "publicacion")
    private List<Calificacion> calificaciones;

    // Getters y setters para los atributos de la entidad
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public List<Comentario> getComentarios() {
        return comentarios;
    }
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }
    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }
}
