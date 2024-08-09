package com.projecto.angovaquinha;


import com.projecto.angovaquinha.enums.NivelAcessoEnum;
import com.projecto.angovaquinha.modelos.*;
import com.projecto.angovaquinha.repositorios.*;
import com.projecto.angovaquinha.servicos.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(EstadoVaquinhaRepositorio estadoVaquinhaRepositorio, NivelAcessoRepositorio nivelAcessoRepositorio, UsuarioService usuarioService, InformacaoContactoRepositorio informacaoContactoRepositorio, ContaRepositorio contaRepositorio, CategoriaVaquinhaRepositorio categoriaVaquinhaRepositorio, SubcategoriaVaquinhaRepositorio subcategoriaVaquinhaRepositorio) {
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
            Conta conta = new Conta(null,"1202.9182.0291.8292.8291.8","BBB",192819891);
            if(contaRepositorio.count() == 0){
                contaRepositorio.save(conta);
            }
            Usuario userAdmin = new Usuario(null,"Joel António","joellucas2020eu@gmail.com","1234", new Date() ,nivelAcesso, conta);
            //CONTA DE USUÁRIO DO ADMINISTRAÇÃO
            if(usuarioService.tuplasExistentes() == 0){
                usuarioService.adicionar(userAdmin);
            }
            InformacaoContacto informacaoContacto = new InformacaoContacto(null, "000000000000000","935816801","Luanda","Luanda","Ilha de Luanda", userAdmin);
            if(informacaoContactoRepositorio.count() == 0){
                informacaoContactoRepositorio.save(informacaoContacto);
            }
            List<SubcategoriaVaquinha> subcategorias = new ArrayList<>();

            // Lista para armazenar as categorias
            List<CategoriaVaquinha> categorias = new ArrayList<>();

            // Categoria: Saúde e Bem-Estar
            CategoriaVaquinha c1 = new CategoriaVaquinha(null, "Saúde e Bem-Estar", "Representa a saúde e qualidade de vida");
            categorias.add(c1);
            // Categoria: Educação
            CategoriaVaquinha c2 = new CategoriaVaquinha(null, "Educação", "Focado em iniciativas educacionais");
            categorias.add(c2);

            // Categoria: Ajuda Comunitária
            CategoriaVaquinha c3 = new CategoriaVaquinha(null, "Ajuda Comunitária", "Projetos que apoiam a comunidade");
            categorias.add(c3);

            // Categoria: Projetos Pessoais
            CategoriaVaquinha c4 = new CategoriaVaquinha(null, "Projetos Pessoais", "Campanhas focadas em projetos individuais");
            categorias.add(c4);

            // Categoria: Causas Humanitárias
            CategoriaVaquinha c5 = new CategoriaVaquinha(null, "Causas Humanitárias", "Campanhas que apoiam causas sociais importantes");
            categorias.add(c5);

            // Categoria: Memoriais e Funerais
            CategoriaVaquinha c6 = new CategoriaVaquinha(null, "Memoriais e Funerais", "Apoio para despesas de funeral e memoriais");
            categorias.add(c6);

            // Categoria: Animais e Pets
            CategoriaVaquinha c7 = new CategoriaVaquinha(null, "Animais e Pets", "Campanhas focadas em animais e cuidados com pets");
            categorias.add(c7);

            // Categoria: Esporte e Atividades Físicas
            CategoriaVaquinha c8 = new CategoriaVaquinha(null, "Esporte e Atividades Físicas", "Campanhas relacionadas a esportes e bem-estar");
            categorias.add(c8);

            // Categoria: Inovações e Tecnologia
            CategoriaVaquinha c9 = new CategoriaVaquinha(null, "Inovações e Tecnologia", "Campanhas para desenvolver novas tecnologias");
            categorias.add(c9);

            // Categoria: Arte e Cultura
            CategoriaVaquinha c10 = new CategoriaVaquinha(null, "Arte e Cultura", "Campanhas voltadas para a promoção de arte e cultura");
            categorias.add(c10);

            if(categoriaVaquinhaRepositorio.count()==0){
                categoriaVaquinhaRepositorio.saveAll(categorias);
            }

            subcategorias.add(new SubcategoriaVaquinha(null, "Tratamento Médico", "Relacionado a tratamentos médicos", c1));
            subcategorias.add(new SubcategoriaVaquinha(null, "Cirurgias", "Relacionado a procedimentos cirúrgicos", c1));
            subcategorias.add(new SubcategoriaVaquinha(null, "Medicamentos", "Aquisição de medicamentos essenciais", c1));
            subcategorias.add(new SubcategoriaVaquinha(null, "Saúde Mental", "Focado em tratamentos e apoio psicológico", c1));
            subcategorias.add(new SubcategoriaVaquinha(null, "Equipamentos Médicos", "Compra de equipamentos para tratamento", c1));
            subcategorias.add(new SubcategoriaVaquinha(null, "Reabilitação", "Processos de recuperação e fisioterapia", c1));

            subcategorias.add(new SubcategoriaVaquinha(null, "Taxas Escolares", "Pagamento de mensalidades e taxas acadêmicas", c2));
            subcategorias.add(new SubcategoriaVaquinha(null, "Materiais Didáticos", "Aquisição de livros e materiais educativos", c2));
            subcategorias.add(new SubcategoriaVaquinha(null, "Bolsas de Estudo", "Financiamento de bolsas de estudo", c2));
            subcategorias.add(new SubcategoriaVaquinha(null, "Cursos e Treinamentos", "Apoio para cursos e capacitações", c2));
            subcategorias.add(new SubcategoriaVaquinha(null, "Projetos Educacionais", "Iniciativas e projetos na área da educação", c2));
            subcategorias.add(new SubcategoriaVaquinha(null, "Educação Infantil", "Focado no desenvolvimento de crianças", c2));

            subcategorias.add(new SubcategoriaVaquinha(null, "Combate à Fome", "Iniciativas para erradicação da fome", c3));
            subcategorias.add(new SubcategoriaVaquinha(null, "Abrigos", "Construção ou manutenção de abrigos", c3));
            subcategorias.add(new SubcategoriaVaquinha(null, "Desastres Naturais", "Ajuda para vítimas de desastres naturais", c3));
            subcategorias.add(new SubcategoriaVaquinha(null, "Programas de Voluntariado", "Suporte para atividades de voluntariado", c3));
            subcategorias.add(new SubcategoriaVaquinha(null, "Projetos Sociais", "Iniciativas para melhorias sociais", c3));

            subcategorias.add(new SubcategoriaVaquinha(null, "Viagens", "Apoio financeiro para viagens", c4));
            subcategorias.add(new SubcategoriaVaquinha(null, "Casamentos", "Financiamento de casamentos", c4));
            subcategorias.add(new SubcategoriaVaquinha(null, "Projetos Criativos", "Apoio para arte, música e outros projetos criativos", c4));
            subcategorias.add(new SubcategoriaVaquinha(null, "Startups e Empreendedorismo", "Suporte para novos negócios", c4));
            subcategorias.add(new SubcategoriaVaquinha(null, "Eventos e Festas", "Financiamento de eventos e celebrações", c4));

            subcategorias.add(new SubcategoriaVaquinha(null, "Direitos Humanos", "Apoio a iniciativas de direitos humanos", c5));
            subcategorias.add(new SubcategoriaVaquinha(null, "Igualdade de Gênero", "Campanhas focadas na promoção da igualdade", c5));
            subcategorias.add(new SubcategoriaVaquinha(null, "Refugiados e Migrantes", "Ajuda para refugiados e migrantes", c5));
            subcategorias.add(new SubcategoriaVaquinha(null, "Ação Ambiental", "Iniciativas voltadas à preservação do meio ambiente", c5));

            subcategorias.add(new SubcategoriaVaquinha(null, "Despesas de Funeral", "Ajuda financeira para funerais", c6));
            subcategorias.add(new SubcategoriaVaquinha(null, "Tributos em Memória", "Campanhas em homenagem a entes queridos", c6));
            subcategorias.add(new SubcategoriaVaquinha(null, "Apoio à Família", "Ajuda para famílias em luto", c6));

            subcategorias.add(new SubcategoriaVaquinha(null, "Resgate de Animais", "Apoio ao resgate e cuidado de animais", c7));
            subcategorias.add(new SubcategoriaVaquinha(null, "Tratamento Veterinário", "Ajuda para custos veterinários", c7));
            subcategorias.add(new SubcategoriaVaquinha(null, "Adoção de Animais", "Iniciativas para adoção de pets", c7));

            subcategorias.add(new SubcategoriaVaquinha(null, "Equipamentos Esportivos", "Financiamento para compra de equipamentos esportivos", c8));
            subcategorias.add(new SubcategoriaVaquinha(null, "Times e Clubes", "Apoio para equipes e clubes esportivos", c8));
            subcategorias.add(new SubcategoriaVaquinha(null, "Eventos Esportivos", "Financiamento de eventos esportivos", c8));
            subcategorias.add(new SubcategoriaVaquinha(null, "Treinamentos", "Apoio para treinamentos esportivos", c8));

            subcategorias.add(new SubcategoriaVaquinha(null, "Desenvolvimento de Software", "Apoio para criação de softwares", c9));
            subcategorias.add(new SubcategoriaVaquinha(null, "Gadgets e Dispositivos", "Iniciativas para novos dispositivos", c9));
            subcategorias.add(new SubcategoriaVaquinha(null, "Projetos de Engenharia", "Financiamento de projetos de engenharia", c9));
            subcategorias.add(new SubcategoriaVaquinha(null, "Pesquisa e Desenvolvimento", "Apoio para P&D em tecnologia", c9));

            subcategorias.add(new SubcategoriaVaquinha(null, "Projetos de Filmes", "Apoio para produção de filmes e vídeos", c10));
            subcategorias.add(new SubcategoriaVaquinha(null, "Produção de Música", "Financiamento para gravações musicais", c10));
            subcategorias.add(new SubcategoriaVaquinha(null, "Exposições de Arte", "Iniciativas para exposições de arte", c10));
            subcategorias.add(new SubcategoriaVaquinha(null, "Publicação de Livros", "Apoio para escritores e publicações", c10));

            if(categoriaVaquinhaRepositorio.count()!=0 && subcategoriaVaquinhaRepositorio.count()==0){
                subcategoriaVaquinhaRepositorio.saveAll(subcategorias);
            }
        };
    }
}

