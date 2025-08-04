package com.lava_jato.entities.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusPagamento {

    A_PAGAR("A pagar"),
    PAGO("Pago");

    private final String customStatusPagamento;

    private StatusPagamento(String customStatusPagamento) {
        this.customStatusPagamento = customStatusPagamento;
    }

    @JsonValue
    public String getCustomStatusPagamento() {
        return customStatusPagamento;
    }
}
