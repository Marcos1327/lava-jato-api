package com.lava_jato.controllers;

import com.lava_jato.entities.dto.request.AtendimentoDTO;
import com.lava_jato.entities.dto.responses.AtendimentoResponseDTO;
import com.lava_jato.services.AtendimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/atendimentos")
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @PostMapping("/create")
    public ResponseEntity<AtendimentoResponseDTO> create(@RequestBody AtendimentoDTO atendimentoDTO) {
        return ResponseEntity.ok(atendimentoService.create(atendimentoDTO));
    }

    @GetMapping("/find-by-id/{atendimentoId}")
    public ResponseEntity<AtendimentoResponseDTO> findById(@PathVariable Long atendimentoId){
        return ResponseEntity.ok(atendimentoService.getById(atendimentoId));
    }

    @DeleteMapping("/delete/{atendimentoId}")
    public ResponseEntity<AtendimentoResponseDTO> delete(@PathVariable Long atendimentoId){
        atendimentoService.deleteById(atendimentoId);
        return ResponseEntity.notFound().build();
    }
}
