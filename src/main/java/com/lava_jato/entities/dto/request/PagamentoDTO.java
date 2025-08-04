package com.lava_jato.entities.dto.request;

import com.lava_jato.entities.enums.StatusPagamento;

public class PagamentoDTO {
    private StatusPagamento statusPagamento;

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
