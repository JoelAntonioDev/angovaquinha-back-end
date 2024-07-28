package com.projecto.angovaquinha.repositorios;

import com.projecto.angovaquinha.modelos.Conta;
import com.projecto.angovaquinha.modelos.EstadoVaquinha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoVaquinhaRepositorio extends JpaRepository<EstadoVaquinha, Long> {

}
