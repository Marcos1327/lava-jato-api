package com.lava_jato.entities.dto.request;

import java.math.BigDecimal;

public class ProdutoAtendimentoDTO {
    private Long produtoAtendimentoId;
    private String nome;
    private BigDecimal preco;
    private Integer quantidadeProduto;

    public ProdutoAtendimentoDTO() {
    }

    public ProdutoAtendimentoDTO(Long produtoAtendimentoId, String nome, Integer quantidadeProduto, BigDecimal preco) {
        this.produtoAtendimentoId = produtoAtendimentoId;
        this.nome = nome;
        this.quantidadeProduto = quantidadeProduto;
        this.preco = preco;
    }

    public Long getProdutoAtendimentoId() {
        return produtoAtendimentoId;
    }

    public void setProdutoAtendimentoId(Long produtoAtendimentoId) {
        this.produtoAtendimentoId = produtoAtendimentoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Integer quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
