package com.lava_jato.services;

import com.lava_jato.entities.dto.responses.CaixaDTO;
import com.lava_jato.entities.enums.StatusPagamento;
import com.lava_jato.entities.model.atendimento.Atendimento;
import com.lava_jato.entities.model.caixa.Despesa;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CaixaService {

    private final AtendimentoService atendimentoService;
    private final DespesaService despesaService;

    public CaixaService(AtendimentoService atendimentoService, DespesaService despesaService) {
        this.atendimentoService = atendimentoService;
        this.despesaService = despesaService;
    }

    public CaixaDTO criarResumoFinanceiro(){
        BigDecimal totalEntradas = totalAtendimentosPagos();
        BigDecimal totalAReceber = totalAtendimentosAPagar();
        BigDecimal totalSaidas = totalDespesasPagas();
        BigDecimal totalAPagar = totalDespesasAPagar();
        BigDecimal saldoAtual = totalSaldoAtual(totalEntradas, totalSaidas);
        BigDecimal saldoProjetado = totalSaldoProjetado(saldoAtual, totalAReceber, totalAPagar);

        CaixaDTO caixaDTO = new CaixaDTO();
        caixaDTO.setTotalEntradas(totalEntradas);
        caixaDTO.setTotalSaidas(totalSaidas);
        caixaDTO.setSaldoAtual(saldoAtual);
        caixaDTO.setTotalAReceber(totalAReceber);
        caixaDTO.setTotalAPagar(totalAPagar);
        caixaDTO.setSaldoProjetado(saldoProjetado);

        return caixaDTO;
    }

    private BigDecimal totalSaldoProjetado(BigDecimal saldoAtual, BigDecimal totalAReceber, BigDecimal totalAPagar) {
        return saldoAtual.add(totalAReceber).subtract(totalAPagar);
    }

    private BigDecimal totalSaldoAtual(BigDecimal totalEntradas, BigDecimal totalSaidas) {
        return totalEntradas.subtract(totalSaidas);
    }

    private BigDecimal totalAtendimentosPagos(){
        BigDecimal totalAtendimentoPago = BigDecimal.ZERO;
        List<Atendimento> atendimentosPagos = atendimentoService.findAllAtendimentoByStatusPagamento(StatusPagamento.PAGO);
        for (Atendimento atendimento : atendimentosPagos){
            if(atendimento.getPrecoTotal() != null) {
                totalAtendimentoPago = totalAtendimentoPago.add(atendimento.getPrecoTotal());
            }
        }
        return totalAtendimentoPago;
    }

    private BigDecimal totalAtendimentosAPagar(){
        BigDecimal totalAtendimentoAPagar = BigDecimal.ZERO;
        List<Atendimento> atendimentosAReceber = atendimentoService.findAllAtendimentoByStatusPagamento(StatusPagamento.A_PAGAR);

        for (Atendimento atendimento : atendimentosAReceber){
            if(atendimento.getPrecoTotal() != null) {
                totalAtendimentoAPagar = totalAtendimentoAPagar.add(atendimento.getPrecoTotal());
            }
        }
        return totalAtendimentoAPagar;
    }

    private BigDecimal totalDespesasPagas(){
        BigDecimal totalDespesasPagas = BigDecimal.ZERO;
        List<Despesa> despesasPagas = despesaService.findAllDespesaByStatusPagamento(StatusPagamento.PAGO);

        for (Despesa despesa : despesasPagas){
            if(despesa.getValorDespesa() != null) {
                totalDespesasPagas = totalDespesasPagas.add(despesa.getValorDespesa());
            }
        }
        return totalDespesasPagas;
    }

    private BigDecimal totalDespesasAPagar(){
        BigDecimal totalDespesasAPagar = BigDecimal.ZERO;
        List<Despesa> despesasAPagar = despesaService.findAllDespesaByStatusPagamento(StatusPagamento.A_PAGAR);
        for (Despesa despesa : despesasAPagar){
            if(despesa.getValorDespesa() != null) {
                totalDespesasAPagar = totalDespesasAPagar.add(despesa.getValorDespesa());
            }
        }
        return totalDespesasAPagar;
    }

}
