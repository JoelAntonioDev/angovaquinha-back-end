package com.projecto.angovaquinha.servicos;


import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.Contribuicao;
import com.projecto.angovaquinha.modelos.Vaquinha;
import com.projecto.angovaquinha.repositorios.ContribuicaoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContribuicaoService {

    public Contribuicao editar(Long id, Contribuicao entidade) throws ExcecaoP {
        return null;
    }

    @Autowired
    private ContribuicaoRepositorio contribuicaoRepositorio;

    public List<Contribuicao> listarTodos(){
        return contribuicaoRepositorio.findAll();
    }


    public Optional<Contribuicao> buscarPorId(Long id) {return contribuicaoRepositorio.findById(id);}

    public Contribuicao adicionar(Contribuicao contribuicao) throws ExcecaoP {
        if(contribuicao.getUsuario() == null || contribuicao.getMoeda().equals("") || contribuicao.getValorMonetario() == 0.0 || contribuicao.getDataCriacao().equals(""))
            throw new ExcecaoP("Contribuição inválida: alguns campos estão vazios ou nulos");
        return contribuicaoRepositorio.save(contribuicao);
    }


    public void eliminar(Long id) {contribuicaoRepositorio.deleteById(id);}

    public List<Contribuicao> listarContribuicoesPorVaquinha(Vaquinha vaquinha){
        return contribuicaoRepositorio.findByVaquinha(vaquinha);
    }
}
