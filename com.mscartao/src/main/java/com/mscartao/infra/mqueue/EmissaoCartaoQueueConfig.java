package com.mscartao.infra.mqueue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmissaoCartaoQueueConfig {
    private static final String QUEUE_NAME = "emissao-cartoes";
//    static final String topicExchangeName = "emissao-cartoes-exchange";

    @Bean
    public Queue emissaoCartoesQueue() {
        return new Queue(QUEUE_NAME, true);
    }

//    @Bean
//    public DirectExchange exchange() {
//        return new DirectExchange(topicExchangeName);
//    }
//
//    @Bean
//    public Binding bindingQueue(Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("minha-routing-key");
//    }

}

