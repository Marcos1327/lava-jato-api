package com.lava_jato.services;

import com.lava_jato.entities.dto.request.ProdutoDTO;
import com.lava_jato.entities.dto.responses.ProdutoResponseDTO;
import com.lava_jato.entities.mapstructs.ProdutoMapper;
import com.lava_jato.entities.model.Produto;
import com.lava_jato.exceptions.handlers.BusinessException;
import com.lava_jato.exceptions.handlers.ResourceNotFoundException;
import com.lava_jato.repositories.ProdutoRepository;
import com.lava_jato.util.Util;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
        validarCamposObrigatorios(produtoDTO);
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

    public Page<ProdutoResponseDTO> findAll(Pageable pageable){
        Page<Produto> produtos = produtoRepository.findAll(pageable);
        return produtos.map(produtoMapper::toResponseDTO);
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
    private void validarCamposObrigatorios(ProdutoDTO produtoDTO){
        Map<String, Object> camposObrigatorios = new HashMap<>();
        camposObrigatorios.put("Nome do produto", produtoDTO.getNomeProduto());
        camposObrigatorios.put("Preço da Venda", produtoDTO.getPrecoProduto());
        camposObrigatorios.put("Quantidade de Produto",  produtoDTO.getQuantidadeProduto());

        Util.validarCamposObrigatorios(camposObrigatorios);
    }
}
