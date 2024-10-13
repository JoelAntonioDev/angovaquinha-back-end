package com.projecto.angovaquinha.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.InformacaoContacto;
import com.projecto.angovaquinha.modelos.NivelAcesso;
import com.projecto.angovaquinha.enums.NivelAcessoEnum;
import com.projecto.angovaquinha.modelos.Usuario;
import com.projecto.angovaquinha.repositorios.UsuarioRepositorio;
import com.projecto.angovaquinha.servicos.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(Map.of("message", "Usuários encontrados", "data", usuarios));
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> buscarUsuario(@PathVariable Long usuarioId) {
        try {
            Usuario usuario = usuarioService.buscarPorId(usuarioId).orElseThrow(() -> new ExcecaoP("Usuario com o Id:" + usuarioId + " não encontrado!"));
            return ResponseEntity.ok(Map.of("message", "Usuários encontrados", "data", usuario));
        }catch (ExcecaoP e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get-qtd-usuarios")
    public ResponseEntity<Map<String, Object>> getTotalUsuarios(){
        Long qtdUsuarios = usuarioService.tuplasExistentes();
        if(qtdUsuarios!=0)
            return ResponseEntity.ok(Map.of("message","Quantidade de usuários retornada com sucesso!", "data",qtdUsuarios));
        return ResponseEntity.ok(Map.of("message","Quantidade de usuários retornada com sucesso!", "data",0));

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

    @PostMapping()
    public ResponseEntity<Map<String, ?>> adicionarUsuario(@RequestBody Map<String, String> formData) {
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
        return ResponseEntity.ok(Map.of("message", "Usuário inserido com sucesso", "data", usuarioSalvo));
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long usuarioId) {
        try {
            usuarioService.eliminar(usuarioId);
            return ResponseEntity.ok(Map.of("message", "Usuário removido com sucesso"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/save-contact-info")
    public ResponseEntity<Map<String, ?>> adicionarInformacaoContacto(@RequestBody Map<String, String> formData) {
        String email = formData.get("email");
        String bi = formData.get("bilheteIdentidade");
        String numeroTelefone = formData.get("numeroTelefone");
        String provincia = formData.get("provincia");
        String municipio = formData.get("municipio");
        String bairro = formData.get("bairro");
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        InformacaoContacto i = new InformacaoContacto();
        System.out.println(usuario);
        i.setBilheteIdentidade(bi);
        i.setNumeroTelefone(numeroTelefone);
        i.setProvincia(provincia);
        i.setMunicipio(municipio);
        i.setBairro(bairro);
        usuario.setInformacaoContacto(i);
            Usuario usuarioSalvo = usuarioService.adicionar(usuario);
        return ResponseEntity
                .ok(Map.of("message", "Informacao adicionado com sucesso", "data", usuarioSalvo ));
    }

    @PutMapping("/edit-contact-info")
    public ResponseEntity<Map<String, String>> editContactInfo(@RequestBody Map<String,Object> formData) {
        try {
        String email = String.valueOf(formData.get("email"));
        formData.remove("email");
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        merge(formData, usuario.getInformacaoContacto());
        atualizarInformacaoId(usuario.getId(), usuario.getInformacaoContacto());

            usuarioService.adicionar(usuario);
            return ResponseEntity.ok(Map.of("message", "Informação de contato editada com sucesso"));
        } catch (Exception e) {
            return ResponseEntity.status(400)
                    .body(Map.of("message", "Erro ao editar informação de contato: " + e.getMessage()));
        }
    }

    private void merge(Map<String, Object> camposOrigem, InformacaoContacto informacaoContactoDestino){
        ObjectMapper objectMapper = new ObjectMapper();
        InformacaoContacto informacaoContactoOrigem = objectMapper.convertValue(camposOrigem, InformacaoContacto.class);

        System.out.println(informacaoContactoOrigem);

        camposOrigem.forEach((nomePropriedade, valorPropriedade)->{
            Field field = ReflectionUtils.findField(InformacaoContacto.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, informacaoContactoOrigem);

            System.out.println(nomePropriedade+"="+valorPropriedade+" =  "+novoValor);
            ReflectionUtils.setField(field,informacaoContactoDestino,novoValor);
        });
    }

    public ResponseEntity<?> atualizarInformacaoId(@PathVariable("usuarioId") Long usuarioId, @RequestBody InformacaoContacto informacaoContacto){
        try {
            Usuario usuario = usuarioRepositorio.findById(usuarioId)
                    .orElseThrow(()-> new ExcecaoP("Não existe restaurante com Id:"+usuarioId));
            BeanUtils.copyProperties(informacaoContacto, usuario.getInformacaoContacto());
            usuarioService.adicionar(usuario);
            return ResponseEntity.ok(usuario);
        } catch (ExcecaoP e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Map<String, String>> editarUsuario(@PathVariable Long usuarioId, @RequestBody Usuario novaInfo) {
        try {
            usuarioService.editar(usuarioId, novaInfo);
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
