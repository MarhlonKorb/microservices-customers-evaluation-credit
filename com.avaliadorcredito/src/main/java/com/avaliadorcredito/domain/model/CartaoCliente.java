package com.avaliadorcredito.domain.model;

import java.math.BigDecimal;

public record CartaoCliente(String nome, String bandeira, BigDecimal limiteCredito) {
}
