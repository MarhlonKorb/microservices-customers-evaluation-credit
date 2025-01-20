package com.mscartao.application;

import com.mscartao.domain.model.Cartao;
import com.mscartao.representation.CartaoInputDto;
import com.mscartao.representation.ClienteCartaoOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
public class CartaoResources {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    public CartaoResources(CartaoService cartaoService, ClienteCartaoService clienteCartaoService) {
        this.cartaoService = cartaoService;
        this.clienteCartaoService = clienteCartaoService;
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaMenorIgual(@RequestParam("renda") Long renda) {
        var cartoes = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(cartoes);
    }

    @PostMapping
    public ResponseEntity cadastrarCartao(@RequestBody CartaoInputDto input) {
        var cartao = cartaoService.save(input.toModel());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ClienteCartaoOutput>> getCartoesClienteByCpf(@RequestParam("cpf") String cpf) {
        var clienteCartaos = clienteCartaoService.listCartoesByCpf(cpf);
        List<ClienteCartaoOutput> listaCartoes = clienteCartaos
                .stream()
                .map(ClienteCartaoOutput::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaCartoes);
    }

}
