package com.projecto.angovaquinha.repositorios;

import com.projecto.angovaquinha.modelos.EstadoVaquinha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoVaquinhaRepositorio extends JpaRepository<EstadoVaquinha, Long> {
    Optional<EstadoVaquinha> findByEstado(String estado);
}
