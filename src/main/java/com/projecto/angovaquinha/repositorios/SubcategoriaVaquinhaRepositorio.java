package com.projecto.angovaquinha.repositorios;

import com.projecto.angovaquinha.modelos.SubcategoriaVaquinha;
import org.springframework.data.repository.CrudRepository;

public interface SubcategoriaVaquinhaRepositorio extends CrudRepository<SubcategoriaVaquinha, Integer> {
    SubcategoriaVaquinha findById(long id);
    SubcategoriaVaquinha findByNome(String nome);
    void deleteById(long id);

}
