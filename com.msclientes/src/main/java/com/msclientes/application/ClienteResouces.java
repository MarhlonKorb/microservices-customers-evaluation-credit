package com.msclientes.application;

import com.msclientes.representation.ClienteInputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
public class ClienteResouces {
    private final ClienteService clienteService;

    public ClienteResouces(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(params = "cpf")
    public ResponseEntity findByCpf(@RequestParam("cpf") String cpf) {
        var cliente = clienteService.findByCpf(cpf);
        if(cliente.isEmpty()){
          return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<URI> save(@RequestBody ClienteInputDto input) {
        var cliente = clienteService.save(input.toModel());
        // Returns the URI endpoint containing the client info
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
        return ResponseEntity.ok(headerLocation);
    }

}
