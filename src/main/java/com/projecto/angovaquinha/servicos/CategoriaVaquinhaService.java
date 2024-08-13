package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.modelos.CategoriaVaquinha;
import com.projecto.angovaquinha.repositorios.CategoriaVaquinhaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoriaVaquinhaService  {

    CategoriaVaquinhaRepositorio categoriaVaquinhaRepositorio;

    public CategoriaVaquinhaService(CategoriaVaquinhaRepositorio categoriaVaquinhaRepositorio) {
        this.categoriaVaquinhaRepositorio = categoriaVaquinhaRepositorio;
    }

    public List<CategoriaVaquinha> listar() {
        return (List<CategoriaVaquinha>) categoriaVaquinhaRepositorio.findAll();
    }
    public CategoriaVaquinha buscarPeloId(long id){
        return categoriaVaquinhaRepositorio.findById(id);
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
