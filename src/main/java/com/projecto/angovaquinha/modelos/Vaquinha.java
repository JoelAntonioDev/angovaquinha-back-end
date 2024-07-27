package com.projecto.angovaquinha.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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

    @Column(name="descricao", nullable = false)
    private String descricao;

    @Column(name="categoria", nullable = false)
    private String categoria;

    @Column(name="data_criacao", nullable = false)
    private Date dataCriacao;

    @OneToOne
    @JoinColumn(name="id_estado_vaquinha", nullable = false)
    private EstadoVaquinha estadoVaquinha;

    @OneToOne
    @JoinColumn(name="id_contribuicao", nullable = false)
    private Contribuicao contribuicao;

    @ManyToOne
    @JoinColumn(name="id_usuario", nullable = false)
    private Usuario usuario;
}
