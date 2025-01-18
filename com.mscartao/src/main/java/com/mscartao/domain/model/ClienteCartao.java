package com.mscartao.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ClienteCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    @ManyToOne
    @JoinColumn(name = "id_cartao")
    private Cartao cartao;

    private BigDecimal limite;

    public String getCpf() {
        return cpf;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public BigDecimal getLimite() {
        return limite;
    }
}
