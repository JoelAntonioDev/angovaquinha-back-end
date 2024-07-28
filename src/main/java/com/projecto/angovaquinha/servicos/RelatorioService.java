package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.Contribuicao;
import com.projecto.angovaquinha.modelos.Relatorio;
import com.projecto.angovaquinha.repositorios.RelatorioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    RelatorioRepositorio relatorioRepositorio;

    public List<Relatorio> listarRelatorios(){
        return relatorioRepositorio.findAll();
    }

    public Relatorio buscarRelatorioById(Long id){
        return relatorioRepositorio.findById(id).get();
    }

    public Relatorio adicionarRelatorio(Relatorio relatorio) throws ExcecaoP {
        if(relatorio.getUsuario() == null || relatorio.getDescricao().equals("") || relatorio.getTitulo().equals("") || relatorio.getDataCriacao().equals(""))
            throw new ExcecaoP("Relatório inválido: alguns campos estão vazios ou nulos");
        return relatorioRepositorio.save(relatorio);
    }

    /*public Relatorio editarRelatorio(Long id, Relatorio relatorio) throws ExcecaoP {
        return relatorioRepositorio.updateRelatorioById(id, relatorio);
    }*/

    public void eliminarContribuicao(Long id){
        relatorioRepositorio.deleteById(id);
    }
}
