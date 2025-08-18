package com.lava_jato.entities.mapstructs;

import com.lava_jato.entities.dto.request.ProdutoAtendimentoDTO;
import com.lava_jato.entities.dto.request.ServicoAtendimentoDTO;
import com.lava_jato.entities.dto.responses.AtendimentoResponseDTO;
import com.lava_jato.entities.dto.responses.PagamentoResponseDTO;
import com.lava_jato.entities.model.Produto;
import com.lava_jato.entities.model.TipoServico;
import com.lava_jato.entities.model.atendimento.Atendimento;
import com.lava_jato.entities.model.atendimento.ProdutoAtendimento;
import com.lava_jato.entities.model.atendimento.ServicoAtendimento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClienteMapper.class, TipoServicoMapper.class, ProdutoMapper.class})
public interface AtendimentoMapper {

    @Mapping(target = "servicos", source = "servicos")
    @Mapping(target = "produtos", source = "produtos")
    @Mapping(target = "pagamento", expression = "java(toPagamentoResponseDTO(atendimento))")
    AtendimentoResponseDTO toResponseDTO(Atendimento atendimento);

    default PagamentoResponseDTO toPagamentoResponseDTO(Atendimento atendimento){
        if(atendimento == null){return null;}

        PagamentoResponseDTO pagamentoResponseDTO = new PagamentoResponseDTO();
        pagamentoResponseDTO.setStatusPagamento(atendimento.getStatusPagamento());
        pagamentoResponseDTO.setPrecoTotal(atendimento.getPrecoTotal());
        return pagamentoResponseDTO;
    }

    @Mapping(target = "tipoServicoId", source = "servico.tipoServicoId")
    ServicoAtendimentoDTO toServicoAtendimentoDTO(ServicoAtendimento servicoAtendimento);

    @Mapping(target = "produtoId", source = "produto.produtoId")
    ProdutoAtendimentoDTO toProdutoAtendimentoDTO(ProdutoAtendimento produtoatendimento);

    default TipoServico mapTipoServico(Long tipoServicoId){
        if(tipoServicoId == null) return null;
        TipoServico tipoServico = new TipoServico();
        tipoServico.setTipoServicoId(tipoServicoId);
        return tipoServico;
    }

    default Produto mapProduto(Long produtoId) {
        if(produtoId == null) return null;
        Produto produto = new Produto();
        produto.setProdutoId(produtoId);
        return produto;
    }


}
