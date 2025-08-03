package com.lava_jato.entities.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusAtendimento {
    NA_FILA("Na Fila"),
    EM_ANDAMENTO("Em Atendimento"),
    FINALIZADO("Finalizado");

    private final String customStatusAtendimento;

    StatusAtendimento(String customStatusAtendimento) {
        this.customStatusAtendimento = customStatusAtendimento;
    }

    @JsonValue
    public String getCustomStatusAtendimento() {
        return customStatusAtendimento;
    }
}
