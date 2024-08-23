package com.projecto.angovaquinha.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Usuario {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @JsonIgnore
    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name="data_criacao", nullable = false)
    private Date dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_nivel_acesso", nullable = false)
    private NivelAcesso nivelAcesso;

    @OneToOne
    @JoinColumn(name = "id_conta")
    private Conta conta;
}