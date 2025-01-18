package com.avaliadorcredito.domain.model;

import java.math.BigDecimal;

public record Cartao(Long id, String nome, String bandeira, BigDecimal limiteBasico) {
}
