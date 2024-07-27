package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.modelos.NivelAcesso;
import com.projecto.angovaquinha.modelos.NivelAcessoEnum;
import com.projecto.angovaquinha.repositorios.NivelAcessoRepositorio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NivelAcessoService {

    @Autowired
    private NivelAcessoRepositorio nivelAcessoRepositorio;

    public List<NivelAcesso> listarNivelAcesso() {
        return nivelAcessoRepositorio.findAll();
    }

    public NivelAcesso salvarNivelAcesso(NivelAcesso nivelAcesso){
        return nivelAcessoRepositorio.save(nivelAcesso);
    }

}
