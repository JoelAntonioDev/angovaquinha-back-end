package com.projecto.angovaquinha.repositorios;

import com.projecto.angovaquinha.modelos.Conta;
import com.projecto.angovaquinha.modelos.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatorioRepositorio extends JpaRepository<Relatorio, Long> {
    public Relatorio updateById(Long id, Relatorio relatorio);
}
