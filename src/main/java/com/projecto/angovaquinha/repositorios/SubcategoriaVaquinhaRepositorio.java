package com.projecto.angovaquinha.repositorios;

import com.projecto.angovaquinha.modelos.SubcategoriaVaquinha;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubcategoriaVaquinhaRepositorio extends CrudRepository<SubcategoriaVaquinha, Integer> {
    Optional<SubcategoriaVaquinha> findById(long id);
    SubcategoriaVaquinha findByNome(String nome);
    void deleteById(long id);

}
