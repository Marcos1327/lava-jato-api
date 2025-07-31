package com.lava_jato.entities.mapstructs;

import com.lava_jato.entities.dto.responses.TipoServicoResponseDTO;
import com.lava_jato.entities.model.TipoServico;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TipoServicoMapper {

    TipoServicoResponseDTO toResponseDTO (TipoServico tipoServico);
}
