package com.mscartao.representation;

import com.mscartao.domain.model.BandeiraCartao;
import com.mscartao.domain.model.Cartao;

import java.math.BigDecimal;

public record CartaoInputDto(Long id, String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limite) {

    public Cartao toModel(){
        return new Cartao(id, nome, bandeira, renda, limite);
    }
}
