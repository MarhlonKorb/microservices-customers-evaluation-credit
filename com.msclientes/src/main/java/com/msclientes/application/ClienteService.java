package com.msclientes.application;

import com.msclientes.domain.Cliente;
import com.msclientes.infra.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
