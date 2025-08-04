package com.lava_jato.controllers;

import com.lava_jato.entities.dto.responses.CaixaDTO;
import com.lava_jato.services.CaixaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/caixa")
public class CaixaController {

    private final CaixaService caixaService;

    public CaixaController(CaixaService caixaService) {
        this.caixaService = caixaService;
    }

    @GetMapping("/resumo")
    public ResponseEntity<CaixaDTO> getResumoFinanceiro(){
        return ResponseEntity.ok(caixaService.criarResumoFinanceiro());
    }
}
