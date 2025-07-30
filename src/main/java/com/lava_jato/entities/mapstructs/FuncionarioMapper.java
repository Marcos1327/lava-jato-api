package com.lava_jato.entities.mapstructs;

import com.lava_jato.entities.dto.responses.FuncionarioResponseDTO;
import com.lava_jato.entities.model.Funcionario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FuncionarioMapper {

    FuncionarioResponseDTO toResponseDTO(Funcionario funcionario);
}
