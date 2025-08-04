package com.lava_jato.entities.dto.responses;

import com.lava_jato.entities.enums.StatusPagamento;

import java.math.BigDecimal;

public class PagamentoResponseDTO {
    private StatusPagamento statusPagamento;
    private BigDecimal precoTotal;

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }
}
