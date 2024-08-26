package com.projecto.angovaquinha.servicos;


import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.NivelAcesso;
import com.projecto.angovaquinha.repositorios.NivelAcessoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NivelAcessoService {

    @Autowired
    private NivelAcessoRepositorio nivelAcessoRepositorio;


    public List<NivelAcesso> listarTodos() {return nivelAcessoRepositorio.findAll();}


    public Optional<NivelAcesso> buscarPorId(Long id) {return nivelAcessoRepositorio.findById(id);}


    public NivelAcesso adicionar(NivelAcesso nivelAcesso) throws ExcecaoP {
        return nivelAcessoRepositorio.save(nivelAcesso);
    }


    public NivelAcesso editar(Long id, NivelAcesso entidade) throws ExcecaoP {return null;}


    public void eliminar(Long id) {}
}
