package com.mscartao.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira;

    private BigDecimal renda;

    private BigDecimal limiteBasico;

    public Cartao() {
    }

    public Cartao(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limiteBasico) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.renda = renda;
        this.limiteBasico = limiteBasico;
    }

    public String getNome() {
        return nome;
    }

    public BandeiraCartao getBandeira() {
        return bandeira;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public BigDecimal getLimiteBasico() {
        return limiteBasico;
    }
}
