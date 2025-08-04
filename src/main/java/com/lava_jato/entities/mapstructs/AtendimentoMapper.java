package com.lava_jato.entities.mapstructs;

import com.lava_jato.entities.dto.request.PagamentoDTO;
import com.lava_jato.entities.dto.responses.AtendimentoResponseDTO;
import com.lava_jato.entities.dto.responses.PagamentoResponseDTO;
import com.lava_jato.entities.model.Atendimento;
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

}
