package com.projecto.angovaquinha.controller;

import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.Contribuicao;
import com.projecto.angovaquinha.modelos.Usuario;
import com.projecto.angovaquinha.modelos.Vaquinha;
import com.projecto.angovaquinha.servicos.ContribuicaoService;
import com.projecto.angovaquinha.servicos.UsuarioService;
import com.projecto.angovaquinha.servicos.VaquinhaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.*;

@RestController
@RequestMapping("/contribuicoes")
public class ContribuicaoController {
    @Autowired
    private ContribuicaoService contribuicaoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VaquinhaService vaquinhaService;

    @GetMapping
    public ResponseEntity<List<Contribuicao>> getContribuicoes(){
        return ResponseEntity.ok(contribuicaoService.listarTodos());
    }

    @GetMapping("/{contribuicaoId}")
    public ResponseEntity<?> buscarContribuicao(@PathVariable("contribuicaoId") Long contribuicaoId){
        try {
            Contribuicao contribuicao = contribuicaoService.buscarPorId(contribuicaoId).orElseThrow(() -> new ExcecaoP("Contribuição inexistente"));
            return ResponseEntity.ok(contribuicao);
        }catch (ExcecaoP p){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(p.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<Map<String, ?>> addContribuicao(@RequestBody Map<String, String> formData, HttpSession session) {
        String valorMonetario = formData.get("valorMonetario");
        String moeda = formData.get("moeda");
        Long id = Long.valueOf(formData.get("idVaquinha"));
        Date dataCriacao = new Date();
        //Recebendo email do front, mas a inserção é feita com o id do user
        session.setAttribute("userEmail","joellucas2020eu@gmail.com");
        String email = session.getAttribute("userEmail").toString();
        Usuario u = usuarioService.buscarUsuarioPorEmail(email);
        if (!valorMonetario.isEmpty() && !moeda.isEmpty() && u.getId() != 0L) {
            try {
                Vaquinha v = vaquinhaService.buscarVaquinhaById(id).orElseThrow(()-> new ExcecaoP("Vaquinha não encontrada para o Id:"+id));
                Contribuicao contribSalva = contribuicaoService.adicionar(new Contribuicao(null, Double.valueOf(valorMonetario), moeda, dataCriacao, u, v));
                return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Contribuição efectuada com sucesso!", "data", contribSalva));
            } catch (ExcecaoP e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        return ResponseEntity.ok(Map.of("message", "Contribuição não efectuada!", "data", formData.toString()));
    }

    @GetMapping("/get-contribuintes/{vaquinhaId}")
    public ResponseEntity<Map<String, Object>> getContribuicoesPorVaquinha(@PathVariable("vaquinhaId") Long vaquinhaId) {
        Long id = vaquinhaId;
        try {
            Vaquinha vaquinha = vaquinhaService.buscarVaquinhaById(id).orElseThrow(()->new ExcecaoP("Vaquinha Não Encontrada para este ID:"+vaquinhaId));
            List<Contribuicao> listaContribuintes = contribuicaoService.listarContribuicoesPorVaquinha(vaquinha);
            List<Map<String, Object>> valorRetorno = new ArrayList<>();
            if (listaContribuintes.isEmpty()) {
                new ExcecaoP("Não há doações nesta vaquinha:"+vaquinhaId);
            }
            listaContribuintes.forEach((valor) -> {
                Map<String, Object> contribuinte = new HashMap<>();
                contribuinte.put("valorMonetario", valor.getValorMonetario());
                contribuinte.put("moeda", valor.getMoeda());
                contribuinte.put("usuario", Map.of("nome", valor.getUsuario().getNome(), "email", valor.getUsuario().getEmail()));
                valorRetorno.add(contribuinte);
            });
            return ResponseEntity.ok(Map.of("message", "Doações encontradas com sucesso", "data", valorRetorno));
        }catch (ExcecaoP e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }
}
