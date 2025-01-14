package com.msclientes.representation;

import com.msclientes.domain.Cliente;

public record ClienteInputDto(String cpf, String nome, Integer idade) {

    public Cliente toModel(){
        return new Cliente(cpf, nome, idade);
    }
}
