package com.mscartao.application;

import com.mscartao.domain.model.ClienteCartao;
import com.mscartao.infra.repository.ClienteCartaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteCartaoService {

    private final ClienteCartaoRepository clienteCartaoRepository;

    public ClienteCartaoService(ClienteCartaoRepository clienteCartaoRepository) {
        this.clienteCartaoRepository = clienteCartaoRepository;
    }

    public List<ClienteCartao> listCartoesByCpf(String cpf){
        return clienteCartaoRepository.findByCpf(cpf);
    }
}
