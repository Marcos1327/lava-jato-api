package com.lava_jato.entities.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long funcionarioId;
    private String nome;
    private String cargo;
    private String telefone;
    private String observacoes;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;
    private Boolean isTemporario;
    private String vinculo;

    public Funcionario() {}

    public Funcionario(String nome, String cargo, String telefone, String observacoes, LocalDate dataCriacao, Boolean isTemporario,  String vinculo) {
        this.nome = nome;
        this.cargo = cargo;
        this.telefone = telefone;
        this.observacoes = observacoes;
        this.isTemporario = isTemporario;
        this.dataCriacao = dataCriacao;
        this.vinculo = vinculo;
    }

    public Long getFuncionarioId() {
        return funcionarioId;
    }
    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getIsTemporario() {
        return isTemporario;
    }

    public void setIsTemporario(Boolean temporario) {
        isTemporario = temporario;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }
}
