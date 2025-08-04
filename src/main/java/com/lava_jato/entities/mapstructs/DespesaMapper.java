package com.lava_jato.entities.mapstructs;

import com.lava_jato.entities.dto.responses.DespesaResponseDTO;
import com.lava_jato.entities.model.caixa.Despesa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DespesaMapper {

    DespesaResponseDTO toResponseDTO(Despesa despesa);
}
