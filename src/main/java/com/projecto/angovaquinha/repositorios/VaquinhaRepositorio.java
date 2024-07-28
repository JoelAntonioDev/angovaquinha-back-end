package com.projecto.angovaquinha.repositorios;

import com.projecto.angovaquinha.modelos.Conta;
import com.projecto.angovaquinha.modelos.Vaquinha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaquinhaRepositorio extends JpaRepository<Vaquinha, Long> {
    public Vaquinha updateById(Long id, Vaquinha vaquinha);
}
