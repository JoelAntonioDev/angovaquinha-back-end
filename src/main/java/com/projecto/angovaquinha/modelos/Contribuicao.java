package com.projecto.angovaquinha.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contribuicao {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="valor_monetario", nullable = false)
    private Double valorMonetario;

    @Column(name="moeda", nullable = false)
    private String moeda;

    @Column(name="data_criacao", nullable = false)
    private Date dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id_vaquinha")
    private Vaquinha vaquinha;
}
