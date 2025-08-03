package com.lava_jato.entities.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_servico_atendimento")
public class ServicoAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long servicoAtendimentoId;

    private String observacao;

    private String nome;

    @Column(precision = 10, scale = 2)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "atendimento_id")
    private Atendimento atendimento;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private TipoServico servico;


    public ServicoAtendimento() {
    }

    public ServicoAtendimento(Long servicoAtendimentoId, String observacao, String nome, BigDecimal preco, Atendimento atendimento, TipoServico servico) {
        this.servicoAtendimentoId = servicoAtendimentoId;
        this.observacao = observacao;
        this.nome = nome;
        this.preco = preco;
        this.atendimento = atendimento;
        this.servico = servico;
    }

    public Long getServicoAtendimentoId() {
        return servicoAtendimentoId;
    }

    public void setServicoAtendimentoId(Long servicoAtendimentoId) {
        this.servicoAtendimentoId = servicoAtendimentoId;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public TipoServico getServico() {
        return servico;
    }

    public void setServico(TipoServico servico) {
        this.servico = servico;
    }
}
