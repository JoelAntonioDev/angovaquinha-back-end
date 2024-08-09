package com.projecto.angovaquinha.repositorios;

import com.projecto.angovaquinha.modelos.InformacaoContacto;
import com.projecto.angovaquinha.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InformacaoContactoRepositorio extends JpaRepository<InformacaoContacto, Long> {
    InformacaoContacto findByUsuario(Optional<Usuario> usuario);
    void deleteById(long id);
}
