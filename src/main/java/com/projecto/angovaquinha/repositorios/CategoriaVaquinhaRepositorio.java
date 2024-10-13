package com.projecto.angovaquinha.repositorios;

import com.projecto.angovaquinha.modelos.CategoriaVaquinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoriaVaquinhaRepositorio extends JpaRepository<CategoriaVaquinha, Integer> {
    Optional<CategoriaVaquinha> findById(long id);
    CategoriaVaquinha findByNome(String nome);
    void deleteById(long id);

}
