package com.lava_jato.entities.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "tb_pessoa_fisica")
public class PessoaFisica extends Cliente{

    private String nome;

    public PessoaFisica() {
        super();
    }

    public PessoaFisica(String nome, String email,  String telefone, String observacoes,  LocalDate dataCriacao){
       super(email, telefone, observacoes, TipoCliente.PESSOA_FISICA,  dataCriacao);
       this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
