package com.lava_jato.entities.dto.responses;

import java.time.LocalDate;

public class AtendimentoResponseDTO {
    private Long atendimentoId;
    private LocalDate dataCriacao;

    private ClienteResumoDTO cliente;
    private VeiculoResumoDTO veiculo;

    public Long getAtendimentoId() {
        return atendimentoId;
    }

    public void setAtendimentoId(Long atendimentoId) {
        this.atendimentoId = atendimentoId;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public ClienteResumoDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResumoDTO cliente) {
        this.cliente = cliente;
    }

    public VeiculoResumoDTO getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(VeiculoResumoDTO veiculo) {
        this.veiculo = veiculo;
    }
}
