package com.example.demo.controller;

import com.example.demo.model.Calificacion;
import com.example.demo.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    // GET para obtener todas las calificaciones
    @GetMapping
    public List<Calificacion> getAllCalificaciones() {
        return calificacionService.obtenerTodasLasCalificaciones();
    }

    // GET para calcular el promedio de las calificaciones
    @GetMapping("/promedio")
    public double calcularPromedioDeCalificaciones() {
        // Llama al método en CalificacionService para calcular el promedio
        Optional<Double> promedioOptional = calificacionService.calcularPromedioDeCalificaciones();
        // Verifica si el promedio existe y lo devuelve, o devuelve 0 si no hay calificaciones
        return promedioOptional.orElse(0.0);
    }
    @GetMapping("/promedio/{idPublicacion}")
    public double calcularPromedioDeCalificacionesPorIdPublicacion(@PathVariable Long idPublicacion) {
        // Llama al método en CalificacionService para calcular el promedio
        Optional<Double> promedioOptional = calificacionService.calcularPromedioDeCalificacionesPorIdPublicacion(idPublicacion);
        // Verifica si el promedio existe y lo devuelve, o devuelve 0 si no hay calificaciones
        return promedioOptional.orElse(0.0);
    }
}
