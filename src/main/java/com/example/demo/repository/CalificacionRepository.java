package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Calificacion;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    List<Calificacion> findByPublicacionId(Long idPublicacion);
}
