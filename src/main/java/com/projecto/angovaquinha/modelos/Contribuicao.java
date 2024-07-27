package com.projecto.angovaquinha.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contribuicao {
    @jakarta.persistence.Id
    private Long id;

    @Column(name="valor_monetario", nullable = false)
    private Double valorMonetario;

    @Column(name="moeda", nullable = false)
    private String moeda;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;
}
