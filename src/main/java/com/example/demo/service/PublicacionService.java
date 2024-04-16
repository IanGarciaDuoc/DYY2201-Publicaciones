package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.PublicacionRepository;
import com.example.demo.model.Publicacion;

import java.util.List;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    // Método para obtener todas las publicaciones
    public List<Publicacion> GetAll(){
        return publicacionRepository.findAll();
    }

    // Método para obtener una publicación por su ID
    public Publicacion GetById(Long id) throws Exception{
        // Busca la publicación por su ID en el repositorio
        Publicacion actualPublicacion = publicacionRepository.findById(id).get();
        // Si no se encuentra la publicación, lanza una excepción
        if(actualPublicacion == null){
            throw new Exception("No se encontró la publicación con el ID: " + id);
        }
        return actualPublicacion;
    }

    // Método para crear una nueva publicación
    public Publicacion Create(Publicacion publicacion) throws Exception{
        // Validación: si la publicación no tiene comentarios, se lanza una excepción
        // if(publicacion.getComentarios() == null || publicacion.getComentarios().isEmpty()){
        //     throw new Exception("La publicación debe tener un comentario");
        // }
        // Guarda la nueva publicación en el repositorio y la devuelve
        return publicacionRepository.save(publicacion);
    }

    // Método para actualizar una publicación existente
    public Publicacion Update(Long id, Publicacion publicacion) throws Exception{
        // Obtiene la publicación existente por su ID
        Publicacion actualPublicacion = GetById(id);
        // Actualiza los atributos de la publicación con los valores proporcionados
        actualPublicacion.setCalificaciones(publicacion.getCalificaciones());
        actualPublicacion.setComentarios(publicacion.getComentarios());
        actualPublicacion.setTitulo(publicacion.getTitulo());
        // Guarda la publicación actualizada en el repositorio y la devuelve
        return publicacionRepository.save(actualPublicacion);
    }

    // Método para eliminar una publicación por su ID
    public void DeleteById(Long id){
        // Elimina la publicación del repositorio por su ID
        publicacionRepository.deleteById(id);
    }

}
