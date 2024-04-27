package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Calificacion;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CalificacionRepositoryTest {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Test
    public void guardarCalificacion() {
        Calificacion calificacion = new Calificacion();
        calificacion.setPuntuacion(5); // Asigna una puntuación de ejemplo

        Calificacion resultado = calificacionRepository.save(calificacion);

        assertNotNull(resultado.getId());
        assertEquals(5, resultado.getPuntuacion()); // Verifica si la puntuación se guarda correctamente
    }
}
