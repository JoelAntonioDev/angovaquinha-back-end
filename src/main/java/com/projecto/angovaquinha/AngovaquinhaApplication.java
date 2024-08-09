package com.projecto.angovaquinha;

import com.projecto.angovaquinha.controller.ContribuicaoController;
import com.projecto.angovaquinha.repositorios.UsuarioRepositorio;
import com.projecto.angovaquinha.servicos.ContribuicaoService;
import com.projecto.angovaquinha.servicos.HashingService;
import com.projecto.angovaquinha.servicos.UsuarioService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class AngovaquinhaApplication {

    public static void main(String[] args) {

        SpringApplication.run(AngovaquinhaApplication.class, args);
        
    }

}
