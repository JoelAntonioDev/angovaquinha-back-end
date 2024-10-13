-- Tabelas básicas de estado da vaquinha
INSERT INTO estado_vaquinha (id, estado) VALUES (1, 'ACTIVADO');
INSERT INTO estado_vaquinha (id, estado) VALUES (2, 'DESACTIVADO');

-- Tabelas de níveis de acesso
INSERT INTO nivel_acesso (id, nivel) VALUES (1, 'ADMINISTRADOR');
INSERT INTO nivel_acesso (id, nivel) VALUES (2, 'SOLICITADOR');
INSERT INTO nivel_acesso (id, nivel) VALUES (3, 'CONTRIBUINTE');
INSERT INTO nivel_acesso (id, nivel) VALUES (4, 'USUARIO_NORMAL');

-- Tabela de contas bancárias
INSERT INTO conta (id, iban, nome_banco, numero_conta) VALUES (1, '1202.9182.0291.8292.8291.8', 'BBB', 192819891);

-- Tabela de usuários
insert into usuario(id, data_criacao, email, nome, senha, id_conta, id_nivel_acesso) values (1, now(),'joellucas2020eu@gmail.com','Joel António','1234',1,1);

-- Tabela de categorias de vaquinha
INSERT INTO categoria_vaquinha (id, nome, descricao) VALUES (1, 'Saúde e Bem-Estar', 'Representa a saúde e qualidade de vida');
INSERT INTO categoria_vaquinha (id, nome, descricao) VALUES (2, 'Educação', 'Focado em iniciativas educacionais');
INSERT INTO categoria_vaquinha (id, nome, descricao) VALUES (3, 'Ajuda Comunitária', 'Projetos que apoiam a comunidade');
INSERT INTO categoria_vaquinha (id, nome, descricao) VALUES (4, 'Projetos Pessoais', 'Campanhas focadas em projetos individuais');
INSERT INTO categoria_vaquinha (id, nome, descricao) VALUES (5, 'Causas Humanitárias', 'Campanhas que apoiam causas sociais importantes');
INSERT INTO categoria_vaquinha (id, nome, descricao) VALUES (6, 'Memoriais e Funerais', 'Apoio para despesas de funeral e memoriais');
INSERT INTO categoria_vaquinha (id, nome, descricao) VALUES (7, 'Animais e Pets', 'Campanhas focadas em animais e cuidados com pets');
INSERT INTO categoria_vaquinha (id, nome, descricao) VALUES (8, 'Esporte e Atividades Físicas', 'Campanhas relacionadas a esportes e bem-estar');
INSERT INTO categoria_vaquinha (id, nome, descricao) VALUES (9, 'Inovações e Tecnologia', 'Campanhas para desenvolver novas tecnologias');
INSERT INTO categoria_vaquinha (id, nome, descricao) VALUES (10, 'Arte e Cultura', 'Campanhas voltadas para a promoção de arte e cultura');

-- Tabela de subcategorias de vaquinha
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (1, 'Tratamento Médico', 'Relacionado a tratamentos médicos', 1);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (2, 'Cirurgias', 'Relacionado a procedimentos cirúrgicos', 1);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (3, 'Medicamentos', 'Aquisição de medicamentos essenciais', 1);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (4, 'Saúde Mental', 'Focado em tratamentos e apoio psicológico', 1);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (5, 'Equipamentos Médicos', 'Compra de equipamentos para tratamento', 1);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (6, 'Reabilitação', 'Processos de recuperação e fisioterapia', 1);

INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (7, 'Taxas Escolares', 'Pagamento de mensalidades e taxas acadêmicas', 2);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (8, 'Materiais Didáticos', 'Aquisição de livros e materiais educativos', 2);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (9, 'Bolsas de Estudo', 'Financiamento de bolsas de estudo', 2);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (10, 'Cursos e Treinamentos', 'Apoio para cursos e capacitações', 2);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (11, 'Projetos Educacionais', 'Iniciativas e projetos na área da educação', 2);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (12, 'Educação Infantil', 'Focado no desenvolvimento de crianças', 2);

INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (13, 'Combate à Fome', 'Iniciativas para erradicação da fome', 3);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (14, 'Abrigos', 'Construção ou manutenção de abrigos', 3);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (15, 'Desastres Naturais', 'Ajuda para vítimas de desastres naturais', 3);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (16, 'Programas de Voluntariado', 'Suporte para atividades de voluntariado', 3);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (17, 'Projetos Sociais', 'Iniciativas para melhorias sociais', 3);

INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (18, 'Viagens', 'Apoio financeiro para viagens', 4);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (19, 'Casamentos', 'Financiamento de casamentos', 4);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (20, 'Projetos Criativos', 'Apoio para arte, música e outros projetos criativos', 4);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (21, 'Startups e Empreendedorismo', 'Suporte para novos negócios', 4);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (22, 'Eventos e Festas', 'Financiamento de eventos e celebrações', 4);

INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (23, 'Direitos Humanos', 'Apoio a iniciativas de direitos humanos', 5);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (24, 'Igualdade de Gênero', 'Campanhas focadas na promoção da igualdade', 5);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (25, 'Refugiados e Migrantes', 'Ajuda para refugiados e migrantes', 5);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (26, 'Ação Ambiental', 'Iniciativas voltadas à preservação do meio ambiente', 5);

INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (27, 'Despesas de Funeral', 'Ajuda financeira para funerais', 6);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (28, 'Tributos em Memória', 'Campanhas em homenagem a entes queridos', 6);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (29, 'Apoio à Família', 'Ajuda para famílias em luto', 6);

INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (30, 'Resgate de Animais', 'Apoio ao resgate e cuidado de animais', 7);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (31, 'Tratamento Veterinário', 'Ajuda para custos veterinários', 7);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (32, 'Adoção de Animais', 'Iniciativas para adoção de pets', 7);

INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (33, 'Equipamentos Esportivos', 'Financiamento para compra de equipamentos esportivos', 8);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (34, 'Times e Clubes', 'Apoio para equipes e clubes esportivos', 8);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (35, 'Eventos Esportivos', 'Financiamento de eventos esportivos', 8);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (36, 'Treinamentos', 'Apoio para treinamentos esportivos', 8);

INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (37, 'Desenvolvimento de Software', 'Apoio para criação de softwares', 9);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (38, 'Gadgets e Dispositivos', 'Iniciativas para novos dispositivos', 9);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (39, 'Projetos de Engenharia', 'Financiamento de projetos de engenharia', 9);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (40, 'Pesquisa e Desenvolvimento', 'Apoio para P&D em tecnologia', 9);

INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (41, 'Projetos de Filmes', 'Apoio para produção de filmes e vídeos', 10);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (42, 'Produção de Música', 'Financiamento para gravações musicais', 10);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (43, 'Exposições de Arte', 'Iniciativas para exposições de arte', 10);
INSERT INTO subcategoria_vaquinha (id, nome, descricao, id_categoria_vaquinha) VALUES (44, 'Publicação de Livros', 'Apoio para escritores e publicações', 10);
