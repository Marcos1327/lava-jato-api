package com.lava_jato.entities.dto.request;

import java.util.List;

public class AtendimentoDTO {

    private Long clienteId;
    private Long veiculoId;
    private List<ServicoAtendimentoDTO> servicos;
    private List<ProdutoAtendimentoDTO> produtos;
    private PagamentoDTO pagamento;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }

    public List<ServicoAtendimentoDTO> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServicoAtendimentoDTO> servicos) {
        this.servicos = servicos;
    }

    public List<ProdutoAtendimentoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoAtendimentoDTO> produtos) {
        this.produtos = produtos;
    }

    public PagamentoDTO getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoDTO pagamento) {
        this.pagamento = pagamento;
    }
}
