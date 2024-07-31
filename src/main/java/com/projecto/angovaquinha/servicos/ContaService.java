package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.InterfaceService.InterfaceServico;
import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.Conta;
import com.projecto.angovaquinha.repositorios.ContaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService  implements InterfaceServico<Conta,Long> {

    @Autowired
    private ContaRepositorio contaRepositorio;
    @Override
    public List<Conta> listarTodos(){
        return contaRepositorio.findAll();
    }
    @Override
    public Optional<Conta> buscarPorId(Long id){
        return contaRepositorio.findById(id);
    }

    @Override
    public Conta adicionar(Conta conta) throws ExcecaoP {
        if(conta.getIban() == null || conta.getIban().equals("") || conta.getIban().equals("") || conta.getNumeroConta() == 0 || conta.getNomeBanco().equals("") || conta.getUsuario() == null)
            throw new ExcecaoP("Conta inválida: alguns campos estão vazios ou nulos");
        return contaRepositorio.save(conta);
    }

    @Override
    public Conta editar(Long id, Conta entidade) throws ExcecaoP {
        return null;
    }

    @Override
    public void eliminar(Long id) {
        contaRepositorio.deleteById(id);
    }


    /*public Conta editarConta(Long id, Conta conta){
        return contaRepositorio.updateContaById(id, conta);
    }*/
}
