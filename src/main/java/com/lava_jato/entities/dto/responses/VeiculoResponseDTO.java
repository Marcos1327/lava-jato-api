package com.lava_jato.entities.dto.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class VeiculoResponseDTO {
    private Long veiculoId;
    private String modelo;
    private String placa;
    private String observacao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;
    private ClienteResumoDTO proprietario;

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public ClienteResumoDTO getProprietario() {
        return proprietario;
    }

    public void setProprietario(ClienteResumoDTO proprietario) {
        this.proprietario = proprietario;
    }
}
