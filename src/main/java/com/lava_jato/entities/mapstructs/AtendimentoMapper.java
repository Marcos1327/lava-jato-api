package com.lava_jato.entities.mapstructs;

import com.lava_jato.entities.dto.responses.AtendimentoResponseDTO;
import com.lava_jato.entities.model.Atendimento;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClienteMapper.class, TipoServicoMapper.class})
public interface AtendimentoMapper {

    @Mapping(target = "servicoAtendimentos", source = "servicoAtendimentos")
    AtendimentoResponseDTO toResponseDTO(Atendimento atendimento);

}
