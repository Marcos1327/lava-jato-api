package com.lava_jato.controllers;

import com.lava_jato.entities.dto.VeiculoDTO;
import com.lava_jato.entities.dto.responses.VeiculoResponseDTO;
import com.lava_jato.entities.model.PessoaFisica;
import com.lava_jato.entities.model.Veiculo;
import com.lava_jato.services.VeiculoService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/find-by-id/{veiculoId}")
    public ResponseEntity<VeiculoResponseDTO> findById(@PathVariable Long veiculoId){
        return ResponseEntity.ok(veiculoService.getById(veiculoId));
    }

    @GetMapping("/")
    public ResponseEntity<List<VeiculoResponseDTO>> findAll(){
        return ResponseEntity.ok(veiculoService.findAllVeiculos());
    }

    @DeleteMapping("/delete/{veiculoId}")
    public ResponseEntity<Void> delete(@PathVariable Long veiculoId){
        veiculoService.deleteById(veiculoId);
        return ResponseEntity.noContent().build();
    }
}
