package com.mscartao.representation;

import com.mscartao.domain.model.ClienteCartao;

import java.math.BigDecimal;

public record ClienteCartaoOutput(String nome, String bandeiraCartao, BigDecimal limiteLiberado) {

    public static ClienteCartaoOutput fromModel(ClienteCartao clienteCartao) {
        return new ClienteCartaoOutput(
                clienteCartao.getCartao().getNome(),
                clienteCartao.getCartao().getBandeira().toString(),
                clienteCartao.getLimite()
        );
    }
}
