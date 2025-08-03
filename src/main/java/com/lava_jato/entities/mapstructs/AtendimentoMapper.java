package com.lava_jato.entities.mapstructs;

import com.lava_jato.entities.dto.responses.AtendimentoResponseDTO;
import com.lava_jato.entities.model.Atendimento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClienteMapper.class, TipoServicoMapper.class, ProdutoMapper.class})
public interface AtendimentoMapper {

    @Mapping(target = "servicos", source = "servicos")
    @Mapping(target = "produtos", source = "produtos")
    AtendimentoResponseDTO toResponseDTO(Atendimento atendimento);

}
