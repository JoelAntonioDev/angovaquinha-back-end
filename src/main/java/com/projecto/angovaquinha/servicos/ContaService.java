package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.Conta;
import com.projecto.angovaquinha.repositorios.ContaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepositorio contaRepositorio;

    public List<Conta> listarContas(){
        return contaRepositorio.findAll();
    }
    public Conta buscarContaPorId(Long id){
        return contaRepositorio.findById(id).get();
    }
    public Conta adicionarConta(Conta conta) throws ExcecaoP {
        if(conta.getIban() == null || conta.getIban().equals("") || conta.getIban().equals("") || conta.getNumeroConta() == 0 || conta.getNomeBanco().equals("") || conta.getUsuario() == null)
            throw new ExcecaoP("Conta inválida: alguns campos estão vazios ou nulos");
        return contaRepositorio.save(conta);
    }
    /*public Conta editarConta(Long id, Conta conta){
        return contaRepositorio.updateContaById(id, conta);
    }*/
    public void eliminarConta(Long id){
        contaRepositorio.deleteById(id);
    }
}
