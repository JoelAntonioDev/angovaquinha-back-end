package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.EstadoVaquinha;
import com.projecto.angovaquinha.modelos.Vaquinha;
import com.projecto.angovaquinha.repositorios.EstadoVaquinhaRepositorio;
import com.projecto.angovaquinha.repositorios.VaquinhaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaquinhaService {

    @Autowired
    private VaquinhaRepositorio vaquinhaRepositorio;
    @Autowired
    private EstadoVaquinhaRepositorio estadoVaquinhaRepositorio;
    public List<Vaquinha> listarVaquinhas(){
        return vaquinhaRepositorio.findAll();
    }

    public Vaquinha buscarVaquinhaById(Long id){
        return vaquinhaRepositorio.findById(id).get();
    }

    public Vaquinha adicionarVaquinha(Vaquinha vaquinha) throws ExcecaoP {
        if(vaquinha.getUsuario() == null || vaquinha.getDescricao().equals("") || vaquinha.getTitulo().equals("") || vaquinha.getDataCriacao().equals("") || vaquinha.getEstadoVaquinha() == null )
            throw new ExcecaoP("Vaquinha inválida: alguns campos estão vazios ou nulos");
        return vaquinhaRepositorio.save(vaquinha);
    }

    /*public Vaquinha editarVaquinha(Long id, Vaquinha vaquinha) throws ExcecaoP {
        return vaquinhaRepositorio.updateVaquinhaById(id, vaquinha);
    }*/

    public void eliminarVaquinha(Long id){
        vaquinhaRepositorio.deleteById(id);
    }
    public Vaquinha buscarVaquinhaPeloTitulo(String titulo){
        return vaquinhaRepositorio.findByTitulo(titulo);
    }

    public Vaquinha editar(Long id, Vaquinha vaquinha) throws ExcecaoP {
        Optional<Vaquinha> vaquinhaExistente = vaquinhaRepositorio.findById(id);
        if (!vaquinhaExistente.isPresent()) {
            throw new ExcecaoP("Vaquinha não encontrada");
        }
        Vaquinha vaquinhaAtualizado = vaquinhaExistente.get();
        if (vaquinha.getTitulo() != null) {
            vaquinhaAtualizado.setTitulo(vaquinha.getTitulo());
        }
        if (vaquinha.getDescricao() != null) {
            vaquinhaAtualizado.setDescricao(vaquinha.getDescricao());
        }
        if (vaquinha.getQuantia() != 0) {
            vaquinhaAtualizado.setQuantia(vaquinha.getQuantia());
        }
        // Atualize outros campos conforme necessário
        return vaquinhaRepositorio.save(vaquinhaAtualizado);
    }
    public Vaquinha alternarEstado(Long vaquinhaId) throws ExcecaoP {
        Vaquinha vaquinha = vaquinhaRepositorio.findById(vaquinhaId)
                .orElseThrow(() -> new ExcecaoP("Vaquinha não encontrada"));

        EstadoVaquinha estadoAtual = vaquinha.getEstadoVaquinha();
        EstadoVaquinha novoEstado;

        if ("ACTIVADO".equals(estadoAtual.getEstado())) {
            novoEstado = estadoVaquinhaRepositorio.findByEstado("DESACTIVADO")
                    .orElseThrow(() -> new ExcecaoP("Estado DESACTIVADO não encontrado"));
        } else {
            novoEstado = estadoVaquinhaRepositorio.findByEstado("ACTIVADO")
                    .orElseThrow(() -> new ExcecaoP("Estado ACTIVADO não encontrado"));
        }

        vaquinha.setEstadoVaquinha(novoEstado);
        return vaquinhaRepositorio.save(vaquinha);
    }
    public Long tuplasExistentes(){
        return vaquinhaRepositorio.count();
    }
}
