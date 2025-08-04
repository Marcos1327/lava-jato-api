package com.lava_jato.entities.dto.responses;

import java.math.BigDecimal;

public class CaixaDTO {
    private BigDecimal totalEntradas;
    private BigDecimal totalSaidas;
    private BigDecimal saldoAtual;
    private BigDecimal totalAReceber;
    private BigDecimal totalAPagar;
    private BigDecimal saldoProjetado;

    public BigDecimal getTotalEntradas() {
        return totalEntradas;
    }

    public void setTotalEntradas(BigDecimal totalEntradas) {
        this.totalEntradas = totalEntradas;
    }

    public BigDecimal getTotalSaidas() {
        return totalSaidas;
    }

    public void setTotalSaidas(BigDecimal totalSaidas) {
        this.totalSaidas = totalSaidas;
    }

    public BigDecimal getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(BigDecimal saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public BigDecimal getTotalAReceber() {
        return totalAReceber;
    }

    public void setTotalAReceber(BigDecimal totalAReceber) {
        this.totalAReceber = totalAReceber;
    }

    public BigDecimal getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(BigDecimal totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public BigDecimal getSaldoProjetado() {
        return saldoProjetado;
    }

    public void setSaldoProjetado(BigDecimal saldoProjetado) {
        this.saldoProjetado = saldoProjetado;
    }
}
