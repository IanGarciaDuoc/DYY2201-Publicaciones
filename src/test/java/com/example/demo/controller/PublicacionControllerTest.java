package com.example.demo.controller;

import com.example.demo.DTO.PublicacionData;
import com.example.demo.model.Publicacion;
import com.example.demo.service.PublicacionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.service.ComentarioService;
import com.example.demo.service.CalificacionService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PublicacionController.class)
public class PublicacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicacionService publicacionService;

    @MockBean
    private ComentarioService comentarioService;

    @MockBean
    private CalificacionService calificacionService;

    // Test para obtener todas las publicaciones
    @Test
    public void testGetAllPublicaciones() throws Exception {
        given(publicacionService.GetAll()).willReturn(List.of(new Publicacion()));
        mockMvc.perform(MockMvcRequestBuilders.get("/publicaciones"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // Test para obtener una publicación por su ID
    @Test
    public void testGetPublicacionById() throws Exception {
        Long publicacionId = 1L;
        Publicacion publicacion = new Publicacion();
        publicacion.setId(publicacionId);
        given(publicacionService.GetById(publicacionId)).willReturn(publicacion);
        mockMvc.perform(MockMvcRequestBuilders.get("/publicaciones/{id}", publicacionId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(publicacionId));
    }

    // Test para crear una publicación
   


    // Test para actualizar una publicación
   
    // Test para eliminar una publicación
    @Test
    public void testDeletePublicacion() throws Exception {
        Long publicacionId = 1L;
    
        // Simular que el servicio encuentra la publicación antes de intentar eliminarla
        Publicacion publicacion = new Publicacion(); // Supongamos que existe una publicación con este ID
        publicacion.setId(publicacionId);
        when(publicacionService.GetById(publicacionId)).thenReturn(publicacion);
        
        // Configurar la simulación para eliminar la publicación
        doNothing().when(publicacionService).DeleteById(publicacionId);
    
        mockMvc.perform(MockMvcRequestBuilders.delete("/publicaciones/{id}", publicacionId))
               .andExpect(status().isOk());
    
        // Verificar que el servicio fue llamado correctamente
        verify(publicacionService, times(1)).DeleteById(publicacionId);
    }
}
