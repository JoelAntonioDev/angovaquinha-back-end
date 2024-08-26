package com.projecto.angovaquinha.servicos;


import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.InformacaoContacto;
import com.projecto.angovaquinha.modelos.Usuario;
import com.projecto.angovaquinha.repositorios.InformacaoContactoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformacaoContactoService {

    @Autowired
    InformacaoContactoRepositorio informacaoContactoRepositorio;


    public List<InformacaoContacto> listarTodos() {
        return informacaoContactoRepositorio.findAll();
    }


    public Optional<InformacaoContacto> buscarPorId(Long id) {
        return informacaoContactoRepositorio.findById(id);
    }


    public InformacaoContacto adicionar(InformacaoContacto informacaoContacto) throws ExcecaoP {
        if(informacaoContacto.getBairro().equals("") || informacaoContacto.getProvincia().equals("") || informacaoContacto.getMunicipio().equals("") || informacaoContacto.getNumeroTelefone().equals("") || informacaoContacto.getBilheteIdentidade().equals(""))
            throw new ExcecaoP("Informação de Contacto Inválida: alguns campos estão vazios ou nulos");
        return informacaoContactoRepositorio.save(informacaoContacto);
    }

    public InformacaoContacto editar(Long id, InformacaoContacto informacaoContacto) throws ExcecaoP {
        Optional<InformacaoContacto> infomacaoExistente = informacaoContactoRepositorio.findById(id);

        if (!infomacaoExistente.isPresent()) throw new ExcecaoP("Informacao de Contacto não encontrado");

        InformacaoContacto infomacaoAtualizado = infomacaoExistente.get();
        if (informacaoContacto.getBairro() != null) infomacaoAtualizado.setBairro(informacaoContacto.getBairro());
        if (informacaoContacto.getProvincia() != null) infomacaoAtualizado.setProvincia(informacaoContacto.getProvincia());
        if (informacaoContacto.getMunicipio() != null) infomacaoAtualizado.setMunicipio(informacaoContacto.getMunicipio());
        if(informacaoContacto.getNumeroTelefone()!=null) infomacaoAtualizado.setNumeroTelefone(informacaoContacto.getNumeroTelefone());
        return informacaoContactoRepositorio.save(infomacaoAtualizado);
    }


    public void eliminar(Long id) {informacaoContactoRepositorio.deleteById(id);}


    public InformacaoContacto buscarPorUsuarioId(Optional<Usuario> usuario){
        return informacaoContactoRepositorio.findByUsuario(usuario);
    }
    public void eliminar(long id){
        informacaoContactoRepositorio.deleteById(id);
    }
}

