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
public class InformacaoContacto {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bilhete_identidade")
    private String bilheteIdentidade;
    @Column(name = "numero_telefone")
    private String numeroTelefone;

    private String provincia;

    private String municipio;

    private String bairro;

}
