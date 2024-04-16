package com.example.demo.controller;

import com.example.demo.DTO.PublicacionData;
import com.example.demo.model.Calificacion;
import com.example.demo.model.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Publicacion;
import com.example.demo.service.PublicacionService;
import com.example.demo.service.CalificacionService;
import com.example.demo.service.ComentarioService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    public List<Publicacion> getAllPublicaciones() {
        return publicacionService.GetAll();
    }

    // GET para obtener una publicación por su ID
    @GetMapping("/{id}")
    public Publicacion getPublicacionById(@PathVariable Long id) throws Exception {
        return publicacionService.GetById(id);
    }
     @PostMapping
     public Publicacion createPublicacion(@RequestBody PublicacionData publicacionData) throws Exception{
        Publicacion newPublicacion = new Publicacion(publicacionData.getTitulo(), publicacionData.getContenido());
        newPublicacion = publicacionService.Create(newPublicacion);
        Date hoy = new Date();
        Comentario newComentario = new Comentario(publicacionData.getComentario(), newPublicacion, hoy);
        newComentario = comentarioService.create(newComentario);
        Calificacion newCalificacion = new Calificacion(publicacionData.getCalificacion(), newPublicacion);
        newCalificacion = calificacionService.create(newCalificacion);

        ArrayList<Comentario> listcomentarios = new ArrayList<Comentario>();
        listcomentarios.add(newComentario);

        ArrayList<Calificacion> listcalificacion = new ArrayList<Calificacion>();
        listcalificacion.add(newCalificacion);

        newPublicacion.setComentarios(listcomentarios);
        newPublicacion.setCalificaciones(listcalificacion);
        return newPublicacion;
    }
    // PUT para actualizar una publicación existente
    @PutMapping("/{id}")
    public Publicacion updatePublicacion(@PathVariable Long id, @RequestBody Publicacion publicacionDetails) throws Exception {
        return publicacionService.Update(id, publicacionDetails);
    }

    // DELETE para eliminar una publicación
    @DeleteMapping("/{id}")
    public void deletePublicacion(@PathVariable Long id) {
        publicacionService.DeleteById(id);
    }
}
