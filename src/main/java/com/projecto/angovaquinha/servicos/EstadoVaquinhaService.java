package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.EstadoVaquinha;
import com.projecto.angovaquinha.repositorios.EstadoVaquinhaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoVaquinhaService  {

    @Autowired
    EstadoVaquinhaRepositorio estadoVaquinhaRepositorio;

    /*public EstadoVaquinha editarEstadoVaquinha(Long id, EstadoVaquinha estadoVaquinha){
        return estadoVaquinhaRepositorio.updateEstadoVaquinhaById(id, estadoVaquinha);
    }*/
    public void eliminarEstadoVaquinha(Long id){
        estadoVaquinhaRepositorio.deleteById(id);
    }


    public List<EstadoVaquinha> listarTodos() {return estadoVaquinhaRepositorio.findAll();}


    public Optional<EstadoVaquinha> buscarPorId(Long id) {
        return estadoVaquinhaRepositorio.findById(id);
    }


    public EstadoVaquinha adicionar(EstadoVaquinha estadoVaquinha) throws ExcecaoP {
        if(estadoVaquinha.getEstado() == null)
            throw new ExcecaoP("Estado Vaquinha inválida: alguns campos estão vazios ou nulos");
        return estadoVaquinhaRepositorio.save(estadoVaquinha);
    }


    public EstadoVaquinha editar(Long id, EstadoVaquinha entidade) throws ExcecaoP {
        return null;
    }


    public void eliminar(Long id) {estadoVaquinhaRepositorio.deleteById(id);}
}
