package com.lava_jato.entities.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lava_jato.entities.enums.StatusAtendimento;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_atendimento")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long atendimentoId;

    @Enumerated(EnumType.STRING)
    private StatusAtendimento statusAtendimento;

    @ManyToOne()
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne()
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;


    public Atendimento() {
    }

    public Atendimento(Long atendimentoId, StatusAtendimento statusAtendimento, Cliente cliente, Veiculo veiculo, LocalDate dataCriacao) {
        this.atendimentoId = atendimentoId;
        this.statusAtendimento = statusAtendimento;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataCriacao = dataCriacao;
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
}
