package com.lava_jato.entities.model.atendimento;

import com.lava_jato.entities.model.Produto;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_produto_atendimento")
public class ProdutoAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produtoAtendimentoId;
    private String nome;
    private BigDecimal preco;
    private Integer quantidadeProduto;

    @ManyToOne()
    @JoinColumn(name = "antendimento_id")
    private Atendimento atendimento;

    @ManyToOne()
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public ProdutoAtendimento() {
    }

    public ProdutoAtendimento(Long produtoAtendimentoId, String nome, Integer quantidadeProduto, BigDecimal preco, Atendimento atendimento, Produto produto) {
        this.produtoAtendimentoId = produtoAtendimentoId;
        this.nome = nome;
        this.quantidadeProduto = quantidadeProduto;
        this.preco = preco;
        this.atendimento = atendimento;
        this.produto = produto;
    }

    public Long getProdutoAtendimentoId() {
        return produtoAtendimentoId;
    }

    public void setProdutoAtendimentoId(Long produtoId) {
        this.produtoAtendimentoId = produtoId;
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

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
