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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import static com.projecto.angovaquinha.configuracao.FilePathUtil.getProjectRoot;

@RestController
@RequestMapping("/api")
public class VaquinhaController {
    private final VaquinhaService vaquinhaService;
    private final SubcategoriaVaquinhaService subcategoriaVaquinhaService;
    private final EstadoVaquinhaService estadoVaquinhaService;
    private final UsuarioService usuarioService;

    public VaquinhaController(VaquinhaService vaquinhaService, SubcategoriaVaquinhaService subcategoriaVaquinhaService, EstadoVaquinhaService estadoVaquinhaService, UsuarioService usuarioService) {
        this.vaquinhaService = vaquinhaService;
        this.subcategoriaVaquinhaService = subcategoriaVaquinhaService;
        this.estadoVaquinhaService = estadoVaquinhaService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/add-vaquinha")
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


        Optional<EstadoVaquinha> e = estadoVaquinhaService.buscarPorId(2L);
        Usuario u = usuarioService.buscarUsuarioPorEmail(email);
        SubcategoriaVaquinha s = subcategoriaVaquinhaService.buscarPeloId(subcategoria);
        Vaquinha v = new Vaquinha(null,titulo, descricao, new Date(), objectivo,relativePath+"\\"+fileName, e.orElse(null),u,s);
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
    @GetMapping("/get-vaquinhas")
    public ResponseEntity<Map<String, Object>> getVaquinhas(){
        List<Vaquinha> v = vaquinhaService.listarVaquinhas();
        if(v.isEmpty()){
            return ResponseEntity.status(404).body(Map.of("message","Não foi encontrado nenhuma vaquinha","data",""));
        }
        return ResponseEntity.ok(Map.of("message","Existem vaquinhas", "data",v));
    }
    @PostMapping("/get-vaquinha")
    public ResponseEntity<Map<String, Object>> getVaquinha(@RequestParam("titulo") String titulo){
        Vaquinha v = vaquinhaService.buscarVaquinhaPeloTitulo(titulo);
        if(v == null){
            return ResponseEntity.status(404).body(Map.of("message","Vaquinha não encontrada","data",""));
        }
        return ResponseEntity.ok(Map.of("message","Vaquinha encontrada com sucesso", "data",v));
    }

    @GetMapping("/get-categorias")
    public ResponseEntity<Map<String, Object>> getCategoriasSubcategorias() {
        List<SubcategoriaVaquinha> subcategorias = subcategoriaVaquinhaService.listar();
        if (subcategorias.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "Categorias e Subcategorias não encontradas"));
        }
        return ResponseEntity.ok(Map.of("message", "Categorias e Subcategorias encontradas com sucesso", "data", subcategorias));
    }
}
