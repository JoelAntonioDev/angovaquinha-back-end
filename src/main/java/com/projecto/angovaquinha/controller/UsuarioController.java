package com.projecto.angovaquinha.controller;

import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.InformacaoContacto;
import com.projecto.angovaquinha.modelos.NivelAcesso;
import com.projecto.angovaquinha.enums.NivelAcessoEnum;
import com.projecto.angovaquinha.modelos.Usuario;
import com.projecto.angovaquinha.servicos.InformacaoContactoService;
import com.projecto.angovaquinha.servicos.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final InformacaoContactoService informacaoContactoService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, InformacaoContactoService informacaoContactoService) {
        this.usuarioService = usuarioService;
        this.informacaoContactoService = informacaoContactoService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> formData, HttpSession session) {
        boolean ehAutenticado = usuarioService.loginUsuario(formData.get("email"), formData.get("senha"));
        if (ehAutenticado) {
            session.setAttribute("userEmail", formData.get("email"));
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
        usuario.setDataCriacao(new Date());
        NivelAcesso n = new NivelAcesso(4L, NivelAcessoEnum.USUARIO_NORMAL);
        usuario.setNivelAcesso(n);
        Usuario usuarioSalvo = usuarioService.adicionar(usuario);
        if (usuarioSalvo == null) {
            return ResponseEntity.ok(Map.of("message", "Usuário inserido com sucesso", "data", formData.toString()));
        }
        return ResponseEntity.ok(Map.of("message", "Usuário inserido com sucesso", "data", usuarioSalvo.toString()));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            usuarioService.eliminar(id);
            return ResponseEntity.ok(Map.of("message", "Usuário removido com sucesso"));
        } catch (Exception e) {
            System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" + id);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao remover o usuário"));
        }
    }

    @PostMapping("/save-contact-info")
    public ResponseEntity<Map<String, String>> saveContactInfo(@RequestBody Map<String, String> formData) {
        String email = formData.get("email");
        String bi = formData.get("bilheteIdentidade");
        String numeroTelefone = formData.get("numeroTelefone");
        String provincia = formData.get("provincia");
        String municipio = formData.get("municipio");
        String bairro = formData.get("bairro");
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        InformacaoContacto i = new InformacaoContacto(null, bi, numeroTelefone, provincia, municipio, bairro, usuario);
        try {
            informacaoContactoService.adicionar(i);
        } catch (ExcecaoP e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity
                .ok(Map.of("message", "Informacao do Contacto inserido com sucesso", "data", i.toString()));
    }

    @PostMapping("/info-contact")
    public ResponseEntity<Map<String, Object>> viewContact(@RequestBody Map<String, String> formData) {
        String id = formData.get("id");
        Usuario u = new Usuario();
        u.setId(Long.parseLong(id));
        InformacaoContacto i = informacaoContactoService.buscarPorUsuarioId(Optional.of(u));

        if (i != null) {
            i.getUsuario().setSenha("");
            return ResponseEntity.ok(Map.of("message", "Informação encontrada", "data", i));
        }
        return ResponseEntity.ok(Map.of("message", "Informação não encontrada"));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(Map.of("message", "Usuários encontrados", "data", usuarios));
    }

    @GetMapping("/get-qtd-usuarios")
    public ResponseEntity<Map<String, Object>> getTotalUsuarios(){
        Long qtdUsuarios = usuarioService.tuplasExistentes();
        if(qtdUsuarios!=0)
            return ResponseEntity.ok(Map.of("message","Quantidade de usuários retornada com sucesso!", "data",qtdUsuarios));
        return ResponseEntity.ok(Map.of("message","Quantidade de usuários retornada com sucesso!", "data",0));

    }

    @PutMapping("/edit-contact-info/{id}")
    public ResponseEntity<Map<String, String>> editContactInfo(@PathVariable Long id,
                                                               @RequestBody InformacaoContacto novaInfo) {
        try {
            informacaoContactoService.editar(id, novaInfo);
            return ResponseEntity.ok(Map.of("message", "Informação de contato editada com sucesso"));
        } catch (ExcecaoP e) {
            return ResponseEntity.status(400)
                    .body(Map.of("message", "Erro ao editar informação de contato: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Erro interno: " + e.getMessage()));
        }
    }

    @DeleteMapping("/deleteInformacao/{id}")
    public ResponseEntity<?> deleteInformacao(@PathVariable Long id) {
        try {
            informacaoContactoService.eliminar(id);
            return ResponseEntity.ok(Map.of("message", "Usuário removido com sucesso"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erro ao remover o usuário"));
        }
    }

    @PutMapping("/edit-user/{id}")
    public ResponseEntity<Map<String, String>> editarUsuario(@PathVariable Long id, @RequestBody Usuario novaInfo) {
        try {
            usuarioService.editar(id, novaInfo);
            return ResponseEntity.ok(Map.of("message", "Usuário  editado com sucesso"));
        } catch (ExcecaoP e) {
            return ResponseEntity.status(400).body(Map.of("message", "Erro ao editar Usuário: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Erro interno: " + e.getMessage()));
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpSession session) {
        if (session != null) {
            session.removeAttribute("user"); // Invalidate session before committing the response
        }
        return ResponseEntity.ok(Map.of("message", "Sessão terminada com sucesso", "data", ""));
    }

}
