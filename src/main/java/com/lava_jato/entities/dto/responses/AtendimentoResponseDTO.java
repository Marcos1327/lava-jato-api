package com.lava_jato.entities.dto.responses;

import com.lava_jato.entities.dto.request.PagamentoDTO;
import com.lava_jato.entities.dto.request.ProdutoAtendimentoDTO;
import com.lava_jato.entities.dto.request.ServicoAtendimentoDTO;
import com.lava_jato.entities.enums.StatusAtendimento;
import com.lava_jato.entities.enums.TipoLancamento;

import java.time.LocalDate;
import java.util.List;

public class AtendimentoResponseDTO {
    private Long atendimentoId;
    private StatusAtendimento statusAtendimento;
    private TipoLancamento tipoLancamento;
    private Boolean arquivado;
    private PagamentoResponseDTO pagamento;
    private LocalDate dataCriacao;
    private ClienteResumoDTO cliente;
    private VeiculoResumoDTO veiculo;
    private List<ServicoAtendimentoDTO> servicos;
    private List<ProdutoAtendimentoDTO> produtos;

    public Long getAtendimentoId() {
        return atendimentoId;
    }

    public void setAtendimentoId(Long atendimentoId) {
        this.atendimentoId = atendimentoId;
    }

    public StatusAtendimento getStatusAtendimento() {
        return statusAtendimento;
    }

    public void setStatusAtendimento(StatusAtendimento statusAtendimento) {
        this.statusAtendimento = statusAtendimento;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public ClienteResumoDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResumoDTO cliente) {
        this.cliente = cliente;
    }

    public VeiculoResumoDTO getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(VeiculoResumoDTO veiculo) {
        this.veiculo = veiculo;
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

    public PagamentoResponseDTO getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoResponseDTO pagamento) {
        this.pagamento = pagamento;
    }

    public Boolean getArquivado() {
        return arquivado;
    }

    public void setArquivado(Boolean arquivado) {
        this.arquivado = arquivado;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }
}
