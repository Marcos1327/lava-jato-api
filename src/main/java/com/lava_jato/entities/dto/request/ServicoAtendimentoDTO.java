package com.lava_jato.entities.dto.request;

import java.math.BigDecimal;

public class ServicoAtendimentoDTO {
    private Long servicoAtendimentoId;
    private String nome;
    private BigDecimal preco;
    private String observacao;

    public ServicoAtendimentoDTO() {
    }

    public ServicoAtendimentoDTO(Long servicoAtendimentoId,BigDecimal preco, String nome, String observacao) {
        this.servicoAtendimentoId = servicoAtendimentoId;
        this.preco = preco;
        this.nome = nome;
        this.observacao = observacao;
    }

    public Long getServicoAtendimentoId() {
        return servicoAtendimentoId;
    }

    public void setServicoAtendimentoId(Long servicoAtendimentoId) {
        this.servicoAtendimentoId = servicoAtendimentoId;
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
