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
public class Conta {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="iban", nullable = false)
    private String iban;

    @Column(name="nome_banco", nullable = false)
    private String nomeBanco;

    @Column(name="numero_conta")
    private int numeroConta;

}
