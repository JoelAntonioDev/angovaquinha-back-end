package com.projecto.angovaquinha.repositorios;

import com.projecto.angovaquinha.modelos.Conta;
import com.projecto.angovaquinha.modelos.NivelAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelAcessoRepositorio extends JpaRepository<NivelAcesso, Long> {
    boolean existsByNivel(String nivel);
    int findIdByNivel(String nivel);
}
