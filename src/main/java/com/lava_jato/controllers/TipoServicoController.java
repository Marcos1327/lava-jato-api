package com.lava_jato.controllers;

import com.lava_jato.entities.dto.request.TipoServicoDTO;
import com.lava_jato.entities.dto.responses.TipoServicoResponseDTO;
import com.lava_jato.services.TipoServicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/servicos")
public class TipoServicoController {

    private final TipoServicoService tipoServicoService;

    public TipoServicoController(TipoServicoService tipoServicoService){
        this.tipoServicoService = tipoServicoService;
    }

    @PostMapping("/create")
    public ResponseEntity<TipoServicoResponseDTO> create(@RequestBody TipoServicoDTO tipoServicoDTO){
        return ResponseEntity.ok(tipoServicoService.create(tipoServicoDTO));
    }

    @PatchMapping("update/{tipoServicoId}")
    public ResponseEntity<TipoServicoResponseDTO> update(@PathVariable Long tipoServicoId, @RequestBody TipoServicoDTO tipoServicoDTO){
        return ResponseEntity.ok(tipoServicoService.update(tipoServicoId,  tipoServicoDTO));
    }

    @GetMapping("/")
    public ResponseEntity<Page<TipoServicoResponseDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(tipoServicoService.findAll(pageable));
    }

    @GetMapping("/find-by-id/{tipoServicoId}")
    public ResponseEntity<TipoServicoResponseDTO> findById(@PathVariable Long tipoServicoId){
        return ResponseEntity.ok(tipoServicoService.getById(tipoServicoId));
    }

    @DeleteMapping("delete/{tipoServicoId}")
    public ResponseEntity<TipoServicoResponseDTO> delete(@PathVariable Long tipoServicoId){
        tipoServicoService.deleteById(tipoServicoId);
        return ResponseEntity.noContent().build();
    }
}
