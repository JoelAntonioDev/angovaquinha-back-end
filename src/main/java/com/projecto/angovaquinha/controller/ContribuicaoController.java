package com.projecto.angovaquinha.controller;

import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.Contribuicao;
import com.projecto.angovaquinha.modelos.Usuario;
import com.projecto.angovaquinha.modelos.Vaquinha;
import com.projecto.angovaquinha.servicos.ContribuicaoService;
import com.projecto.angovaquinha.servicos.UsuarioService;
import com.projecto.angovaquinha.servicos.VaquinhaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ContribuicaoController {
    private final ContribuicaoService contribuicaoService;
    private final UsuarioService usuarioService;
    private final VaquinhaService vaquinhaService;
    public ContribuicaoController(ContribuicaoService contribuicaoService, UsuarioService usuarioService, VaquinhaService vaquinhaService) {
        this.contribuicaoService = contribuicaoService;
        this.usuarioService = usuarioService;
        this.vaquinhaService = vaquinhaService;
    }

    @PostMapping("/add-contribuicao")
    public ResponseEntity<Map<String, String>> addContribuicao(@RequestBody Map<String, String> formData, HttpSession session) {
        String valorMonetario = formData.get("valorMonetario");
        String moeda = formData.get("moeda");
        Long id = Long.valueOf(formData.get("id"));
        Date dataCriacao = new Date();
        //Recebendo email do front, mas a inserção é feita com o id do user
        String email = session.getAttribute("userEmail").toString();
        Usuario u = usuarioService.buscarUsuarioPorEmail(email);
        Vaquinha v = vaquinhaService.buscarVaquinhaById(id);
        if (!valorMonetario.isEmpty() && !moeda.isEmpty() && u.getId() != 0L ) {
            try {
                contribuicaoService.adicionar(new Contribuicao(null,Double.valueOf(valorMonetario),moeda,dataCriacao,u, v));
            } catch (ExcecaoP e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.ok(Map.of("message","Contribuição efectuada com sucesso!","data",formData.toString()));
        }
        return ResponseEntity.ok(Map.of("message","Contribuição não efectuada!","data",formData.toString()));
    }
    @PostMapping("/get-contribuintes")
    public ResponseEntity<Map<String, Object>> getContribuintes(@RequestBody Map<String, String> formData){
        Long id = Long.valueOf(formData.get("id"));
        Vaquinha v = vaquinhaService.buscarVaquinhaById(id);
        List<Contribuicao> listaContribuintes = contribuicaoService.listarContribuicoesPorVaquinha(v);
        if(listaContribuintes.isEmpty()){
            return ResponseEntity.ok(Map.of("message","Não há doações nesta vaquinha","data",formData.toString()));
        }
        return ResponseEntity.ok(Map.of("message","Doações encontradas com sucesso","data",listaContribuintes));
    }
}
