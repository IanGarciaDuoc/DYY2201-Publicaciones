package com.example.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Comentario;
import com.example.demo.repository.ComentarioRepository;


import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ComentarioServiceTest {

    @InjectMocks
    private ComentarioService comentarioService;

    @Mock
    private ComentarioRepository comentarioRepositoryMock;

    private Comentario comentario; // Variable para almacenar la instancia de Comentario

    @BeforeEach
    public void setUp() {
        comentario = new Comentario(); // Inicializar la instancia de Comentario
        comentario.setMensaje("Nuevo comentario test"); // Configurar el contenido del comentario
    }

    @Test
    public void guardarComentario() throws Exception {
        // Configurar el comportamiento simulado de comentarioRepositoryMock
        when(comentarioRepositoryMock.save(any())).thenReturn(comentario);

        // Llamar al método de servicio y almacenar el resultado
        Comentario resultado = comentarioService.create(comentario);

        // Verificar si el contenido del comentario guardado coincide con el contenido esperado
        assertEquals("Nuevo comentario test", resultado.getMensaje());
    }
    @Test
    public void testObtenerComentarioPorId() throws Exception {
        // Preparar los datos de prueba
        Long idComentarioExistente = 1L;
        Comentario comentarioMock = new Comentario();
        comentarioMock.setId(idComentarioExistente);
        comentarioMock.setMensaje("Este es un comentario de prueba");

        // Configurar el comportamiento simulado del repositorio
        when(comentarioRepositoryMock.findById(idComentarioExistente)).thenReturn(java.util.Optional.of(comentarioMock));

        // Ejecutar el método a probar
        Comentario resultado = comentarioService.GetById(idComentarioExistente);

        // Verificar si se obtiene el comentario esperado
        assertEquals(comentarioMock, resultado);
    }
}
