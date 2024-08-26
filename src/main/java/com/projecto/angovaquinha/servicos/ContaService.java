package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.Conta;
import com.projecto.angovaquinha.repositorios.ContaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepositorio contaRepositorio;

    public List<Conta> listarTodos(){
        return contaRepositorio.findAll();
    }

    public Optional<Conta> buscarPorId(Long id){
        return contaRepositorio.findById(id);
    }


    public Conta adicionar(Conta conta) throws ExcecaoP {
        if(conta.getIban() == null || conta.getIban().equals("") || conta.getIban().equals("") || conta.getNumeroConta() == 0 || conta.getNomeBanco().equals("") )
            throw new ExcecaoP("Conta inválida: alguns campos estão vazios ou nulos");
        return contaRepositorio.save(conta);
    }


    public Conta editar(Long id, Conta entidade) throws ExcecaoP {
        return null;
    }


    public void eliminar(Long id) {
        contaRepositorio.deleteById(id);
    }


    /*public Conta editarConta(Long id, Conta conta){
        return contaRepositorio.updateContaById(id, conta);
    }*/
}
