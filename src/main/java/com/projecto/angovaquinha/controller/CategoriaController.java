package com.projecto.angovaquinha.controller;

import com.projecto.angovaquinha.excecoes.ExcecaoP;
import com.projecto.angovaquinha.modelos.CategoriaVaquinha;
import com.projecto.angovaquinha.servicos.CategoriaVaquinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaVaquinhaService categoriaVaquinhaService;

    @GetMapping
    public List<CategoriaVaquinha> listarCategorias(){
        return categoriaVaquinhaService.listar();
    }

    @GetMapping("/{categoriaId}")
    public ResponseEntity<Object> buscarCategoriaPorId(@PathVariable Long categoriaId){
        try {
            CategoriaVaquinha categoriaVaquinha = categoriaVaquinhaService.buscarPeloId(categoriaId).orElseThrow(()-> new ExcecaoP("Categoria não encontrada para o Id:"+categoriaId));
            return ResponseEntity.ok(categoriaVaquinha);
        }catch (ExcecaoP e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
