package com.example.demo.controller;

import com.example.demo.model.Calificacion;
import com.example.demo.service.CalificacionService;
import com.example.demo.repository.CalificacionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(CalificacionController.class)
public class CalificacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalificacionService calificacionService;

    @MockBean
    private CalificacionRepository calificacionRepository;

    // Test para obtener todas las calificaciones
   // Test para obtener todas las calificaciones
// Test para obtener todas las calificaciones
// Test para obtener todas las calificaciones
@Test
public void testGetAllCalificaciones() throws Exception {
    Calificacion calificacion = new Calificacion();  // Asumiendo que esta instancia esté correctamente inicializada si es necesario
    given(calificacionService.obtenerTodasLasCalificaciones()).willReturn(Arrays.asList(calificacion));
    mockMvc.perform(MockMvcRequestBuilders.get("/calificaciones"))
           .andExpect(status().isOk())
           .andExpect(content().contentType("application/hal+json"));  // Cambiado a application/hal+json
}

//
}
