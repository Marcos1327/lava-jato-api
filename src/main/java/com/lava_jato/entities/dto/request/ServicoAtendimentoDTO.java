package com.lava_jato.entities.dto.request;

import java.math.BigDecimal;

public class ServicoAtendimentoDTO {
    private Long tipoServicoId;
    private String nome;
    private BigDecimal preco;
    private String observacao;

    public ServicoAtendimentoDTO() {
    }

    public ServicoAtendimentoDTO(Long tipoServicoId,BigDecimal preco, String nome, String observacao) {
        this.tipoServicoId = tipoServicoId;
        this.preco = preco;
        this.nome = nome;
        this.observacao = observacao;
    }

    public Long getTipoServicoId() {
        return tipoServicoId;
    }

    public void setTipoServicoId(Long tipoServicoId) {
        this.tipoServicoId = tipoServicoId;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
