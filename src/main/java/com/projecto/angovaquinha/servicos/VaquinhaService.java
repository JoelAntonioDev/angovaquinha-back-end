package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.Vaquinha;
import com.projecto.angovaquinha.repositorios.VaquinhaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaquinhaService {

    @Autowired
    VaquinhaRepositorio vaquinhaRepositorio;

    public List<Vaquinha> listarVaquinhas(){
        return vaquinhaRepositorio.findAll();
    }

    public Vaquinha buscarVaquinhaById(Long id){
        return vaquinhaRepositorio.findById(id).get();
    }

    public Vaquinha adicionarVaquinha(Vaquinha vaquinha) throws ExcecaoP {
        if(vaquinha.getUsuario() == null || vaquinha.getDescricao().equals("") || vaquinha.getTitulo().equals("") || vaquinha.getDataCriacao().equals("") || vaquinha.getEstadoVaquinha() == null || vaquinha.getContribuicao() == null)
            throw new ExcecaoP("Vaquinha inválida: alguns campos estão vazios ou nulos");
        return vaquinhaRepositorio.save(vaquinha);
    }

    /*public Vaquinha editarVaquinha(Long id, Vaquinha vaquinha) throws ExcecaoP {
        return vaquinhaRepositorio.updateVaquinhaById(id, vaquinha);
    }*/

    public void eliminarVaquinha(Long id){
        vaquinhaRepositorio.deleteById(id);
    }
}
