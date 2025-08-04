package com.lava_jato.entities.model.caixa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lava_jato.entities.enums.StatusPagamento;
import com.lava_jato.entities.enums.TipoLancamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_depesa")
public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long despesaId;

    private String descricao;
    @Column(precision = 10, scale = 2)
    private BigDecimal valorDespesa;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDespesa;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipoLancamento = TipoLancamento.SAIDA;

    public Long getDespesaId() {
        return despesaId;
    }

    private void setDespesaId(Long despesaId) {
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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }
}
