package com.example.demo.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class CalificacionesModelTest {

    @Test
    public void testConstructorConParametros() {
        // Crear una publicación de ejemplo
        Publicacion publicacion = new Publicacion("Título de la publicación", "Contenido de la publicación");

        // Crear una calificación de ejemplo con parámetros
        Calificacion calificacion = new Calificacion(5, publicacion);

        // Verificar si los atributos de la calificación se configuraron correctamente
        assertEquals(5, calificacion.getPuntuacion());
        assertEquals(publicacion, calificacion.getPublicacion());
    }

    @Test
    public void testConstructorVacio() {
        // Crear una calificación vacía
        Calificacion calificacion = new Calificacion();

        // Verificar que la calificación se haya creado correctamente
        assertNotNull(calificacion);
    }

    @Test
    public void testSetterYGetter() {
        // Crear una publicación de ejemplo
        Publicacion publicacion = new Publicacion("Título de la publicación", "Contenido de la publicación");

        // Crear una calificación
        Calificacion calificacion = new Calificacion();

        // Establecer valores para los atributos de la calificación
        calificacion.setPuntuacion(4);
        calificacion.setPublicacion(publicacion);

        // Verificar que los atributos se hayan establecido correctamente
        assertEquals(4, calificacion.getPuntuacion());
        assertEquals(publicacion, calificacion.getPublicacion());
    }
}
