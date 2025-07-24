package com.lava_jato.entities.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_cliente")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;
    private String email;
    private String telefone;
    private String observacoes;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    public Cliente() {}

    public Cliente(String email, String telefone, String observacoes, TipoCliente tipoCliente,  LocalDate dataCriacao) {
        this.email = email;
        this.telefone = telefone;
        this.observacoes = observacoes;
        this.tipoCliente = tipoCliente;
        this.dataCriacao = dataCriacao;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

     public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
     }

     public LocalDate getDataCriacao() {
        return dataCriacao;
     }

     public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
     }

}
