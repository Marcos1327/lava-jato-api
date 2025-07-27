package com.lava_jato.entities.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "tb_pessoa_juridica")
public class PessoaJuridica extends Cliente {

    private String nomeEmpresa;
    private String nomeResponsavel;

    public PessoaJuridica(){
        super();
    }

    public PessoaJuridica(String email, String telefone, String observacoes, LocalDate dataCriacao, String nomeEmpresa, String nomeResponsavel) {
        super(email,telefone,observacoes, dataCriacao);
        this.nomeEmpresa = nomeEmpresa;
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

}
