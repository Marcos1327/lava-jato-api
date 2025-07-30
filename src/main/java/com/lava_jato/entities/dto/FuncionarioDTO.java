package com.lava_jato.entities.dto;

public class FuncionarioDTO {
    private String nome;
    private String cargo;
    private String telefone;
    private String observacoes;
    private Boolean isTemporario;

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

    public Boolean getIsTemporario() {
        return isTemporario;
    }

    public void setIsTemporario(Boolean temporario) {
        isTemporario = temporario;
    }
}
