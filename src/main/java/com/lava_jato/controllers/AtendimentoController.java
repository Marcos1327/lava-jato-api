package com.lava_jato.controllers;

import com.lava_jato.entities.dto.request.AtendimentoDTO;
import com.lava_jato.entities.dto.responses.AtendimentoResponseDTO;
import com.lava_jato.services.AtendimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping("/update/{atendimentoId}")
    public ResponseEntity<AtendimentoResponseDTO> update(@PathVariable Long  atendimentoId, @RequestBody AtendimentoDTO atendimentoDTO) {
        return ResponseEntity.ok(atendimentoService.update(atendimentoId, atendimentoDTO));
    }

    @PatchMapping("/arquivar/{atendimentoId}")
    public ResponseEntity<AtendimentoResponseDTO> arquivar(@PathVariable Long atendimentoId){
        return ResponseEntity.ok(atendimentoService.arquivarAtendimento(atendimentoId));
    }

    @PatchMapping("/arquivar-todos")
    public ResponseEntity<Void> arquivarTodosFinalizados(){
        atendimentoService.arquivarTodosAtendimentosFinalizados();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/arquivados")
    public ResponseEntity<List<AtendimentoResponseDTO>> arquivados(){
        return ResponseEntity.ok(atendimentoService.findAllAtendimentosArquivados());
    }

    @PatchMapping("/desarquivar/{atendimentoId}")
    public ResponseEntity<Void> desarquivar(@PathVariable Long atendimentoId){
        atendimentoService.desarquivarAtendimento(atendimentoId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/desarquivar-todos")
    public ResponseEntity<Void> desarquivarTodosFinalizados(){
        atendimentoService.desarquivarTodosAtendimentos();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<AtendimentoResponseDTO>> findAll() {
        return ResponseEntity.ok(atendimentoService.findAll());
    }

    @GetMapping("/find-by-id/{atendimentoId}")
    public ResponseEntity<AtendimentoResponseDTO> findById(@PathVariable Long atendimentoId){
        return ResponseEntity.ok(atendimentoService.getById(atendimentoId));
    }

    @DeleteMapping("/delete/{atendimentoId}")
    public ResponseEntity<AtendimentoResponseDTO> delete(@PathVariable Long atendimentoId){
        atendimentoService.deleteById(atendimentoId);
        return ResponseEntity.noContent().build();
    }
}
