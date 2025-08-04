package com.lava_jato.entities.dto.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lava_jato.entities.enums.StatusPagamento;
import com.lava_jato.entities.enums.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DespesaResponseDTO {
    private Long despesaId;
    private String descricao;
    private BigDecimal valorDespesa;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDespesa;
    private StatusPagamento statusPagamento;
    private TipoLancamento tipoLancamento;

    public Long getDespesaId() {
        return despesaId;
    }

    public void setDespesaId(Long despesaId) {
        this.despesaId = despesaId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorDespesa() {
        return valorDespesa;
    }

    public void setValorDespesa(BigDecimal valorDespesa) {
        this.valorDespesa = valorDespesa;
    }

    public LocalDate getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(LocalDate dataDespesa) {
        this.dataDespesa = dataDespesa;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }
}
