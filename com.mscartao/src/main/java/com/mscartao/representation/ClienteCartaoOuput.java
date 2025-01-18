package com.mscartao.representation;

import com.mscartao.domain.model.ClienteCartao;

import java.math.BigDecimal;

public record ClienteCartaoOuput(String nome, String bandeiraCartao, BigDecimal limiteLiberado) {

    public static ClienteCartaoOuput fromModel(ClienteCartao clienteCartao) {
        return new ClienteCartaoOuput(
                clienteCartao.getCartao().getNome(),
                clienteCartao.getCartao().getBandeira().toString(),
                clienteCartao.getLimite()
        );
    }
}
