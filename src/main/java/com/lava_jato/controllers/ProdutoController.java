package com.lava_jato.controllers;

import com.lava_jato.entities.dto.request.ProdutoDTO;
import com.lava_jato.entities.dto.responses.ProdutoResponseDTO;
import com.lava_jato.services.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProdutoResponseDTO> create(@RequestBody ProdutoDTO produtoDTO){
        return ResponseEntity.ok(produtoService.createProduto(produtoDTO));
    }

    @PatchMapping("/update/{produtoId}")
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable("produtoId") Long produtoId, @RequestBody ProdutoDTO produtoDTO){
        return ResponseEntity.ok(produtoService.updateProduto(produtoId, produtoDTO));
    }

    @GetMapping("/")
    public ResponseEntity<Page<ProdutoResponseDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(produtoService.findAll(pageable));
    }

    @DeleteMapping("/delete/{produtoId}")
    public ResponseEntity<Void> delete(@PathVariable("produtoId") Long produtoId){
        produtoService.deleteById(produtoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long produtoId){
        return ResponseEntity.ok(produtoService.getById(produtoId));
    }
}
