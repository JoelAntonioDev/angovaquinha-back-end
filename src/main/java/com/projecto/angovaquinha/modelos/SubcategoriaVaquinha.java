package com.projecto.angovaquinha.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubcategoriaVaquinha {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_categoria_vaquinha", nullable = false)
    private CategoriaVaquinha categoria;

}
