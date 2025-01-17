package com.mscartao.representation;

import com.mscartao.domain.BandeiraCartao;
import com.mscartao.domain.Cartao;

import java.math.BigDecimal;

public record CartaoInputDto(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limite) {

    public Cartao toModel(){
        return new Cartao(nome, bandeira, renda, limite);
    }
}
