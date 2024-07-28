package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.Conta;
import com.projecto.angovaquinha.modelos.EstadoVaquinha;
import com.projecto.angovaquinha.repositorios.EstadoVaquinhaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoVaquinhaService {

    @Autowired
    EstadoVaquinhaRepositorio estadoVaquinhaRepositorio;

    public List<EstadoVaquinha> listarEstadoVaquinha(){
        return estadoVaquinhaRepositorio.findAll();
    }
    public EstadoVaquinha buscarEstadoVaquinhaById(Long id){
        return estadoVaquinhaRepositorio.findById(id).get();
    }
    public EstadoVaquinha adicionarEstadoVaquinha(EstadoVaquinha estadoVaquinha) throws ExcecaoP {
        if(estadoVaquinha.getEstado() == null)
            throw new ExcecaoP("Estado Vaquinha inválida: alguns campos estão vazios ou nulos");
        return estadoVaquinhaRepositorio.save(estadoVaquinha);
    }
    /*public EstadoVaquinha editarEstadoVaquinha(Long id, EstadoVaquinha estadoVaquinha){
        return estadoVaquinhaRepositorio.updateEstadoVaquinhaById(id, estadoVaquinha);
    }*/
    public void eliminarEstadoVaquinha(Long id){
        estadoVaquinhaRepositorio.deleteById(id);
    }
}
