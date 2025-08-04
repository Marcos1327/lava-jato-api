package com.lava_jato.entities.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lava_jato.entities.enums.StatusAtendimento;
import com.lava_jato.entities.enums.StatusPagamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_atendimento")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atendimentoId;

    @Enumerated(EnumType.STRING)
    private StatusAtendimento statusAtendimento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @ManyToOne()
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne()
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @OneToMany(mappedBy = "atendimento", cascade = CascadeType.ALL)
    private List<ServicoAtendimento> servicos;

    @OneToMany(mappedBy = "atendimento", cascade = CascadeType.ALL)
    private List<ProdutoAtendimento> produtos;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;

    @Column(precision = 10, scale = 2)
    private BigDecimal precoTotal;


    public Atendimento() {
    }

    public Atendimento(Long atendimentoId, StatusAtendimento statusAtendimento, StatusPagamento statusPagamento, Cliente cliente, Veiculo veiculo, LocalDate dataCriacao,
                       List<ServicoAtendimento> servicos, List<ProdutoAtendimento> produtos, BigDecimal precoTotal) {
        this.atendimentoId = atendimentoId;
        this.statusAtendimento = statusAtendimento;
        this.statusPagamento = statusPagamento;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataCriacao = dataCriacao;
        this.servicos = servicos;
        this.produtos = produtos;
        this.precoTotal = precoTotal;
    }

    public Long getAtendimentoId() {
        return atendimentoId;
    }

    public void setAtendimentoId(Long atendimentoId) {
        this.atendimentoId = atendimentoId;
    }

    public StatusAtendimento getStatusAtendimento() {
        return statusAtendimento;
    }

    public void setStatusAtendimento(StatusAtendimento statusAtendimento) {
        this.statusAtendimento = statusAtendimento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<ServicoAtendimento> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServicoAtendimento> servicos) {
        this.servicos = servicos;
    }

    public List<ProdutoAtendimento> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoAtendimento> produtos) {
        this.produtos = produtos;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }
}
