package com.lava_jato.controllers;

import com.lava_jato.entities.dto.request.VeiculoDTO;
import com.lava_jato.entities.dto.responses.VeiculoResponseDTO;
import com.lava_jato.services.VeiculoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<VeiculoResponseDTO> create(@RequestBody VeiculoDTO veiculoDTO){
        return ResponseEntity.ok(veiculoService.createVeiculo(veiculoDTO));
    }

    @PatchMapping("/update/{veiculoId}")
    public ResponseEntity<VeiculoResponseDTO> update(@PathVariable("veiculoId") Long veiculoId, @RequestBody VeiculoDTO veiculoDTO){
        return ResponseEntity.ok(veiculoService.updateVeiculo(veiculoId, veiculoDTO));
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoResponseDTO> findById(@PathVariable Long veiculoId){
        return ResponseEntity.ok(veiculoService.getById(veiculoId));
    }

    @GetMapping("/")
    public ResponseEntity<Page<VeiculoResponseDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(veiculoService.findAllVeiculos(pageable));
    }

    @DeleteMapping("/delete/{veiculoId}")
    public ResponseEntity<Void> delete(@PathVariable Long veiculoId){
        veiculoService.deleteById(veiculoId);
        return ResponseEntity.noContent().build();
    }
}
