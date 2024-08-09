package com.projecto.angovaquinha.repositorios;

import com.projecto.angovaquinha.modelos.CategoriaVaquinha;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaVaquinhaRepositorio extends CrudRepository<CategoriaVaquinha, Integer> {
    CategoriaVaquinha findById(long id);
    CategoriaVaquinha findByNome(String nome);
    void deleteById(long id);

}
