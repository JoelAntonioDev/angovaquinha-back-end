package com.projecto.angovaquinha.controller;

import com.projecto.angovaquinha.modelos.Usuario;
import com.projecto.angovaquinha.servicos.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> formData) {
        boolean ehAutenticado = usuarioService.loginUsuario(formData.get("email"), formData.get("senha"));
        if (ehAutenticado) {
            //session.setAttribute("userEmail", formData.get("email"));
            Usuario usuario = usuarioService.buscarUsuarioPorEmail(formData.get("email"));
            usuario.setSenha("");
            return ResponseEntity.ok(Map.of("message", "Autenticado com sucesso", "data", usuario));
        }
        return ResponseEntity.status(401).body(Map.of("message", "Credenciais inválidas", "data", formData.toString()));
    }
    @PostMapping("/signUp")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody Map<String, String> formData) {
        Usuario usuario = new Usuario();
        usuario.setNome(formData.get("nome"));
        usuario.setEmail(formData.get("email"));
        usuario.setSenha(formData.get("senha"));
        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
        if (usuarioSalvo == null) {
            return ResponseEntity.ok(Map.of("message","Usuário inserido com sucesso", "data", formData.toString()));
        }
        return ResponseEntity.ok(Map.of("message","Usuário inserido com sucesso", "data", usuarioSalvo.toString()));
    }
}
