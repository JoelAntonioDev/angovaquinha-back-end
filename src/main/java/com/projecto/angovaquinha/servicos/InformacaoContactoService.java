package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.EstadoVaquinha;
import com.projecto.angovaquinha.modelos.InformacaoContacto;
import com.projecto.angovaquinha.repositorios.InformacaoContactoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformacaoContactoService {

    @Autowired
    InformacaoContactoRepositorio informacaoContactoRepositorio;

    public List<InformacaoContacto> listarInformacaoContacto(){
        return informacaoContactoRepositorio.findAll();
    }
    public InformacaoContacto buscarInformacaoContactoById(Long id){
        return informacaoContactoRepositorio.findById(id).get();
    }
    public InformacaoContacto adcionarInformacaoContacto(InformacaoContacto informacaoContacto) throws ExcecaoP {
        if(informacaoContacto.getBairro().equals("") || informacaoContacto.getProvincia().equals("") || informacaoContacto.getMunicipio().equals("") || informacaoContacto.getNumeroTelefone().equals("") || informacaoContacto.getBilheteIdentidade().equals(""))
            throw new ExcecaoP("Informação de Contacto Inválida: alguns campos estão vazios ou nulos");
        return informacaoContactoRepositorio.save(informacaoContacto);
    }
    /*public InformacaoContacto editarInformacaoContacto(Long id, InformacaoContacto informacaoContacto){
        return informacaoContactoRepositorio.updateInformacaoContactoById(id, informacaoContacto);
    }*/
    public void eliminarInformacaoContacto(Long id){
        informacaoContactoRepositorio.deleteById(id);
    }
}

