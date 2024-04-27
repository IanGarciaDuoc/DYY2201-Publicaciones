package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class ComentarioModelTest {

    @Test
    public void testConstructorConParametros() {
        // Crear un comentario de ejemplo con parámetros
        Publicacion publicacion = new Publicacion();
        Date fecha = new Date();
        Comentario comentario = new Comentario("Este es un comentario de prueba", publicacion, fecha);

        // Verificar si los atributos del comentario se configuraron correctamente
        assertEquals("Este es un comentario de prueba", comentario.getMensaje());
        assertEquals(publicacion, comentario.getPublicacion());
        assertEquals(fecha, comentario.getFecha());
    }

    @Test
    public void testConstructorVacio() {
        // Crear un comentario vacío
        Comentario comentario = new Comentario();

        // Verificar que el comentario se haya creado correctamente
        assertNotNull(comentario);
    }

    @Test
    public void testSetterYGetter() {
        // Crear un comentario vacío
        Comentario comentario = new Comentario();

        // Establecer valores para los atributos del comentario
        Publicacion publicacion = new Publicacion();
        comentario.setPublicacion(publicacion);
        comentario.setMensaje("Nuevo mensaje de comentario");
        Date fecha = new Date();
        comentario.setFecha(fecha);

        // Verificar si los valores se establecieron correctamente
        assertEquals(publicacion, comentario.getPublicacion());
        assertEquals("Nuevo mensaje de comentario", comentario.getMensaje());
        assertEquals(fecha, comentario.getFecha());
    }
}
