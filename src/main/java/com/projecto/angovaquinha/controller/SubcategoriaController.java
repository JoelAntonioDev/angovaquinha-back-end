package com.projecto.angovaquinha.controller;

import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.SubcategoriaVaquinha;
import com.projecto.angovaquinha.servicos.SubcategoriaVaquinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subcategorias")
public class SubcategoriaController {

    @Autowired
    private SubcategoriaVaquinhaService subcategoriaVaquinhaService;

    @GetMapping
    public List<SubcategoriaVaquinha> listarSubategorias(){
        return subcategoriaVaquinhaService.listar();
    }

    @GetMapping("/{subcategoriaId}")
    public ResponseEntity<Object> buscarCategoriaPorId(@PathVariable Long subcategoriaId){
        try {
            SubcategoriaVaquinha subcategoriaVaquinha = subcategoriaVaquinhaService.buscarPeloId(subcategoriaId).orElseThrow(()-> new ExcecaoP("Subcategoria não encontrada para o Id:"+subcategoriaId));
            return ResponseEntity.ok(subcategoriaVaquinha);
        }catch (ExcecaoP e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
