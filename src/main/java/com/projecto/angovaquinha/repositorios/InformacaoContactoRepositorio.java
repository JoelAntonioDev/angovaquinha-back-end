package com.projecto.angovaquinha.repositorios;

import com.projecto.angovaquinha.modelos.Conta;
import com.projecto.angovaquinha.modelos.InformacaoContacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformacaoContactoRepositorio extends JpaRepository<InformacaoContacto, Long> {

}
