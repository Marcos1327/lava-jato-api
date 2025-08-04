package com.lava_jato.services;

import com.lava_jato.entities.dto.request.ProdutoDTO;
import com.lava_jato.entities.dto.responses.ProdutoResponseDTO;
import com.lava_jato.entities.mapstructs.ProdutoMapper;
import com.lava_jato.entities.model.Produto;
import com.lava_jato.exceptions.handlers.BusinessException;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.exceptions.handlers.ValidationException;
import com.lava_jato.repositories.ProdutoRepository;
import com.lava_jato.util.Util;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    @Transactional
    public ProdutoResponseDTO createProduto(ProdutoDTO produtoDTO){
        validarCamposObrigatoriosProduto(produtoDTO);
        verificaSeExisteProdutoPeloNome(produtoDTO.getNomeProduto());

        Produto produto = new Produto();

        produto.setNomeProduto(produtoDTO.getNomeProduto());
        produto.setPrecoProduto(produtoDTO.getPrecoProduto());
        produto.setQuantidadeProduto(produtoDTO.getQuantidadeProduto());
        produto.setObservacao(produtoDTO.getObservacao());
        produto.setDataCriacao(LocalDate.now());

        produtoRepository.save(produto);
        ProdutoResponseDTO produtoResponseDTO = produtoMapper.toResponseDTO(produto);
        return produtoResponseDTO;
    }

    @Transactional
    public ProdutoResponseDTO updateProduto(Long produtoId, ProdutoDTO produtoDTO){
        Produto produto = findById(produtoId);

        if(produtoDTO.getNomeProduto() != null && !produtoDTO.getNomeProduto().isEmpty()){
            produto.setNomeProduto(produtoDTO.getNomeProduto());
        }
        if(produtoDTO.getPrecoProduto() != null){
            produto.setPrecoProduto(produtoDTO.getPrecoProduto());
        }
        if(produtoDTO.getQuantidadeProduto() != null){
            produto.setQuantidadeProduto(produto.getQuantidadeProduto() + produtoDTO.getQuantidadeProduto());
        }
        if(produtoDTO.getObservacao() != null && !produtoDTO.getObservacao().isEmpty()){
            produto.setObservacao(produtoDTO.getObservacao());
        }

        produtoRepository.save(produto);
        ProdutoResponseDTO produtoResponseDTO = produtoMapper.toResponseDTO(produto);
        return produtoResponseDTO;
    }

    public List<ProdutoResponseDTO> findAll(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(produtoMapper::toResponseDTO).collect(Collectors.toList());
    }

    public ProdutoResponseDTO getById(Long produtoId){
        Produto produto = findById(produtoId);
        return produtoMapper.toResponseDTO(produto);
    }

    public Produto getProdutoByIdEntity(Long produtoId){
       return findById(produtoId);
    }

    @Transactional
    public void consumirEstoque(Produto produto, int quantidadeSolicitada){

        if(produto.getQuantidadeProduto() < quantidadeSolicitada){
            throw new BusinessException("Estoque insuficiente para o produto: " + produto.getNomeProduto());
        }

        produto.setQuantidadeProduto(produto.getQuantidadeProduto() - quantidadeSolicitada);
        produtoRepository.save(produto);
    }


    @Transactional
    public void deleteById(Long produtoId){
        Produto produto  = findById(produtoId);
        produtoRepository.delete(produto);
    }

    private Produto findById(Long produtoId){
        return produtoRepository.findById(produtoId).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado pelo Id : " + produtoId));
    }
    private void verificaSeExisteProdutoPeloNome(String nomeProduto){
        if(produtoRepository.existsProdutoByNomeProduto(nomeProduto)){
            throw new BusinessException("Já existe um produto com esse nome: " + nomeProduto);
        }
    }
    private void validarCamposObrigatoriosProduto(ProdutoDTO produtoDTO) {
        List<String> camposObrigatorios = new ArrayList<>();

        if (Util.isNullOrEmpty(produtoDTO.getNomeProduto())){
            camposObrigatorios.add("Nome do produto");
        }
        if (Util.isNullOrEmpty(produtoDTO.getPrecoProduto().toString())) {
            camposObrigatorios.add("Preço da Venda");
        }
        if (Util.isNullOrEmpty(produtoDTO.getQuantidadeProduto().toString())) {
            camposObrigatorios.add("Quantidade de Produto");
        }

        if (!camposObrigatorios.isEmpty()) {
            throw new ValidationException("Os seguintes campos são obrigatórios: " + String.join(", ", camposObrigatorios));
        }
    }
}
