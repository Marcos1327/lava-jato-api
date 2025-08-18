package com.lava_jato.controllers;

import com.lava_jato.entities.dto.request.DespesaDTO;
import com.lava_jato.entities.dto.responses.DespesaResponseDTO;
import com.lava_jato.services.DespesaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/despesas")
public class DespesaController {

    private final DespesaService despesaService;

    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @PostMapping("/create")
    public ResponseEntity<DespesaResponseDTO> create(@RequestBody DespesaDTO despesaDTO){
        return ResponseEntity.ok(despesaService.create(despesaDTO));
    }

    @PatchMapping("/update/{despesaId}")
    public ResponseEntity<DespesaResponseDTO> update(@PathVariable Long despesaId, @RequestBody DespesaDTO despesaDTO){
        return ResponseEntity.ok(despesaService.update(despesaId, despesaDTO));
    }

    @GetMapping("/")
    public ResponseEntity<Page<DespesaResponseDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(despesaService.findAll(pageable));
    }

    @GetMapping("/find-by-id/{despesaId}")
    public ResponseEntity<DespesaResponseDTO> findById(@PathVariable Long despesaId){
        return ResponseEntity.ok(despesaService.getById(despesaId));
    }

    @DeleteMapping("delete/{despesaId}")
    public ResponseEntity<DespesaResponseDTO> delete(@PathVariable Long despesaId){
        despesaService.deleteById(despesaId);
        return ResponseEntity.noContent().build();
    }
}
