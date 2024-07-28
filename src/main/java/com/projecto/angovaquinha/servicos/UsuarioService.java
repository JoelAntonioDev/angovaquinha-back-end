package com.projecto.angovaquinha.servicos;

import com.projecto.angovaquinha.modelos.Usuario;
import com.projecto.angovaquinha.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Autowired
    private HashingService hashingService;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario salvarUsuario(Usuario usuario) {
        usuario.setSenha(hashingService.gerarHash(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario buscarUsuarioPorNome(String nome){
        return usuarioRepository.findByNome(nome);
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
