package com.lava_jato.entities.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
//    private Cliente cliente;

    public Veiculo() {
    }

    public Veiculo(Long veiculoId, String modelo, String placa, String observacao,  LocalDate dataCriacao) {
         this.veiculoId = veiculoId;
         this.modelo = modelo;
         this.placa = placa;
         this.observacao = observacao;
         this.dataCriacao = dataCriacao;
    }

    public Long getVeiculoId() {
        return veiculoId;
    }
    public void setVeiculoId(Long veiculoId) {
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
}
