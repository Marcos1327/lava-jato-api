package com.lava_jato.entities.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String nomeProduto;
    private Integer quantidadeProduto;
    private Double precoProduto;
    private String observacao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    public Produto() {}

    public Produto(String nomeProduto, Integer quantidadeProduto, Double precoProduto, String observacao) {
        this.nomeProduto = nomeProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.precoProduto = precoProduto;
        this.observacao = observacao;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Integer quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }
    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

     public LocalDate getDataCadastro() {
        return dataCadastro;
     }

     public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
     }

}
