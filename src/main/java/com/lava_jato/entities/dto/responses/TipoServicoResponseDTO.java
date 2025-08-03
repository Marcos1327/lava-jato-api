package com.lava_jato.entities.dto.responses;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TipoServicoResponseDTO {
    private Long tipoServicoId;
    private String nomeServico;
    private BigDecimal precoServico;
    private String observacao;
    private LocalDate dataCriacao;

    public Long getTipoServicoId() {
        return tipoServicoId;
    }

    public void setTipoServicoId(Long tipoServicoId) {
        this.tipoServicoId = tipoServicoId;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public BigDecimal getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(BigDecimal precoServico) {
        this.precoServico = precoServico;
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
}
