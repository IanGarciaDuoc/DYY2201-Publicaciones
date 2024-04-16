package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.CalificacionRepository;
import com.example.demo.model.Calificacion;

import java.util.List;
import java.util.Optional;

@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    // Método para obtener todas las calificaciones
    public List<Calificacion> obtenerTodasLasCalificaciones() {
        return calificacionRepository.findAll();
    }

    public Calificacion GetById(Long id) throws Exception{
        // Busca la publicación por su ID en el repositorio
        Calificacion actualCalificacion= calificacionRepository.findById(id).get();
        // Si no se encuentra la publicación, lanza una excepción
        if(actualCalificacion == null){
            throw new Exception("No se encontró la calificacion con el ID: " + id);
        }
        return actualCalificacion;
    }

    public Calificacion create(Calificacion calificacion) throws Exception {
        // You need to implement the save method in your ComentarioRepository
        return calificacionRepository.save(calificacion);
    }

    public void DeleteById(Long id){
        // Elimina la publicación del repositorio por su ID
        calificacionRepository.deleteById(id);
    }


    // Método para calcular el promedio de las calificaciones
    public Optional<Double> calcularPromedioDeCalificaciones() {
        List<Calificacion> calificaciones = obtenerTodasLasCalificaciones();
        if (calificaciones.isEmpty()) {
            return Optional.empty(); // No hay calificaciones, devuelve Optional vacío
        }

        // Calcular el promedio
        double sumatoria = 0;
        for (Calificacion calificacion : calificaciones) {
            sumatoria += calificacion.getPuntuacion(); // Suponiendo que el valor de la calificación es un double
        }
        double promedio = sumatoria / calificaciones.size();

        return Optional.of(promedio);
    }

    // Método para actualizar una calificación existente
    public Optional<Calificacion> actualizarCalificacion(Long idCalificacion, Integer nuevaPuntuacion) {
        Optional<Calificacion> calificacionOptional = calificacionRepository.findById(idCalificacion);
        if (calificacionOptional.isPresent()) {
            Calificacion calificacion = calificacionOptional.get();
            calificacion.setPuntuacion(nuevaPuntuacion);
            return Optional.of(calificacionRepository.save(calificacion));
        } else {
            return Optional.empty(); // La calificación con el ID dado no fue encontrada
        }
    }

    // Método para eliminar una calificación por su ID
    public boolean eliminarCalificacion(Long idCalificacion) {
        Optional<Calificacion> calificacionOptional = calificacionRepository.findById(idCalificacion);
        if (calificacionOptional.isPresent()) {
            calificacionRepository.deleteById(idCalificacion);
            return true; // La calificación fue eliminada con éxito
        } else {
            return false; // La calificación con el ID dado no fue encontrada
        }
    }

    public Optional<Double> calcularPromedioDeCalificacionesPorIdPublicacion(Long idPublicacion) {
        // Obtener todas las calificaciones por ID de publicación
        List<Calificacion> calificaciones = calificacionRepository.findByPublicacionId(idPublicacion);
    
        // Verificar si hay calificaciones asociadas a esa publicación
        if (calificaciones.isEmpty()) {
            return Optional.empty(); // Devolver un Optional vacío si no hay calificaciones
        }
    
        // Calcular el promedio de las calificaciones
        double sumatoria = 0;
        for (Calificacion calificacion : calificaciones) {
            sumatoria += calificacion.getPuntuacion(); // Suponiendo que el valor de la calificación es un double
        }
        double promedio = sumatoria / calificaciones.size();
    
        return Optional.of(promedio); // Devolver el promedio como un Optional
    }

}
