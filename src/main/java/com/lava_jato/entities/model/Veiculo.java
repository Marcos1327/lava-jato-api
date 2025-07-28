package com.lava_jato.entities.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long veiculoId;
    private String modelo;
    private String placa;
    private String observacao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente proprietario;

    public Veiculo() {
    }

    public Veiculo(Long veiculoId, String modelo, String placa, String observacao,  LocalDate dataCriacao,  Cliente proprietario) {
         this.veiculoId = veiculoId;
         this.modelo = modelo;
         this.placa = placa;
         this.observacao = observacao;
         this.dataCriacao = dataCriacao;
         this.proprietario = proprietario;
    }

    public Long getVeiculoId() {
        return veiculoId;
    }
    private void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getPlaca() {
         return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Cliente getProprietario() {
        return proprietario;
    }
    public void setProprietario(Cliente proprietario) {
        this.proprietario = proprietario;
    }

}
