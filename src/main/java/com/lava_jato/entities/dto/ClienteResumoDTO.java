package com.lava_jato.entities.dto;

public class ClienteResumoDTO {
    private Long clienteId;
    private String nome;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
