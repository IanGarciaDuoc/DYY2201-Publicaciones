package com.example.demo.controller;

import com.example.demo.DTO.PublicacionData;
import com.example.demo.model.Calificacion;
import com.example.demo.model.Comentario;
import com.example.demo.model.Publicacion;
import com.example.demo.service.PublicacionService;
import com.example.demo.service.CalificacionService;
import com.example.demo.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;
    @Autowired
    private ComentarioService comentarioService;
    @Autowired
    private CalificacionService calificacionService;

    // GET para obtener todas las publicaciones
    @GetMapping
    public CollectionModel<EntityModel<Publicacion>> getAllPublicaciones() {
        List<EntityModel<Publicacion>> publicaciones = publicacionService.GetAll().stream()
                .map(publicacion -> {
                    try {
                        return EntityModel.of(publicacion,
                                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PublicacionController.class).getPublicacionById(publicacion.getId())).withSelfRel(),
                                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PublicacionController.class).getAllPublicaciones()).withRel("publicaciones"));
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
        return CollectionModel.of(publicaciones, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PublicacionController.class).getAllPublicaciones()).withSelfRel());
    }

    // GET para obtener una publicación por su ID
    @GetMapping("/{id}")
    public EntityModel<Publicacion> getPublicacionById(@PathVariable Long id) throws Exception {
        Publicacion publicacion = publicacionService.GetById(id);
        return EntityModel.of(publicacion,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PublicacionController.class).getPublicacionById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PublicacionController.class).getAllPublicaciones()).withRel("publicaciones"));
    }

    // POST para crear una nueva publicación
    @PostMapping
    public EntityModel<Publicacion> createPublicacion(@RequestBody PublicacionData publicacionData) throws Exception {
        Publicacion newPublicacion = new Publicacion(publicacionData.getTitulo(), publicacionData.getContenido());
        newPublicacion = publicacionService.Create(newPublicacion);
        Date hoy = new Date();
        Comentario newComentario = new Comentario(publicacionData.getComentario(), newPublicacion, hoy);
        newComentario = comentarioService.create(newComentario);
        Calificacion newCalificacion = new Calificacion(publicacionData.getCalificacion(), newPublicacion);
        newCalificacion = calificacionService.create(newCalificacion);

        ArrayList<Comentario> listComentarios = new ArrayList<>();
        listComentarios.add(newComentario);
        ArrayList<Calificacion> listCalificaciones = new ArrayList<>();
        listCalificaciones.add(newCalificacion);

        newPublicacion.setComentarios(listComentarios);
        newPublicacion.setCalificaciones(listCalificaciones);
        
        return EntityModel.of(newPublicacion,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PublicacionController.class).getPublicacionById(newPublicacion.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PublicacionController.class).getAllPublicaciones()).withRel("publicaciones"));
    }

    // PUT para actualizar una publicación existente
    @PutMapping("/{id}")
    public EntityModel<Publicacion> updatePublicacion(@PathVariable Long id, @RequestBody Publicacion publicacionDetails) throws Exception {
        Publicacion updatedPublicacion = publicacionService.Update(id, publicacionDetails);
        return EntityModel.of(updatedPublicacion,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PublicacionController.class).getPublicacionById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PublicacionController.class).getAllPublicaciones()).withRel("publicaciones"));
    }

    // DELETE para eliminar una publicación
    @DeleteMapping("/{id}")
    public void deletePublicacion(@PathVariable Long id) {
        publicacionService.DeleteById(id);
    }
}

