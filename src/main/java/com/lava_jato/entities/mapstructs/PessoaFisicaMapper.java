package com.lava_jato.entities.mapstructs;

import com.lava_jato.entities.dto.PessoaFisicaDTO;
import com.lava_jato.entities.model.PessoaFisica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PessoaFisicaMapper {

    PessoaFisica dtoToEntity(PessoaFisicaDTO pessoaFisicaDTO);

    PessoaFisicaDTO entityToDto(PessoaFisica pessoaFisica);
}
