package com.lava_jato.controllers;

import com.lava_jato.entities.dto.responses.ClienteResumoDTO;
import com.lava_jato.entities.dto.request.PessoaFisicaDTO;
import com.lava_jato.entities.dto.request.PessoaJuridicaDTO;
import com.lava_jato.entities.dto.responses.PessoaFisicaResponseDTO;
import com.lava_jato.entities.dto.responses.PessoaJuridicaResponseDTO;
import com.lava_jato.services.ClienteService;
import com.lava_jato.services.PessoaFisicaService;
import com.lava_jato.services.PessoaJuridicaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<PessoaFisicaResponseDTO> createPessoaFisica(@RequestBody PessoaFisicaDTO pessoaFisicaDTO){
        return ResponseEntity.ok(pessoaFisicaService.createPessoaFisica(pessoaFisicaDTO));
    }

    @PostMapping("/create-pj")
    public ResponseEntity<PessoaJuridicaResponseDTO> createPessoaJuridica(@RequestBody PessoaJuridicaDTO pessoaJuridicaDTO){
        return ResponseEntity.ok(pessoaJuridicaService.createPessoaJuridica(pessoaJuridicaDTO));
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteResumoDTO> findClienteById(@PathVariable("clienteId") Long clienteId){
         return ResponseEntity.ok(clienteService.getClienteById(clienteId));
    }

    @GetMapping("/")
    public ResponseEntity<Page<ClienteResumoDTO>> findAllClientes(Pageable pageable){
        return ResponseEntity.ok(clienteService.findAllClientes(pageable));
    }

    @GetMapping("/find-all-pf")
    public ResponseEntity<List<PessoaFisicaResponseDTO>> findAllPessoaFisica(){
        return ResponseEntity.ok(pessoaFisicaService.findAllPessoaFisica());
    }

    @GetMapping("/find-all-pj")
    public ResponseEntity<List<PessoaJuridicaResponseDTO>> findAllPessoaJuridica(){
        return ResponseEntity.ok(pessoaJuridicaService.findAllPessoaJuridica());
    }

    @PatchMapping("/update-pf/{clienteId}")
    public ResponseEntity<PessoaFisicaResponseDTO> updatePessoaFisica(@PathVariable("clienteId") Long clienteId, @RequestBody PessoaFisicaDTO pessoaFisicaDTO){
        return ResponseEntity.ok(pessoaFisicaService.updatePessoaFisica(clienteId, pessoaFisicaDTO));
    }

    @PatchMapping("/update-pj/{clienteId}")
    public ResponseEntity<PessoaJuridicaResponseDTO> updatePessoaJuridica(@PathVariable("clienteId") Long clienteId, @RequestBody PessoaJuridicaDTO pessoaJuridicaDTO){
        return ResponseEntity.ok(pessoaJuridicaService.updatePessoaJuridica( clienteId, pessoaJuridicaDTO));
    }

    @DeleteMapping("/delete/{clienteId}")
    public ResponseEntity<Void>  deletePessoaFisica(@PathVariable("clienteId") Long clienteId){
        clienteService.deleteCliente(clienteId);
        return ResponseEntity.noContent().build();
    }
}
