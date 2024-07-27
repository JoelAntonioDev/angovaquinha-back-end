package com.projecto.angovaquinha.repositorios;

import com.projecto.angovaquinha.modelos.NivelAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelAcessoRepositorio extends JpaRepository<NivelAcesso, Integer> {
    boolean existsByNivel(String nivel);
    // Remova métodos não usados e ajuste conforme necessário
}
