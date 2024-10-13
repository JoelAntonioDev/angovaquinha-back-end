package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.CategoriaVaquinha;
import com.projecto.angovaquinha.repositorios.CategoriaVaquinhaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaVaquinhaService  {

    @Autowired
    CategoriaVaquinhaRepositorio categoriaVaquinhaRepositorio;


    public List<CategoriaVaquinha> listar() {
        return (List<CategoriaVaquinha>) categoriaVaquinhaRepositorio.findAll();
    }

    public Optional<CategoriaVaquinha> buscarPeloId(long id) {
        Optional<CategoriaVaquinha> categoriaVaquinha = categoriaVaquinhaRepositorio.findById(id);
        return categoriaVaquinha;
    }
    public CategoriaVaquinha buscarPeloNome(String nome){
        return categoriaVaquinhaRepositorio.findByNome(nome);
    }
    public void eliminar(long id){
        categoriaVaquinhaRepositorio.deleteById(id);
    }
    public void adicionar(CategoriaVaquinha categoriaVaquinha){
        categoriaVaquinhaRepositorio.save(categoriaVaquinha);
    }
}
