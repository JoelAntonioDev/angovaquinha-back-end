package com.projecto.angovaquinha.repositorios;


import com.projecto.angovaquinha.modelos.Contribuicao;
import com.projecto.angovaquinha.modelos.Vaquinha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContribuicaoRepositorio extends JpaRepository<Contribuicao, Long> {
    List<Contribuicao> findByVaquinha(Vaquinha vaquinha);
}
