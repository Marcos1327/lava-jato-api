package com.lava_jato.controllers;

import com.lava_jato.entities.dto.ProdutoDTO;
import com.lava_jato.entities.dto.responses.ProdutoResponseDTO;
import com.lava_jato.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<ProdutoResponseDTO>> findAll(){
        return ResponseEntity.ok(produtoService.findAll());
    }

    @DeleteMapping("/delete/{produtoId}")
    public ResponseEntity<Void> delete(@PathVariable("produtoId") Long produtoId){
        produtoService.deleteById(produtoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find-by-id/{produtoId}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long produtoId){
        return ResponseEntity.ok(produtoService.getById(produtoId));
    }
}
