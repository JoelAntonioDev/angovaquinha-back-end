package com.projecto.angovaquinha.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Optional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vaquinha {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="titulo", nullable = false)
    private String titulo;

    @Column(name="descricao", nullable = false, length = 500)
    private String descricao;

    @Column(name="data_criacao", nullable = false)
    private Date dataCriacao;

    @Column(name="quantia", nullable = false)
    private int quantia;

    @Column(name="caminho_imagem")
    private String caminhoImagem;

    @OneToOne
    @JoinColumn(name="id_estado_vaquinha", nullable = false)
    private EstadoVaquinha estadoVaquinha;

    @ManyToOne
    @JoinColumn(name="id_usuario", nullable = false)
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name="id_subcategoria_vaquinha", nullable = false)
    private SubcategoriaVaquinha subcategoriaVaquinha;

}
