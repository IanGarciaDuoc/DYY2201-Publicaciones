
package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PublicacionesModelTest {

    @Test
    public void testConstructorConParametros() {
        // Crear una publicación de ejemplo con parámetros
        Publicacion publicacion = new Publicacion("Título de la publicación", "Contenido de la publicación");

        // Verificar si los atributos de la publicación se configuraron correctamente
        assertEquals("Título de la publicación", publicacion.getTitulo());
        assertEquals("Contenido de la publicación", publicacion.getContenido());
    }

    @Test
    public void testConstructorVacio() {
        // Crear una publicación vacía
        Publicacion publicacion = new Publicacion();

        // Verificar que la publicación se haya creado correctamente
        assertNotNull(publicacion);
    }

    @Test
    public void testSetterYGetter() {
        // Crear una publicación vacía
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo("Nuevo título de la publicación");
        publicacion.setContenido("Nuevo contenido de la publicación");

        // Crear instancias de comentarios
        Comentario comentario1 = new Comentario("¡Excelente publicación!", publicacion, new Date());
        Comentario comentario2 = new Comentario("Gracias por compartir esto.", publicacion, new Date());

        // Agregar los comentarios a la lista de comentarios de la publicación
        List<Comentario> comentarios = new ArrayList<>();
        comentarios.add(comentario1);
        comentarios.add(comentario2);
        publicacion.setComentarios(comentarios);

        // Verificar que los comentarios se hayan establecido correctamente
        assertEquals(2, publicacion.getComentarios().size());
        assertEquals("¡Excelente publicación!", publicacion.getComentarios().get(0).getMensaje());
        assertEquals("Gracias por compartir esto.", publicacion.getComentarios().get(1).getMensaje());
        assertEquals(publicacion, publicacion.getComentarios().get(0).getPublicacion());
        assertEquals(publicacion, publicacion.getComentarios().get(1).getPublicacion());
    }

    @Test
    public void testCalificaciones() {
        // Crear una publicación vacía
        Publicacion publicacion = new Publicacion();

        // Crear una lista de calificaciones de ejemplo
        List        <Calificacion> calificaciones = new ArrayList<>();
        Calificacion calificacion1 = new Calificacion(5, publicacion);
        Calificacion calificacion2 = new Calificacion(4, publicacion);
        calificaciones.add(calificacion1);
        calificaciones.add(calificacion2);

        // Agregar las calificaciones a la publicación
        publicacion.setCalificaciones(calificaciones);

        // Verificar que las calificaciones se hayan establecido correctamente
        assertEquals(2, publicacion.getCalificaciones().size());
        assertEquals(5, publicacion.getCalificaciones().get(0).getPuntuacion());
        assertEquals(4, publicacion.getCalificaciones().get(1).getPuntuacion());
    }
}


