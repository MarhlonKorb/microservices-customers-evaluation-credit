package com.avaliadorcredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value(value = "${mq.queues.emissao-cartoes}")
    private String emissaoCartoesFilaNome;

    @Bean
    public Queue queueEmissaoCartoes(){
        return new Queue(emissaoCartoesFilaNome, true);
    }
}
