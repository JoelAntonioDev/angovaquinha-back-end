package com.projecto.angovaquinha.repositorios;

import com.projecto.angovaquinha.modelos.Conta;
import com.projecto.angovaquinha.modelos.Contribuicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContribuicaoRepositorio extends JpaRepository<Contribuicao, Long> {
    public Contribuicao updateContribuicaoById(Long id, Contribuicao contribuicao);
}
