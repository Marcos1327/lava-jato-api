package com.lava_jato.controllers;

import com.lava_jato.entities.dto.PessoaFisicaDTO;
import com.lava_jato.entities.model.PessoaFisica;
import com.lava_jato.repositories.ClienteRepository;
import com.lava_jato.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/create-pf")
    public ResponseEntity<PessoaFisica> createPessoaFisica(@Valid @RequestBody PessoaFisicaDTO pessoaFisicaDTO){
        return ResponseEntity.ok(clienteService.createPessoaFisica(pessoaFisicaDTO));
    }

}
