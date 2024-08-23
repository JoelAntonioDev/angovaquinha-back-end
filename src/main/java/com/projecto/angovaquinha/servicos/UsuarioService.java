package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.InterfaceService.InterfaceServico;
import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.InformacaoContacto;
import com.projecto.angovaquinha.modelos.Usuario;
import com.projecto.angovaquinha.repositorios.InformacaoContactoRepositorio;
import com.projecto.angovaquinha.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements InterfaceServico<Usuario, Long> {

    @Autowired
    private UsuarioRepositorio usuarioRepository;
    @Autowired
    private HashingService hashingService;
    @Autowired
    private InformacaoContactoService informacaoContactoService;


    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuario : usuarios) {
            usuario.setSenha("");
        }
        return usuarios;
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        usuario.ifPresent(usuario1 -> usuario1.setSenha(""));
        return usuario;
    }

    @Override
    public Usuario adicionar(Usuario usuario) {
        usuario.setSenha(hashingService.gerarHash(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario editar(Long id, Usuario usuario) throws ExcecaoP {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (!usuarioExistente.isPresent()) {
            throw new ExcecaoP("Usuário não encontrado");
        }
        Usuario usuarioAtualizado = usuarioExistente.get();
        if (usuario.getNome() != null) {
            usuarioAtualizado.setNome(usuario.getNome());
        }
        if (usuario.getEmail() != null) {
            usuarioAtualizado.setEmail(usuario.getEmail());
        }
        if (usuario.getSenha() != null) {
            usuarioAtualizado.setSenha(hashingService.gerarHash(usuario.getSenha()));
        }
        // Atualize outros campos conforme necessário
        return usuarioRepository.save(usuarioAtualizado);
    }

    @Override
    public void eliminar(Long id) {
        Optional<Usuario> u = buscarPorId(id);
        InformacaoContacto i = informacaoContactoService.buscarPorUsuarioId(u);
        if(i != null)
            informacaoContactoService.eliminar(i.getId());
        usuarioRepository.deleteById(id);
    }


    public void eliminarPeloEmail(String email){
        Optional<Usuario> u = Optional.ofNullable(buscarUsuarioPorEmail(email));
        InformacaoContacto i = informacaoContactoService.buscarPorUsuarioId(u);
        informacaoContactoService.eliminar(i.getId());
        usuarioRepository.deleteByEmail(email);
    }


    public Usuario buscarUsuarioPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        usuario.setSenha("");
        return usuario;
    }

    public Usuario buscarUsuarioPorNome(String nome){
        Usuario usuario = usuarioRepository.findByNome(nome);
        usuario.setSenha("");
        return usuario;
    }

    public boolean loginUsuario(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            System.out.println("Usuário não encontrado");
            return false;
        }
        boolean autenticado = hashingService.verificarHash(senha, usuario.getSenha());
        System.out.println("Senha correta: " + autenticado);
        return autenticado;
    }

    public Long tuplasExistentes(){
        return usuarioRepository.count();
    }
}
