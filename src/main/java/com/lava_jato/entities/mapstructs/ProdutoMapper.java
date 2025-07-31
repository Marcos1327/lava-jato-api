package com.lava_jato.entities.mapstructs;

import com.lava_jato.entities.dto.responses.ProdutoResponseDTO;
import com.lava_jato.entities.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProdutoMapper {

    ProdutoResponseDTO toResponseDTO(Produto produto);

}
