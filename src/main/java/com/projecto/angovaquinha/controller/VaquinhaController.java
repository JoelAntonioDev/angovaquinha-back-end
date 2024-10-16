package com.projecto.angovaquinha.controller;

import com.projecto.angovaquinha.configuracao.FilePathUtil;
import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.EstadoVaquinha;
import com.projecto.angovaquinha.modelos.SubcategoriaVaquinha;
import com.projecto.angovaquinha.modelos.Usuario;
import com.projecto.angovaquinha.modelos.Vaquinha;
import com.projecto.angovaquinha.servicos.EstadoVaquinhaService;
import com.projecto.angovaquinha.servicos.SubcategoriaVaquinhaService;
import com.projecto.angovaquinha.servicos.UsuarioService;
import com.projecto.angovaquinha.servicos.VaquinhaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/vaquinhas")
public class VaquinhaController {
    @Autowired
    private VaquinhaService vaquinhaService;
    @Autowired
    private SubcategoriaVaquinhaService subcategoriaVaquinhaService;
    @Autowired
    private EstadoVaquinhaService estadoVaquinhaService;
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Adicionar uma vaquinha",
            description = "Retorna a vaquinha adicionada, recebe as informações de vaquinha"
    )
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @ApiResponse(responseCode = "500", description = "Falha ao salvar arquivo")
    public ResponseEntity<Map<String, String>> addVaquinha(
            @RequestParam("email") String email,
            @RequestParam("objectivo") Integer objectivo,
            @RequestParam("subcategoria") Long subcategoria,
            @RequestParam("categoria") Long categoria,
            @RequestParam("descricao") String descricao,
            @RequestParam("titulo") String titulo,
            @RequestPart("file") MultipartFile file) {

        // Obtém o caminho absoluto para o diretório relativo
        String absolutePath = FilePathUtil.getProjectRoot()+"\\src\\main\\java\\com\\projecto\\angovaquinha\\recursos\\imagens\\";
        String relativePath = FilePathUtil.getRelativePath(absolutePath);
        // Obtém o caminho absoluto para o diretório relativo
        File uploadDirFile = new File(absolutePath);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }
        // Cria o arquivo de destino
        String fileName = file.getOriginalFilename();
        File dest = new File(absolutePath, fileName);

        //vaquinha por padrão está com o estado desactivado
        Optional<EstadoVaquinha> e = estadoVaquinhaService.buscarPorId(2L);
        Usuario u = usuarioService.buscarUsuarioPorEmail(email);
        Optional<SubcategoriaVaquinha> s = subcategoriaVaquinhaService.buscarPeloId(subcategoria);
        Vaquinha v = new Vaquinha(null,titulo, descricao, new Date(), objectivo,relativePath+"\\"+fileName, e.orElse(null),u,s.get());
        try {
            vaquinhaService.adicionarVaquinha(v);
        } catch (ExcecaoP ex) {
            throw new RuntimeException(ex);
        }
        try {
            file.transferTo(dest);
        } catch (IOException ex) {
            return ResponseEntity.status(500).body(Map.of("mensagem", "Falha ao salvar o arquivo"));
        }
        // Cria uma resposta para enviar ao cliente
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Arquivo salvo com sucesso!");
        response.put("fileName", fileName);
        response.put("filePath", dest.getAbsolutePath());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getVaquinhas() {
        List<Vaquinha> vaquinhas = vaquinhaService.listarVaquinhas();
        if (vaquinhas.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("message", "Não foram encontradas vaquinhas", "data", ""));
        }

        // Adicionar detalhes de categoria, subcategoria e estado às vaquinhas
        List<Map<String, Object>> vaquinhasWithDetails = vaquinhas.stream().map(vaquinha -> {
            Map<String, Object> vaquinhaData = new HashMap<>();
            vaquinhaData.put("id", vaquinha.getId());
            vaquinhaData.put("titulo", vaquinha.getTitulo());
            vaquinhaData.put("descricao", vaquinha.getDescricao());
            vaquinhaData.put("quantia", vaquinha.getQuantia());
            //vaquinhaData.put("categoria", vaquinha.getCategoriaVaquia());
            vaquinhaData.put("subcategoria", vaquinha.getSubcategoriaVaquinha());
            vaquinhaData.put("estado", vaquinha.getEstadoVaquinha());
            return vaquinhaData;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("message", "Vaquinhas encontradas", "data", vaquinhasWithDetails));
    }

    @GetMapping("/{vaquinhaId}")
    public ResponseEntity<Map<String, Object>> buscarVaquinha(@PathVariable Long vaquinhaId){
        try {
            Vaquinha v = vaquinhaService.buscarVaquinhaById(vaquinhaId).orElseThrow(() -> new ExcecaoP("Vaquinha não encontrada, Id:" + vaquinhaId));
            return ResponseEntity.ok(Map.of("message", "Existem vaquinhas", "data", v));
        }catch (ExcecaoP e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping("/{titulo}")
    public ResponseEntity<Map<String, Object>> getVaquinha(@PathVariable("titulo") String titulo){
        Vaquinha v = vaquinhaService.buscarVaquinhaPeloTitulo(titulo);
        if(v == null){
            return ResponseEntity.status(404).body(Map.of("message","Vaquinha não encontrada","data",""));
        }
        return ResponseEntity.ok(Map.of("message","Vaquinha encontrada com sucesso", "data",v));
    }
    /*
    @GetMapping("/get-categorias")
    public ResponseEntity<Map<String, Object>> getCategoriasSubcategorias() {
        List<SubcategoriaVaquinha> subcategorias = subcategoriaVaquinhaService.listar();
        if (subcategorias.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "Categorias e Subcategorias não encontradas"));
        }
        return ResponseEntity.ok(Map.of("message", "Categorias e Subcategorias encontradas com sucesso", "data", subcategorias));
    }*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVaquinha(@PathVariable Long id) {
        try {
            vaquinhaService.eliminarVaquinha(id);
            return ResponseEntity.ok(Map.of("message", "Vaquinha removida com sucesso"));
        } catch (ExcecaoP e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> editarVaquinha(@PathVariable Long id, @RequestBody Vaquinha novaInfo) {
        try {
            vaquinhaService.editar(id, novaInfo);
            return ResponseEntity.ok(Map.of("message", "Vaquinha  editada com sucesso"));
        } catch (ExcecaoP e) {
            return ResponseEntity.status(400).body(Map.of("message", "Erro ao editar Vaquinha: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Erro interno: " + e.getMessage()));
        }
    }

    @PatchMapping("/toggle-vaquinha-status/{id}")
    public ResponseEntity<Vaquinha> alternarEstado(@PathVariable Long id) {
        try {
            Vaquinha vaquinha = vaquinhaService.alternarEstado(id);
            return ResponseEntity.ok(vaquinha);
        } catch (ExcecaoP e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/get-qtd-vaquinhas")
    public ResponseEntity<Map<String, Object>> getTotalVaquinhas(){
        Long qtdVaquinhas = vaquinhaService.tuplasExistentes();
        if(qtdVaquinhas!=0)
            return ResponseEntity.ok(Map.of("message","Quantidade de vaquinhas retornada com sucesso!", "data",qtdVaquinhas));
        return ResponseEntity.ok(Map.of("message","Quantidade de vaquinhas retornada com sucesso!", "data",0));
    }


}
