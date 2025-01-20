package com.avaliadorcredito.domain.model;

import java.math.BigDecimal;

public record DadosSolicitacaoEmissaoCartao(Long idCartao, String cpf, String endereco, BigDecimal limiteLiberado) {
}
