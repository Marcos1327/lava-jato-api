package com.lava_jato.controllers;

import com.lava_jato.entities.dto.PessoaFisicaDTO;
import com.lava_jato.entities.dto.PessoaJuridicaDTO;
import com.lava_jato.entities.model.Cliente;
import com.lava_jato.entities.model.PessoaFisica;
import com.lava_jato.entities.model.PessoaJuridica;
import com.lava_jato.repositories.ClienteRepository;
import com.lava_jato.services.ClienteService;
import com.lava_jato.services.PessoaFisicaService;
import com.lava_jato.services.PessoaJuridicaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final PessoaJuridicaService pessoaJuridicaService;
    private final PessoaFisicaService pessoaFisicaService;

    public ClienteController(ClienteService clienteService, PessoaJuridicaService pessoaJuridicaService, PessoaFisicaService pessoaFisicaService) {
        this.clienteService = clienteService;
        this.pessoaJuridicaService = pessoaJuridicaService;
        this.pessoaFisicaService = pessoaFisicaService;
    }

    @PostMapping("/create-pf")
    public ResponseEntity<PessoaFisica> createPessoaFisica(@RequestBody PessoaFisicaDTO pessoaFisicaDTO){
        return ResponseEntity.ok(pessoaFisicaService.createPessoaFisica(pessoaFisicaDTO));
    }

    @PostMapping("/create-pj")
    public ResponseEntity<PessoaJuridica> createPessoaJuridica(@RequestBody PessoaJuridicaDTO pessoaJuridicaDTO){
        return ResponseEntity.ok(pessoaJuridicaService.createPessoaJuridica(pessoaJuridicaDTO));
    }

    @GetMapping("/findById/{clienteId}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable("clienteId") Long clienteId){
         return ResponseEntity.ok(clienteService.getPessoaFisicaById(clienteId));
    }

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> findAllClientes(){
        return ResponseEntity.ok(clienteService.findAllClientes());
    }

    @GetMapping("/find-all-pf")
    public ResponseEntity<List<PessoaFisica>> findAllPessoaFisica(){
        return ResponseEntity.ok(pessoaFisicaService.findAllPessoaFisica());
    }

    @GetMapping("/find-all-pj")
    public ResponseEntity<List<PessoaJuridica>> findAllPessoaJuridica(){
        return ResponseEntity.ok(pessoaJuridicaService.findAllPessoaJuridica());
    }


}
