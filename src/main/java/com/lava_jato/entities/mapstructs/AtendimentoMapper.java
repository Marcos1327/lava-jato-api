package com.lava_jato.entities.mapstructs;

import com.lava_jato.entities.dto.responses.AtendimentoResponseDTO;
import com.lava_jato.entities.model.Atendimento;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClienteMapper.class})
public interface AtendimentoMapper {

    AtendimentoResponseDTO toResponseDTO(Atendimento atendimento);
}
