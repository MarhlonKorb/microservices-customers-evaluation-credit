package com.avaliadorcredito.application;

import com.avaliadorcredito.application.exception.DadosClienteNotFoundException;
import com.avaliadorcredito.application.exception.ErroComunicacaoMicroservicesException;
import com.avaliadorcredito.domain.model.DadosAvaliacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("avaliacao-credito")
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    public AvaliadorCreditoController(AvaliadorCreditoService avaliadorCreditoService) {
        this.avaliadorCreditoService = avaliadorCreditoService;
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultarSituacaoCliente(@RequestParam("cpf") String cpf) {
        try {
            var situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            var statusCode = Optional
                    .ofNullable(HttpStatus.resolve(e.getStatus()))
                    .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(statusCode).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dadosAvaliacao) {
        try {
            var retornoAvaliacaoCliente = avaliadorCreditoService.executaAvaliacao(dadosAvaliacao.cpf(), dadosAvaliacao.renda());
            return ResponseEntity.ok(retornoAvaliacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            var statusCode = Optional
                    .ofNullable(HttpStatus.resolve(e.getStatus()))
                    .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(statusCode).body(e.getMessage());
        }
    }
}
