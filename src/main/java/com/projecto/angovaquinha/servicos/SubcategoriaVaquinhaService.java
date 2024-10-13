package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.modelos.CategoriaVaquinha;
import com.projecto.angovaquinha.modelos.SubcategoriaVaquinha;
import com.projecto.angovaquinha.repositorios.SubcategoriaVaquinhaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoriaVaquinhaService  {

    @Autowired
    SubcategoriaVaquinhaRepositorio subcategoriaVaquinhaRepositorio;

    public List<SubcategoriaVaquinha> listar() {
        return (List<SubcategoriaVaquinha>) subcategoriaVaquinhaRepositorio.findAll();
    }
    public Optional<SubcategoriaVaquinha> buscarPeloId(long id){
        return subcategoriaVaquinhaRepositorio.findById(id);
    }
    public SubcategoriaVaquinha buscarPeloNome(String nome){
        return subcategoriaVaquinhaRepositorio.findByNome(nome);
    }
    public void eliminar(long id){
        subcategoriaVaquinhaRepositorio.deleteById(id);
    }
    public void adicionar(SubcategoriaVaquinha subcategoriaVaquinha){
        subcategoriaVaquinhaRepositorio.save(subcategoriaVaquinha);
    }
}
