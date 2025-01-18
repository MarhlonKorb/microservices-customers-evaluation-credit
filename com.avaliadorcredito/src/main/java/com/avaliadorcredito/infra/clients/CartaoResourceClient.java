package com.avaliadorcredito.infra.clients;

import com.avaliadorcredito.domain.model.Cartao;
import com.avaliadorcredito.domain.model.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartao", path = "/cartoes")
public interface CartaoResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoCliente>> getCartoesClienteByCpf(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    ResponseEntity<List<Cartao>> getCartoesRendaMenorIgual(@RequestParam("renda") Long renda);
}
