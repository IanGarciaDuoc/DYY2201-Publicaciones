package com.example.demo.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.example.demo.model.Publicacion;


@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
public class PublicacionRepositoryTest {

    @Autowired
    private PublicacionRepository publicacionRepository;


    @AfterEach
    public void tearDown() {
        publicacionRepository.deleteAll();
    }

    @Test
    public void guardarPublicacion() {
        Publicacion publicacion = new Publicacion();
        publicacion.setContenido("nuevo comentario");

        Publicacion resultado = publicacionRepository.save(publicacion);

        assertNotNull(resultado.getId());
        assertEquals("nuevo comentario", resultado.getContenido()); // Corregido para comparar el contenido de la publicación
    }

    @BeforeEach
    public void setUp() {
    // Crear y guardar una publicación de prueba en la base de datos antes de cada prueba
    Publicacion publicacion = new Publicacion();
    publicacion.setContenido("Contenido de prueba");
    publicacionRepository.save(publicacion);
}
}
