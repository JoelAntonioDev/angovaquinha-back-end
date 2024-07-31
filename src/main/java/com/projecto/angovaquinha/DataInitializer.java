package com.projecto.angovaquinha;


import com.projecto.angovaquinha.modelos.*;
import com.projecto.angovaquinha.repositorios.EstadoVaquinhaRepositorio;
import com.projecto.angovaquinha.repositorios.InformacaoContactoRepositorio;
import com.projecto.angovaquinha.repositorios.NivelAcessoRepositorio;
import com.projecto.angovaquinha.repositorios.UsuarioRepositorio;
import com.projecto.angovaquinha.servicos.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Date;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(EstadoVaquinhaRepositorio estadoVaquinhaRepositorio, NivelAcessoRepositorio nivelAcessoRepositorio, UsuarioService usuarioService, InformacaoContactoRepositorio informacaoContactoRepositorio) {
        return args -> {
            //DADOS SOBRE O ESTADO DA VAQUINHA
            if (estadoVaquinhaRepositorio.count() == 0) {
                estadoVaquinhaRepositorio.save(new EstadoVaquinha(null, "ACTIVADO"));
                estadoVaquinhaRepositorio.save(new EstadoVaquinha(null, "DESACTIVADO"));
            }
            NivelAcesso nivelAcesso = new NivelAcesso(null, NivelAcessoEnum.ADMINISTRADOR);
            //DADOS SOBRE O NÍVEL DE ACESSO
            if(nivelAcessoRepositorio.count() == 0){
                nivelAcessoRepositorio.save(nivelAcesso);
                nivelAcessoRepositorio.save(new NivelAcesso(null, NivelAcessoEnum.SOLICITADOR));
                nivelAcessoRepositorio.save(new NivelAcesso(null, NivelAcessoEnum.CONTRIBUINTE));
                nivelAcessoRepositorio.save(new NivelAcesso(null, NivelAcessoEnum.USUARIO_NORMAL));
            }
            InformacaoContacto informacaoContacto =new InformacaoContacto(null, "000000000000000","935816801","Luanda","Luanda","Ilha de Luanda");
            if(informacaoContactoRepositorio.count() == 0){
                informacaoContactoRepositorio.save(informacaoContacto);
            }
            //CONTA DE USUÁRIO DO ADMINISTRAÇÃO
            if(usuarioService.tuplasExistentes() == 0){
                Usuario userAdmin = new Usuario(null,"Joel António","joellucas2020eu@gmail.com","1234", new Date() ,nivelAcesso,informacaoContacto);
                usuarioService.adicionar(userAdmin);
            }
        };
    }
}

