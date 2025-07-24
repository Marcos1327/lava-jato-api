package com.lava_jato.entities.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_tipo_servico")
public class TipoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoServicoId;
    private String nomeServico;
    private Double precoServico;
    private String descricaoServico;
    private LocalDate dataServico;

    public TipoServico() {}

    public TipoServico(String nomeServico,  Double precoServico, String descricaoServico, LocalDate dataServico ) {
         this.nomeServico = nomeServico;
         this.precoServico = precoServico;
         this.descricaoServico = descricaoServico;
         this.dataServico = dataServico;
    }

    public Long getTipoServicoId() {
        return tipoServicoId;
    }

    public void setTipoServicoId(Long tipoServicoId) {
        this.tipoServicoId = tipoServicoId;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Double getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(Double precoServico) {
        this.precoServico = precoServico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public LocalDate getDataServico() {
        return dataServico;
    }

    public void setDataServico(LocalDate dataServico) {
        this.dataServico = dataServico;
    }
}
