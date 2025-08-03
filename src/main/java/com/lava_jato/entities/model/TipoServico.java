package com.lava_jato.entities.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_tipo_servico")
public class TipoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoServicoId;
    private String nomeServico;
    @Column(precision = 10, scale = 2)
    private BigDecimal precoServico;
    private String observacao;
    private LocalDate dataCriacao;

    public TipoServico() {}

    public TipoServico(String nomeServico, BigDecimal precoServico, String observacao, LocalDate dataCriacao ) {
         this.nomeServico = nomeServico;
         this.precoServico = precoServico;
         this.observacao = observacao;
         this.dataCriacao = dataCriacao;
    }

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
