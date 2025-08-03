package com.lava_jato.entities.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ServicoAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long servicoAtendimentoId;

    private String observacao;

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

    public ServicoAtendimento(Long servicoAtendimentoId, String observacao, BigDecimal preco, Atendimento atendimento, TipoServico servico) {
        this.servicoAtendimentoId = servicoAtendimentoId;
        this.observacao = observacao;
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
