package com.example.demo.controller;

import com.example.demo.model.Calificacion;
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

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CalificacionController.class)
public class CalificacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalificacionService calificacionService;

    // Test para obtener todas las calificaciones
    @Test
    public void testGetAllCalificaciones() throws Exception {
        given(calificacionService.obtenerTodasLasCalificaciones()).willReturn(List.of(new Calificacion()));
        mockMvc.perform(MockMvcRequestBuilders.get("/calificaciones"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // Test para calcular el promedio de calificaciones
    @Test
    public void testCalcularPromedioDeCalificaciones() throws Exception {
        given(calificacionService.calcularPromedioDeCalificaciones()).willReturn(Optional.of(4.5));
        mockMvc.perform(MockMvcRequestBuilders.get("/calificaciones/promedio"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$").value(4.5));
    }

    // Test para calcular el promedio de calificaciones por ID de publicación
    @Test
    public void testCalcularPromedioDeCalificacionesPorIdPublicacion() throws Exception {
        Long idPublicacion = 1L;
        given(calificacionService.calcularPromedioDeCalificacionesPorIdPublicacion(idPublicacion)).willReturn(Optional.of(3.8));
        mockMvc.perform(MockMvcRequestBuilders.get("/calificaciones/promedio/{id}", idPublicacion))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$").value(3.8));
    }
}
