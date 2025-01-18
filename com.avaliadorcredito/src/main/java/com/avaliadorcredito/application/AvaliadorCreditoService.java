package com.avaliadorcredito.application;

import com.avaliadorcredito.application.exception.DadosClienteNotFoundException;
import com.avaliadorcredito.application.exception.ErroComunicacaoMicroservicesException;
import com.avaliadorcredito.domain.model.*;
import com.avaliadorcredito.infra.clients.CartaoResourceClient;
import com.avaliadorcredito.infra.clients.ClienteResourceClient;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteResourceClient;
    private final CartaoResourceClient cartaoResourceClient;

    public AvaliadorCreditoService(ClienteResourceClient clienteResourceClient, CartaoResourceClient cartaoResourceClient) {
        this.clienteResourceClient = clienteResourceClient;
        this.cartaoResourceClient = cartaoResourceClient;
    }

    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> clienteResponse = clienteResourceClient.dadosCliente(cpf);
            DadosCliente dadosClienteResponse = new DadosCliente(
                    clienteResponse.getBody().id(),
                    clienteResponse.getBody().nome(),
                    clienteResponse.getBody().idade()
            );
            var cartoesClienteResponse = cartaoResourceClient.getCartoesClienteByCpf(cpf);
            return new SituacaoCliente(dadosClienteResponse, cartoesClienteResponse.getBody());
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }

    public RetornoAvaliacaoCliente executaAvaliacao(String cpf, Long renda) throws ErroComunicacaoMicroservicesException, DadosClienteNotFoundException {
        try {
            var clienteResponse = clienteResourceClient.dadosCliente(cpf);
            var cartoesResponse = cartaoResourceClient.getCartoesRendaMenorIgual(renda);
            var listaCartoesAprovados = getCartoesAprovadosCliente(
                    cartoesResponse.getBody(),
                    clienteResponse.getBody()
            );
            return new RetornoAvaliacaoCliente(listaCartoesAprovados);
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }

    /**
     * Converte os cartões recebidos em cartões aprovados para o cliente de acordo com os dados do cliente
     *
     * @param cartoes
     * @param dadosCliente
     * @return List<CartaoAprovado>
     */
    private List<CartaoAprovado> getCartoesAprovadosCliente(List<Cartao> cartoes, DadosCliente dadosCliente) {
        assert dadosCliente != null;
        return requireNonNull(cartoes).stream().map(c -> {
            var limiteAprovadoCliente = calculaLimiteAprovadoCliente(
                    c.limiteBasico(),
                    BigDecimal.valueOf(dadosCliente.idade())
            );
            return new CartaoAprovado(c.nome(), c.bandeira(), limiteAprovadoCliente);
        }).toList();
    }

    /**
     * O limite aprovado do cliente é calculado de acordo com a idade do cliente dividido pelo fator 10
     * e multiplicado pelo limite básico do cartão
     *
     * @param limiteBasicoCartao
     * @param idadeCliente
     * @return BigDecimal
     */
    public BigDecimal calculaLimiteAprovadoCliente(BigDecimal limiteBasicoCartao, BigDecimal idadeCliente) {
        return idadeCliente.divide(BigDecimal.TEN).multiply(limiteBasicoCartao);
    }
}
