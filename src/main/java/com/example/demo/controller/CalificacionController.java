package com.example.demo.controller;

import com.example.demo.model.Calificacion;
import com.example.demo.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    // GET para obtener todas las calificaciones
    @GetMapping
    public CollectionModel<EntityModel<Calificacion>> getAllCalificaciones() {
        List<EntityModel<Calificacion>> calificaciones = calificacionService.obtenerTodasLasCalificaciones().stream()
                .map(calificacion -> {
                    try {
                        return EntityModel.of(calificacion,
                                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CalificacionController.class).getCalificacion(calificacion.getId())).withSelfRel(),
                                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CalificacionController.class).getAllCalificaciones()).withRel("calificaciones"));
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
        return CollectionModel.of(calificaciones, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CalificacionController.class).getAllCalificaciones()).withSelfRel());
    }

    // GET para obtener una calificación específica por id (nuevo método)
    @GetMapping("/{id}")
public EntityModel<Calificacion> getCalificacion(@PathVariable Long id) throws Exception {
    Calificacion calificacion = calificacionService.GetById(id);
        //.orElseThrow(() -> new RuntimeException("Calificación no encontrada")); // Asegúrate de que aquí lanzas una excepción específica
    return EntityModel.of(calificacion,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CalificacionController.class).getCalificacion(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CalificacionController.class).getAllCalificaciones()).withRel("calificaciones"));
}


    // Los métodos para calcular el promedio pueden incluir enlaces a la lista de calificaciones o al detalle de cada publicación
    @GetMapping("/promedio")
    public EntityModel<Double> calcularPromedioDeCalificaciones() {
        Optional<Double> promedioOptional = calificacionService.calcularPromedioDeCalificaciones();
        Double promedio = promedioOptional.orElse(0.0);
        return EntityModel.of(promedio,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CalificacionController.class).calcularPromedioDeCalificaciones()).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CalificacionController.class).getAllCalificaciones()).withRel("calificaciones"));
    }

    @GetMapping("/promedio/{idPublicacion}")
    public EntityModel<Double> calcularPromedioDeCalificacionesPorIdPublicacion(@PathVariable Long idPublicacion) {
        Optional<Double> promedioOptional = calificacionService.calcularPromedioDeCalificacionesPorIdPublicacion(idPublicacion);
        Double promedio = promedioOptional.orElse(0.0);
        return EntityModel.of(promedio,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CalificacionController.class).calcularPromedioDeCalificacionesPorIdPublicacion(idPublicacion)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CalificacionController.class).getAllCalificaciones()).withRel("calificaciones"));
    }
}
