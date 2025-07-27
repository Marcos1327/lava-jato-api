package com.lava_jato.entities.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Veiculo> veiculos;

    public Cliente() {}

    public Cliente(String email, String telefone, String observacoes,  LocalDate dataCriacao) {
        this.email = email;
        this.telefone = telefone;
        this.observacoes = observacoes;
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

     public LocalDate getDataCriacao() {
        return dataCriacao;
     }

     public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
     }

     public List<Veiculo> getVeiculos() {
        return veiculos;
     }

     public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
     }


}
