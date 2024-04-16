package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.ComentarioRepository;
import com.example.demo.model.Comentario;

import java.util.List;

@Service // Add @Service annotation to mark this class as a service component
public class ComentarioService {

    @Autowired // Add @Autowired annotation to inject ComentarioRepository bean
    private ComentarioRepository comentarioRepository;

    public List<Comentario> GetAll(){
        return comentarioRepository.findAll();
    }

    public Comentario GetById(Long id) throws Exception{
        // Busca la publicación por su ID en el repositorio
        Comentario actualComentario= comentarioRepository.findById(id).get();
        // Si no se encuentra la publicación, lanza una excepción
        if(actualComentario == null){
            throw new Exception("No se encontró el comentario con el ID: " + id);
        }
        return actualComentario;
    }

    public Comentario create(Comentario comentario) throws Exception {
        // You need to implement the save method in your ComentarioRepository
        return comentarioRepository.save(comentario);
    }

    public void DeleteById(Long id){
        // Elimina la publicación del repositorio por su ID
        comentarioRepository.deleteById(id);
    }
    

}
