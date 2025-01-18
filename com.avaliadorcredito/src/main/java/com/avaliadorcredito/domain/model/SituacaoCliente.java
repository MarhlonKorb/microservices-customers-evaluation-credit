package com.avaliadorcredito.domain.model;

import java.util.List;

public record SituacaoCliente(DadosCliente cliente, List<CartaoCliente> cartoes) {

}
