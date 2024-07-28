package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.Contribuicao;
import com.projecto.angovaquinha.repositorios.ContribuicaoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContribuicaoService {

    @Autowired
    private ContribuicaoRepositorio contribuicaoRepositorio;

    public List<Contribuicao> listarContribuicoes(){
        return contribuicaoRepositorio.findAll();
    }
    public Contribuicao buscarContribuicaoPorId(Long id){
        return contribuicaoRepositorio.findById(id).get();
    }
    public Contribuicao adicionarContribuicao(Contribuicao contribuicao) throws ExcecaoP {
        if(contribuicao.getUsuario() == null || contribuicao.getMoeda().equals("") || contribuicao.getValorMonetario() == 0.0 || contribuicao.getDataCriacao().equals(""))
            throw new ExcecaoP("Contribuição inválida: alguns campos estão vazios ou nulos");
        return contribuicaoRepositorio.save(contribuicao);
    }
    /*public Contribuicao editarContribuicao(Long id, Contribuicao contribuicao){
        return contribuicaoRepositorio.updateContribuicaoById(id, contribuicao);
    }*/
    public void eliminarContribuicao(Long id){
        contribuicaoRepositorio.deleteById(id);
    }
}
