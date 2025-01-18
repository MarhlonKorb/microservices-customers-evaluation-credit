package com.avaliadorcredito.domain.model;

import java.math.BigDecimal;

public record CartaoAprovado(String cartao, String bandeira, BigDecimal limiteAprovado) {
}
