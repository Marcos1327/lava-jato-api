package com.lava_jato.services;

import com.lava_jato.entities.dto.request.AtendimentoDTO;
import com.lava_jato.entities.dto.request.ProdutoAtendimentoDTO;
import com.lava_jato.entities.dto.request.ServicoAtendimentoDTO;
import com.lava_jato.entities.dto.responses.AtendimentoResponseDTO;
import com.lava_jato.entities.enums.StatusAtendimento;
import com.lava_jato.entities.enums.StatusPagamento;
import com.lava_jato.entities.mapstructs.AtendimentoMapper;
import com.lava_jato.entities.model.Cliente;
import com.lava_jato.entities.model.Produto;
import com.lava_jato.entities.model.TipoServico;
import com.lava_jato.entities.model.Veiculo;
import com.lava_jato.entities.model.atendimento.Atendimento;
import com.lava_jato.entities.model.atendimento.ProdutoAtendimento;
import com.lava_jato.entities.model.atendimento.ServicoAtendimento;
import com.lava_jato.exceptions.handlers.BusinessException;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.repositories.AtendimentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final ClienteService clienteService;
    private final VeiculoService veiculoService;
    private final TipoServicoService tipoServicoService;
    private final ProdutoService produtoService;
    private final AtendimentoMapper atendimentoMapper;

    public AtendimentoService(AtendimentoRepository atendimentoRepository, ClienteService clienteService, VeiculoService veiculoService,
                              AtendimentoMapper atendimentoMapper, TipoServicoService tipoServicoService, ProdutoService produtoService) {

        this.atendimentoRepository = atendimentoRepository;
        this.clienteService = clienteService;
        this.veiculoService = veiculoService;
        this.tipoServicoService = tipoServicoService;
        this.produtoService = produtoService;
        this.atendimentoMapper = atendimentoMapper;
    }

    @Transactional
    public AtendimentoResponseDTO create(AtendimentoDTO atendimentoDTO) {
        Atendimento atendimento = new Atendimento();

        Cliente cliente = clienteService.getClienteByIdEntity(atendimentoDTO.getClienteId());
        Veiculo veiculo = veiculoService.getVeiculoByIdEntity(atendimentoDTO.getVeiculoId());

        atendimento.setCliente(cliente);
        atendimento.setVeiculo(veiculo);
        atendimento.setDataCriacao(LocalDate.now());

        List<ServicoAtendimento> servicosAtendimento = criarServicosAtendimento(atendimentoDTO, atendimento);
        atendimento.setServicos(servicosAtendimento);

        List<ProdutoAtendimento> produtosAtendimento = criarProdutoAtendimento(atendimentoDTO, atendimento);
        atendimento.setProdutos(produtosAtendimento);

        BigDecimal precoTotal = calcularPrecoTotal(atendimentoDTO);
        atendimento.setPrecoTotal(precoTotal);
        atendimento.setStatusPagamento(atendimentoDTO.getPagamento().getStatusPagamento());

        atendimentoRepository.save(atendimento);
        AtendimentoResponseDTO atendimentoResponseDTO = atendimentoMapper.toResponseDTO(atendimento);

        return  atendimentoResponseDTO;
    }

    @Transactional
    public AtendimentoResponseDTO update(Long atendimentoId, AtendimentoDTO atendimentoDTO){
        Atendimento atendimento = findById(atendimentoId);

        if(atendimentoDTO.getClienteId() != null) {
            Cliente cliente = clienteService.getClienteByIdEntity(atendimentoDTO.getClienteId());
            atendimento.setCliente(cliente);
        }
        if(atendimentoDTO.getVeiculoId() != null) {
            Veiculo veiculo = veiculoService.getVeiculoByIdEntity(atendimentoDTO.getVeiculoId());
            atendimento.setVeiculo(veiculo);
        }
        if(atendimentoDTO.getStatusAtendimento() != null) {
            atendimento.setStatusAtendimento(atendimentoDTO.getStatusAtendimento());
        }
        if(atendimentoDTO.getPagamento() != null) {
            atendimento.setStatusPagamento(atendimentoDTO.getPagamento().getStatusPagamento());
        }
        if(atendimentoDTO.getServicos() != null &&  !atendimentoDTO.getServicos().isEmpty()) {
            atendimento.getServicos().clear();
            List<ServicoAtendimento> servicosAtualizados = criarServicosAtendimento(atendimentoDTO, atendimento);
            atendimento.setServicos(servicosAtualizados);
        }
        if(atendimentoDTO.getProdutos() != null &&  !atendimentoDTO.getProdutos().isEmpty()) {
            atendimento.getProdutos().clear();
            List<ProdutoAtendimento> produtosAtualizados =  criarProdutoAtendimento(atendimentoDTO, atendimento);
            atendimento.setProdutos(produtosAtualizados);
        }
        if(atendimentoDTO.getProdutos() != null &&  !atendimentoDTO.getProdutos().isEmpty() || (atendimentoDTO.getServicos() != null && !atendimentoDTO.getServicos().isEmpty())) {
           BigDecimal precoTotalAtualizado = calcularPrecoTotal(atendimentoDTO);
           atendimento.setPrecoTotal(precoTotalAtualizado);
        }

        atendimentoRepository.save(atendimento);
        return atendimentoMapper.toResponseDTO(atendimento);
    }

    public AtendimentoResponseDTO arquivarAtendimento(Long atendimentoId){
        Atendimento atendimento = findById(atendimentoId);

        if(!atendimento.getStatusAtendimento().equals(StatusAtendimento.FINALIZADO) && !atendimento.getStatusPagamento().equals(StatusPagamento.PAGO)) {
            throw new BusinessException("Só é possivel arquivar atendimentos finalizados e pagos .");
        }
        atendimento.setArquivado(true);
        atendimentoRepository.save(atendimento);

        return atendimentoMapper.toResponseDTO(atendimento);
    }

    public void arquivarTodosAtendimentosFinalizados(){
        List<Atendimento> atendimentosFinalizados = atendimentoRepository.findAllByStatusAtendimentoAndArquivadoFalseAndStatusPagamento(StatusAtendimento.FINALIZADO, StatusPagamento.PAGO);

        if(atendimentosFinalizados.isEmpty()){
            throw new BusinessException("Não há atendimentos a ser arquivados.");
        }

        for(Atendimento atendimento : atendimentosFinalizados){
            atendimento.setArquivado(true);
        }
        atendimentoRepository.saveAll(atendimentosFinalizados);
    }

    public void desarquivarAtendimento(Long atendimentoId){
        Atendimento atendimento = findById(atendimentoId);

        if(atendimento.getArquivado()){
            atendimento.setArquivado(false);
        }
        atendimentoRepository.save(atendimento);
        atendimentoMapper.toResponseDTO(atendimento);
    }

    public void desarquivarTodosAtendimentos(){
        List<Atendimento> arquivados = atendimentoRepository.findAllByArquivadoTrue();

        for(Atendimento atendimento : arquivados){
            atendimento.setArquivado(false);
        }
        atendimentoRepository.saveAll(arquivados);
    }

    public Page<AtendimentoResponseDTO> findAllAtendimentosArquivados(Pageable pageable){
        Page<Atendimento> atendimentosArquivados = atendimentoRepository.findAllByArquivadoTrue(pageable);
        return atendimentosArquivados.map(atendimentoMapper::toResponseDTO);
    }

    public Page<AtendimentoResponseDTO> findAll(Pageable pageable) {
        Page<Atendimento> atendimentos = atendimentoRepository.findAll(pageable);
        return atendimentos.map(atendimentoMapper::toResponseDTO);
    }

    public AtendimentoResponseDTO getById(Long atendimentoId) {
        Atendimento atendimento = findById(atendimentoId);
        return atendimentoMapper.toResponseDTO(atendimento);
    }

    public void deleteById(Long atendimentoId) {
        Atendimento atendimento = findById(atendimentoId);
        if(atendimento.getArquivado().equals(Boolean.TRUE)){
            throw new BusinessException("Não é possivel deletar um atendimento arquivado.");
        }
        atendimentoRepository.delete(atendimento);
    }

    public List<Atendimento> findAllAtendimentoByStatusPagamento(StatusPagamento statusPagamento){
        return atendimentoRepository.findByStatusPagamento(statusPagamento);
    }

    private Atendimento findById(Long atendimentoId){
        return atendimentoRepository.findById(atendimentoId).orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado pelo id: " + atendimentoId));
    }

    private List<ServicoAtendimento> criarServicosAtendimento(AtendimentoDTO atendimentoDTO, Atendimento atendimento) {
        List<ServicoAtendimento> servicos = new ArrayList<>();

        for (ServicoAtendimentoDTO servicoDTO : atendimentoDTO.getServicos()) {

            TipoServico tipoServico = tipoServicoService.getTipoServicoByIdEntity(servicoDTO.getTipoServicoId());

            ServicoAtendimento servicoAtendimento = new ServicoAtendimento();
            servicoAtendimento.setServico(tipoServico);
            servicoAtendimento.setNome(tipoServico.getNomeServico());
            servicoAtendimento.setPreco(tipoServico.getPrecoServico());
            servicoAtendimento.setObservacao(servicoDTO.getObservacao());
            servicoAtendimento.setAtendimento(atendimento);

            servicos.add(servicoAtendimento);
        }

        return servicos;
    }

    private List<ProdutoAtendimento> criarProdutoAtendimento(AtendimentoDTO atendimentoDTO, Atendimento atendimento) {
        List<ProdutoAtendimento> produtos = new ArrayList<>();

        for(ProdutoAtendimentoDTO produtoAtendimentoDTO : atendimentoDTO.getProdutos()){
            Produto produto = produtoService.getProdutoByIdEntity(produtoAtendimentoDTO.getProdutoId());
            produtoService.consumirEstoque(produto, produtoAtendimentoDTO.getQuantidadeProduto());

            ProdutoAtendimento produtoAtendimento = new ProdutoAtendimento();
            produtoAtendimento.setProduto(produto);
            produtoAtendimento.setNome(produto.getNomeProduto());
            produtoAtendimento.setPreco(produto.getPrecoProduto());
            produtoAtendimento.setQuantidadeProduto(produtoAtendimentoDTO.getQuantidadeProduto());
            produtoAtendimento.setAtendimento(atendimento);

            produtos.add(produtoAtendimento);
        }

        return produtos;
    }

    private BigDecimal calcularPrecoTotal(AtendimentoDTO atendimentoDTO) {
        return calcularTotalServicos(atendimentoDTO).add(calcularTotalProdutos(atendimentoDTO));
    }

    private BigDecimal calcularTotalServicos(AtendimentoDTO atendimentoDTO){
        BigDecimal total = BigDecimal.ZERO;

        for(ServicoAtendimentoDTO servicoAtendimentoDTO : atendimentoDTO.getServicos()){
            TipoServico servico = tipoServicoService.getTipoServicoByIdEntity(servicoAtendimentoDTO.getTipoServicoId());
            total = total.add(servico.getPrecoServico());
        }
        return total;
    }

    private BigDecimal calcularTotalProdutos(AtendimentoDTO atendimentoDTO){
        BigDecimal total = BigDecimal.ZERO;

        for(ProdutoAtendimentoDTO produtoAtendimentoDTO : atendimentoDTO.getProdutos()){
            Produto produto = produtoService.getProdutoByIdEntity(produtoAtendimentoDTO.getProdutoId());
            BigDecimal subtotal = produto.getPrecoProduto().multiply(BigDecimal.valueOf(produtoAtendimentoDTO.getQuantidadeProduto()));
            total = total.add(subtotal);
        }
        return total;
    }
}
