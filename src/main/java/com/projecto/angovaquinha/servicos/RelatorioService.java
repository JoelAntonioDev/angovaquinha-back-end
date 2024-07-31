package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.InterfaceService.InterfaceServico;
import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.Contribuicao;
import com.projecto.angovaquinha.modelos.Relatorio;
import com.projecto.angovaquinha.repositorios.RelatorioRepositorio;
import org.hibernate.integrator.spi.IntegratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelatorioService implements InterfaceServico<Relatorio,Long> {

    @Autowired
    RelatorioRepositorio relatorioRepositorio;




    @Override
    public List<Relatorio> listarTodos() {return relatorioRepositorio.findAll();}

    @Override
    public Optional<Relatorio> buscarPorId(Long id) {return relatorioRepositorio.findById(id);}

    @Override
    public Relatorio adicionar(Relatorio relatorio) throws ExcecaoP {
        if(relatorio.getUsuario() == null || relatorio.getDescricao().equals("") || relatorio.getTitulo().equals("") || relatorio.getDataCriacao().equals(""))
            throw new ExcecaoP("Relatório inválido: alguns campos estão vazios ou nulos");
        return relatorioRepositorio.save(relatorio);
    }

    @Override
    public Relatorio editar(Long id, Relatorio relatorio) throws ExcecaoP {
        Optional<Relatorio> relatorioExistente = relatorioRepositorio.findById(id);

        if (!relatorioExistente.isPresent()) throw new ExcecaoP("Relatorio  não encontrado");

        Relatorio relatorioActualizado = relatorioExistente.get();

        if(relatorio.getDataCriacao()!=null) relatorioActualizado.setDataCriacao(relatorio.getDataCriacao());
        if(relatorio.getTitulo()!=null)relatorioActualizado.setTitulo(relatorio.getTitulo());
        return relatorioRepositorio.save(relatorioActualizado);
    }

    @Override
    public void eliminar(Long id) {
        relatorioRepositorio.deleteById(id);
    }
}
