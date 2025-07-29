package com.lava_jato.entities.mapstructs;

import com.lava_jato.entities.dto.responses.PessoaJuridicaResponseDTO;
import com.lava_jato.entities.model.PessoaJuridica;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PessoaJuridicaMapper {

    PessoaJuridicaResponseDTO toResponseDTO(PessoaJuridica pessoaJuridica);
}
