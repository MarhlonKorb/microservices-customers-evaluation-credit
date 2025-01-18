package com.msclientes.domain;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente() {
    }

    public Cliente(String cpf, String nome, Integer idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }
}
