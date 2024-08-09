package com.projecto.angovaquinha.controller;


import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.InformacaoContacto;
import com.projecto.angovaquinha.modelos.NivelAcesso;
import com.projecto.angovaquinha.enums.NivelAcessoEnum;
import com.projecto.angovaquinha.modelos.Usuario;
import com.projecto.angovaquinha.servicos.InformacaoContactoService;
import com.projecto.angovaquinha.servicos.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        usuario.setDataCriacao(new Date());
        NivelAcesso n = new NivelAcesso(4L,NivelAcessoEnum.USUARIO_NORMAL);
        usuario.setNivelAcesso(n);
        Usuario usuarioSalvo = usuarioService.adicionar(usuario);
        if (usuarioSalvo == null) {
            return ResponseEntity.ok(Map.of("message","Usuário inserido com sucesso", "data", formData.toString()));
        }
        return ResponseEntity.ok(Map.of("message","Usuário inserido com sucesso", "data", usuarioSalvo.toString()));
    }
    @PostMapping("/deleteUser")
    public ResponseEntity<Map<String, String>> eliminarUsuario(@RequestBody Map<String, String> formData) {
        String id = formData.get("id");
        //String bilheteIdentidade = formData.get("bilheteIdentidade");
        usuarioService.eliminar(Long.parseLong(id));
        return ResponseEntity.ok(Map.of("message","Usuário eliminado com sucesso", "data", formData.toString()));
        //return ResponseEntity.ok(Map.of("message","Usuário não eliminado", "data", formData.toString()));
    }
    @PostMapping("/save-contact-info")
    public ResponseEntity<Map<String, String>> saveContactInfo(@RequestBody Map<String, String> formData){
        String email = formData.get("email");
        String bi = formData.get("bilheteIdentidade");
        String numeroTelefone = formData.get("numeroTelefone");
        String provincia = formData.get("provincia");
        String municipio = formData.get("municipio");
        String bairro = formData.get("bairro");
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        InformacaoContacto i = new InformacaoContacto(null,bi,numeroTelefone,provincia,municipio,bairro,usuario);
        try {
            informacaoContactoService.adicionar(i);
        } catch (ExcecaoP e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(Map.of("message","Informacao do Contacto inserido com sucesso", "data", i.toString()));
    }
    @PostMapping("/delete-contact")
    public ResponseEntity<Map<String, String>> deleteContact(@RequestBody Map<String, String> formData) {
        String id = formData.get("id");
        informacaoContactoService.eliminar(Integer.parseInt(id));
        return ResponseEntity.ok(Map.of("message","Contacto eliminado com sucesso", "data", formData.toString()));
    }
    @PostMapping("/info-contact")
    public ResponseEntity<Map<String, Object>> viewContact(@RequestBody Map<String, String> formData){
        String id = formData.get("id");
        Usuario u = new Usuario();
        u.setId(Long.parseLong(id));
        InformacaoContacto i = informacaoContactoService.buscarPorUsuarioId(Optional.of(u));

        if(i!=null) {
            i.getUsuario().setSenha("");
            return ResponseEntity.ok(Map.of("message", "Informação encontrada", "data", i));
        }
        return ResponseEntity.ok(Map.of("message","Informação não encontrada"));
    }
}
