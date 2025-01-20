package com.mscartao.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mscartao.domain.model.Cartao;
import com.mscartao.domain.model.ClienteCartao;
import com.mscartao.domain.model.DadosSolicitacaoEmissaoCartao;
import com.mscartao.infra.repository.CartaoRepository;
import com.mscartao.infra.repository.ClienteCartaoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoCartaoSubscriber {

    private final CartaoRepository cartaoRepository;

    private final ClienteCartaoRepository clienteCartaoRepository;

    public EmissaoCartaoSubscriber(CartaoRepository cartaoRepository, ClienteCartaoRepository clienteCartaoRepository) {
        this.cartaoRepository = cartaoRepository;
        this.clienteCartaoRepository = clienteCartaoRepository;
    }

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void solicitacaoEmissaoCartao(String payload) {
        var mapper = new ObjectMapper();
        try {
            var dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
            Cartao cartao = cartaoRepository.findById(dados.idCartao()).orElseThrow();
            ClienteCartao clienteCartao = new ClienteCartao(dados.cpf(), cartao, dados.limiteLiberado());
            clienteCartaoRepository.save(clienteCartao);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
