package com.lava_jato.controllers;

import com.lava_jato.entities.dto.VeiculoDTO;
import com.lava_jato.entities.model.PessoaFisica;
import com.lava_jato.entities.model.Veiculo;
import com.lava_jato.services.VeiculoService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService){
        this.veiculoService = veiculoService;
    }

    @PostMapping("/create")
    public ResponseEntity<Veiculo> create(@RequestBody VeiculoDTO veiculoDTO){
        return ResponseEntity.ok(veiculoService.createVeiculo(veiculoDTO));
    }

    @GetMapping("/find-by-id/{veiculoId}")
    public ResponseEntity<Veiculo> findById(@PathVariable Long veiculoId){
        return ResponseEntity.ok(veiculoService.getById(veiculoId));
    }
}
