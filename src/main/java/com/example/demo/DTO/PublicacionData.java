package com.example.demo.DTO;

public class PublicacionData {
    
    private String titulo;
    private String comentario;
    private Integer calificacion;
    private String contenido;

    // Constructor con parámetros
    public PublicacionData(String titulo, String comentario, Integer calificacion, String contenido) {
        this.titulo = titulo;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.contenido = contenido;
    }

    // Métodos getters y setters para acceder y modificar los atributos del DTO
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
