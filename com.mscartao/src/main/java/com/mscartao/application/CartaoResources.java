package com.mscartao.application;

import com.mscartao.domain.Cartao;
import com.mscartao.representation.CartaoInputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartoes")
public class CartaoResources {

    private final CartaoService cartaoService;

    public CartaoResources(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getAll(@RequestParam("renda") Long renda) {
        var cartoes = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(cartoes);
    }

    @PostMapping
    public ResponseEntity cadastrarCartao(@RequestBody CartaoInputDto input) {
        var cartao = cartaoService.save(input.toModel());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
