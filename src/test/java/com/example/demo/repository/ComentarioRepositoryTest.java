package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Comentario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioRepositoryTest {

    @Autowired
    private ComentarioRepository comentarioRepository;

    private Comentario comentario;

    @BeforeEach
    public void setUp() {
        comentario = new Comentario();
        comentario.setMensaje("Este es un comentario de prueba.");
    }

    @AfterEach
    public void tearDown() {
        comentarioRepository.deleteAll();
    }

    @Test
    public void guardarComentario() {
        Comentario resultado = comentarioRepository.save(comentario);

        assertNotNull(resultado.getId());
        assertEquals("Este es un comentario de prueba.", resultado.getMensaje());
    }

    
}