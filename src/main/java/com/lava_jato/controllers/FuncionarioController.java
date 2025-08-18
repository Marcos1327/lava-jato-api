package com.lava_jato.controllers;

import com.lava_jato.entities.dto.request.FuncionarioDTO;
import com.lava_jato.entities.dto.responses.FuncionarioResponseDTO;
import com.lava_jato.services.FuncionarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/create")
    public ResponseEntity<FuncionarioResponseDTO> create(@RequestBody FuncionarioDTO funcionarioDTO) {
        return ResponseEntity.ok(funcionarioService.create(funcionarioDTO));
    }

    @PatchMapping("/update/{funcionarioId}")
    public ResponseEntity<FuncionarioResponseDTO> update(@PathVariable Long funcionarioId, @RequestBody FuncionarioDTO funcionarioDTO) {
        return ResponseEntity.ok(funcionarioService.update(funcionarioId, funcionarioDTO));
    }

    @GetMapping("/")
    public ResponseEntity<Page<FuncionarioResponseDTO>> findAll(Pageable pageable) {
        return  ResponseEntity.ok(funcionarioService.findAll(pageable));
    }

    @GetMapping("/find-by-id/{funcionarioId}")
    public ResponseEntity<FuncionarioResponseDTO> findById(@PathVariable Long funcionarioId) {
        return ResponseEntity.ok(funcionarioService.getById(funcionarioId));
    }

    @DeleteMapping("/delete/{funcionarioId}")
    public ResponseEntity<FuncionarioResponseDTO> delete(@PathVariable Long funcionarioId) {
        funcionarioService.deleteById(funcionarioId);
        return ResponseEntity.noContent().build();
    }
}
