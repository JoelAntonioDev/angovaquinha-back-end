package com.projecto.angovaquinha.modelos;

import jakarta.persistence.*;
import lombok.*;


@Data
@Embeddable
@EqualsAndHashCode
public class InformacaoContacto {

    @Column(name = "bilhete_identidade", nullable = true)
    private String bilheteIdentidade;

    @Column(name = "numero_telefone", nullable = true)
    private String numeroTelefone;

    @Column(name = "provincia", nullable = true)
    private String provincia;

    @Column(name = "municipio", nullable = true)
    private String municipio;

    @Column(name = "bairro", nullable = true)
    private String bairro;

}
