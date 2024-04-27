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

import com.example.demo.model.Publicacion;
import com.example.demo.repository.PublicacionRepository;

@ExtendWith(MockitoExtension.class)
public class PublicacionServiceTest {

    @InjectMocks
    private PublicacionService publicacionService;

    @Mock
    private PublicacionRepository publicacionRepositoryMock;

    @BeforeEach
    public void setUp() {
        Publicacion publicacion = new Publicacion(); // Inicializar la instancia de Publicacion
        publicacion.setTitulo("Nuevo titulo test"); // Configurar el título de la publicación
    }
  

    @Test
    public void guardarPublicacion() throws Exception {
        // Crear una instancia de Publicacion
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo("Nuevo titulo test");

        // Configurar el comportamiento simulado de publicacionRepositoryMock
        when(publicacionRepositoryMock.save(any())).thenReturn(publicacion);

        // Llamar al método de servicio y almacenar el resultado
        Publicacion resultado = publicacionService.Create(publicacion);

        // Verificar si el título de la publicación guardada coincide con el título esperado
        assertEquals("Nuevo titulo test", resultado.getTitulo());
    }
    @Test
    public void testGetByIdExistente() throws Exception {
        // Preparar los datos de prueba
        Long idExistente = 1L;
        Publicacion publicacionMock = new Publicacion();
        publicacionMock.setId(idExistente);
        publicacionMock.setTitulo("Publicacion de prueba");

        // Configurar el comportamiento simulado del repositorio
        when(publicacionRepositoryMock.findById(idExistente)).thenReturn(java.util.Optional.of(publicacionMock));

        // Ejecutar el método a probar
        Publicacion resultado = publicacionService.GetById(idExistente);

        // Verificar si se obtiene la publicación esperada
        assertNotNull(resultado); // Se verifica si el resultado no es nulo
        assertEquals(idExistente, resultado.getId());
        assertEquals("Publicacion de prueba", resultado.getTitulo());
    }
}
