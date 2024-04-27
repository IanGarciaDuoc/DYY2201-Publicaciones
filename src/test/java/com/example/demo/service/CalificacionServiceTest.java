package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Calificacion;
import com.example.demo.repository.CalificacionRepository;

@ExtendWith(MockitoExtension.class)
public class CalificacionServiceTest {

    @InjectMocks
    private CalificacionService calificacionService;

    @Mock
    private CalificacionRepository calificacionRepositoryMock;

    @BeforeEach
    public void setUp() {
        // Configurar el comportamiento simulado del repositorio antes de cada prueba si es necesario
    }

    @Test
    public void testCrearCalificacion() throws Exception {
        // Preparar los datos de prueba
        Calificacion calificacion = new Calificacion();
        calificacion.setPuntuacion(5); // Asignar una puntuación de ejemplo

        // Configurar el comportamiento simulado del repositorio
        when(calificacionRepositoryMock.save(any())).thenReturn(calificacion);

        // Llamar al método de servicio y almacenar el resultado
        Calificacion resultado = calificacionService.create(calificacion);

        // Verificar si la calificación se creó correctamente
        assertNotNull(resultado); // Verificar si el resultado no es nulo
        assertEquals(5, resultado.getPuntuacion()); // Verificar si la puntuación coincide con la esperada
    }

    @Test
    public void testObtenerCalificacionPorId() throws Exception {
        // Preparar los datos de prueba
        Long idCalificacionExistente = 1L;
        Calificacion calificacionMock = new Calificacion();
        calificacionMock.setId(idCalificacionExistente);
        calificacionMock.setPuntuacion(4); // Asignar una puntuación de ejemplo

        // Configurar el comportamiento simulado del repositorio
        when(calificacionRepositoryMock.findById(idCalificacionExistente)).thenReturn(java.util.Optional.of(calificacionMock));

        // Ejecutar el método a probar
        Calificacion resultado = calificacionService.GetById(idCalificacionExistente);

        // Verificar si se obtiene la calificación esperada
        assertNotNull(resultado); // Verificar si el resultado no es nulo
        assertEquals(idCalificacionExistente, resultado.getId()); // Verificar si el ID coincide con el esperado
        assertEquals(4, resultado.getPuntuacion()); // Verificar si la puntuación coincide con la esperada
    }
}
