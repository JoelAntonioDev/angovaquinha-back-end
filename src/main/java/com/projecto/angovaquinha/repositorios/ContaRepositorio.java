package com.projecto.angovaquinha.repositorios;

import com.projecto.angovaquinha.modelos.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepositorio extends JpaRepository<Conta, Long> {

}
